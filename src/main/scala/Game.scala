import board._
import scala.util.{Try, Success, Failure}

//import artificial._

object Game {
  def main(arg: Array[String]): Unit = {
    val first_boards = Setup // Setup actions could be directly in Main
    val greenBoard = first_boards(0)
    val blueBoard = first_boards(1)

    val result = MainLoop(3, greenBoard, blueBoard)
    println("\n :: Winner is " +result+ " player ! ::")
  }

  def Setup: List[List[List[String]]] = {
    /**
      - Choose dual mode or IA mode
      - Ask to player positions (takecoordinates)
      - Send coordinates to editLines()
      - Create new boards
    **/
    val emptyBoard = List.fill(10)(List.fill(10)(" "))

    val greenCoord = takeBoats("GREEN")
    val blueCoord = takeBoats("BLUE")

    val greenBoard = Board.createBoard(1, emptyBoard, greenCoord)
    val blueBoard = Board.createBoard(1, emptyBoard, blueCoord)

    return List(greenBoard, blueBoard)
  }

  // Essayer de faire 1 itération pour 1 joueur, pas les deux
  // Voir MainLoop_2
  def MainLoop(i: Int, greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (i==0) {return "STOP"}
    println("\n___ MainLoop ___")


    // Green player plays on Blue player board
    println(Board.prettyPrint("GREEN",greenBoard))
    val new_blueBoard = play("GREEN",blueBoard)
    //var state = isEnded(new_blueBoard,greenBoard)

    //if (state != "-") {return state}

    // Blue player plays on green player board
    println(Board.prettyPrint("BLUE",new_blueBoard))
    val new_greenBoard = play("BLUE",greenBoard)
    //state = isEnded(new_blueBoard,new_greenBoard) // editing state var not functionnal


    //if (state != "-") {return state}
    //else return MainLoop(i-1, new_greenBoard,new_blueBoard)
    return MainLoop(i-1, new_greenBoard,new_blueBoard)
  }

  def MainLoop_2(i:Int, opponentBoard:List[List[String]], playerBoard:List[List[String]], player=String): String = {
    
    return " "
  }



  def isEnded(greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (Board.isLoosed(greenBoard)) {
      return "GREEN"
    }
    else if (Board.isLoosed(blueBoard)) {
      return "BLUE"
    }
    else return "-"
  }

  def play(player: String, opponentBoard: List[List[String]]): List[List[String]] = {
    /**
    - Player enter coordinates (takePoint("TARGET"))
    - play edit the opponent board
    - play add tries to the tries list
    **/
    val target = takePoint("TARGET","")
    val x = target(0)
    val y = target(1)

    if (opponentBoard(y)(x-1) == "O"){
      println("Touched !")
      return opponentBoard.updated(y, opponentBoard(y).updated(x-1, "x"))
    }
    else { return opponentBoard }
  }


