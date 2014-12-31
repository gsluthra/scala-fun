package generics

object VarianceGenerics {

  abstract class Human {
    val name: String
  }

  case class Female(override val name: String, val age: Int) extends Human

  case class Male(override val name: String, val age: Int) extends Human

  def printNameLength(human: Human) = {
    println(human.name.length)
  }

  def printMiddleElement[Human](people: Seq[Human]): Unit ={
    println(people(people.size/2))
  }

  def main(args: Array[String]) {
    printNameLength(Female("Neha", 30))
    printNameLength(Male("GP", 30))
    val humans = Seq(Female("Female1", 20), Female("Female2", 22), Female("Female3", 24), Male("Male1", 30), Male("Male2", 35))
    val females = Seq(Female("Female1", 20), Female("Female2", 22), Female("Female3", 24))
    printMiddleElement(humans)
    printMiddleElement(females)

  }

  class MySeq[T]{
    var init = 0
    def add(element: T) = {init = init + 1}
    def remove(index: Int) = {init = init - 1}
    def size: Int = init

//    val f = new MySeq[Female]
//    val xs:MySeq[Human] = f
  }

}
