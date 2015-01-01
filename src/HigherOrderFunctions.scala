object HigherOrderFunctions {

  def main(args: Array[String]) {
    val incrFunc = {x: Int => x + 1}
    println(incrFunc(2))

    def someFunc(f: Int => Int, number: Int): Unit ={
      println(f(number))
    }

    someFunc(incrFunc, 20)
    someFunc(_+10, 20)
    someFunc(x => x+2, 30)

    // Using Function1 object to create a function (last param is the return type - in this case Boolean)
    val funcObj = new Function1[Int, Boolean] {
      override def apply(v1: Int): Boolean = if(v1>10) true else false
    }

    //Much better to read, since its clear its a function that goes from Int to Boolean
    val funcObjX = new ((Int) => Boolean) {
      override def apply(v1: Int): Boolean = if(v1>10) true else false
    }

    println(funcObj(20))
    println(funcObjX(9))

  }

}
