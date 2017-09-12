package com.webcrawler.slick

import com.webcrawler.dao.MailSaveDAO

object WebCrawler3 extends App {

  import scala.io.Source

  val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";

  val sourceAsString = Source.fromURL(urlArchives).mkString

  val mailContent = sourceAsString.split("[\\r\\n]+").toSeq.toList.filter(x => x.contains("mbox/date"));

  val listOfMailBox = mailContent.flatMap(_.split("&middot;").
    filter(_.contains("mbox/date"))).
    map { x =>
      x.replace("/date\">Date</a>", "").
        replace("<a href=\"", "").trim()
    }

  val dao = new MailSaveDAO

  val allMails = dao.getMails(listOfMailBox)
//  
//  println("size "+allMails.size)
//  
//  allMails.take(10).foreach { x => println(x) }

  dao.save(allMails);
  
}