package com.akka.streams

import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec
import com.webcrawler.model.Mail
import java.nio.charset.MalformedInputException

object ETLMails extends App {

  //val sampleUrl="http://mail-archives.apache.org/mod_mbox/maven-users/201704.mbox/raw/%3CCAPCjjnGzugvWN4Gd4Vzn2i0guyzKHjDJuV1iEdaemoZRCkB-fQ%40mail.gmail.com%3E/";

  import scala.util.matching.Regex

  def extractValues(indMail: String, fileName: String): Option[Mail] = {

    val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";
    val keyValMails: Regex = "(From|Date|Message-ID|Subject): ([0-9a-zA-Z-#()<>\"\"@.,:$-+=_? ]+)".r

    val m = new Mail

    for (patternMatch <- keyValMails.findAllMatchIn(indMail)) {

      val key = patternMatch.group(1);

      val value = patternMatch.group(2);

      //println(s"Key=${key} AND value=${value}")

      key.trim().toLowerCase() match {
        case "from" => m.from = value
        case "date" => m.date = value
        case "message-id" => m.messageId = value
        case "subject" => m.subject = value
        case x => println("Not valid " + x);
      }
    }

    if (m.messageId != "") {

      try {

        val url = urlArchives + fileName + "/raw/" + m.messageId + "/";

        println(url);

        val body = Source.fromURL(url).mkString

        m.messageBody = body

      } catch {
        case e: MalformedInputException => println(e.getMessage); None
      }

      Some(m)

    }else
    {
      None
    }

  }

  //  

  val list = ListBuffer.empty[String]

  def getMailList(mbox1: String) = {

    @tailrec
    def iter(mailbody: String, startPos: Int): Seq[String] = {

      val pos = mailbody.indexOf("From users-return-");

      if (pos > 0) {
        list.append(mailbody.substring(startPos, pos))

        iter(mailbody.substring(pos + 1), pos);

      } else {
        list
      }

    }

    iter(mbox1, 0);

  }

}