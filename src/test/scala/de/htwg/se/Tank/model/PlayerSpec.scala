package de.htwg.se.Tank.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PlayerSpec extends WordSpec with Matchers {
  private val lp = 100
  "A Player" when { "new" should {
    val player = Player("Your Name", lp)
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have damage taken" in {
      player.damagetaken(50)
    }
    "have lifepoints" in {
      player.lp should be (50)
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}

//  "A Shot" when {
//    "new" should {
//      val player = Player("Your Name", lp)
//      "have lifepoints" in {
//        player.lp should be (lp)
//      }
//      "have a nice String representation" in {
//        player.damagetaken should be > 0
//      }
//    }
//  }

}
