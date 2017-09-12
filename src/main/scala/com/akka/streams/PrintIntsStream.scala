package com.akka.streams

import akka.stream._
import akka.stream.scaladsl._
import akka.NotUsed
import akka.stream.ActorMaterializer
import scala.concurrent._
import akka.Done
import akka.actor._

object PrintIntsStream extends App {

  val source: Source[Int, NotUsed] = Source(1 to 100)

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()
  implicit val ec=system.dispatcher
  
  val done: Future[Done] = source.runForeach { x => println(x) }

  done.onComplete { x => system.terminate() }
  
}