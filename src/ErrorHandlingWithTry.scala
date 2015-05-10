import scala.util.{Failure, Success, Try}

object ErrorHandlingWithTry {

  def main(args: Array[String]) {
    val maybeInt = Try(1/0)
    maybeInt match {
      case Success(a) => println(a)
      case Failure(ex) => ex.printStackTrace()
    }

//    val anotherMaybeInt = maybeInt recover {
//      case ArithmeticException => 0
//    }
//
//    println(anotherMaybeInt)
  }

}
