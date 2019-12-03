package artificial

import board._
import scala.util.Random._
import scala.annotation.tailrec

object Artificial {

  def fakeBoats(i:Int, other_boats:List[String]): List[String] = {
    /**
      - replace Player.takeBoats
      - each turn of recursive iteration create a boat of size i
    **/
    val sizes = List(2,3,5) // size of boats

    val from_point = fakeFrom(sizes(i))
    val fake_to = fakeTo(sizes(i), from_point)
    val to_point = fake_to._1
    val dim = fake_to._2

    val v = if (dim == 0) 1 else 0
    val variations = (from_point(dim) to to_point(dim)).toList

    val boat = Board.betweenPoints(0, sizes(i), from_point, dim, variations, "").split(" - ").toList

    if ( boat.exists(other_boats.contains) ) { //check if some boats are crossed
      return fakeBoats(i, other_boats)
    }
    else {
      if (i==2){ return boat ::: other_boats}
      else { return fakeBoats(i+1, boat ::: other_boats) }
    }
  }

  def fakeFrom(size:Int): List[Int] = {
    /**
      - choose a random FROM point
    **/
    val x = nextInt(10 - (size)).abs + 1
    val y = nextInt(10 - (size)).abs + 1

    return List(x,y)
  }

  def fakeTo(size: Int, from: List[Int]): (List[Int],Int) = {
    /**
      - choose a TO point using size and FROM point
      - Vertical or Horizontal is randomly choosed
    **/
    val vertical = nextBoolean()
    val x_from = from(0)
    val y_from = from(1)
    val dim = if (vertical) 1 else 0

    if (vertical){ return (List(x_from, y_from + (size-1)), dim) }
    else { return (List(x_from + (size-1), y_from), dim) }
  }


  //// TODO should choose based on good history results
  def play(opponentBoard: List[List[String]], history: List[String]): (List[List[String]], String) = {
    /**
      - replace Player.play
      - choose a point not on history
      - should choose based on good history results
    **/
    val x = nextInt(9).abs + 1
    val y = nextInt(9).abs + 1
    val tried = x.toString+ ":" +y.toString

    if (opponentBoard(y)(x-1) == "O"){
      // "@" character will be used to know if the target was good
      return (opponentBoard.updated(y, opponentBoard(y).updated(x-1, "x")), "@"+tried)
    }
    else { return (opponentBoard, tried) }
  }

}
