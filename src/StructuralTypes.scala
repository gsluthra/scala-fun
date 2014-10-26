
object StructuralTypes {

  object Resources {
    type Resource = { //The contract is on structure.. which in this case is the close() method
       def close(): Unit
    }

    def closeResource(r: Resource) = r.close()
  }

  case class MyClass(name: String) {
    def close() = println("Close called in MyClass")
  }

  def test() = {
    println("Test code")
    Resources.closeResource(System.in)
    Resources.closeResource(MyClass("Gurpreet"))
  }

  def main(args: Array[String]) {
    test()
  }

}
