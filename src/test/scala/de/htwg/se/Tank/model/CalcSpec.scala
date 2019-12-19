package de.htwg.se.Tank.model

import de.htwg.se.Tank.model.gameComponent.gameBase.Calc
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CalcSpec extends WordSpec with Matchers {
  private val lp = 100
  "Calculation" when {
    "shootCalc" should {
      var obj = Calc
      "G" in {
        obj.G should be(9.81)
      }
      "shootCalc List" in {
        val l = Calc.shootCalc
        l should not be(null)
      }
    }
  }
}
