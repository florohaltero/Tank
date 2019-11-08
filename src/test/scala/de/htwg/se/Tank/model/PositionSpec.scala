package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PositionSpec extends WordSpec with Matchers {
  "A Position" when {
    "new" should {
      var pos = Position(100, 100)
      "have x " in {
        pos.x should be(100)
      }
      " have y" in {
        pos.y should be(100)
      }
    }
    "A Position" when {
      "moved" should {
        var pos = Position(100, 100)
        pos = pos.move(1,1)


      "have moved to" in {
        pos.x should be (1.0)
      }
      "and moved to Y" in {
        pos.y should be (1.0)
      }
    }
  }

}}
