package de.htwg.se.Tank.model

object GameInit {
  override def toString: String = {
    var name = "\n" + "partyname: " + partyname + "\n"
    name += Map.toString
    name
  }
  final val MAP_X = 30
  final val MAP_Y = 15
  var partyname : String = ""

  def setMapSettings(partyname: String, map: Int, name1: String,name2: String) : Unit = {
    Map.p1 = PlayerFactory.createPlayer1(name1,Map.generatePos(1,0))
    Map.p2 = PlayerFactory.createPlayer2(name2,Map.generatePos(1,0))
    Map.setFX(map)
    this.partyname = partyname
  }

}
