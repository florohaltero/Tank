package de.htwg.se.Tank.model
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RoundSpec extends WordSpec with Matchers{
  "A Round" when { "new" should{
    val round = Round()
      "round time" in {
        round.roundtime() should be (Timer.apply(60000, false))
      }
  }}
}
