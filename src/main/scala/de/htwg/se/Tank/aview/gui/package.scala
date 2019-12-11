package de.htwg.se.Tank.aview

import de.htwg.se.Tank.aview.TUI
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Node, Scene}
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{BorderPane, FlowPane, HBox, StackPane, TilePane}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, Button, CheckBox, Label, Menu, MenuBar, MenuItem, SplitPane, Tab, TabPane}
import scalafx.scene.shape.{Box, Circle, Polyline, Rectangle}
import de.htwg.se.Tank.controller.Controller
import de.htwg.se.Tank.model.{Game, GameInit, Map, Position}
import scalafx.scene.canvas.Canvas
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.media.{AudioClip, Media, MediaPlayer}

import scala.swing.SplitPane

object SimpleScalaFXApp extends JFXApp {
  stage = new PrimaryStage {
    title = "Simple ScalaFX App"
    scene = new Scene {
      val rootPane = new BorderPane

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


      rootPane.top = menuBar
      val mainPane = new BorderPane
      val r1 = new Rectangle{
        height= 100
        width = 20
        fill = Yellow
      }
      val r2 = new Rectangle{
        height = 30
        width = 50
        fill = Black
      }
      //val node = new Node
      val box = new HBox{

        children.add(r1)
        children.add(r2)
        //children = Seq(r1,r2)
        //content = List(r1,r2)
      }

      mainPane.setCenter(box)
      rootPane.center = mainPane
      root = rootPane
    }
  }
}