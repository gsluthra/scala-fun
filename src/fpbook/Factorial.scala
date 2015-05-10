package fpbook

object Factorial{

  def main(args: Array[String]) {
    (1 to 100).map { elem =>
      println(s"Factorial of ${elem} = ${factorial(elem)}")
      println(s"Factorial of ${elem} = ${factorial_with_tailRec_inner_function(elem)}")
    }
  }

//  @annotation.tailrec
  //Not tail recursive
  def factorial(number: BigInt) : BigInt = {
    if(number == 0) 1 else number * factorial(number - 1)
  }

  def factorial_with_tailRec_inner_function(number: BigInt): BigInt = {
    @annotation.tailrec
    def go(number: BigInt, acc: BigInt): BigInt = {
      if(number == 0) acc else go(number - 1, number*acc)
    }

    /*
    Note that here the accumulator is being passed to the function, so current method
    does not need to remember any data, and can be optimized away
     */
    go(number, 1)
  }

}