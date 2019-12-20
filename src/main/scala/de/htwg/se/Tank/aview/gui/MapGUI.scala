package de.htwg.se.Tank.aview.gui


import de.htwg.se.Tank.controller.controllerComponent.{ControllerInterface, Fire, NewGame, UpdateMap}
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.{Calc, GameInit}
import de.htwg.se.Tank.model.playerComponent.playerBase.Position
import javafx.collections.FXCollections
import scalafx.Includes._
import scalafx.animation.PathTransition
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event._
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.effect.DropShadow
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{BorderPane, HBox, TilePane, VBox}
import scalafx.scene.media.AudioClip
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Polygon, Polyline, Rectangle}
import scalafx.scene.text.Text
import scalafx.util.Duration

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.swing.Reactor
//noinspection ScalaStyle
class MapGUI(controller: ControllerInterface) extends JFXApp with Reactor {

  listenTo(controller)
  reactions += {
    case event: NewGame => createOverlay()
    case event: UpdateMap => createMap()
    case event: Fire => showFire
  }

  final val WIDTH : Double = 1000
  final val HEIGHT : Double = 600
  final val XScale: Double = WIDTH/GameInit.MAP_X2
  final val YScale: Double = HEIGHT/GameInit.MAP_Y2

  var rootPane = new BorderPane
  //val controller = new Controller(Game("Standard", 0, "Flo", "Sasch"))
  //GameInit.setMapSettings(0, "Sascha", "Flo")
  var map = gameBase.Map
  var mainPane = new BorderPane
  var bottomBox = new HBox

  val path = "masterofpuppets.mp3"
  val res = getClass.getResource(path)
  val clip = new AudioClip(res.toString)
  clip.play()
  createOverlay()

  def createOverlay() = {
    stage = new PrimaryStage {
      scene = new Scene {
        fill = LightBlue
        rootPane = new BorderPane
        val vbox = new VBox()
        val playerbox1 = new HBox()
        val playerbox2 = new HBox()
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

        val player1textField = new TextField()
        val player2textField = new TextField

        val player1 = new Text {
          text = "Player 1: "
        }
        val player2 = new Text {
          text = "Player 2: "
        }

        val mapNum = new ChoiceBox(FXCollections.observableArrayList(0, 1)){
          accessibleHelp = "Test"
        }

        val start = new Button("New Game")
        /*
        text.layoutX = 70
        text.layoutY = 130
        player1textField.layoutX = 140
        player1textField.layoutY = 190
        player2textField.layoutX = 140
        player2textField.layoutY = 245
        player1.layoutX = 75
        player1.layoutY = 205
        player2.layoutX = 75
        player2.layoutY = 255
        player1.setScaleX(1.35)
        player1.setScaleY(1.35)
        player2.setScaleX(1.35)
        player2.setScaleY(1.35)
        start.scaleX = 1.65
        start.scaleY = 1.65
        start.layoutX = 175
        start.layoutY = 300
        end.scaleX = 1.5
        end.scaleY = 1.5
        end.layoutX = 175
        end.layoutY = 350

         */
        val end = new Button("Leave Game")

        start.onAction = (e: ActionEvent) => {
          controller.setGame("Standard", mapNum.getSelectionModel.getSelectedItem, player1textField.text(), player2textField.text())
        }
        end.onAction = (e: ActionEvent) => {
          val alert = new Alert(AlertType.Confirmation)
          alert.setTitle("")
          alert.setHeaderText("Wirklich Verlassen?")
          val result = alert.showAndWait()
          result match {
            case Some(ButtonType.OK) => stage.close()
            case _ =>
          }
        }

        rootPane.prefHeight = 350
        rootPane.setStyle("-fx-background-color: green")
        rootPane.center = vbox
        playerbox1.children.addAll(player1,player1textField)
        playerbox2.children.addAll(player2,player2textField)
        val hbuttons = new HBox()
        hbuttons.alignment = Pos.Center
        hbuttons.children.addAll(start,end)
        vbox.children.addAll(text,playerbox1,playerbox2,mapNum,hbuttons)
        playerbox1.alignment = Pos.TopCenter
        playerbox2.alignment = Pos.TopCenter
        vbox.alignment = Pos.TopCenter
        //content = List(start, end, text, player1textField, player2textField, player1, player2)
        root = rootPane
      }
    }
  }


