package controllers

import play.api.mvc.{Action, Controller}
import service.PrinterService

class OtherApplicationController(printerService: PrinterService) extends Controller {
  def b = Action {
    Ok(printerService.print("OtherApplicationController"))
  }
}
