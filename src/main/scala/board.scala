package board

/**
def prettyPrint(board: Board): Unit =
  board.grid
       .map(_.map(c => if (c == CELL_DEAD) " - " else " * ").mkString)
       .foreach(println)
**/

def isLoosed(board: Board): Boolean = {
  // for boat in board.boatsList: if not isDestroyed(boat) return false
  return false
}

def isDestroyed(boat: List): Boolean = {
  if boat.contains(1){
    return false
  }
  else return true
}
