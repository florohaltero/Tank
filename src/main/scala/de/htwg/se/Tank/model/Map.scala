package de.htwg.se.Tank.model

case class Map(x : Int, y : Int) {
  def generate() : (Int, Int) = (x, y)
}
