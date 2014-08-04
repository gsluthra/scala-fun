/**
 * FROM BLOG:
 * ==========
 * http://danielwestheide.com/blog/2013/02/06/the-neophytes-guide-to-scala-part-12-type-classes.html
 */
object Math{

  //Trait on Type T
  trait NumberType[T] {
    def plus(x: T, y: T): T
    def divide(x: T, y:Int): T
  }

  //Companion Object with implicit singletons
  //Written by Library writer as default for double
  object NumberType {
    //Can also write a val instead of object
    implicit object NumberTypeDouble extends NumberType[Double]{
      override def plus(x: Double, y: Double): Double = x + y
      override def divide(x: Double, y: Int): Double = x / y
    }
  }

}


// LIBRARY CODE
// Written on T, but expecting an implicit (NumberType) where the Type enforces the behavior
// The Type needs to have some methods defined

object Statistics3{

  import Math.NumberType

  def mean[T](xs: Seq[T]) (implicit ev: NumberType[T]) : T = {
    val sum: T = xs.reduce(ev.plus(_, _))
    ev.divide(sum, xs.size)
  }

  def median[T](xs: Seq[T]): T = {
    xs(xs.size/2)
  }
}


//CLIENT CODE
object MyMainApp{

  import Math.NumberType

  implicit object NumberLikeString extends NumberType[String]{
    override def plus(x: String, y: String): String = {
      x + y
    }

    override def divide(x: String, y: Int): String = {
      x.substring(0, x.length/y)
    }
  }


  def main(args: Array[String]) {

    val ss = Seq("abc", "def", "something else", "andmore")
    println("MEAN    : "+ Statistics3.mean(ss));

    val xs = Seq(12, 13, 14.5, 17, 19)
    println("MEAN    : "+ Statistics3.mean(xs));
    println("MEDIAN  : "+ Statistics3.median(xs));
  }
}



