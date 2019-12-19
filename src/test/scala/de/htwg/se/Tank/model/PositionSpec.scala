package de.htwg.se.Tank.model
import de.htwg.se.Tank.model.playerComponent.playerBase.Position
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PositionSpec extends WordSpec with Matchers {
  "A Position" when {
    "new" should {
      val pos = Position(100, 100)
      "have x " in {
        pos.x should be(100)
      }
      " have y" in {
        pos.y should be(100)
      }
      pos.move(1, 1)
      "moved" in {
        pos.move(1, 1) should be (pos.move(1, 1))
      }

  }

}}
