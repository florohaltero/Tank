package de.htwg.se.Tank.model

case class Hitbox(height: Int, width: Int, pos: Position) {
  def posInHitbox(pos: Position): Boolean ={
    true
  }
}
