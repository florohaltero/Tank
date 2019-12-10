
import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{FlowPane, HBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, CheckBox, Label}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map}

import scala.util.Random

object ScalaFXHelloWorld extends JFXApp {
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  val tui = new TUI(controller)
  stage = new PrimaryStage {
    width = 800
    height = 600
    scene = new Scene {
      fill = LightBlue
      val box = new HBox {
        padding = Insets(20)
        alignmentInParent = Pos.BaselineCenter
        children = new Text {
          text = "Tank"
          style = "-fx-font-size: 100pt"
          fill = new LinearGradient(
            stops = Stops(Black, SaddleBrown)
          )
          effect = new DropShadow {
            color = SaddleBrown
            radius = 50
            spread = 0.35
          }
        }

      }
      box.setAlignment(Pos.Center)
      val start = new Button("Start Game")
      start.layoutX = 350
      start.layoutY = 300
      val end = new Button("End Game")
      end.layoutX = 350
      end.layoutY = 400
      start.onAction = (e:ActionEvent) => {
        createMapGUI()
      }
      content = List(start, end, box)
    }
  }

  def createMapGUI() = {
    stage = new PrimaryStage {
      title = "Map"
      width = 800
      height = 600
      scene = new Scene {
        val map = Map
        val gameinit = GameInit
        val seq : Seq[Double] = map.getFxDouble()
        val line : Polyline = Polyline(map.getFxDouble():_*)
        line.layoutX = 0
        line.layoutY = 300

        //line.getPoints.addAll(new Tuple2[Double, Double] = {
        //})
        gameinit.setMapSettings(0, "Sascha", "Flo")
        content = line
      }
    }
  }
}