
trait LoggerTrait {
  var maxLength: Int = 3
  def logString(s: String) = {
    if(s.length> maxLength)
      println(s.substring(0,maxLength))
    else
      println(s)
  }

}

trait TwentyCharLogger extends LoggerTrait {
  maxLength = 20
  override def logString(s: String) = {
    super.logString("**********"+ s)
  }
}

object MyDummyClass extends TwentyCharLogger{
  def main(args: Array[String]){
    logString("Hello what the hell is going on. Tell me.")
    logString("Gurpreet")
  }
}