package de.htwg.se.Tank.model
import scala.collection.mutable.ListBuffer
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
    var t : Double= 0
    while(y >= 0 && x < Map.endOfMap._1){
      //Berechnung von x,y Koordinate
      x = v0 * Math.cos(alpha) * t
      y =  x*Math.tan(alpha) - G/(2*Math.pow(v0,2)*Math.pow(Math.cos(alpha),2)) * Math.pow(x,2) + h0
      //Start X wird auf den Schiefen Wurf addiert um diesen später richtig anzuzeigen
      listbuffer.append((x + sx,y))

      //Intervall von 0.2sek schiefer Wurf ausrechnen
      t += Map.MAP_UNIT
    }
    listbuffer.toList
  }

//  def hit(game: Game): Tuple2[Boolean, Game] = {
//    val hit = Calc.shootCalc(game.mapObject.p1.pos, game.mapObject.p1.tank.canonAngle, 50, game.mapObject.beginOfMap)
//    var res: Tuple2[Boolean, Game] = (false, game)
//    for (game.mapObject.getFXList() <- hit) {
//      if (game.mapObject.getFXList() == hit) {
//        res = (true, game)
//      }
//    }
//    res
//  }
}

