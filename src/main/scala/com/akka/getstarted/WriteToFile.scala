package com.akka.getstarted

import akka.stream._
import akka.stream.scaladsl._
import akka.NotUsed
import akka.actor.ActorSystem
import akka.util.ByteString
import java.nio.file.Paths
import akka.compat.Future
import scala.concurrent.Future

object WriteToFile extends App {

  val source: Source[Int, NotUsed] = Source(1 to 100)

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val result: Future[IOResult] = factorials.map { x => ByteString(s"$x\n") }
    .runWith(FileIO.toPath(Paths.get("fact.txt")))
    
    

}