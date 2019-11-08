package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MapSpec extends WordSpec with Matchers {
  private val x = 100
  private val y = 100
  private val x1 = 20
  private val y1 = 10
  "A Map" when { "new" should {
      val map = Map((0,0),(x,y), 0, "Flo", "Sascha")
      "have beginOfMap x" in {
        map.beginOfMap._1 should be(0)
      }
      "have beginOfMap y" in {
        map.beginOfMap._2 should be(0)
      }
      "have endOfMap x" in {
        map.endOfMap._1 should be(100)
      }
      "have endOfMap y" in {
        map.endOfMap._2 should be(100)
      }
      "have map" in {
        map.map should be (0)
      }
      "have Player1" in {
        map.name1 should be ("Flo")
      }
      "have Player2" in {
        map.name2 should be("Sascha")
      }

    map.setFX(1)
    "have fx" in {
      map.map should be (0)
    }
    "have Pos in Map" in {
      map.posInMap(Position(50,50)) should be (true)
    }
    "have no Pos in Map" in {
      map.posInMap(Position(-1,0)) should be (false)
    }
    }
  }
}