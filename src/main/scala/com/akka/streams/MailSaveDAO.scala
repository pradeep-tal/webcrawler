package com.akka.streams

import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ListBuffer
import com.webcrawler.model.Mail
import scala.io.Source
import java.nio.charset.MalformedInputException

class MailSaveDAO  {

  val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";

  class Mails(tag: Tag) extends Table[(String, String, String, String, String)](tag, "mails") {

    def messageid = column[String]("messageid")
    def from = column[String]("from_")
    def subject = column[String]("subject")
    def date = column[String]("date")
    def messagebody = column[String]("messagebody")

    def * = (messageid, from, subject, date, messagebody)

  }

  def getMails(files: List[String]): List[Mail] = {

    println("GetMails MailSaveDAO ")

    val l = ListBuffer.empty[Mail]

    for (fileName <- files if fileName.startsWith("201410")) {

      try {
        val mbox = Source.fromURL(urlArchives + fileName).mkString

        val indMails = mbox.split("From users-return-");

        for (mail <- indMails) {

          val extractedData = ETLMails.extractValues(mail, fileName);

          //println(extractedData)

          if (extractedData != None)
            l += extractedData.get

        }
      } catch {
        case e: MalformedInputException => print(e.getMessage)
      }

    }

    l.toList

  }

  def save(listOfMails: List[Mail]) = {

    val mails = TableQuery[Mails];

    val db = Database.forConfig("mysqlcon")

    try {

      val setup = DBIO.seq(mails ++= listOfMails.map { x => (x.messageId, x.from, x.subject, x.date, x.messageBody) })

      val result = db.run(setup)

    } finally {
      db.close()
    }
  }

}