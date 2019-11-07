package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class MapSpec extends WordSpec with Matchers {
  private val x = 100
  private val y = 100
  private val x1 = 20
  private val y1 = 10
  "A Map" when { "new" should {
      val map = Map((0,0),(x,y),0, "Flo", "Sascha")
/*      "have set x" in {
        map.x should be(x)
      }
      "have set y" in {
        map.y should be(y)
      }
      "generate Pos" in {
        map.generatePos() should be (x, y)
      }*/
    }
  }
}