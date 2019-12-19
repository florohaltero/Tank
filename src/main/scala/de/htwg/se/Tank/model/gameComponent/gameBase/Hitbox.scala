package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.model.playerComponent.playerBase.Position

case class Hitbox(height: Int, width: Int, pos: Position) {
  var xRange : ((Double),(Double)) = ((pos.x - 0.5*width),(pos.x + 0.5*width))
  var yRange : ((Double),(Double)) = ((pos.y - 0.5*height),(pos.x + 0.5*height))
  def posInHitbox(pos: Position): Boolean ={
    if(pos.x >= xRange._1 && pos.x <= xRange._2 && pos.y >= yRange._1 && pos.y <= yRange._2) {
      return true
    }
    false
  }
}
