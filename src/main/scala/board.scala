package board

object Board{

  def createBoard(i:Int, board:List[List[String]], coord: List[String]): List[List[String]] = {
    val point = coord(i-1).split(":")
    val x = point(0).toInt
    val y = point(1).toInt

    val new_board = board.updated(y, board(y).updated(x-1, "O" )) //x-1 because 9 not 10

    if (i == 10){ return new_board } // 10 = 2 + 3 + 5 (size of boats)
    else{ return createBoard(i+1, new_board, coord) }
  }


  def isLoosed(i:Int, board: List[List[String]]): Boolean = {
    /**
      - iteration récursive sur les ligne de la boards
      - break à False si trouve un "O"
      - retourne True  sinon
    **/
    if (board(i).contains("O")){
      return false
    }
    else if (i == 9){
      return true
    }
    else {
      return isLoosed(i+1, board)
    }
  }


  def grid(i:Int, board:List[List[String]], pretty:String, _oK:String, _Ko:String): String = {
    /**
      - Place points based on points coordinates
    **/
    val bar = "\n---|---|---|---|---|---|---|---|---|---|\n"
    val line = " " +i.toString + board(i).mkString(" | "," | ","") + bar

    if (i == 1){
      return grid(i+1, board, bar + line, _oK, _Ko) }
    else if (i == 9){
      return (pretty + line).replaceAll("O",_oK).replaceAll("x",_Ko) } //functionnal ?
    else{
      return grid(i+1, board, pretty+line, _oK, _Ko) }
  }


  def prettyPrint(player: String, board: List[List[String]]): String = {
    /**
      - Create the pretty aspect of the board
      - call grid to place the points
    **/
    val ANSI_RESET = "\u001B[0m"
    val ANSI_RED_B = "\u001b[1;31m"
    val ANSI_BLUE_B = "\u001b[1;34m"
    val ANSI_GREEN_B = "\u001b[1;32m"

    val color = if (player == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B

    val _Ko = ANSI_RED_B + "+" + ANSI_RESET
    val _oK = color + "O" + ANSI_RESET


    val first_line = color+ "\n    ___________ " +player+ " PLAYER ___________   \n"+
        ANSI_RESET+ "\n• Your board:\n" + "\n   | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |"

    return first_line + grid(1, board, "", _oK, _Ko)
  }
}
