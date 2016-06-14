import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader}

class MyApplicationLoader extends ApplicationLoader {
  override def load(context: Context): Application = {
    new MyApplicationComponents(context).application
  }
}
