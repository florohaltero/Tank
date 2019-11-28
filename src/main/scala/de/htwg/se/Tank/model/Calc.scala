package de.htwg.se.Tank.model
import scala.collection.mutable.ListBuffer
object Calc {
  final val G = 9.81
  def shootCalc(startPos: Position, angle: Int, power: Int, begin_end : (Int,Int)): List[(Double, Double)] = {
    // Berechnungen
    val h0 = startPos.y
    val v0 = power
    val alpha = angle*2*Math.PI/360
    val sx = startPos.x
    var x = startPos.x
    var y : Double = 0
    val listbuffer: ListBuffer[(Double, Double)] = ListBuffer.empty[(Double, Double)]
    var t : Double= 0
    while(y >= 0 && x < begin_end._2){
      //Berechnung von x,y Koordinate
      x = v0 * Math.cos(alpha) * t
      y =  x*Math.tan(alpha) - G/(2*Math.pow(v0,2)*Math.pow(Math.cos(alpha),2)) * Math.pow(x,2) + h0
      //Start X wird auf den Schiefen Wurf addiert um diesen später richtig anzuzeigen
      listbuffer.append((x + sx,y))

      //Intervall von 0.2sek schiefer Wurf ausrechnen
      t += 0.02
    }
    listbuffer.toList
  }
}

