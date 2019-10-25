package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class MapSpec extends WordSpec with Matchers{
  private val x = 100
  private val y = 100
  "A Map" should {
    val map = Map(x, y)
    "have set" in {
      map.generate() should be(x , y)
    }
  }
}
