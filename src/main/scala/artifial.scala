package artificial

import game._
import scala.util.Random._

object Artificial {

  // should be recursive (1 boat a turn)
  def takeBoats: List[String] = {
    /**
      -> Set a random point From and point TO (it have to make sense)
      -> Then call classic betweenPoints to get all the points
      -> return the list of all points
    **/

    val from_point = fakeFrom(3)
    val fake_to = fakeTo(3, from_point)
    val to_point = fake_to._1
    val dim = fake_to._2

    println(from_point+ " - " +to_point+ " :: " +dim)
    val v = if (dim == 0) 1 else 0
    val variations = (from_point(dim) to to_point(dim)).toList
    println(variations)

    val boat_B = Game.betweenPoints(0, 3, from_point, dim, variations, "").split(" - ").toList
    println("--> " +boat_B)



    val boat_A = List("1:9","2:9")
    //val boat_B = List("7:1","7:2","7:3")
    val boat_C = List("2:1","2:2","2:3","2:4","2:5")

    val merged = boat_C ::: boat_B ::: boat_A
    return merged
  }

  def fakeFrom(size:Int): List[Int] = {
    /**
      - Choose a random from point
    **/
    val x = nextInt(10 - (size-1)).abs
    val y = nextInt(10 - (size-1)).abs

    return List(x,y)
  }

  def fakeTo(size: Int, from: List[Int]): (List[Int],Int) = {
    /**
      - Choose a to point using size and from point
      - Vertical or Horizontal is randomly choosed
    **/
    val vertical = nextBoolean()
    val x_from = from(0)
    val y_from = from(1)
    val dim = if (vertical) 1 else 0

    if (vertical){
      print("vertical\n")
      return (List(x_from, y_from + (size-1)), dim)
    }
    else {
      print("horizontal\n")
      return (List(x_from + (size-1), y_from), dim)
    }
  }


  def play(opponentBoard: List[List[String]], history: List[String]): (List[List[String]], String) = {
    /**
      - Should play like Game.play but randomly first
      - Then should use the history
    **/
    val x = nextInt(9).abs + 1
    val y = nextInt(9).abs + 1
    val tried = x.toString+ ":" +y.toString

    if (opponentBoard(y)(x-1) == "O"){
      // "@"" character will be used to know if the target was good
      return (opponentBoard.updated(y, opponentBoard(y).updated(x-1, "x")), "@"+tried)
    }
    else { return (opponentBoard, tried) }
  }

}
