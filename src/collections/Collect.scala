package collections

import scala.util.{Success, Failure, Try}

object Collect {

  def main(args: Array[String]) {
    //Domain objects
    case class Person(age: Int, name: String)
    val xs = List(3, 4, 5, Person(30, "Gurpreet"), Person(40, "Mushtaq"), 45)
    println(xs.collect {
      case Person(age, name) => Person(age + 1, name)
    })
    println("---------")

    //Trys
    val ts = List(Try(1/0), Try(2), Try(3/0), Try(45))
    println(ts)
    val zs = ts.filter(_.isSuccess).map(x=>x.get+1)
    println(zs)
    println(ts.collect {
      case Success(i) => i + 1
    })
    println("---------")

    //Options
    val oList = List(Some("Hello"), Option(null), None, Some("Another"), Some("GOOGLY"), None)
    println(oList.flatten)
    println(oList.map(o => o.map(_.length)))
    println(oList.flatMap(o => o.map(_.length)))
    println(oList.collect {
      case Some(x) => x.length
    })


  }

}
