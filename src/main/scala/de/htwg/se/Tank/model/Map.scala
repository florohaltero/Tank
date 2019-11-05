package de.htwg.se.Tank.model
//import scala.math

case class Map(beginOfMap : (Int,Int),
               endOfMap : (Int,Int),
               map: Int ){

  var fx: Double => Double = (x:Double) => math.sin(0.5 * x + 2)
  setFX(map)

  def posInMap(pos: Position): Boolean = {
    if (pos.x >= beginOfMap._1 && pos.y >= beginOfMap._2 && pos.x <= endOfMap._1 && pos.y <= endOfMap._2) {
      true
    }
    false
  }

  def getYFromMapFct(x: Double): Double = {
    val y = fx(x)
    if (posInMap(Position(x, y))) {
      return y
    }
    null
  }

  def setFX(i: Int) = i match {
    case 0 => fx = (x: Double) => math.sin(0.5 * x + 2)
  }

}
