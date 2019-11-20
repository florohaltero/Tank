package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TankSpec extends WordSpec with Matchers {
  val pos = Position(200,50)
  val angle = 10
  "A Tank" when { "new" should {
    val tank = Tank(pos, 100, angle)
    "have Position X" in {
      tank.pos.x should be (200)
    }
    "have Position Y" in {
      tank.pos.y should be (50)
    }
    "have lifepoints" in  {
      tank.lp should be (100)
    }
    "have Canon Angle" in {
      tank.canonAngle should be (10)
    }
    }}
}
