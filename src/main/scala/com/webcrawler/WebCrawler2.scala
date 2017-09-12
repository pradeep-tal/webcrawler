package com.webcrawler

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

object WebCrawler2 extends App {

  import scala.io.Source

  val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";

  val urlget = Source.fromURL(urlArchives).mkString

  val filterIn = urlget.split("[\\r\\n]+").toSeq.toList.filter(x => x.contains("mbox/date"));

  val gethref = filterIn.flatMap(x => x.split("&middot;").
    filter(x => x.contains("mbox/date"))).
    map { x =>
      x.replace("/date\">Date</a>", "").
        replace("<a href=\"", "").trim()
    }

  println(gethref)
  
  
  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()


}