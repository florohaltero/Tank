package de.htwg.se.Tank.model
import org.scalatest._


class PositionSpec extends WordSpec with Matchers {
  "A Position" when {
    "new" should {
      var pos = new Position(100, 100)
      "have x " in {
        pos.x should be (100)
      }
      " have y" in {
        pos.y should be (100)
      }
    }
  }

}
