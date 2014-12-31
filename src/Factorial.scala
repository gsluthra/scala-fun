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

    go(number, 1)
  }

}