package generics

object Random {

  trait Comparator[T]{
    def compare(a1: T, a2: T): (T, T)
  }

  /**
   * Returns a random element from the sequence
   */
  def randomMethod[T](elements: Seq[T]): T = {
    elements((Math.random() * elements.size).toInt)
  }

  def sortMethod[T](elements: Seq[T]) (implicit comparator: Comparator[T]): Seq[T] = {
    //Some sorting algorithm, which wishes to compare and sort
    val left = elements(0)
    val right = elements(1)
    val (bigger, smaller) = comparator.compare(left, right)
    return elements
  }

  def main(args: Array[String]) {
    val seq = Seq[String]("1","2","3","444","gur","pur","dummy","333")
    println(randomMethod(seq))

    val seqInt = Seq[Int](1,2,3,4,5,6,7,8,9,10)
    println(randomMethod(seqInt))

    val seqSomeNumber = Seq(1,2,3,4,5,6,7,8,9,10)
    println(randomMethod(seqSomeNumber))

    val seqSomeMixedStuff = Seq(1,2,3,4,5,"XXXX","YYYY","ZZZ",9,10)
    println(randomMethod(seqSomeMixedStuff))

//    sortMethod(seqSomeMixedStuff) //Won't compile unless we have a comparator written for it
  }
}
