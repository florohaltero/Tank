package de.htwg.se.Tank.aview.gui

import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, FlowPane, HBox, StackPane, TilePane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Button, CheckBox, Label, Menu, MenuBar, MenuItem}
import scalafx.scene.shape.{Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map}
import scalafx.scene.control.Alert.AlertType

import scala.collection.mutable.ListBuffer
//noinspection ScalaStyle
object GUI extends JFXApp {

  final val WIDTH : Double = 700
  final val HEIGHT : Double = 500
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1*XScale,d._2*YScale))
    l.toList
  }

  val border = new BorderPane{
    top = createMenuBar
    center = createTitle
    bottom = createButton
  }

  stage = new PrimaryStage {
    title = "Tank Menu"
    scene = new Scene(WIDTH, HEIGHT) {
      root = border
    }
  }
    private def createMenuBar : MenuBar = new MenuBar() {
      val menuBar = new MenuBar
      val fileMenu = new Menu("New")
      val newGame = new MenuItem("New")
      val options = new Menu("Options")
      val exitGame = new MenuItem("Exit")
      val undo = new MenuItem("Undo")
      val redo = new MenuItem("Redo")
      options.items = List(undo, redo)
      fileMenu.items = List(newGame, exitGame)
      menuBar.menus = List(fileMenu, options)
      menuBar
    }

  private def createTitle : StackPane = new StackPane(){
    layoutY = 50
    style = "-fx-background-color: green"
    children = Seq(
      new Text() {
        text = "Tank"
        style = "-fx-font-size: 60pt; "
        alignmentInParent = Pos.TopCenter
        fill = Black
      })
    }

  private def createButton : HBox = new HBox(){
    //prefTileWidth = 300

    style = "-fx-background-color: green"
    prefHeight = 100
    children = List(
      new Button("New Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterRight
        onAction = (e: ActionEvent) => println("a")//controller.setDefaultGame()
      },
      new Button("Exit Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterLeft
        onAction = (e: ActionEvent) => System.exit(0)
      }
    )
  }

  def createMapGUI() = {
    stage = new PrimaryStage{
      title = "Map"
      width = 800
      height = 600
      scene = new Scene {
        val line : Polyline = Polyline(Map.getFxDouble():_*)
        line.layoutX = 0
        line.layoutY = 300
        //line.getPoints.addAll(new Tuple2[Double, Double] = {
        //})
        content = line
      }
    }
  }

      /*
      val border = new BorderPane{
        top = menuBar
        bottom = showText()
      }*/

      //content = List(rootPane,h)
      /*content = new HBox {
        padding = Insets(20)
        children = new Text(){
          text = "Tank"
          style = "-fx-font-size: 80pt; -fx-alignment: CENTER;"
          fill = Black
          TextAlignment.CENTER


        }
        children = Seq(
          new Text {
            text = "Hello "
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          },
          new Text {
            text = "World!!!"
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(Cyan, DodgerBlue)
            )
            effect = new DropShadow {
              color = DodgerBlue
              radius = 25
              spread = 0.25
            }
          }
        )
      }*/


}