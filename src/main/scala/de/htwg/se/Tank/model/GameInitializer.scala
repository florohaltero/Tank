package de.htwg.se.Tank.model

object GameInitializer {
  val version = 0.1
  val name = "Tank"

  def setGame(partyname :String, map: Int, name1: String, name2: String): Game ={
    Game(partyname,Map((0,0),(100,50),map), Player(name1), Player(name2))
  }
}
