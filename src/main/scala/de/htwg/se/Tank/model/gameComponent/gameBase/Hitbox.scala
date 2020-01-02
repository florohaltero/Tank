package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.model.playerComponent.playerBase.Position

case class Hitbox(height: Int, width: Int, pos: Position) {
  var xRange : ((Double),(Double)) = ((pos.x - 0.5*width),(pos.x + 0.5*width))
  var yRange : ((Double),(Double)) = ((pos.y - 0.5*height),(pos.y + 0.5*height))

  def posInHitbox(p: Position): Boolean ={
    if(p.x >= xRange._1 && p.x <= xRange._2 && p.y >= yRange._1 && p.y <= yRange._2) {
      return true
    }
    false
  }
}
