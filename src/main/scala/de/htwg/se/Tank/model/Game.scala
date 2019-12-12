package de.htwg.se.Tank.model

import de.htwg.se.Tank.model.GameInit
import de.htwg.se.Tank.model.Map.{activePlayer, fx, moves, posInMap, NUMBER_OF_MOVES}

case class Game(partyname: String, mapNum: Int, name1: String, name2: String){
  var map : Map.type = Map
  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name += map.toString
    name
  }
  final val MAP_X2 = 30
  final val MAP_X1 = 0
  final val MAP_Y2 = 15
  final val MAP_Y1 = 0

 GameInit.setMapSettings(mapNum, name1, name2)


  def moveLeft() : Player = {
    if(moves > 0) {
      val tmp : Position = activePlayer.pos
      activePlayer.pos = Position(activePlayer.pos.x-1,fx(activePlayer.pos.x-1))
      if(!posInMap(activePlayer.pos)){
        activePlayer.pos = tmp
      }
      moves -= 1
    }
    //checkActivePlayer()
    activePlayer
  }

  def undoMoveLeft() : Player ={
    if(moves < NUMBER_OF_MOVES) {
      val tmp : Position = activePlayer.pos
      activePlayer.pos = Position(activePlayer.pos.x+1,fx(activePlayer.pos.x+1))
      if(!posInMap(activePlayer.pos)){
        activePlayer.pos = tmp
      }
      moves += 1
    }
    //checkActivePlayer()
    activePlayer
  }

  def moveRight() : Player ={
    if(moves > 0) {
      val tmp : Position = activePlayer.pos
      activePlayer.pos = Position(activePlayer.pos.x + 1,fx(activePlayer.pos.x + 1) )
      if(!posInMap(activePlayer.pos)){
        activePlayer.pos = tmp
      }
      moves -= 1
    }
    //checkActivePlayer()
    activePlayer
  }

  def undoMoveRight() : Player ={
    if(moves < NUMBER_OF_MOVES) {
      val tmp : Position = activePlayer.pos
      activePlayer.pos = Position(activePlayer.pos.x - 1,fx(activePlayer.pos.x - 1) )
      if(!posInMap(activePlayer.pos)){
        activePlayer.pos = tmp
      }
      moves += 1
    }
    //checkActivePlayer()
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

  def changePlayer() : Unit = {
    Map.StateContext.state.changePlayer()
  }





}
