package de.htwg.se.Tank.model


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
        val l = Calc.shootCalc(Position(0, 0), 30, 100, (0, 100))
        l should not be(null)
      }
    }
  }
}
