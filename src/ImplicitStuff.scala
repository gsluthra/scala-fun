
//The "package" object is usually a good place to keep implicits for it being easy to find by developers


object ImplicitStuff {

  object ImplicitStuff1{

      implicit def someMethod: Int = {
        println("called this method")
        20 + 5 + 13
      }

      def multiplyBy2(implicit x: Int) = x*2

      def test(): Unit ={
        println("1::::::::" + multiplyBy2)
      }

  }

  object ImplicitStuff2{

      implicit val xx = 53

      def multiplyBy2(implicit x: Int) = x*2

      def test(): Unit ={
        println("2::::::::" + multiplyBy2)
      }

  }


  object ImplicitStuff3{

    case class MyPerson(name: String, age: Int){

      //Setting default param value if no implicit is found
      def yearsRemainingToLive(implicit maxAge: Int = 500): Int = {
        maxAge - age
      }
      def ageDifference(implicit other: MyPerson): Int = {
        other.age - age
      }
    }

    object MyPerson {
      val name = "Hello"
      val age = 120
      implicit val defaultYearsMax: Int = 100
      implicit val defaultPerson: MyPerson = new MyPerson("someone", 200)
      implicit def stringToInt(s: String): Int = s.length*30 //Also called an implicit View (Compiler will search for type conversion, or for method searching)
    }



    def test(): Unit ={
      //Import needed, because compiler will search for conversion to int in STRING's companion object, and not MyPersons companion object (it searches in SOURCE types companion)
      import ImplicitStuff3.MyPerson.stringToInt


      println("3::::::::")

      val mohan = new MyPerson("mohan", 20)
      println("mohans remaining years: "+ mohan.yearsRemainingToLive("22"))
      println("mohans remaining years: "+ mohan.yearsRemainingToLive)
      println("Age Difference: "+ mohan.ageDifference )

    }

  }

  object ImplicitStuff4{

    trait Order[T] {
      def sort(someObject: T): T
    }

    trait Boo{}

    case class SomeBoo(name: String)

    trait Foo{}

    object Foo {
      implicit lazy val someOrdering  = new Order[Foo] {
        override def sort(someObject: Foo): Foo = someObject
      }
    }

    def aMethod[T](param1: T)(implicit evidence: Order[T]): Unit ={
      println("Called method with param1: "+ evidence.sort(param1))
    }

    def test() = {
      println(aMethod(new Foo {}))
//      println(aMethod(new Boo {}))
//      println(aMethod(new SomeBoo("GP")))
    }
  }

  object ImplicitStuff5{

    // A => B
    implicit def toTapper[T](obj: T) = new Tapper(obj) //Optionally, you can comment this line, and make the class Tapper implicit

    class Tapper[S](val obj: S){
      def tap(message: String): S ={
        println(s"${message}::: ${obj}")
        obj
      }
    }

    def test() = {
      val sss = "Hello"
      sss.tap("Some tap").length()
//      new Person("GP", 34).tap("Person tapped")
    }
  }


  def main(args: Array[String]) {

      ImplicitStuff1.test()
      ImplicitStuff2.test()
      ImplicitStuff3.test()
      ImplicitStuff4.test()
      ImplicitStuff5.test()
  }

}


