package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.model.gameComponent.{gameInterface, mapInterface}
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, PlayerFactory, Position}

import scala.collection.mutable.ListBuffer

object Map extends mapInterface {
  var fx: Double => Double = (x:Double) => 5 * math.sin(0.1 * x) + 10
  final val NUMBER_OF_MOVES : Int = 10
  var moves : Int = _
  var activePlayer : Player = _
  var beginOfMap : (Int,Int) = (1,1)
  var endOfMap : (Int,Int) = (1,1)
  //Player 1 fängt an
  var p1 : Player = PlayerFactory.createPlayer1("")
  var p2 : Player = PlayerFactory.createPlayer2("")
  var shotList: List[((Int),(Int))] = List.empty
  final val POSX_RANGE = 0.2
  final val NOPOS_RANGE = 0.1
  var GUImode : Boolean = false
  var ListFX : List[(Double,Double)] = Nil
  val MAP_UNIT = 0.02
  var winner : Player = _


  def getFxDouble(): List[Double] = {
    val list = getFXList(true)
    val lbuffer: ListBuffer[Double] = ListBuffer.empty
    for (i <- list) {
      lbuffer.append(i._1)
      lbuffer.append(i._2)

    }
    lbuffer.toList
  }

  def getFXList(GUImode : Boolean) : List[(Double,Double)] = {
    val listbuffer: ListBuffer[(Double,Double)] = ListBuffer.empty[(Double,Double)]
    if(!GUImode){
      for(i <- beginOfMap._1 to endOfMap._1) {
        listbuffer.append((i, fx(i).toInt))
      }
    } else {
      var i : Double = beginOfMap._1
      while(i < endOfMap._1){
        listbuffer.append((i, fx(i)))
        i += MAP_UNIT
      }
    }

    listbuffer.toList
  }

  def posInMap(pos: Position): Boolean = {
    if (pos.x >= beginOfMap._1 && pos.y >= beginOfMap._2 && pos.x <= endOfMap._1 && pos.y <= endOfMap._2) {
      return true
    }
    false
  }

  def setFX(i: Option[Int]): Boolean = i match {
    case Some(0) => fx = (x: Double) => 5 * math.sin(0.1 * x) + 7
      true
    case Some(1) => fx = (x: Double) => 10
      true
    case Some(2) => fx = (x: Double) => 0
      true
    case None =>
      false
  }

  def generatePos(id : Int): Position = {
   var pos: Position = null
   var x : Double = 0;
   if(id == 1) {
     val begin = (beginOfMap._1 + NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin + POSX_RANGE * endOfMap._1).toInt
     x = (Math.random()*end + begin)
     val y = fx(x)
     pos = Position(x,y)
   } else if(id == 2){
     val begin = (endOfMap._1 - NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin - POSX_RANGE * endOfMap._1).toInt
     x = (Math.random()*(begin-end) + end)
     val y = fx(x)
     pos = Position(x,y)
   } else {
      throw new IllegalArgumentException
   }
   pos
 }
  override def getActivePlayer: Player = activePlayer

  object StateContext {
    var state : State = _
    var last_moves : Int = _

    trait State {
      def changePlayer(): State
      def setPlayer(): State
    }

    case class P1State() extends State {
      override def changePlayer(): State = {
        activePlayer = p2
        moves = NUMBER_OF_MOVES
        state = P2State()
        state
      }

      override def setPlayer(): State ={
        activePlayer = p1
        moves = NUMBER_OF_MOVES
        state = P1State()
        state
      }
    }
    case class P2State() extends State {
      override def changePlayer(): State = {
        activePlayer = p1
        moves = NUMBER_OF_MOVES
        state = P1State()
        state
      }
      override def setPlayer(): State ={
        activePlayer = p2
        moves = NUMBER_OF_MOVES
        state = P2State()
        state
      }
    }
    def getState:State = {state}
    def setState(s: State):Unit = {
      state = s
      state.setPlayer()
    }
  }

  override def toString: String = {
    //var s = "Start Map " + beginOfMap + " End Map: " + endOfMap + "\n" +"MapFunction: " + fx
    var s = ""
    s += p1.toString + "\n"
    s += p2.toString + "\n"
    for(i <- endOfMap._2.until(beginOfMap._2).by(-1)){
      s += "||"
      for(j <- beginOfMap._1 to endOfMap._1){
        if(shotList.contains(j,i)) {
          s += "*"
        }
        if(ListFX.contains(j,i)) {
          s += "+"
        } else if(i == beginOfMap._2 + 1){
          s += "="
        } else if(i == endOfMap._2){
          s += "="
        }  else {
          s+= " "
        }
        if(i == p1.pos.y.toInt && j == p1.pos.x.toInt) {
          s += "T1"
        }
        if(i == p2.pos.y.toInt && j == p2.pos.x.toInt){
          s += "T2"
        }
        if(j == endOfMap._1){
          s += "||"
        }
      }
      s += "\n"
    }
    s+= "Aktiver Spieler: " + activePlayer.name + " restliche Anzahl Züge: " + moves
    if(winner != null){s += "Gewinner: " + winner.name}
    s
  }

  override def getPlayer(id: Int): Player = {
    id match {
      case 1 => p1
      case 2 => p2
    }
  }


//  import play.api.libs.json._
//  implicit def mapWrites = Json.writes[gameInterface]
//  implicit def mapReads = Json.reads[gameInterface]
}


