package game

import board._
import artificial._
import player._

import scala.annotation.tailrec
import scala.sys.process._
import io.AnsiColor._


object Game {
  def main(arg: Array[String]): Unit = {
    println("\n"*5) // For cleaning
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
      - Choose dual mode or AI mode
      - Ask to player positions (takecoordinates)
      - Send coordinates to editLines()
      - Create new boards
    **/
    val emptyBoard = List.fill(10)(List.fill(10)(" "))

    val ANSI_YELLOW_B = "\u001b[1;33m"
    val ANSI_RESET = "\u001B[0m"
    println(ANSI_YELLOW_B+ "AI mode ? [y/N]" +ANSI_RESET)
    val ai_mode = scala.io.StdIn.readBoolean()

    // AI will always replace BLUE player
    val blueCoord = if (ai_mode) Artificial.fakeBoats(0,List[String]()) else Player.takeBoats("BLUE")
    val greenCoord = Player.takeBoats("GREEN")
    println(blueCoord)
    println(greenCoord)

    val greenBoard = Board.createBoard(1, emptyBoard, greenCoord)
    val blueBoard = Board.createBoard(1, emptyBoard, blueCoord)

    return (greenBoard, blueBoard, ai_mode)
  }


  //def clear() = "clear".!
  def clear(): Unit = {} // for debug

  def MainLoop(ai_mode:Boolean, greenBoard:List[List[String]], blueBoard:List[List[String]], greenHistory:List[String], blueHistory:List[String]): String = {

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
    clear()
    if (ai_mode){
      println("__ AI is playing __")
      println(Board.prettyPrint("BLUE",new_blueBoard)) // For DEBUG
    }
    else { println( Board.prettyPrint("BLUE",new_blueBoard) + showHistory(blueHistory) ) }

    val blue_played = if (ai_mode) Artificial.play(greenBoard, blueHistory) else Player.play(greenBoard)
    val new_greenBoard = blue_played._1
    val blue_tried = blue_played._2
    val new_blueHistory = blueHistory ::: List(blue_tried)

    if (ai_mode){
      if (blue_tried.contains("@")){ println("-> AI touched you at " +blue_tried.substring(1)+ " !") }
      else { println("-> AI missed you") }
    }

    val state_2 = isEnded(new_blueBoard,new_greenBoard)
    if (state_2 != "-") { return state_2 }

    else { return MainLoop(ai_mode, new_greenBoard,new_blueBoard, new_greenHistory,new_blueHistory)}
  }


  def showHistory(history: List[String]): String = {
    val ANSI_YELLOW_B = "\u001b[1;33m"
    val ANSI_RESET = "\u001B[0m"
    val previous = history.mkString(" "," - "," ")

    return "• Previous targets (" +ANSI_YELLOW_B+ "touched" +ANSI_RESET+ "): " +previous
  }


  def isEnded(greenBoard: List[List[String]], blueBoard:  List[List[String]]): String = {
    if (Board.isLoosed(0, greenBoard)) {
      return "GREEN"
    }
    else if (Board.isLoosed(0, blueBoard)) {
      return "BLUE"
    }
    else return "-"
  }


}
