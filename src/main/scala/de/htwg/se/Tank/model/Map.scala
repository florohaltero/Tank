package de.htwg.se.Tank.model
//import scala.math

case class Map(beginOfMap : (Int,Int),
               endOfMap : (Int,Int),
               map: Int ){
  override def toString: String = {
    var s = "Start Map " + beginOfMap + " End Map: " + endOfMap + "\n" +"MapFunction: " + fx
    s
  }

  var fx: Double => Double = (x:Double) => 5 * math.sin(0.1 * x) + 10
  //var y = 15;
  final val POSX_RANGE = 0.2
  final val NOPOS_RANGE = 0.1
  //setFX(map)
  var fxList : List[(Int, Int)]
  for(i <- beginOfMap._1 to endOfMap._1) {
    fxList = (i, fx(i).toInt) :: fxList
  }

  def posInMap(pos: Position): Boolean = {
    if (pos.x >= beginOfMap._1 && pos.y >= beginOfMap._2 && pos.x <= endOfMap._1 && pos.y <= endOfMap._2) {
      return true
    }
    false
  }

  def getYFromMapFct(x: Double): Double = {
    val y = fx(x)
    if (posInMap(Position(x, y))) {
      return y
    }
    0.0
  }

  def setFX(i: Int): Boolean = i match {
    case 0 => fx = (x: Double) => 5*math.sin(0.1 * x)+10
      true
    case 1 => fx = (x: Double) => 20
      true
  }

 def generatePos(player: Player):Position={
   var pos:Position=null
   if(player.id == 1) {
     val begin = (beginOfMap._1 + NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin + POSX_RANGE * endOfMap._1).toInt
     val x = (Math.random()*end + begin)
     val y = fx(x)
     pos = Position(x,y)
   }
   if(player.id == 2){
     val begin = (endOfMap._1 - NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin - POSX_RANGE * endOfMap._1).toInt
     val x = (Math.random()*(begin-end) + end)
     val y = fx(x)
     pos = Position(x,y)
   }
    pos

 }

}

