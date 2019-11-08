package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameSpec extends WordSpec with Matchers{
  "A Game" when { "new" should {
    val game_0 = Game("Standard", 0, "Flo", "Sascha")
    "have partyname" in {
      game_0.partyname should be("Standard")
    }
    "have map" in {
      game_0.map should be (0)
    }
    "have Player 1" in {
      game_0.player1 should be ("Flo")
    }
    "have Player 2" in {
      game_0.player2 should be ("Sascha")
    }
    "have Player1 map" in {
      game_0.mapObject.p1.name should be ("Flo")
    }
    "have Player2" in {
      game_0.mapObject.p2.name should be ("Sascha")
    }
  }}
}
