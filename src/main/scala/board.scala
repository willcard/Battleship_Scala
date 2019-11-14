package board

object Board{
  // A revoir
  def isLoosed(board: List[List[String]]): Boolean = {
    /**
    for boat in board.boatsList:
      if not isDestroyed(boat) return false
    **/
    return false
  }

  // A revoir
  def isDestroyed(boat: List[List[String]]): Boolean = {
    if (boat(1).contains("O")){
      return false
    }
    else return true
  }

  def grid(i:Int, board:List[List[String]], pretty:String, _oK:String, _Ko:String, bar:String): String = {
    val line = " " +i.toString + board(i).mkString(" | "," | ","") + bar

    if (i==1){
      return grid(i+1, board, bar + line, _oK, _Ko, bar)
      }
    else if (i==9){         //replaceAll pas très functionnal
      return (pretty + line).replaceAll("O",_oK).replaceAll("x",_Ko)
      }
    else {
      return grid(i+1, board, pretty+line, _oK, _Ko, bar)
      }
  }


  def prettyPrint(player: String, board: List[List[String]]): String = {

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

    val bar = "\n---|---|---|---|---|---|---|---|---|---|\n"
    val first_line = color+ "\n    ___________ " +player+ " PLAYER ___________   "+
        ANSI_RESET+ "\n   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |"//\n" + bar

    //println(_Ko)
    return first_line + grid(1, board, "", _oK, _Ko, bar)
  }
}
