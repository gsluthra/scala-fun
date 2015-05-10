package fpbook

// Tail Recursive Implementation of Fibonacci
// Notice how the compiler optimization makes the tail recursive version run so much faster

object Fibonacci {

  def main(args: Array[String]) {
    println(s"The fibonacci number series: ")
    (1 to 35).map { ele =>
      print(s" ${fibonacci(ele)}")
    }
    println("")
    (1 to 35).map { ele =>
      print(s" ${fibonacciTR(ele)}")
    }
  }

  def fibonacci(number: BigInt): BigInt = {
    if(number > 1) fibonacci(number - 1) + fibonacci( number - 2)
    else number
  }

  def fibonacciTR(number: Int): BigInt = {

    @annotation.tailrec
    def fibTR(first: BigInt, second: BigInt, counter: BigInt, max: Int): BigInt = {
      if(counter< max)
        fibTR(second, first + second, counter + 1, max)
      else
        first + second
    }

    fibTR(0, 1, 2, number)
  }
}
