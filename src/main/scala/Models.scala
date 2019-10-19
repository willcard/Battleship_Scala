class Boat(val size:Int) {

  println("\n|-> Boat of size " +size+ ", horizontal (1) or vertical (2) ? \n" )

  var orientation : String = new String()
  if (scala.io.StdIn.readInt() == 1) { orientation = "horizontal" }
  else { orientation = "vertical" }

  def show: String = { "< " + "• "*size + ">  is " +orientation+ " in (A3-B7)" }

}


class Player(var indice:Int) {
  println("-> Player " +indice+ ", what's your name ?\n>")
  val name = scala.io.StdIn.readLine()

  val boat_3 = new Boat(3)
  val boat_6 = new Boat(6)

  def call(): Unit = {
    println("+ Player "+  indice + " (" +name+ ")")
  }

  def showBoats: Unit = {
    println("\n")
    call()
    println("|--  " + boat_3.show)
    println("|--  " + boat_6.show)
  }

}





object Game {
  def main(args: Array[String]): Unit = {

    println("\n\n-------- New Game --------")

    var player1 = new Player(1)
    player1.showBoats

    var player2 = new Player(2)
    player2.showBoats

    println("--------------------------\n")
  }
}
