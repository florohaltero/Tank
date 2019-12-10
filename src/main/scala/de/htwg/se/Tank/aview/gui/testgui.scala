package de.htwg.se.Tank.aview.gui

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Pos
import scalafx.scene.{Node, Scene}
import scalafx.scene.effect.{DropShadow, InnerShadow}
import scalafx.scene.layout.{BorderPane, FlowPane, Region, StackPane, TilePane, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Ellipse
import scalafx.scene.text.{Font, FontWeight, Text}


object BorderLayoutExample extends JFXApp {

  val borderPane = new BorderPane {
    top = createTitle
    center = createBackground
    //bottom = createScoreBoxes
  }

  stage = new PrimaryStage {
    scene = new Scene(600, 400) {
      // Assign borderPane directly to `root` to avoid layout issues.
      // If assigned to `content` there will be `Group` node at root that interferes with automatic rescaling.
      root = borderPane
    }
  }


  //---------------------------------------------------------------------------


  private def createTitle = new TilePane {
    snapToPixel = false
    children = List(
      new StackPane {
        style = "-fx-background-color: black"
        children = new Text("ScalaFX") {
          font = Font.font(null, FontWeight.Bold, 18)
          fill = Color.White
          alignmentInParent = Pos.CenterRight
        }
      },
      new Text("Reversi") {
        font = Font.font(null, FontWeight.Bold, 18)
        alignmentInParent = Pos.CenterLeft
      })
    prefTileHeight = 40
    prefTileWidth <== parent.selectDouble("width") / 2
  }


  private def createBackground = new Region {
    style = "-fx-background-color: radial-gradient(radius 100%, white, gray)"
  }


}