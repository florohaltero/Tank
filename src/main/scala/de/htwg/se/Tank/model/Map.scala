package de.htwg.se.Tank.model
import scala.collection.mutable.ListBuffer

case class Map(beginOfMap : (Int,Int),
               endOfMap : (Int,Int),
               map: Int , name1 : String, name2 : String){

  object StateContext{
    var state : State = _
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
        if(ListFX.contains((j,i))) {
          s += "+"
        }  else if( i == beginOfMap._2 + 1){
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
  setFX(map)

  var p1 = PlayerFactory.createPlayer1(name1,this.generatePos(1,0))
  var p2 = PlayerFactory.createPlayer2(name2,this.generatePos(2,0))
  final val NUMBER_OF_MOVES : Int = 2
  var moves : Int = _
  var activePlayer : Player = _

  //Player 1 fängt an
  StateContext.setState(StateContext.P1State())
  var shotList: List[((Int),(Int))] = List.empty
  final val POSX_RANGE = 0.2
  final val NOPOS_RANGE = 0.1
  var ListFX = getFXList()

  private def checkActivePlayer(): Boolean ={
    if(moves == 0) {
      StateContext.state.changePlayer()
      true
    } else {false}
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

  def moveAngleUp() : Player = {
    activePlayer.tank = activePlayer.tank.canonUp()
    activePlayer
  }

  def moveAngleDown() : Player = {
    activePlayer.tank = activePlayer.tank.canonDown()
    activePlayer
  }

  def shoot(power :Int) : Unit = {
    //shotList = Calc.shootCalc(activePlayer.pos,activePlayer.tank.canonAngle,power,(beginOfMap._1,endOfMap._1))
    println("Shoot")
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
    case 1 => fx = (x: Double) => 10
      true
  }

  def generatePos(id : Int, xPos: Int):Position={
   var pos:Position = null
   var x : Double = 0;
   if(id == 1) {
     val begin = (beginOfMap._1 + NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin + POSX_RANGE * endOfMap._1).toInt
     if(xPos == 0){
       x = (Math.random()*end + begin)
     } else{
       x = xPos
     }
     val y = fx(x)
     pos = Position(x,y)
   } else if(id == 2){
     val begin = (endOfMap._1 - NOPOS_RANGE * endOfMap._1).toInt
     val end = (begin - POSX_RANGE * endOfMap._1).toInt
     if(xPos == 0) {
       x = (Math.random()*(begin-end) + end)
     } else{
       x = xPos
     }
     val y = fx(x)
     pos = Position(x,y)
   } else {
      throw new IllegalArgumentException
   }
   pos
 }
}


