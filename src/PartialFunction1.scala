// More like Partially Applied Function
object PartialFunction1 {

  //(A, f(A,B)=> C) => (f(B) => C)
  def partial1[A,B,C](a: A, f: (A,B) => C): B => C = {
     val functionBtoC = new ((B) => C){
       override def apply(v1: B): C = f(a, v1)
     }
    functionBtoC
  }

  def partial1_again[A,B,C](a: A, f: (A,B) => C): B => C = {
    def functionBtoC(b: B):C = f(a, b)
    functionBtoC
  }

  //Because the RHS is basically supposed to be a function that goes from B -> C
  def partial1_alternative[A,B,C](a: A, f: (A,B) => C): B => C = {
    b:B => f(a, b)
  }

  def partial1_alternative2[A,B,C](a: A, f: (A,B) => C): (B => C) = f(a, _)

  def main(args: Array[String]) {
    def increment(a:Int, b: String) = {
      a + b
    }

    val f2 = partial1_again(20, increment)
    println(f2("Hello"))
    println(f2("Wssup"))
  }


}
