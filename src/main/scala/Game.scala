import board
import user
import artificial


def MainLoop(redBoard: Board, blueBoard: Board): String = {
  // Red player plays on Blue player board and thus create a new Blue Board
  val new_blueBoard = play("RED",blueBoard)
  var state = isEnded(new_blueBoard,redBoard)

  if (state != "NO") {return state}

  // Blue player plays on Red player board and thus create a new Red Board
  val new_redBoard = play("BLUE",redBoard)
  state = isEnded(new_blueBoard,new_redBoard) // pas très functionnal

  if (state != "NO") {return state}
  else return MainLoop(new_blueBoard,new_redBoard)
}


def isEnded(redBoard: Board, blueBoard: Board): String = {
  if isLoosed(redBoard) {
    return "RED"
  }
  else if isLoosed(blueBoard) {
    return "BLUE"
  }
  else return "NO"
}
