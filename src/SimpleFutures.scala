import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Random}

object SimpleFutures {

  val seconds = 1000;

  def simpleFutureStuff(){
    def someFutureMethod: Future[Int] = future {
      Thread.sleep(6 * seconds)
      Random.nextInt(100)
    }

    val x = 9
    x + 2

    // let: A, (A => B) => B
    // bind: F[A], (A => F[B]) => F[B] (Monad)

    val someF = someFutureMethod

    //Registering one callback
    someF.onComplete{
      case Success(i) => Thread.sleep(2*seconds); println("1 Recevied Random Number: "+ i)
      case Failure(_) => println("1 Failed!!")
    }

    //Registring another callback
    someF.onComplete{
      case Success(i) => Thread.sleep(2*seconds); println("2 Second callback says I got: "+ i)
      case Failure(_) => println("2 Failed!!")
    }


  }

  def mapBasedFuture(){

    def someFutureMethod = future {
      Thread.sleep(6 * seconds)
      "$$"+Random.nextInt(100)
    }

    //Chaining possible here..
    val someF = someFutureMethod
      .map { result =>
        Thread.sleep(3 * seconds)
        println("Got random number: " + result)
        result + " || Added this || "
      }.map{ result =>
        Thread.sleep(3 * seconds)
        println("In Second Callback: "+ result)
        result + " || Cool!!  || "
    }

    //Registering onComplete callback
    someF.onComplete{
      case Success(s) => println("Succeeded with result:  " + s)
    }

    //Waiting for future to complete
    while(!someF.isCompleted){
      print(".")
      Thread.sleep(1*seconds)
    }
    //Future is complete, print its value
    println("COMPLETE!!")
    println(someF.value.getOrElse("Got NOTHING!! :( "))
  }

  def main(args: Array[String]) {

    simpleFutureStuff()

    mapBasedFuture()

    //Wait for all output to print
    println("Waiting....")
    Thread.sleep(10 * seconds)
    println(">>>>>> Done! ")
  }
}
