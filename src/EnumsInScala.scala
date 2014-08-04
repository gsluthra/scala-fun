import scala.util.Random

object TrafficLightColor extends Enumeration{
  val RED, GREEN, YELLOW = Value
}

object MyApp {
  import TrafficLightColor._

  private def chooseTrafficColor() = {
    Random.shuffle(Seq(RED,GREEN,YELLOW).toList).head
  }

  private def checkTrafficLight {
    val color = chooseTrafficColor()
    if (color == GREEN) println("GO")
    else if (color == RED) println("STOP!")
    else if (color == YELLOW) println("SLOW")
  }

  def main(args: Array[String]) {
    println("Hello World")

    for(x <- 1 to 10){
      checkTrafficLight
    }
  }

}
