
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
import scalafx.scene.control.{Alert, Button, CheckBox, Label}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map}
import scalafx.scene.control.Alert.AlertType

import scala.util.Random

object ScalaFXHelloWorld extends JFXApp {
  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  val tui = new TUI(controller)
  stage = new PrimaryStage {
    width = 800
    height = 600
    scene = new Scene {
      fill = LightBlue
      val text = new Text {
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
      text.layoutX = 235
      text.layoutY = 200


      val start = new Button("Start Game")
      start.scaleX = 2
      start.scaleY = 2
      start.layoutX = 350
      start.layoutY = 300
      val end = new Button("End Game")
      end.scaleX = 2
      end.scaleY = 2
      end.layoutX = 350
      end.layoutY = 400
      start.onAction = (e:ActionEvent) => {
        createMapGUI()
      }
      end.onAction = (e:ActionEvent) => {
        val alert = new Alert(AlertType.Confirmation)
        alert.setTitle("")
        alert.setHeaderText("Wirklich Verlassen?")
        alert.showAndWait()
        stage.close()
      }
      content = List(start, end, text)
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