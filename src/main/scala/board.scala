package board

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

  def prettyPrint: String = {
    val bar: String = "---|---|---|---|---|\n"
    val first_line: String = "   | 1 | 2 | 3 | 4 |\n" + bar
    // Just example for now
    return first_line + " 1 |   | O | - | O |\n" +bar+
           " 2 |   |   |   |   |\n" +bar+
           " 3 |   |   |   |   |\n" +bar
  }
}
