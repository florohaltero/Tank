package de.htwg.se.Tank.model.gameComponent.gameBase


import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position}

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

object Calc {
  final val G = 9.81
  def shootCalc : List[(Double, Double)] = {
    // Berechnungen
    val h0 = Map.activePlayer.pos.y
    val v0 = Map.activePlayer.power
    val alpha = Map.activePlayer.tank.canonAngle*2*Math.PI/360
    val sx = Map.activePlayer.pos.x
    var x = Map.activePlayer.pos.x
    var y : Double = 0
    val listbuffer: ListBuffer[(Double, Double)] = ListBuffer.empty[(Double, Double)]
    var t : Double = 0
    breakable{
      while(y >= 0 && x < Map.endOfMap._1){
        //Berechnung von x,y Koordinate
        x = v0 * Math.cos(alpha) * t
        y =  x*Math.tan(alpha) - G/(2*Math.pow(v0,2)*Math.pow(Math.cos(alpha),2)) * Math.pow(x,2) + h0
        //Start X wird auf den Schiefen Wurf addiert um diesen später richtig anzuzeigen
        listbuffer.append((x + sx,y))
        if(hit(x + sx,y)){
          break
        }
        //Intervall von 0.2sek schiefer Wurf ausrechnen
        t += Map.MAP_UNIT
      }}
    listbuffer.toList
  }

  def hit(pos : ((Double), (Double))) : Boolean = {
    if(Map.activePlayer.equals(Map.p1)){
      if(Map.p2.tank.hitbox.posInHitbox(Position(pos._1,pos._2))) {
        Map.p2.tank.getDamage(20)
        if(Map.p2.tank.lp <= 0){
          win(Map.activePlayer)
          true
        }
        return true
      }
      false
    } else {
      if(Map.p1.tank.hitbox.posInHitbox(new Position(pos._1,pos._2))) {
        Map.p1.tank.getDamage(20)
        if(Map.p1.tank.lp <= 0){
          win(Map.activePlayer)
          true
        }
        return true
      }
      false
    }

  }

  def win(player: Player): Boolean = {
    Map.winner = player
    true
  }

}
