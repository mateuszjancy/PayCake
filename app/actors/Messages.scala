package actors

object Messages {

  case object SayHello

  case class SetName(name: String)

  case object ChangeName

}
