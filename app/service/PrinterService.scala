package service

trait PrinterServiceComponent {
  val printerService: PrinterService
}

trait PrinterService {
  def print(who: String) = s"Hello, $who"
}
