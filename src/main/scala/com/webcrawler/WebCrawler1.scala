package com.webcrawler

object WebCrawler1 extends App {

  import scala.io.Source

  val urlArchives = "ht
import com.webcrawler.WriteToSourcetp://mail-archives.apache.org/mod_mbox/maven-users/";

  val opSource = args(0)

  println("OP " + opSource)

  val urlget = Source.fromURL(urlArchives).mkString

  val filterIn = urlget.split("[\\r\\n]+").toSeq.toList.filter(x => x.contains("mbox/date"));

  val listOfMailBox = filterIn.flatMap(_.split("&middot;").
    filter(_.contains("mbox/date"))).
    map { x =>
      x.replace("/date\">Date</a>", "").
        replace("<a href=\"", "").trim()
    }
  
  WriteToSource(opSource).write(listOfMailBox);

}