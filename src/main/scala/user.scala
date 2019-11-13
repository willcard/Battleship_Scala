package user

import board._

object User {
  def play(player: String, opponentBoard: Board): Board = {
    var new_opponentBoard = opponentBoard //pour l'instant

    println(Board.prettyPrint(player))
    println(opponentBoard.get_boat_A)

    /**
    - Player try to find boat on opponent_board (give a point)
    - Edit the board
    - Edit the boats
    - return the new_opponent_board, and may be the new opponent boats
    **/
    return new_opponentBoard
  }
}
