package de.htwg.se.Tank.model.gameComponentSpec.gameBaseSpec

import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.{Calc, Game}
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
      var obj = Calc
      "G" in {
        obj.G should be(9.81)
      }
      "shootCalc List" in {
        val l = Calc.shootCalc(true)
        l should not be(null)
      }
    }
  }
}