  def takeBoats(player:String): List[String] = {
    /**
        - Used to instance boards
        - Call takecoordinates 3 times (for boat size 2, 3 and 5)
        - Return a concatenate list of all points
    **/
    val ANSI_BLUE_B = "\u001b[1;34m"
    val ANSI_GREEN_B = "\u001b[1;32m"
    val ANSI_RESET = "\u001B[0m"

    val color = if (player == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B
    val player_line = color+ "\n______ " +player+ " PLAYER ______" +ANSI_RESET
    println(player_line)

    val boat_A = takeCoordinates(2)
    //val boat_B = takeCoordinates(3)
    //val boat_C = takeCoordinates(5)
    val boat_B = List("7:1","7:2","7:3")
    val boat_C = List("2:1","2:2","2:3","2:4","2:5")

    val merged = boat_C ::: boat_B ::: boat_A
    return merged
  }

  def takeCoordinates(size:Int): List[String] = {
    /**
      -> take  head and  tail points: "1:2" and "1:5"
      -> check if it's a possible boat: same line ok (1 coordinate ==)
      -> check if size is ok
      -> return the boat points list using beetweenPoints
    **/
    val ANSI_RED = "\u001b[0;31m"
    val ANSI_RESET = "\u001B[0m"

    println("\n   < Boat of size " +size+ " >")
    val point_from = takePoint("FROM","")
    val point_to = takePoint("TO","")

    if (point_from == point_to){
      println(ANSI_RED+"# error - point TO = point FROM #\n"+ANSI_RESET)
      return takeCoordinates(size)
    }

    // 1 similaire -> fonction ?
    if (point_from(0) == point_to(0)){
      if ( (point_to(1) - point_from(1)) < 0 ){
        println(ANSI_RED+"# error - boat is reversed (FROM > TO) #\n"+ANSI_RESET)
        return takeCoordinates(size)
      }
      else if ((point_to(1) - point_from(1)) != size - 1){
        println(ANSI_RED+"# error - boat is not of size " +size+ " #\n"+ANSI_RESET)
        return takeCoordinates(size)
      }
      else {
        val variations = (point_from(1) to point_to(1)).toList

        val fullBoat = betweenPoints(0, size, point_from(0), 0, variations, "")
        println("\nPoints are "+fullBoat)

        return fullBoat.split(" - ").toList
      }
    }
    // 2 similaire -> fonction ?
    else if (point_from(1) == point_to(1)){
      if ( (point_to(0) - point_from(0)) < 0 ){
        println(ANSI_RED+"# error - boat is reversed (FROM > TO) #\n"+ANSI_RESET)
        return takeCoordinates(size)
      }
      else if ((point_to(0) - point_from(0)) != size - 1){
        println(ANSI_RED+"# error - boat is not of size " +size+ " #\n"+ANSI_RESET)
        return takeCoordinates(size)
      }
      else {
        val variations = (point_from(0) to point_to(0)).toList

        val fullBoat = betweenPoints(0, size, point_from(1), 1, variations, "")
        println("\nPoints are "+fullBoat)

        return fullBoat.split(" - ").toList
      }
    }

    else {
      println(ANSI_RED+"# error - point TO and point FROM are not on the same line/column #\n"+ANSI_RESET)
      return takeCoordinates(size)
    }
  }


  def betweenPoints(indice:Int, size:Int, fixed:Int, dim:Int, variations:List[Int], points:String): String = {
    /**
      - return a list containing all the points of the boat
      -> recursive for size iteration to add the points
    **/
    val point = if (dim == 0) fixed.toString+ ":" +variations(indice).toString else variations(indice).toString+ ":" +fixed.toString
    val newPoints = points+ " - " +point

    if (indice == (size - 1)) { return point }
    else { return point + " - " + betweenPoints(indice+1, size, fixed, dim, variations, newPoints) }
  }


  // print l'erreur avant de récursive (pas besoin de la passer en param)
  def takePoint(text:String,error:String): List[Int] = {
    // text can be: "FROM:", "TO:", "TARGET:"
    val ANSI_RED = "\u001b[0;31m"
    val ANSI_RESET = "\u001B[0m"

    val point = scala.io.StdIn.readLine(error +text+ " = ")
    val coordinates = point.split(":").toList

    if (coordinates.length != 2) {
      return takePoint(text, ANSI_RED+"# error - incorrect format (expected X:Y) #\n"+ANSI_RESET)
    }

    val x = coordinates(0)
    val y = coordinates(1)

    if ( ( ! Try(x.toInt).isSuccess) || ( ! Try(y.toInt).isSuccess) ){
      return takePoint(text, ANSI_RED+"# error - X or Y not Integer #\n"+ANSI_RESET)
    }
    else if ( ! (1 to 9 contains x.toInt) || ! (1 to 9 contains y.toInt) ) {
      return takePoint(text, ANSI_RED+"# error - values not in [1,9] interval #\n"+ANSI_RESET)
    }

    return List(x.toInt, y.toInt)
  }

}
