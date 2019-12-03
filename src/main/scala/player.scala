package player

import board._
import scala.util.{Try, Success, Failure}
import scala.annotation.tailrec

object Player {

  // print error before recursive (don't make it param)
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

        val fullBoat = Board.betweenPoints(0, size, point_from, 0, variations, "")
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

        val fullBoat = Board.betweenPoints(0, size, point_from, 1, variations, "")
        println("\nPoints are "+fullBoat)

        return fullBoat.split(" - ").toList
      }
    }

    else {
      println(ANSI_RED+"# error - point TO and point FROM are not on the same line/column #\n"+ANSI_RESET)
      return takeCoordinates(size)
    }
  }




  // ////////  SHOULD pay attention to previous boats
  // def takeBoats(player:String): List[String] = {
  //   /**
  //       - Used to instance boards
  //       - Call takecoordinates 3 times (for boat size 2, 3 and 5)
  //       - Return a concatenate list of all points
  //   **/
  //   val ANSI_BLUE_B = "\u001b[1;34m"
  //   val ANSI_GREEN_B = "\u001b[1;32m"
  //   val ANSI_RESET = "\u001B[0m"
  //
  //   val color = if (player == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B
  //   val player_line = color+ "\n______ " +player+ " PLAYER ______" +ANSI_RESET
  //   println(player_line)
  //
  //   val boat_A = takeCoordinates(2)
  //   val boat_B = takeCoordinates(3)
  //   //val boat_C = takeCoordinates(5)
  //   //val boat_B = List("7:1","7:2","7:3")
  //   val boat_C = List("2:1","2:2","2:3","2:4","2:5")
  //
  //   val merged = boat_C ::: boat_B ::: boat_A
  //   return merged
  // }


  // def takeBoats(i:Int, player:String, other_boats:List[String]): List[String] = {
  //   /**
  //       - Used to instance boards
  //       - Call takecoordinates 3 times (for boat size 2, 3 and 5)
  //       - Return a concatenate list of all points
  //   **/
  //   val sizes = List(2,3,5) // size of boats
  //
  //   val ANSI_BLUE_B = "\u001b[1;34m"
  //   val ANSI_GREEN_B = "\u001b[1;32m"
  //   val ANSI_RESET = "\u001B[0m"
  //
  //   val color = if (player == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B
  //   val player_line = color+ "\n______ " +player+ " PLAYER ______" +ANSI_RESET
  //   println(player_line)
  //
  //   val new_boat = takeCoordinates(sizes(i))
  //
  //   val merged = boat_C ::: boat_B ::: boat_A
  //   return merged
  // }

  def takeBoats(i:Int, player:String, other_boats:List[String]): List[String] = {
    /**
        - Used to instance boards
        - Call takecoordinates 3 times (for boat size 2, 3 and 5)
        - Return a concatenate list of all points
    **/
    val sizes = List(2,3,5) // size of boats

    val ANSI_BLUE_B = "\u001b[1;34m"
    val ANSI_GREEN_B = "\u001b[1;32m"
    val ANSI_RED_B = "\u001b[1;31m"
    val ANSI_RESET = "\u001B[0m"

    if (i == 0) {
      val color = if (player == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B
      val player_line = color+ "\n______ " +player+ " PLAYER ______" +ANSI_RESET
      println(player_line)
    }

    val new_boat = takeCoordinates(sizes(i))

    if (new_boat.exists(other_boats.contains)){ //check if some boats are crossed
      println(ANSI_RED_B + "# This boats is crossing an other boat, please retry #" + ANSI_RESET)
      return takeBoats(i, player, other_boats)
    }
    else {
      if (i == 2) { return other_boats ::: new_boat}
      else { return takeBoats(i+1, player, other_boats ::: new_boat) }
    }
  }



  def play(opponentBoard: List[List[String]]): (List[List[String]], String) = {
    /**
    - Player enter coordinates (takePoint("TARGET"))
    - play edit the opponent board
    - play add tries to the tries list
    - return the target tried and the new board
    **/
    val ANSI_YELLOW_B = "\u001b[1;33m"
    val ANSI_RESET = "\u001B[0m"

    val target = takePoint("TARGET","")
    val x = target(0)
    val y = target(1)
    val tried = x.toString+ ":" +y.toString

    if (opponentBoard(y)(x-1) == "O"){
      println("You touched an opponent boat !\n")
      return (opponentBoard.updated(y, opponentBoard(y).updated(x-1, "x")), ANSI_YELLOW_B +tried+ ANSI_RESET )
    }
    else { return (opponentBoard, tried) }
  }


}
