package de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.MapSize
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import de.htwg.se.Tank.model.gameComponent.gameBase.SmallMap

@RunWith(classOf[JUnitRunner])
class MapSizeSpec extends WordSpec with Matchers{
  val small = new SmallMap
  val big = new BigMap
  "A MapSiz" when {"new" should {
    "Small have a nice String" in {
      small.getName should be ("small")
    }
    "Big have a nice String" in {
      big.getName should be ("big")
    }

  }}
}
