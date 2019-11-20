package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.PrivateMethodTester._
@RunWith(classOf[JUnitRunner])
class MapSpec extends WordSpec with Matchers {
  private val x = 100
  private val y = 100
  private val x1 = 20
  private val y1 = 10
  var fx: Double => Double = (x:Double) => 5*x
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
      "have f(x)" in {
        fx(2) should be (10)
      }

    map.setFX(0)
    "have fx" in {
      map.map should be (0)
    }
    "have Pos in Map" in {
      map.posInMap(Position(50,50)) should be (true)
    }
    "have no Pos in Map" in {
      map.posInMap(Position(-1,0)) should be (false)
    }
    "have Number of moves" in {
      map.moves should be (5)
    }
    }
  }
  "A map2" when {"generated Position Player1" should {
    val map = Map((0,0),(3,3), 0, "Flo", "Sascha")
    map.p1.pos = map.generatePos(1,1)
    "changed Player" in {
      val changePlayer = PrivateMethod[String]('changePlayer)
      map invokePrivate changePlayer()
      map.activePlayer should be (map.p2)
      map invokePrivate changePlayer()
      map.activePlayer should be (map.p1)
    }
    "check active Player" in{
      map.moves = 0
      val checkActivePlayer = PrivateMethod[String]('checkActivePlayer)
      map invokePrivate checkActivePlayer()
      map.activePlayer should be (map.p2)
    }
    map.setFX(1)
    "have f1" in {
      map.map should be (0)
    }
    "have moveLeft" in {
      map.moveLeft()
      map.moves should be (4)
    }
    "have moveLeft in Map" in {
      map.moveLeft()
      map.p1.pos.x should be (1)
    }
    "have moveRight" in {
      map.moveRight()
      map.moves should be (2)
    }

  }

  }
}