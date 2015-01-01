object BinarySearch {

  @annotation.tailrec
  def binarySearch(xs: Seq[Int], numToSearch: Int): Boolean = {
//    println(xs)
//    println(numToSearch)

    val middleIndex = xs.length / 2
    if(xs.length == 1 && xs(0)!=numToSearch) return false

    if (xs(middleIndex) == numToSearch) true
    else if(numToSearch < xs(middleIndex)) binarySearch(xs.slice(0, middleIndex), numToSearch)
    else binarySearch(xs.slice(middleIndex, xs.length), numToSearch)
  }

  //Polymorphic Binary Search for Generic Type T
  @annotation.tailrec
  def binarySearchForAny[T](xs: Seq[T], numToSearch: T, lesser: (T, T) => Boolean): Boolean = {
    val middleIndex = xs.length / 2
    if(xs.length == 1 && xs(0)!=numToSearch) return false

    if (xs(middleIndex) == numToSearch) true
    else if(lesser(numToSearch,xs(middleIndex))) binarySearchForAny(xs.slice(0, middleIndex), numToSearch, lesser)
    else binarySearchForAny(xs.slice(middleIndex, xs.length), numToSearch, lesser)
  }

  def main(args: Array[String]) {
    val xs = Seq(20, 30, 40, 12, 3, 460, 28, 1, 200)
    println(binarySearch(xs.sorted, 12))
    println(binarySearch(xs.sorted, 20))
    println(binarySearch(xs.sorted, 460))
    println(binarySearch(xs.sorted, 461))
    println(binarySearch(xs.sorted, 1))
    println(binarySearchForAny(xs.sorted, 20, (a:Int, b:Int) => (a < b)))

    val as = Seq('a', 'b', 'c', 'd', 'e')
    println(binarySearchForAny(as.sorted, 'd', (a:Char, b:Char) => (a < b)))
    println(binarySearchForAny(as.sorted, 'x', (a:Char, b:Char) => (a < b)))
  }

}
