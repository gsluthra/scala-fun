trait NumberInterface[A]{
  def get: A
  def plus(other: NumberInterface[A]): NumberInterface[A]
  def divide(y: Int): NumberInterface[A]
}

//Define a statistics library, that expects folks to pass a NumberInterface[A] element
// Here the library writer only writes code on one type (NumberInterface)
// and callers implement the trait (wrap their objects in an adaptor trait)

object Statistics2 {
  def mean[A](xs: Seq[NumberInterface[A]]) = {
    xs.reduce(_.plus(_)).divide(xs.size)
  }
  def median[A](xs: Seq[NumberInterface[A]]) = {
    xs(xs.size/2)
  }
}


//---------------------------------------CLIENT------------------------------
// Write an implementation for the trait (as a user of the library)
// This one will wrap the double. Client has to write their own trait implementation
// for each datatype on which they want to call the statistics library
// Workload shifted to CLIENT (instead of LIBRARY Writer)
// Plus -- allows library writer to not be aware of the client types

case class MyDouble(x: Double) extends NumberInterface[Double]{
  override def get: Double = x

  override def divide(y: Int) = {
    MyDouble(x / y)
  }

  override def plus(other: NumberInterface[Double]) = {
    MyDouble(x + other.get)
  }
}


// MAIN USER
object MyStat2App {
  def main(args: Array[String]) = {
    val xs = Seq(MyDouble(12), MyDouble(13), MyDouble(14.1))
    println("MEAN  : "+ Statistics2.mean(xs));
    println("MEDIAN: "+ Statistics2.median(xs));
  }
}

