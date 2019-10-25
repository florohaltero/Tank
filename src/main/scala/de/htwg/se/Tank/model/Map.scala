package de.htwg.se.Tank.model

case class Map(x : Int, y : Int) {
  def generatePos() : (Int, Int) = (x, y)
}
