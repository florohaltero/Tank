import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{Background, BackgroundFill, BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize, BorderPane, CornerRadii, FlowPane, HBox, StackPane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Button, ButtonType, CheckBox, Label, TextField}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map, PlayerFactory}
import scalafx.scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView}

import scala.collection.mutable.ListBuffer
import scala.swing.TextArea
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
      text.layoutX = 350
      text.layoutY = 200

      val player1textField = new TextField() {
      }
      val player2textField = new TextField
      player1textField.layoutX = 425
      player1textField.layoutY = 275
      player2textField.layoutX = 425
      player2textField.layoutY = 325

      val player1 = new Text {
        text = "Player 1: "
      }
      val player2 = new Text {
        text = "Player 2: "
      }

      player1.layoutX = 360
      player1.layoutY = 290
      player2.layoutX = 360
      player2.layoutY = 340
      player1.setScaleX(1.35)
      player1.setScaleY(1.35)
      player2.setScaleX(1.35)
      player2.setScaleY(1.35)

      val start = new Button("New Game")
      start.scaleX = 1.65
      start.scaleY = 1.65
      start.layoutX = 460
      start.layoutY = 400
      val end = new Button("Leave Game")
      end.scaleX = 1.5
      end.scaleY = 1.5
      end.layoutX = 460
      end.layoutY = 480
      start.onAction = (e:ActionEvent) => {
        controller.setGame("Standard", 0, player1textField.text(), player2textField.text())
        createMapGUI
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
      content = List(start, end, text, player1textField, player2textField, player1, player2)
    }
  }


  def createMapGUI() = {
    stage = new PrimaryStage {
      title = "Map"
      scene = new Scene (WIDTH, HEIGHT) {
        //fill = SandyBrown
        val seq: Seq[Double] = Map.getFxDouble()
        val guiscale = getGUIScale(Map.getFXList(true))
        val line: Polyline = Polyline(guiscale: _*)
        line.layoutX = 0
        line.layoutY = 0
        val player1 = new Text {
          text = PlayerFactory.createPlayer1(Map.p1.name).toString
        }
        player1.layoutX = 10
        player1.layoutY = 20

        val player2 = new Text {
          text = PlayerFactory.createPlayer1(Map.p2.name).toString
        }
        player2.layoutX = 10
        player2.layoutY = 50
        content = List(line, player1, player2)
      }
    }
  }

  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    val l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }


}