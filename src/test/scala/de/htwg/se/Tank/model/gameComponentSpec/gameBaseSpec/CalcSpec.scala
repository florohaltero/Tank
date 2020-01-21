package de.htwg.se.Tank.model.gameComponentSpec.gameBaseSpec

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.{Calc, Game, Map}
import de.htwg.se.Tank.model.playerComponent.playerBase.Player
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CalcSpec extends WordSpec with Matchers {
  private val lp = 100
  val game = Game("Standard", 2,"small", "Flo", "Sasch")
  val controller = new Controller(game)
  "Calculation" when {
    "shootCalc" should {
      val obj = Calc
      "G" in {
        obj.G should be(9.81)
      }
      "shootCalc List" in {
        val l = Calc.shootCalc(true)
        l should not be(null)
      }
     val player: Player = Map.p2
      "when hit" in {
        obj.hit(player.pos.x + player.pos.x, 0) should be (true)
      }
      "when win" in {
        obj.win(player) should be (true)
      }
    }
  }
}
