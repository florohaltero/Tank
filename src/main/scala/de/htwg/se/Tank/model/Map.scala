package de.htwg.se.Tank.model
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ListBuffer
//import scala.math

case class Map(beginOfMap : (Int,Int),
               endOfMap : (Int,Int),
               map: Int ){
  override def toString: String = {
    //var s = "Start Map " + beginOfMap + " End Map: " + endOfMap + "\n" +"MapFunction: " + fx
    var s = ""

    for(i <- endOfMap._2.until(beginOfMap._2).by(-1)){
      s += "||"
      for(j <- beginOfMap._1 to endOfMap._1){
        if(ListFX.contains((j,i))) {
          s += "+"
        } else if( i == beginOfMap._2 + 1){
          s += "="
        } else if(i == endOfMap._2){
          s += "="
        } else {
          s+= " "
        }
        //if(j == 0){
        //  s += "="
        //}
        if(j == endOfMap._1){
          s += "||"
        }
      }
      s += "\n"
    }
    s
  }

  var fx: Double => Double = (x:Double) => 5 * math.sin(0.1 * x) + 10
  //var y = 15;
  setFX(map)
  final val POSX_RANGE = 0.2
  final val NOPOS_RANGE = 0.1
  var ListFX = getFXList()


  def getFXList() : List[(Int,Int)] ={
    var listbuffer : ListBuffer[(Int,Int)] = ListBuffer.empty[(Int,Int)]
    for(i <- beginOfMap._1 to endOfMap._1) {
      listbuffer.append((i, fx(i).toInt))
    }
    listbuffer.toList
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
    case 0 => fx = (x: Double) => 5*math.sin(0.1 * x) + 10
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

