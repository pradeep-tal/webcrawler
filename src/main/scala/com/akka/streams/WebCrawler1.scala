package com.akka.streams

import akka.stream.scaladsl.Source
import java.io.PrintWriter
import java.io.File

object WebCrawler1 extends App {

  import scala.io.Source

  val urlArchives = "http://mail-archives.apache.org/mod_mbox/maven-users/";

  val opSource = args(0)

  println("OP " + opSource)

  val urlget = Source.fromURL(urlArchives).mkString

  val filterIn = urlget.split("[\\r\\n]+").toSeq.toList.filter(x => x.contains("mbox/date"));

  val gethref = filterIn.flatMap(x => x.split("&middot;").
    filter(x => x.contains("mbox/date"))).
    map { x =>
      x.replace("/date\">Date</a>", "").
        replace("<a href=\"", "").trim()
    }
  
  WriteToSource(opSource).write(gethref);

}