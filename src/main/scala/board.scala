package board

// List( List("X:Y"), List(state_of_the_point) )

class Board(boat_A: List[List[String]]) {

  /**
  def main(args: Array[String]): Unit = {
    val boat_A: List[Int] = List(1,2,3)
  }
  **/

  def get_boat_A: List[List[String]] = { return boat_A }
}

object Board{
  def isLoosed(board: Board): Boolean = {
    // for boat in board.boatsList: if not isDestroyed(boat) return false
    return false
  }

  def isDestroyed(boat: List[List[String]]): Boolean = {
    if (boat(1).contains("O")){
      return false
    }
    else return true
  }


  def prettyPrint(player: String): String = {

    val ANSI_BLUE_B = "\u001b[1;34m" //Bold
    val ANSI_BLUE = "\u001b[0;34m"
    val ANSI_RED_B = "\u001b[1;31m" //Bold
    val ANSI_RED = "\u001b[0;31m"
    val ANSI_RESET = "\u001B[0m"

    var color = ANSI_RED_B
    if (player == "BLUE"){ color = ANSI_BLUE_B }

    val Ko = color + "+" + ANSI_RESET
    val oK = color + "O" + ANSI_RESET

    val bar = "---|---|---|---|---|---|---|---|---|---|\n"
    val first_line = color+ "\n    ___________ " +player+ " PLAYER ___________   "+
        ANSI_RESET+ "\n   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n" + bar

    // Just an example for now
    return first_line +
          " 1 |   |   |   |   |   |   |   |   |   |\n" +bar+
          " 2 |   | " +oK+ " | " +Ko+ " | " +oK+ " | " +oK+ " |   |   |   |   |\n" +bar+
          " 3 |   |   |   |   |   |   |   |   |   |\n" +bar+
          " 4 |   |   |   |   |   |   |   |   |   |\n" +bar+
          " 5 |   |   |   |   |   |   |   | " +oK+ " |   |\n" +bar+
          " 6 |   |   |   |   |   |   |   | " +oK+ " |   |\n" +bar+
          " 7 |   |   |   |   |   |   |   | " +Ko+ " |   |\n" +bar+
          " 8 |   |   |   |   |   |   |   |   |   |\n" +bar+
          " 9 |   |   |   |   |   |   |   |   |   |\n" +bar
  }
}
