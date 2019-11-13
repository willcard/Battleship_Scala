import board._
import user._
//import artificial._

object Game {
  def main(arg: Array[String]): Unit = {
    val first_boards = Setup // Setup actions could be directly in Main

    val redBoard = first_boards(0)
    val blueBoard = first_boards(1)

    MainLoop(10, redBoard, blueBoard)
  }

  def Setup: List[Board] = {
    /**
      - Choose dual mode or IA mode
      - Ask to player positions
      - Create new boards
    **/
    val redBoard = new Board(List(List("B2","C2"),List("-","O")))
    val blueBoard = new Board(List(List("A1","B1","C1"),List("O","O","O")))

    return List(redBoard, blueBoard)
  }

  def MainLoop(i: Int, redBoard: Board, blueBoard: Board): String = {
    if (i==1) {return "STOP"}

    println("\n___ MainLoop ___\n")
    // Red player plays on Blue player board and thus create a new Blue Board
    val new_blueBoard = User.play("RED",blueBoard)
    var state = isEnded(new_blueBoard,redBoard)

    if (state != "NO") {return state}

    // Blue player plays on Red player board and thus create a new Red Board
    val new_redBoard = User.play("BLUE",redBoard)
    state = isEnded(new_blueBoard,new_redBoard) // pas très functionnal

    if (state != "NO") {return state}
    else return MainLoop(i-1, new_redBoard,new_blueBoard)
  }


  def isEnded(redBoard: Board, blueBoard: Board): String = {
    if (Board.isLoosed(redBoard)) {
      return "RED"
    }
    else if (Board.isLoosed(blueBoard)) {
      return "BLUE"
    }
    else return "NO"
  }
}
