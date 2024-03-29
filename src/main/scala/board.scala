package board

import io.AnsiColor._
import scala.annotation.tailrec


//// TODO replace all Ansi color variables by:  BLUE + BOLD, from io.AnsiColor
object Board{

  def createBoard(i:Int, board:List[List[String]], coord: List[String]): List[List[String]] = {
    /**
      - update an empty board by placing points "O" based on coordonates
    **/
    val point = coord(i-1).split(":")
    val x = point(0).toInt
    val y = point(1).toInt

    val new_board = board.updated(y, board(y).updated(x-1, "O" )) // x-1 because 9 not 10

    if (i == 10){ return new_board } // 10 = 2 + 3 + 5 (size of boats)
    else{ return createBoard(i+1, new_board, coord) }
  }

  //// TODO should be updated (too many useless parameters)
  def betweenPoints(indice:Int, size:Int, from_point:List[Int], dim:Int, variations:List[Int], points:String): String = {
    /**
      - return a list containing all the points of the boat
      - recursive for size iteration to add the points
    **/
    val fixed = from_point(dim)
    val point = if (dim == 0) fixed.toString+ ":" +variations(indice).toString else variations(indice).toString+ ":" +fixed.toString
    val newPoints = points+ " - " +point

    if (indice == (size - 1)) { return point }
    else { return point + " - " + betweenPoints(indice+1, size, from_point, dim, variations, newPoints) }
  }


  def isLoosed(i:Int, board: List[List[String]]): Boolean = {
    /**
      - recursive iteration on board lines
      - break with False if find a "O"
      - return True else
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
      - place points based on points coordinates
      - return a grid with all points
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
      - create the pretty aspect of the board
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
