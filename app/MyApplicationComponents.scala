import actors.{HelloActor, HelloActorComponent, NameActor, NameActorComponent}
import akka.actor.{ActorRef, Props}
import controllers.{ApplicationController, OtherApplicationController}
import play.api.ApplicationLoader.Context
import play.api.BuiltInComponentsFromContext
import play.api.routing.Router
import service.{PrinterService, PrinterServiceComponent}

//http://loicdescotte.github.io/posts/play24-compile-time-di/
class MyApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with HelloActorComponent
  with NameActorComponent
  with PrinterServiceComponent {

  import scala.concurrent.ExecutionContext.Implicits.global

  override val printerService: PrinterService = new PrinterService {}
  override val helloActor: ActorRef = actorSystem.actorOf(Props(classOf[HelloActor], printerService), "HelloActor")
  override val nameActor: ActorRef = actorSystem.actorOf(Props(classOf[NameActor], helloActor), "NameActor")

  val applicationController = new ApplicationController(helloActor)
  val otherApplicationController = new OtherApplicationController(printerService)

  override def router: Router = new Routes(httpErrorHandler, applicationController, otherApplicationController)
}
