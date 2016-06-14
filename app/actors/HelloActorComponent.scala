package actors

import actors.Messages.{SayHello, SetName}
import akka.actor.{Actor, ActorRef}
import service.{PrinterService, PrinterServiceComponent}

trait HelloActorComponent {
  this: PrinterServiceComponent =>

  val helloActor: ActorRef //= Props(classOf[HelloActor], printerService)

}

class HelloActor(printerService: PrinterService) extends Actor {

  var name: String = "Init"

  def receive = {
    case SayHello =>
      sender() ! printerService.print(name)
    case SetName(n) => name = n
  }
}
