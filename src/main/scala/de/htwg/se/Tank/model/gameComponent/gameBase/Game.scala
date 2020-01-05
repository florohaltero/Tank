package de.htwg.se.Tank.model.gameComponent.gameBase

import com.google.inject.{Guice, Inject}
import com.google.inject.name.{Named, Names}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Tank.TankModule
import de.htwg.se.Tank.model.gameComponent.gameBase.Map._
import de.htwg.se.Tank.model.gameComponent.{MapSize, gameInterface}
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position}

case class Game @Inject()(partyname: String, @Named("DefaultMap") mapNum: Int
                          ,@Named("DefaultSize") size:String, name1: String, name2: String) extends gameInterface {
  var map : Map.type = Map
  val injector = Guice.createInjector(new TankModule)
  var mapSize : MapSize =
  size match {
    case "small" => injector.instance[MapSize](Names.named("small"))
    case "big" => injector.instance[MapSize](Names.named("big"))
  }
  GameInit.setMapSettings(mapSize, mapNum, name1, name2)

  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name += map.toString
    name
  }

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
    activePlayer.power = power

    println("Shoot")
  }

  def changePlayer() : Unit = {
    Map.StateContext.state.changePlayer()
  }





}
