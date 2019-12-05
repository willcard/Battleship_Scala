package game

import board._
import artificial._
import player._

import scala.annotation.tailrec
import scala.sys.process._
import io.AnsiColor._


object Game {
  def main(arg: Array[String]): Unit = {
    /**
      - main function of the game:
      - setup the game params
      - run the main loop
      - show the winner
    **/
    println("\n"*5)
    clear()

    val first_boards = Setup
    val greenBoard = first_boards._1
    val blueBoard = first_boards._2
    val ai_mode = first_boards._3

    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLUE_B = "\u001b[1;34m"
    val ANSI_GREEN_B = "\u001b[1;32m"

    val result = MainLoop(ai_mode, greenBoard,blueBoard, List[String](),List[String]())
    val color = if (result == "BLUE") ANSI_BLUE_B else ANSI_GREEN_B

    println("\n :: Winner is " +color+result+ANSI_RESET+ " player ::\n")
  }


  def Setup: (List[List[String]], List[List[String]], Boolean) = {
    /**
      - choose dual mode or AI mode
      - ask player positions (takecoordinates)
      - send coordinates to editLines()
      - create new boards
    **/
    val emptyBoard = List.fill(10)(List.fill(10)(" "))

    val ANSI_YELLOW_B = "\u001b[1;33m"
    val ANSI_RESET = "\u001B[0m"
    println(ANSI_YELLOW_B+ "AI mode ? [y/N]" +ANSI_RESET)
    val ai_mode = scala.io.StdIn.readBoolean()

    // AI will always replace BLUE player
    val blueCoord = if (ai_mode) Artificial.fakeBoats(0,List[String]()) else Player.takeBoats(0,"BLUE",List[String]())
    val greenCoord = Player.takeBoats(0,"GREEN",List[String]())

    // println(blueCoord) //for debug

    val greenBoard = Board.createBoard(1, emptyBoard, greenCoord)
    val blueBoard = Board.createBoard(1, emptyBoard, blueCoord)

    return (greenBoard, blueBoard, ai_mode)
  }

  @tailrec
  def MainLoop(ai_mode:Boolean, greenBoard:List[List[String]], blueBoard:List[List[String]], greenHistory:List[String], blueHistory:List[String]): String = {
    /**
      -> Each turn both player play, and for each player:
        - the state of the board is shown
        - the history of his previous targets is shown
        - he plays on the opponent's board
        - the state of the game is checked (ended or not)
    **/

    // GREEN player plays on BLUE player board
    if (! ai_mode){ clear() }
    println( Board.prettyPrint("GREEN",greenBoard) + showHistory(greenHistory) )

    val green_played = Player.play(blueBoard)
    val new_blueBoard = green_played._1
    val green_tried = green_played._2
    val new_greenHistory = greenHistory ::: List(green_tried)

    val state_1 = isEnded(new_blueBoard,greenBoard)
    if (state_1 != "-") { return state_1 }


    // BLUE player plays on GREEN player board
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLUE_B = "\u001b[1;34m"

    if (ai_mode){
      println(ANSI_BLUE_B+ "\n__ AI is playing __" +ANSI_RESET)
      //println(Board.prettyPrint("BLUE",new_blueBoard)) //for debug
    }
    else {
      clear()
      println( Board.prettyPrint("BLUE",new_blueBoard) + showHistory(blueHistory) )
    }

    val blue_played = if (ai_mode) Artificial.play(greenBoard, blueHistory) else Player.play(greenBoard)
    val new_greenBoard = blue_played._1
    val blue_tried = blue_played._2
    val new_blueHistory = blueHistory ::: List(blue_tried)

    if (ai_mode){
      if (blue_tried.contains("@")){ println("-> AI touched you at " +blue_tried.substring(1)+ " !") }
      else { println("-> AI missed you at " +blue_tried) }
    }

    val state_2 = isEnded(new_blueBoard,new_greenBoard)
    if (state_2 != "-") { return state_2 }

    else { return MainLoop(ai_mode, new_greenBoard,new_blueBoard, new_greenHistory,new_blueHistory)}
  }


  def showHistory(history: List[String]): String = {
    /**
      - shows the previous targets that the player attempted
      - if the target was on a boat, the point is printed in yellow
    **/
    val ANSI_YELLOW_B = "\u001b[1;33m"
    val ANSI_RESET = "\u001B[0m"
    val previous = history.mkString(" "," - "," ")

    return "\n• Previous targets (" +ANSI_YELLOW_B+ "touched" +ANSI_RESET+ "): " +previous
  }


  def isEnded(greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    /**
      - check if one board is loosed
      - if true, return the winner color
      - else, return "-"
    **/
    if (Board.isLoosed(0, greenBoard)) {
      return "GREEN"
    }
    else if (Board.isLoosed(0, blueBoard)) {
      return "BLUE"
    }
    else return "-"
  }


  def clear(): Unit = {
    "clear".!
  }
}
