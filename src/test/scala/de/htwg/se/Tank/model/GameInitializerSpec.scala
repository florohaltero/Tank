package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GameInitializerSpec extends WordSpec with Matchers{
  "A Game"  should {
    val newGame = GameInitializer
    "have Version" in {
      newGame.version should be (0.1)
    }
    "have name" in {
      newGame.name should be ("Tank")
    }

  }
}
