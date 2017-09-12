package com.akka.actors

import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.AbstractActor.Receive
import akka.actor.Props
import scala.io.StdIn

object ActorHierarchyExperiments extends App {

  val system = ActorSystem()

  val firstRef = system.actorOf(Props[PrintMyActorRefActor], "first-Actor")

  println(s"First actor:$firstRef")

  firstRef ! "printit"

  println(">>> Press ENTER to exit <<<")
  try StdIn.readLine()
  finally system.terminate()

}

class PrintMyActorRefActor extends Actor {

  override def receive: Receive = {

    case _:String =>

      val secondRef = context.actorOf(Props.empty, "second-actor")
      println(s"Second :: $secondRef")

  }

}