
trait MyTrait {
  def someMethod: String = {
    "someMethodCall" + 1
  }
}


case class MyClass1(val a: Int) extends MyTrait
case class MyClass2(val a: String) extends MyTrait

case class Box[+T <: MyTrait](val a: T) {

  def pack = {
    a.someMethod + " ::: BOX :: "
  }
}

object SomeObject{

  def main(args: Array[String]) {
    val box1: Box[MyTrait] = new Box[MyClass1](new MyClass1(10))
  }
}

object MyObject{


  // Computational abstractions
  // a -> f a                          -- Pointed
  // f a -> (a -> b) -> f b            -- Functor
  // f a -> f (a -> b) -> f b          -- Applicative
  // f a -> (a -> f b) -> f b          -- Monad

  // f a -> a                          -- Copointed
  // f a -> (b -> a) -> f b            -- Contrafunctor / Cofunctor

  // data Option a = Some a
  //               | None

  // a          -> n                   n * n = n2

  // Option a   -> n + 1               (n + 1) * (n + 1) = n2 + 2n + 1

  // Or a b = Inl a
  //        | Inr b

  // algebras
  // types
  // functions

  // data Result = Result (Option String) (Option Int)             -- incorrect

  sealed trait Or[+A, +B]
  case class Inl[+A](x: A) extends Or[A, Nothing]
  case class Inr[+B](x: B) extends Or[Nothing, B]

  def foo(x: Int): Or[Boolean, Int] = {
    if (x == 2)
      Inl(true)
    else
      Inr(90)
  }


  // data FooBar = Foo Bool Bool
  //             | Bar Bool
  //             | Baz




  trait PrettyPrinter[A] { self =>
    def pprint(a: A): String

    def comap[B](f: B => A): PrettyPrinter[B] = new PrettyPrinter[B] {
      def pprint(b: B) = self.pprint(f(b))
    }
  }

  case class Cat(name: String)

  val stringPP = new PrettyPrinter[String] {
    def pprint(s: String) = s
  }

  val catPP: PrettyPrinter[Cat] = stringPP.comap(_.name)

  // PP String -> (Cat -> String) -> PP Cat

  // (a -> b) -> (b -> c) -> (a -> c)          -- vanilla
  // (Monad f) => (a -> f b) -> (b -> f c) -> (a -> f c)          -- kleisli

  // age :: Person -> Int
  // compare `on` age

  def boo[A](o: Option[A]): A = ???

  def fmapL[A, B](f: A => B): List[A] => List[B] = {
    (x: List[A]) => x.map(f)
  }

  def kleisliO[A, B, C](f: A => Option[B], g: B => Option[C]): (A => Option[C]) = {
    (a: A) => f(a) match {
      case Some(b) => g(b)
      case None => None
    }
  }

}