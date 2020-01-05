package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.model.gameComponent.gameBase.Map.StateContext
import de.htwg.se.Tank.model.playerComponent.PlayerFactoryInterface
import de.htwg.se.Tank.model.playerComponent.playerBase.PlayerFactory
import com.google.inject.Guice
import de.htwg.se.Tank.model.gameComponent.MapSize
import javax.inject.Inject

object GameInit {

  final val MAP_X1 = 0
  final val MAP_Y1 = 0

  def setMapSettings (size: MapSize,mapNum: Int, name1: String,name2: String) : Unit = {
    Map.setFX(Option(mapNum))
    Map.ListFX = Map.getFXList(Map.GUImode)
    Map.p1 = PlayerFactory.createPlayer1(name1)
    Map.p2 = PlayerFactory.createPlayer2(name2)
    Map.beginOfMap = (MAP_X1,MAP_Y1)
    Map.endOfMap = (size.MAP_X,size.MAP_Y)
    Map.StateContext.setState(StateContext.P1State())
    Map.moves = Map.NUMBER_OF_MOVES
    Map.winner = null
  }
}
