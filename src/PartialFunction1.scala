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

  def main(args: Array[String]) {
    def increment(a:Int, b: String) = {
      a + b
    }

    val f2 = partial1_again(20, increment)
    println(f2("Hello"))
    println(f2("Wssup"))
  }


}
