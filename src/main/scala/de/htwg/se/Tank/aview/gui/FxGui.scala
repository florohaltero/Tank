import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{FlowPane, HBox, StackPane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Button, ButtonType, CheckBox, Label, TextField}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView}

import scala.collection.mutable.ListBuffer
import scala.util.Random

object FxGui extends JFXApp {
  final val WIDTH : Double = 1000
  final val HEIGHT : Double = 600
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  val tui = new TUI(controller)
  stage = new PrimaryStage {
    width = WIDTH
    height = HEIGHT
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

      val start = new Button("New Game")
      start.scaleX = 2
      start.scaleY = 2
      start.layoutX = 350
      start.layoutY = 300
      val end = new Button("Leave Game")
      end.scaleX = 1.9
      end.scaleY = 1.9
      end.layoutX = 350
      end.layoutY = 400
      start.onAction = (e:ActionEvent) => {
        createMapGUI()
      }
      end.onAction = (e:ActionEvent) => {
        val alert = new Alert(AlertType.Confirmation)
        alert.setTitle("")
        alert.setHeaderText("Wirklich Verlassen?")
        val result = alert.showAndWait()
        result match {
          case Some(ButtonType.OK) => stage.close()
          case _ =>
        }

      }
      content = List(start, end, text)
    }
  }

  def createMapGUI() = {
    stage = new PrimaryStage {
      title = "Map"
      width = WIDTH
      height = HEIGHT
      scene = new Scene (WIDTH, HEIGHT) {
        val map = Map
        val gameinit = GameInit
        val seq : Seq[Double] = map.getFxDouble()
        val guiscale = getGUIScale(map.getFXList(true))
        val line : Polyline = Polyline(guiscale:_*)
        line.layoutX = 0
        line.layoutY = 0
        gameinit.setMapSettings(0, "Sascha", "Flo")
        content = line
      }
    }
  }

  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    val l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }
}