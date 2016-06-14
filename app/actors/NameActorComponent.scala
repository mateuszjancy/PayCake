package actors

import actors.Messages.{ChangeName, SetName}
import akka.actor.{Actor, ActorRef}

import scala.concurrent.duration._
import scala.util.Random

trait NameActorComponent {
  this: HelloActorComponent =>

  val nameActor: ActorRef
}

class NameActor(helloActor: ActorRef) extends Actor {

  import scala.concurrent.ExecutionContext.Implicits.global

  context.system.scheduler.schedule(0 seconds, 5 seconds, self, ChangeName)

  def receive = {
    case ChangeName => helloActor ! SetName(Random.nextString(5))
  }
}
