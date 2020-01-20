package de.htwg.se.Tank.aview.gui
import de.htwg.se.Tank.Tank.controller
import de.htwg.se.Tank.model.playerComponent.playerBase.{PlayerFactory, Position}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MapGUISpec extends WordSpec with Matchers {
  "A GUI" when {
    "new" should {
      val gui = new MapGUI(controller)

    }
  }
}
