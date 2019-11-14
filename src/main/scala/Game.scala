import board._
//import artificial._

object Game {
  def main(arg: Array[String]): Unit = {
    val first_boards = Setup // Setup actions could be directly in Main
    val greenBoard = first_boards(0)
    val blueBoard = first_boards(1)

    MainLoop(2, greenBoard, blueBoard)
  }

  def Setup: List[List[List[String]]] = {
    /**
      - Choose dual mode or IA mode
      - Ask to player positions (takeCoordonates)
      - Send coordonates to editLines()
      - Create new boards
    **/

    val greenCoord = takeCoordonates("GREEN")
    val blueCoord = takeCoordonates("BLUE")

    val greenLines = editLines(greenCoord)
    val blueLines = editLines(blueCoord)

    val greenBoard = List(List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "))
    val blueBoard =  List(List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," ","O"," "),
                          List(" "," "," "," "," "," "," "," ","x"," "),
                          List(" "," "," "," "," "," "," "," ","O"," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," ","O","O","O","O"," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "),
                          List(" "," "," "," "," "," "," "," "," "," "))

    return List(greenBoard, blueBoard)
  }

  def MainLoop(i: Int, greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (i==1) {return "STOP"}

    println("\n___ MainLoop ___")
    // Green player plays on Blue player board
    println(Board.prettyPrint("GREEN",greenBoard))
    val new_blueBoard = play("GREEN",blueBoard)
    var state = isEnded(new_blueBoard,greenBoard)

    if (state != "NO") {return state}

    // Blue player plays on green player board
    println(Board.prettyPrint("BLUE",blueBoard))
    val new_greenBoard = play("BLUE",greenBoard)
    state = isEnded(new_blueBoard,new_greenBoard) // pas très functionnal

    if (state != "NO") {return state}
    else return MainLoop(i-1, new_greenBoard,new_blueBoard)
  }

  def isEnded(greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (Board.isLoosed(greenBoard)) {
      return "GREEN"
    }
    else if (Board.isLoosed(blueBoard)) {
      return "BLUE"
    }
    else return "NO"
  }

  def play(player: String, opponentBoard: List[List[String]]): List[List[String]] = {
    var new_opponentBoard = opponentBoard //pour l'instant
    /**
    - Player enter coordonates
    - play edit the opponent board
    - play add tries to the tries list
    **/
    return new_opponentBoard
  }

  def takeCoordonates(player:String):List[List[String]] = {
    /**
      - Used to instance boards
      - May be used to choose target in play

      -> take  head and  tail points: "1:2" and "1:5"
      -> check if it's a possible boat: same line ok (1 coordonate ==)
      -> return the boat points list: ("1:2", "2:2", "3:2", "4:2", "5:2")
    **/

    // example of output
    return List(List("1:2", "2:2", "3:2"),
                List("5:4","5:5"))
  }

  def editLines(coord: List[List[String]]):List[List[String]] = {
    /**
      -> take the boat points list: (("1:2", "2:2", "3:2", "4:2"),("5:4","5:5"))
      -> return lines edit with the indice of the line
    **/

    // example of output
    return List(List(" "," "," "," "," "," "," "," "," "," "),
              List(" "," "," "," "," "," "," "," "," "," "))
  }
}
