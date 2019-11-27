package de.htwg.se.Tank.model

import scala.collection.mutable.ListBuffer


object Calc {

  final val G = 9.81
  def shootCalc(startPos: Position, angle: Int, power: Int, begin_end : (Int,Int)): List[(Int, Int)] = {
    // Berechnungen
    var vx = Math.cos((30*2*Math.PI)/360) * power
    var vy = Math.sin((30*2*Math.PI)/360) * power
    val h0 = startPos.y
    val v0 = power
    val alpha = angle*2*Math.PI/360
    val x =  startPos.x
    var listbuffer : ListBuffer[(Int,Int)] = ListBuffer.empty[(Int,Int)]

    for(i <- 0 to begin_end._2){
      var y = h0 - G/(2*Math.pow(v0,2)*Math.pow(Math.cos(alpha),2)) * Math.pow(i,2) + i*Math.tan(alpha)
      listbuffer.append((i + x.toInt,y.toInt))
    }
    listbuffer.toList
  }
}

