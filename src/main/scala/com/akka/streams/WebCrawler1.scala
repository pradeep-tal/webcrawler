package com.akka.streams

import akka.stream.scaladsl.Source
import java.io.PrintWriter
import java.io.File

object WebCrawler1 extends App {

  import scala.io.Source

  val urlget = Source.fromURL("http://mail-archives.apache.org/mod_mbox/maven-users/").mkString

  val filterIn = urlget.split("[\\r\\n]+").toSeq.toList.filter { x => x.contains("mbox") }

  filterIn.foreach { println }

  //  
  //  val writer = new PrintWriter(new File("201708.box"))
  //
  //  writer.write(filterIn.mkString)
  //
  //  writer.close()

}