  def getGUIScale(d : List[((Double),(Double))]) : List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (GameInit.MAP_Y2 - d._2) * YScale))
    l.toList
  }

  def getPosinGUIScale(value : Position) : ((Double),(Double)) = {
    (value.x * XScale, (GameInit.MAP_Y2 - value.y) * YScale)
  }

  def removeContent: Unit ={


  }

  def createMap() {
    stage = new PrimaryStage{
      rootPane = new BorderPane
      mainPane = new BorderPane()
      bottomBox = new HBox()
      title = "Map"
      scene = new Scene {
        createMenuBar
        createMapShapes
        createMapButtons
        createTitle
        rootPane.center =  mainPane
        rootPane.bottom = bottomBox
        //rootPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)")
        rootPane.setStyle("-fx-background-color: lightblue")
        root = rootPane
      }
    }
  }

  private def createMenuBar = {
    val menuBar = new MenuBar
    val fileMenu = new Menu("New")
    val newGame = new MenuItem("New") {
     //onAction = (e:ActionEvent) => createOverlay
    }
    val options = new Menu("Options")
    val exitGame = new MenuItem("Exit") {
      onAction = (e:ActionEvent) => System.exit(0)
    }
    val undo = new MenuItem("Undo") {
      onAction = (e:ActionEvent) => controller.undo
    }
    val redo = new MenuItem("Redo") {
      onAction = (e:ActionEvent) => controller.redo
    }
    options.items = List(undo, redo)
    fileMenu.items = List(newGame, exitGame)
    menuBar.menus = List(fileMenu, options)
    rootPane.top= menuBar
  }

  def createMapShapes = {
    var lb : mutable.Buffer[Double] = getGUIScale(gameBase.Map.getFXList(true)).toBuffer
    lb.append(WIDTH)
    lb.append(HEIGHT)
    lb.append(0)
    lb.append(HEIGHT)
    val guiscale = lb.toList
    val line = Polygon(guiscale: _*)
    line.fill = Green
    line.layoutX = 0
    line.layoutY = 0
    val t1 = new Rectangle {
      val p1 = getPosinGUIScale(gameBase.Map.p1.pos)
      height = 10
      width = 20
      x = p1._1 - 0.5 * width()
      y = p1._2 - 0.5 * height()
      fill = Blue
    }
    val t2 = new Rectangle {
      val p2 = getPosinGUIScale(gameBase.Map.p2.pos)
      height = 10
      width = 20
      x = p2._1 - 0.5 * width()
      y = p2._2 - 0.5 * height()
      fill = Red
    }
    mainPane.prefWidth = WIDTH
    mainPane.prefHeight = HEIGHT
    mainPane.children.addAll(line, t1, t2)

  }

  def createMapButtons = {
    val moveLeft = new Button("Move Left") {
      onAction = (e: ActionEvent) => {
        controller.moveLeft()
      }
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.A => controller.moveLeft()
          case _ =>
        }
      }
    }

    val moveRight = new Button("Move Right") {
      onAction = (e: ActionEvent) => {
        controller.moveRight()
      }
      onKeyPressed = (k: KeyEvent) => {
        k.code match {
          case KeyCode.D => controller.moveRight()
          case _ =>
        }
      }
    }
    val angleUp = new Button("Cannon +") {
      onAction = (e: ActionEvent) => {
        controller.moveAngleUp()
      }
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.W => controller.moveAngleUp()
          case _ =>
        }
      }
    }

    val angelDown = new Button("Cannon -") {
      onAction = (e: ActionEvent) => {
        controller.moveAngleDown()
      }
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.S => controller.moveAngleDown()
          case _ =>
        }
      }
    }
    val power = new TextField{
      text = "30"
    }

    val fire = new Button("Fire!"){
      onAction = (e:ActionEvent) => {
        controller.shoot(power.text().toInt)
      }
      onKeyTyped = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Space => controller.shoot(power.text().toInt)
          case _ =>
        }
      }
    }
    val changePlayer = new Button("Change Player"){
      onAction = (e:ActionEvent) => {
        controller.changePlayer()
      }
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.P => controller.changePlayer()
          case _ =>
        }
      }
    }
    bottomBox.children.addAll(moveLeft,moveRight,angleUp,angelDown,power,fire,changePlayer)
  }

  private def createTitle : VBox = new VBox{
//    val name = new Text() {
//      text = "Tank"
//      style = "-fx-font-size: 60pt; "
//      alignmentInParent = Pos.TopCenter
//      fill = Black
//    }

    val infoPlayer1 = new Text(){
      text = gameBase.Map.p1.toString
    }
    val infoPlayer2 = new Text(){
      text = gameBase.Map.p2.toString
    }
    val vbox = new VBox{
      children.addAll(infoPlayer1,infoPlayer2)
    }
    mainPane.children.add(vbox)
  }

  def showFire = {
    val shootLine = Polyline(getGUIScale(Calc.shootCalc):_*)
    val mun = Circle(5,Black)
    val fire = new PathTransition(new Duration(3000),shootLine)
    //fire.node = mun
    fire.setNode(mun)
    //fire.setRate(10)
    fire.setPath(shootLine)
    fire.playFromStart()
    mainPane.children.addAll(mun)
  }

  private def createButton : TilePane = new TilePane(){
    //prefTileWidth = 300

    style = "-fx-background-color: green"
    prefHeight = 100
    children = List(
      new Button("New Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterRight
        onAction = (e: ActionEvent) => createMap()
      },
      new Button("Exit Game") {
        scaleX = 2
        scaleY = 2
        alignmentInParent = Pos.CenterLeft
        onAction = (e: ActionEvent) => System.exit(0)
      })

  }

}