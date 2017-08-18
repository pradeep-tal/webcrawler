package com.akka

import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Actor

object getstartedActorDemo extends App {

  val system = ActorSystem("HelloActor")

  val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")

  helloActor ! "hello"

  helloActor ! "Adieu"

  system.terminate()
}

class HelloActor extends Actor {

  def receive() = {

    case "hello" => println("Received Hello Message")
    case _ => println("Other than Message")

  }

}