// LIBRARY writer will need to write a mean and median for every possible
// type (Double, Int, etc) for someone to be able to use his/her library

object Statistics {

  def mean(xs: Seq[Double]) = {
    xs.sum / xs.size
  }

  def median(xs: Seq[Double]) = {
    xs(xs.size/2)
  }
}

//------- Client ----------------------------------------------------
object MyStatsApp {

  def main(args: Array[String]) = {
    val xs: Seq[Double] = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    computeUsingSeqDouble(xs)
  }

  def computeUsingSeqDouble(xs: Seq[Double]) = {
    println("mean  : " + Statistics.mean(xs))
    println("median: " + Statistics.median(xs))
  }

}
