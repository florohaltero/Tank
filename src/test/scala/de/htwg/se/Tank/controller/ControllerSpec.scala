package de.htwg.se.Tank.controller

import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.Game
import de.htwg.se.Tank.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val game = new Game("Standard", 0, "Flo", "Sasch")
      val controller = new Controller(game)
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.setGame("Standard", 0, "Flo", "Sasch")
        observer.updated should be(true)
        controller.game.mapObject.p1.name should be ("Flo")
        controller.game.mapObject.p2.name should be ("Sasch")
      }
      "notify its Observer after move left" in {
        controller.moveLeft()
        observer.updated should be(true)

      }
      "notify its Observer after move right" in {
        controller.moveRight()
        observer.updated should be(true)
      }
    }
  }
}