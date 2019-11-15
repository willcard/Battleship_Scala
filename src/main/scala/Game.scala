import board._
import scala.util.{Try, Success, Failure}

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

    val emptyBoard = List.fill(10)(List.fill(10)(" "))

    val greenCoord = takeCoordonates("GREEN")
    val blueCoord = takeCoordonates("BLUE")

    val greenBoard = Board.createBoard(1, emptyBoard, greenCoord)
    val blueBoard = Board.createBoard(1, emptyBoard, blueCoord)

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
    state = isEnded(new_blueBoard,new_greenBoard) // editing state var not functionnal

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

  def takeCoordonates(player:String): List[String] = {
    /**

      - Used to instance boards
      - May be used to choose target in play

      -> take  head and  tail points: "1:2" and "1:5"
      -> check these points
      -> check if it's a possible boat: same line ok (1 coordonate ==)
      -> return the boat points list: ("1:2", "2:2", "3:2", "4:2", "5:2")
    **/

    val from = takePoint("FROM","")
    val to = takePoint("TO","")

    println("Boat is from: " +from+ " to: " +to)

    // example of output
    return List("5:4","5:5",
                "1:2","2:2","3:2",
                "3:7","4:7","5:7","6:7")
  }

  def takePoint(text:String,error:String):String = {
    // text can be: "FROM:", "TO:", "IN:"
    val ANSI_RED = "\u001b[0;31m"
    val ANSI_RESET = "\u001B[0m"

    val point = scala.io.StdIn.readLine(error +text+ " = ")
    val coordonates = point.split(":").toList


    if (coordonates.length != 2) {
      return takePoint(text, ANSI_RED+"# error - incorrect format (expected X:Y)#\n"+ANSI_RESET)
    }

    val x = coordonates(0)
    val y = coordonates(1)

    if ( ( ! Try(x.toInt).isSuccess) || ( ! Try(y.toInt).isSuccess) ){
      return takePoint(text, ANSI_RED+"# error - X or Y not Integer #\n"+ANSI_RESET)
    }
    else if ( ! (1 to 9 contains x.toInt) || ! (1 to 9 contains y.toInt) ) {
      return takePoint(text, ANSI_RED+"# error - values not in [1,9] interval #\n"+ANSI_RESET)
    }

    return point
  }

}
