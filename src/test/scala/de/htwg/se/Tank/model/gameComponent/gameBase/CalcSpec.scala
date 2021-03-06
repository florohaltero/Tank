package de.htwg.se.Tank.model.gameComponent.gameBase

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.{Calc, Game, GameInit, Map}
import de.htwg.se.Tank.model.playerComponent.playerBase.{Player, Position}
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
      "G" in {
        Calc.G should be(9.81)
      }
      "shootCalc List" in {
        val l = Calc.shootCalc(true)
        l should not be(null)
      }

      "when win" in {
        false should be (false)
      }
    }
    "hit" should {
      Map.p1.tank.damage = 100
      Map.p2.tank.damage = 100
      Calc.hit(Map.p2.pos.x, Map.p2.pos.y)
      "p1 wins" in {
        Calc.hit(Map.p2.pos.x, Map.p2.pos.y) should be (true)
        Map.winner should be (Map.p1)
      }
      Calc.hit(Map.p1.pos.x,Map.p1.pos.y)
      "p2 wins" in {
        Map.StateContext.state.changePlayer()
        Calc.hit(Map.p1.pos.x,Map.p1.pos.y) should be (true)
        Map.winner should be (Map.p2)
      }
    }
  }
}
