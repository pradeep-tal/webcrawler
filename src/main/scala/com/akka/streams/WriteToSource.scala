package com.akka.streams

import java.io.PrintWriter
import java.io.File
import scala.io.Source
import scala.collection.mutable.ListBuffer
import com.webcrawler.model.Mail
import scalikejdbc.config.DBsWithEnv
import scalikejdbc.DB

import scalikejdbc.config._
import scalikejdbc._
import java.nio.charset.MalformedInputException

trait WriteToSource {

  def write(files: List[String])

}

object WriteToSource {

  val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";

  private class FileSource extends WriteToSource {

    override def write(files: List[String]) = {

      for (fileName <- files if fileName.startsWith("2017")) {

        val writer = new PrintWriter(new File(fileName))
        try {
          val mailContent = Source.fromURL(urlArchives + fileName);
          writer.write(mailContent.mkString)

        } catch {
          case ex: Throwable =>
            println(ex.getMessage)
        } finally {
          if (writer != null)
            writer.close()
        }

      }

    }
  }

  private class DBSource extends WriteToSource {

    override def write(files: List[String]) = {

      print("DB source ")

      val l = ListBuffer.empty[Mail]

      for (fileName <- files if fileName.startsWith("201705")) {

        try {
          val mbox = Source.fromURL(urlArchives + fileName).mkString

          val indMails = mbox.split("From users-return-");

          for (mail <- indMails) {

            val extractedData = ETLMails.extractValues(mail, fileName);
            
            println(extractedData)

            if (extractedData != None)
              l += extractedData.get
              
          }
        } catch {
          case e: MalformedInputException => print(e.getMessage)
        }

      }

      println(" Totals mails " + l.length)

      println("storing in table ");

      DBsWithEnv("prod").setup();

      DB localTx { implicit session =>

        val batchParams: Seq[Seq[Any]] = l.toSeq.map(i => Seq(i.messageId, i.from, i.subject, i.date, i.messageBody))

        sql"insert into mails (messageid, from_,subject,date,messagebody) values (?,?,?,?,?)".batch(batchParams: _*).apply()
      }

    }

    DBsWithEnv("prod").closeAll()
  }

  def apply(stype: String) = {

    if ("database".equals(stype))
      new DBSource
    else
      new FileSource

  }

}