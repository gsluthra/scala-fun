object SomeWinner {

  case class Player(name: String, score: Int)

  def winner (p1: Player, p2: Player): Player = {
    if(p1.score > p2.score) p1 else p2
  }

  def main(args: Array[String]) {
    val gp = Player("Gurpreet",45)
    val monish = Player("Monish",30)

    println("Winner is: "+ winner(gp, monish))


    val p1 = Player("Player1",45)
    val p2 = Player("Player2",450)
    val p3 = Player("Player3",415)
    val p4 = Player("Player4",345)
    val p5 = Player("Player5",360)


    val listOfPlayers = Seq(p1, p2, p3, p4, p5)
    println("Winner is: "+ listOfPlayers.reduceLeft(winner))

  }
}

