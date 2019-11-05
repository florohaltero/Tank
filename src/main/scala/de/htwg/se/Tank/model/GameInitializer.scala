package de.htwg.se.Tank.model

object GameInitializer {
  val version = 0.1
  val name = "Tank"
  var partyname = "Battle"

  def setPartyName(s: String): String = {
    partyname = s
    partyname
  }


}
