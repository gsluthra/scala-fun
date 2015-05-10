package fpbook



object MyClass {
  def main(args: Array[String]) {
    val (coffee, charge) = new Cafe().buyCoffee(CreditCard(1121))
    val (coffees, totalCharge) = new Cafe().buyMultipleCoffee(10,CreditCard(1121))
  }
}


class Cafe {
  def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
    val coffeeCup = Coffee("Cappuchino", 50)
    (coffeeCup, Charge(cc, coffeeCup.price))
  }

  def buyMultipleCoffee(numberOfCoffees: Int, cc: CreditCard) = {
    val coffeeAndChargeList = List.fill(numberOfCoffees)(buyCoffee(cc))
    val (listOfCoffees, listOfCharges) = coffeeAndChargeList.unzip
    val combinedCharge = listOfCharges.reduce { (c1, c2) =>
      c1.combine(c2)
    }
    (listOfCoffees, combinedCharge)
  }
}

case class CreditCard(number: Int)
case class Coffee(flavor: String, price: Double)

case class Charge(cc: CreditCard, amount: Double) {
    def combine(other: Charge): Charge ={
      if(other.cc == this.cc) {
        Charge(this.cc, this.amount + other.amount)
      } else {
        throw new Exception("Can't combine charges of different credit cards!")
      }
    }
}

