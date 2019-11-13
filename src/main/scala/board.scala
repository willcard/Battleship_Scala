package board

/**class Board(boat_A: List[List[String]]) {
  //def main(args: Array[String]): Unit = {}
}**/

object Board{
  // A revoir
  //def isLoosed(board: Board): Boolean = {
  def isLoosed(board: List[List[String]]): Boolean = {
    /**
    for boat in board.boatsList:
      if not isDestroyed(boat) return false
    **/
    return false
  }

  // A revoir
  //def isDestroyed(boat: List[List[String]]): Boolean = {
  def isDestroyed(boat: List[List[String]]): Boolean = {
    if (boat(1).contains("O")){
      return false
    }
    else return true
  }


  def grid(i: Int, _oK:String, _Ko:String): String = {
    val bar = "\n---|---|---|---|---|---|---|---|---|---|\n"

    /**
    for i in range:
      line += " | " +_oK if "O" in boat, _Ko else ....
    return bar+ " " +i.toString+ line
    **/

    return bar +
          " 1 |   |   |   |   |   |   |   |   |   |" +bar+
          " 2 |   | " +_oK+ " | " +_Ko+ " | " +_oK+ " | " +_oK+ " |   |   |   |   |" +bar+
          " 3 |   |   |   |   |   |   |   |   |   |" +bar+
          " 4 |   |   |   |   |   |   |   |   |   |" +bar+
          " 5 |   |   |   |   |   |   |   | " +_oK+ " |   |" +bar+
          " 6 | " +_oK+ " | " +_Ko+ " | " +_Ko+ " | " +_Ko+ " |   |   |   | " +_oK+ " |   |" +bar+
          " 7 |   |   |   |   |   |   |   | " +_Ko+ " |   |" +bar+
          " 8 |   |   |   |   |   |   |   |   |   |" +bar+
          " 9 |   |   |   |   |   |   |   |   |   |" +bar
  }


  def prettyPrint(player: String): String = {

    val ANSI_BLUE_B = "\u001b[1;34m" //Bold
    val ANSI_BLUE = "\u001b[0;34m"
    val ANSI_RED_B = "\u001b[1;31m" //Bold
    val ANSI_RED = "\u001b[0;31m"
    val ANSI_GREEN_B = "\u001b[1;32m" //Bold
    val ANSI_GREEN = "\u001b[0;32m"
    val ANSI_RESET = "\u001B[0m"

    var color = ANSI_GREEN_B
    if (player == "BLUE"){ color = ANSI_BLUE_B }

    val _Ko = ANSI_RED_B + "+" + ANSI_RESET
    val _oK = color + "O" + ANSI_RESET

    val bar = "---|---|---|---|---|---|---|---|---|---|\n"
    val first_line = color+ "\n    ___________ " +player+ " PLAYER ___________   "+
        ANSI_RESET+ "\n   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |"//\n" + bar

    return first_line + grid(1, _oK, _Ko)
  }
}
