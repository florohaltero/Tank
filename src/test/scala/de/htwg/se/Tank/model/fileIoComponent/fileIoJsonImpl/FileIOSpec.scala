package de.htwg.se.Tank.model.fileIoComponent.fileIoJsonImpl
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase.Game
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
case class FileIOSpec() extends WordSpec with Matchers{
  val game = Game("Standard", 2,"small", "Flo", "Sasch")
  val controller = new Controller(game)
  val file = new FileIO
  "File" when {"new JSON" should {
    "load" in {
      file.load should not be (null)
    }
    "write" in {
      file.save(game)
      true should be (true)
    }
  }}
}
