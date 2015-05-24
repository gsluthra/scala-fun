package fpbook

object PolymorphicFunctions {

  
  //A, f(A,B) => C      =>    f(B) => C (a pre-applied)
  def partial[A, B, C](a: A, f: (A, B) => C): (B => C) = {
    (b) => f(a, b)
  }

  //Given f(A,B) => C, you can get a f(A) that returns f(B)=>C
  def curry[A, B, C](f: (A,B) =>  C): A => (B => C) = {
    a =>  b => f(a,b)
  }

  //Given f(A) => f(B) => C, you can get a f(A,B) => C
  def uncurry[A, B, C](f: A => (B => C)): (A,B) => C = {
    (a,b) => f(a)(b)
  }

  // Given f(A) => B, and g(B) => C, you can get a f(A) => C
  def compose[A,B,C](f: A => B, g: B => C): A => C = {
    (a) => g(f(a))
  }


  def exampleOfComposingFunctions: Unit ={
    val squareF = (n: Int) => (n * n)
    val divideF = (n: Int) => (n / 10)

    //Using andThen
    val squareDividerF = squareF andThen divideF
    println(squareDividerF(20))

    //Using our compose function
    val squareDividerFCompose = compose (squareF, divideF)
    println(squareDividerFCompose(20))

    //Normal way
    println(divideF(squareF(20)))
  }

  def main(args: Array[String]) {
    exampleOfComposingFunctions
  }
  
}
