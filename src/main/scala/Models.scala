/**

class Boat(val size:Int) {
  println("\n  |-> Boat of size " + size)

  println("    |--- FROM ?" )
  val _from : String = scala.io.StdIn.readLine()

  println("    |--- TO ?" )
  var _to : String = scala.io.StdIn.readLine()

  val show: String = "< " + "• "*size + ">  (" + _from + "-" + _to + ")"
}



class Player(var indice:Int) {
  println("--> Player " +indice+ ", what's your name ?")
  val name = scala.io.StdIn.readLine()

  val boat_3 = new Boat(3)
  val boat_6 = new Boat(6)

  val call: String = "+ Player "+  indice + " (" +name+ ")"

  val showBoats: String = "\n"+ call+ "\n|  "+ boat_3.show+ "\n|  "+ boat_6.show+ "\n"+ "------"*6+ "\n"
}




  //("A","B","C","D", ..., "J")
    //("1","2","3","4", ..., "10")

class Board {
  def main(args: Array[String]): Unit = {
    var lines = Array.ofDim[Int](10,10)
    var board = BoardArray(Map(("A",False),("B",False)),Map(("A",False),("B",False)))
  }
}

object Game {
  def main(args: Array[String]): Unit = {

    var player1 = new Player(1)
    println(player1.showBoats)

    var player2 = new Player(2)
    println(player2.showBoats)

    var board = new Board()
  }
}
**/
