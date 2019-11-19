package artificial
import scala.util.Random._

object Artificial {

  def takeBoats: List[String] = {
    /**
      -> Set a random point From and point TO (it have to make sense)
      -> Then call classic betweenPoints to get all the points
      -> return the list of all points
    **/

    println("--- Artificial takeBoats example for of size 2")
    val from_test = fakeFrom(2)
    println("From " +from_test.mkString(":"))
    println("To " +fakeTo(2, from_test).mkString(":"))

    //val boat_A = beetweenPoints(0, 2, --problem should get dim--).split(" - ").toList
    val boat_A = List("1:9","2:9")
    val boat_B = List("7:1","7:2","7:3")
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

  def fakeTo(size: Int, from: List[Int]): List[Int] = {
    /**
      - Choose a to point using size and from point
      - Vertical or Horizontal is randomly choosed 
    **/
    val vertical = nextBoolean()
    val x_from = from(0)
    val y_from = from(1)

    if (vertical){
      print("vertical\n")
      return List(x_from, y_from + (size-1))
    }
    else {
      print("horizontal\n")
      return List(x_from + (size-1), y_from)
    }
  }


  def play: Unit = {
    /**
      - Should play like Game.play but randomly first
      _ Then should use the history
    **/

  }

}
