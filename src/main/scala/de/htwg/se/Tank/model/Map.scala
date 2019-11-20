package de.htwg.se.Tank.model
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ListBuffer
//import scala.math

case class Map(beginOfMap : (Int,Int),
               endOfMap : (Int,Int),
               map: Int , name1 : String, name2 : String){
  override def toString: String = {
    //var s = "Start Map " + beginOfMap + " End Map: " + endOfMap + "\n" +"MapFunction: " + fx
    var s = ""
    s += p1.toString + "\n"
    s += p2.toString + "\n"
    for(i <- endOfMap._2.until(beginOfMap._2).by(-1)){
      s += "||"
      for(j <- beginOfMap._1 to endOfMap._1){
        if(ListFX.contains((j,i))) {
          s += "+"
        } else if( i == beginOfMap._2 + 1){
          s += "="
        } else if(i == endOfMap._2){
          s += "="
        }  else {
          s+= " "
        }
        if(i == p1.pos.y.toInt && j == p1.pos.x.toInt) {
          s += "T1"
        }
        if((i == p2.pos.y.toInt && j == p2.pos.x.toInt)){
          s += "T2"
        }
        if(j == endOfMap._1){
          s += "||"
        }
      }
      s += "\n"
    }
    s+= "Aktiver Spieler: " + activePlayer.name + " restliche Anzahl Züge: " + moves
    s
  }

  var fx: Double => Double = (x:Double) => 5 * math.sin(0.1 * x) + 10
  //var y = 15;
  setFX(map)
  var p1 = Player(1,name1,this.generatePos(1))
  var p2 = Player(2,name2, this.generatePos(2))
  final val NUMBER_OF_MOVES : Int = 5
  var moves = NUMBER_OF_MOVES
  var activePlayer = p1

  var round = Round
  //round.apply()
  final val POSX_RANGE = 0.2
  final val NOPOS_RANGE = 0.1
  var ListFX = getFXList()

  private def checkActivePlayer(): Unit ={
    if(moves == 0) {
      changePlayer()
    }
  }
  private def changePlayer(){
    if(activePlayer == p1) {
      activePlayer = p2
      moves = NUMBER_OF_MOVES
    } else {
      activePlayer = p1
      moves = NUMBER_OF_MOVES
    }
  }

  def moveLeft() : Player ={
    val tmp : Position = activePlayer.pos
    activePlayer.pos = Position(activePlayer.pos.x-1,fx(activePlayer.pos.x-1))
    if(!posInMap(activePlayer.pos)){
      activePlayer.pos = tmp
    }
    moves -= 1
    checkActivePlayer()
    activePlayer
  }

  def moveRight() : Player ={
    val tmp : Position = activePlayer.pos
    activePlayer.pos = Position(activePlayer.pos.x + 1,fx(activePlayer.pos.x + 1) )
    if(!posInMap(activePlayer.pos)){
      activePlayer.pos = tmp
    }
    moves -= 1
    checkActivePlayer()
    activePlayer
  }

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
/*
  def getYFromMapFct(x: Double): Double = {
    val y = fx(x)
    if (posInMap(Position(x, y))) {
      return y
    }
    0.0
  }
*/
  def setFX(i: Int): Boolean = i match {
    case 0 => fx = (x: Double) => 5*math.sin(0.1 * x) + 7
      true
    case 1 => fx = (x: Double) => 20
      true
  }

 def generatePos(id : Int):Position={
   var pos:Position=null
   if(id == 1) {
     val begin = (beginOfMap._1 + NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin + POSX_RANGE * endOfMap._1).toInt
     val x = (Math.random()*end + begin)
     val y = fx(x)
     pos = Position(x,y)
   }
   if(id == 2){
     val begin = (endOfMap._1 - NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin - POSX_RANGE * endOfMap._1).toInt
     val x = (Math.random()*(begin-end) + end)
     val y = fx(x)
     pos = Position(x,y)
   }
    pos
 }
}

