package com.akka.streams

import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec
import com.webcrawler.model.Mail
import java.nio.charset.MalformedInputException
import java.net.ConnectException

object ETLMails extends App {

  //val sampleUrl="http://mail-archives.apache.org/mod_mbox/maven-users/201704.mbox/raw/%3CCAPCjjnGzugvWN4Gd4Vzn2i0guyzKHjDJuV1iEdaemoZRCkB-fQ%40mail.gmail.com%3E/";

  import scala.util.matching.Regex

  def extractValues(indMail: String, fileName: String): Option[Mail] = {

    val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";
    val keyValMails: Regex = "(From|Date|Message-ID|Subject): ([0-9a-zA-Z-#()<>\"\"@.,:$-+=_? ]+)".r

    var from, date, message_id, subject, messageBody = "";

    for (patternMatch <- keyValMails.findAllMatchIn(indMail)) {

      val key = patternMatch.group(1);

      val value = patternMatch.group(2);

      //println(s"Key=${key} AND value=${value}")

      key.trim().toLowerCase() match {
        case "from" => from = value
        case "date" => date = value
        case "message-id" => message_id = value
        case "subject" => subject = value
        case x => println("Not valid " + x);
      }
    }

    if (message_id != "") {

      try {

        val url = urlArchives + fileName + "/raw/" + message_id + "/";

        //println(url);

        val body = Source.fromURL(url).mkString

        messageBody = body

      } catch {
        case e: MalformedInputException => println(e.getMessage); None
        case e:ConnectException=>println(e.getMessage);None
      }

      Some(new Mail(message_id, from, subject, date, messageBody))

    } else {
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