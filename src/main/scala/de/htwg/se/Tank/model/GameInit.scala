package de.htwg.se.Tank.model
import de.htwg.se.Tank.model.Map
import de.htwg.se.Tank.model.Map.StateContext

object GameInit {

  final val MAP_X2 = 30
  final val MAP_X1 = 0
  final val MAP_Y2 = 15
  final val MAP_Y1 = 0

  def setMapSettings(mapNum: Int, name1: String,name2: String) : Unit = {
    Map.p1 = PlayerFactory.createPlayer1(name1)
    Map.p2 = PlayerFactory.createPlayer2(name2)
    Map.setFX(Option(mapNum))
    Map.beginOfMap = (MAP_X1,MAP_Y1)
    Map.endOfMap = (MAP_X2,MAP_Y2)
    Map.ListFX = Map.getFXList()
    Map.StateContext.setState(StateContext.P1State())
  }
}
