package de.htwg.se.Tank.model.playerComponentSpec

import de.htwg.se.Tank.model.playerComponent.playerBase
import de.htwg.se.Tank.model.playerComponent.playerBase.Position
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TankSpec extends WordSpec with Matchers {
  val pos = Position(200,50)
  val angle = 10
  val step = 2
  "A Tank" when { "new" should {
    var tank = playerBase.Tank(pos, 100, angle)
    "have vars" in {
      tank.STEP should be (step)
      tank.pos should be (pos)
    }
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
    tank = tank.canonUp()
    "have canon moved up" in {
      tank.canonAngle == (angle + tank.STEP)
    }
    tank = tank.canonDown()
      "have canon moved down" in {
        tank.canonAngle == (angle - tank.STEP)
    }
    }}
}
