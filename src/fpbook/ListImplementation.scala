package fpbook

sealed trait MyList[+A]

case object Nil extends MyList[Nothing]

case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

//Companion Object
object MyList {

  def sum(xs: MyList[Int]): Int = {
    xs match {
      case Nil           => 0
      case Cons(h, tail) => h + sum(tail)
    }
  }

  //Note how apply is created using varagrs, and splat operator on seq
  def apply[A](args: A*): MyList[A] = {
    if (args.isEmpty) Nil
    else Cons(args.head, apply(args.tail: _*))
  }

  def tail[A](xs: MyList[A]): MyList[A] = {
    xs match {
      case Nil              => Nil
      case Cons(head, tail) => tail
    }
  }

  def drop[A](xs: MyList[A], n: Int): MyList[A] = {
    xs match {
      case Nil                          => Nil
      case Cons(head, tail) if (n == 1) => tail
      case Cons(head, tail)             => drop(tail, n - 1)
    }
  }

  def dropWhile[A](xs: MyList[A], f: (A => Boolean)): MyList[A] = {
    xs match {
      case Cons(head, tail) if f(head) => dropWhile(tail, f)
      case _                           => xs
    }
  }


  def setHead[A](xs: MyList[A], newHead: A): MyList[A] = {
    xs match {
      case Nil              => Nil
      case Cons(head, tail) => Cons(newHead, tail)
    }
  }
}

object ListImplementation {
  def main(args: Array[String]) {
    val result = MyList.sum(MyList(2, 3, 4, 5))
    println(result)
    val myList: MyList[String] = MyList("a", "b", "c", "d")
    println(MyList.tail(myList))
    println(MyList.drop(myList, 1))
    println(MyList.tail(Nil))
    println(MyList.setHead(myList, "X"))
    println(MyList.drop(myList, 2))
    println(MyList.dropWhile[Integer](MyList[Integer](1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 8), _ < 7))
    println(MyList.dropWhile[Integer](MyList[Integer](1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 8), _ < 10))
  }
}
