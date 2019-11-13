import board._
//import user._
//import artificial._

object Game {
  def main(arg: Array[String]): Unit = {
    val first_boards = Setup // Setup actions could be directly in Main

    val greenBoard = first_boards(0)
    val blueBoard = first_boards(1)

    MainLoop(2, greenBoard, blueBoard)
  }


  //def Setup: List[Board] = {
  def Setup: List[List[List[String]]] = {
    /**
      - Choose dual mode or IA mode
      - Ask to player positions (takeCoordonates)
      - Create new boards
    **/
    //val greenBoard = new Board(List(List("3:1","3:2"),List("+","O")))
    //val blueBoard = new Board(List(List("5:1","4:1","3:1"),List("O","O","O")))

    val greenBoard = List(List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""))
    val blueBoard = List(List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""),
                          List("","","","","","","","","",""))

    return List(greenBoard, blueBoard)
  }


  //def MainLoop(i: Int, greenBoard: Board, blueBoard: Board): String = {
  def MainLoop(i: Int, greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (i==1) {return "STOP"}

    println("\n___ MainLoop ___")
    // Green player plays on Blue player board
    println(Board.prettyPrint("GREEN"))
    val new_blueBoard = play("GREEN",blueBoard)
    var state = isEnded(new_blueBoard,greenBoard)

    if (state != "NO") {return state}

    // Blue player plays on green player board
    println(Board.prettyPrint("BLUE"))
    val new_greenBoard = play("BLUE",greenBoard)
    state = isEnded(new_blueBoard,new_greenBoard) // pas très functionnal

    if (state != "NO") {return state}
    else return MainLoop(i-1, new_greenBoard,new_blueBoard)
  }


  //def isEnded(greenBoard: Board, blueBoard: Board): String = {
  def isEnded(greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (Board.isLoosed(greenBoard)) {
      return "GREEN"
    }
    else if (Board.isLoosed(blueBoard)) {
      return "BLUE"
    }
    else return "NO"
  }


  //def play(player: String, opponentBoard: Board): Board = {
  def play(player: String, opponentBoard: List[List[String]]): List[List[String]] = {
    var new_opponentBoard = opponentBoard //pour l'instant
    /**
    - Player enter coordonates
    - play edit the opponent board
    - play add tries to the tries list
    **/
    return new_opponentBoard
  }


  def takeCoordonates:Unit = {
    /**
      - Used to instance boards
      - May be used to choose target in play

      -> take  head and  tail points: "1:2" and "1:5"
      -> check if it's a possible boat: same line ok (1 coordonate ==)
      -> return the boat points list: ("1:2", "2:2", "3:2", "4:2", "5:2")
    **/
  }
}
