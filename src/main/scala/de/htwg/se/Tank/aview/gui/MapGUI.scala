package de.htwg.se.Tank.aview.gui


import de.htwg.se.Tank.controller.controllerComponent.{ControllerInterface, Fire, Hit, NewGame, UpdateMap}
import de.htwg.se.Tank.controller.controllerComponent.controllerBaseImpl.controller.Controller
import de.htwg.se.Tank.model.gameComponent.gameBase
import de.htwg.se.Tank.model.gameComponent.gameBase.{Calc, GameInit, Map}
import de.htwg.se.Tank.model.playerComponent.playerBase.Position
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.layout.Background
import scalafx.Includes._
import scalafx.animation.{Animation, PathTransition}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.event._
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.effect.DropShadow
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize, BorderPane, HBox, StackPane, TilePane, VBox}
import scalafx.scene.media.AudioClip
//import scalafx.scene.media.AudioClip
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.shape.{Circle, Polygon, Polyline, Rectangle}
import scalafx.scene.text.Text
import scalafx.stage.StageStyle
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
    case event: Hit => updateText
  }

  final val WIDTH: Double = 1000
  final val HEIGHT: Double = 600
  final val XScale: Double = WIDTH / Map.endOfMap._1
  final val YScale: Double = HEIGHT / Map.endOfMap._2

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

  def createOverlay() : Unit = {
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

        val mapNum = new ChoiceBox(FXCollections.observableArrayList(0, 1)) {
          accessibleHelp = "Test"
        }

        val start = new Button("New Game")
        val end = new Button("Leave Game")
        val load_game = new Button("Load Game") {
          onAction = (e: ActionEvent) => controller.load
        }


        start.onAction = (e: ActionEvent) => {
          controller.setGame("Standard", mapNum.getSelectionModel.getSelectedItem, "small", player1textField.text(), player2textField.text())
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
        rootPane.setStyle("-fx-background-image: url(de/htwg/se/Tank/aview/gui/Game-Tank-Background-Free-Picture.jpg); -fx-background-size: auto 100%;")
        //rootPane.setBackground(new Background(new BackgroundImage(new Image("D:\\Wichtig\\OneDrive\\Studium\\3.Semester\\SE\\Tank\\src\\main\\scala\\de\\htwg\\se\\Tank\\aview\\gui\\Game-Tank-Background-Free-Picture.jpg",32,32,false,true),
        //  BackgroundRepeat.Repeat, BackgroundRepeat.NoRepeat, BackgroundPosition.Default, BackgroundSize.Default)))
        rootPane.center = vbox
        playerbox1.children.addAll(player1, player1textField)
        playerbox2.children.addAll(player2, player2textField)
        val hbuttons = new HBox()
        hbuttons.alignment = Pos.Center
        hbuttons.children.addAll(start, end)
        vbox.children.addAll(text, playerbox1, playerbox2, mapNum, hbuttons, load_game)
        playerbox1.alignment = Pos.TopCenter
        playerbox2.alignment = Pos.TopCenter
        vbox.alignment = Pos.TopCenter
        //content = List(start, end, text, player1textField, player2textField, player1, player2)
        root = rootPane
      }
    }
    stage.getIcons.add(new Image("de/htwg/se/Tank/aview/gui/tank.png"))
    stage.resizable = false
  }


  def getGUIScale(d: List[((Double), (Double))]): List[Double] = {
    var l: ListBuffer[Double] = ListBuffer.empty
    d.foreach(d => l.append(d._1 * XScale, (Map.endOfMap._2 - d._2) * YScale))
    l.toList
  }

  def getPosinGUIScale(value: Position): ((Double), (Double)) = {
    (value.x * XScale, (Map.endOfMap._2 - value.y) * YScale)
  }

  def createMap() {
    stage = new PrimaryStage {
      rootPane = new BorderPane
      mainPane = new BorderPane()
      bottomBox = new HBox()
      title = "Tank"
      scene = new Scene {
        createMenuBar
        createMapShapes
        createMapButtons
        createTitle
        showFireLine
        rootPane.center = mainPane
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
      onAction = (e: ActionEvent) => createOverlay
    }
    val options = new Menu("Options")
    val exitGame = new MenuItem("Exit") {
      onAction = (e: ActionEvent) => System.exit(0)
    }
    val save = new MenuItem("Save") {
      onAction = (e: ActionEvent) => controller.save
    }
    val load = new MenuItem("Load") {
      onAction = (e: ActionEvent) => controller.load
    }
    val undo = new MenuItem("Undo") {
      onAction = (e: ActionEvent) => controller.undo
    }
    val redo = new MenuItem("Redo") {
      onAction = (e: ActionEvent) => controller.redo
    }
    options.items = List(undo, redo, save, load)
    fileMenu.items = List(newGame, exitGame)
    menuBar.menus = List(fileMenu, options)
    rootPane.top = menuBar
  }

  def createMapShapes : Unit = {
    mainPane = new BorderPane()
    var lb: mutable.Buffer[Double] = getGUIScale(gameBase.Map.getFXList(true)).toBuffer
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

  def createMapButtons: Unit = {
    val moveLeft = new Button("Move Left") {
      onAction = (e: ActionEvent) => {
        controller.moveLeft()
      }
    }
    val moveRight = new Button("Move Right") {
      onAction = (e: ActionEvent) => {
        controller.moveRight()
      }
    }
    val angleUp = new Button("Cannon +") {
      onAction = (e: ActionEvent) => {
        controller.moveAngleUp()
      }
    }

    val angelDown = new Button("Cannon -") {
      onAction = (e: ActionEvent) => {
        controller.moveAngleDown()
      }
    }
    val power = new TextField {
      text = Map.getActivePlayer.power.toString
    }

    val fire = new Button("Fire!") {
      onAction = (e: ActionEvent) => {
        controller.shoot(power.text().toInt)
      }
      onKeyTyped = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Space => {
            controller.shoot(power.text().toInt)
          }
          case _ =>
        }
      }
    }
    fire.isFocused
    val changePlayer = new Button("Change Player") {
      onAction = (e: ActionEvent) => {
        controller.changePlayer()
      }
    }
    buttonEvents(power.getText.toInt)
    bottomBox.children.addAll(moveLeft, moveRight, angleUp, angelDown, power, fire, changePlayer)
  }

  private def buttonEvents(power : Int) : Unit = {
    bottomBox.onKeyPressed = (ke: KeyEvent) => {
      ke.code match {
        case KeyCode.F => controller.shoot(power)
        case KeyCode.A => controller.moveLeft()
        case KeyCode.D => controller.moveRight()
        case KeyCode.W => controller.moveAngleUp()
        case KeyCode.S => controller.moveAngleDown()
        case KeyCode.P => controller.changePlayer()
        case KeyCode.Plus => controller.powerUp()
        case KeyCode.Minus => controller.powerDown()
        case _ =>
      }
    }
  }

  private def createTitle: Unit = {
    val topBox = new HBox() {
      val infoPlayer1 = new Text() {
        text = gameBase.Map.p1.toString
      }
      val infoPlayer2 = new Text() {
        text = gameBase.Map.p2.toString
      }
      val infoControls = new Text() {
        text = "controls: \n" +
        "Links/Rechts: a/d\n" +
        "Winkel: w/s\n" +
        "Power: +/-\n" +
        "Schießen: f\n" +
        "Spielerwechsel: p"
      }
      val infoPlayerBox = new VBox {
        children.addAll(infoPlayer1, infoPlayer2)
      }
      val infoControlBox = new VBox {
        children.addAll(infoControls)
      }
      children.addAll(infoPlayerBox, infoControlBox)
    }
    mainPane.children.add(topBox)
  }

  def showFire: Unit = {
    val shootLine = Polyline(getGUIScale(Calc.shootCalc(true)): _*)
    val mun = Circle(5, Black)
    val fire = new PathTransition(new Duration(3000), shootLine)
    fire.setNode(mun)
    fire.setPath(shootLine)
    fire.playFromStart()

    val image = new Image("file:explosion.gif")
    val explosion = new ImageView(image)

    fire.setOnFinished(e => {
      if (Map.winner != null)
        showWinnerGif
    })
    mainPane.children.addAll(mun)
  }

  def showFireLine : Unit = {
    val shootLine = Polyline(getGUIScale(Calc.shootCalc(false).slice(0, 50)): _*)
    mainPane.children.add(shootLine)
  }

  def updateText: Unit = {
    createMapShapes
    createTitle
  }

  def winnerdialog : Unit = {
    val alert = new Alert(AlertType.Information) {
      initOwner(stage)
      title = "Winner"
      headerText = "Press OK for a New Game!"
      contentText = "Winner:" + Map.winner.name
//      jfxBackgroundImage2sfx(new BackgroundImage(new Image("de/htwg/se/Tank/aview/gui/giphy.gif",32,32,false,true),
//        BackgroundRepeat.Repeat, BackgroundRepeat.NoRepeat, BackgroundPosition.Default,
//        BackgroundSize.Default))
    }
    val result = alert.showAndWait()
    result match {
      case Some(ButtonType.OK) => showWinnerGif
    }
  }

  def showWinnerGif: Unit = {
    stage = new PrimaryStage {
      rootPane = new BorderPane
      mainPane = new BorderPane()
      bottomBox = new HBox()
      title = "Winner"
      scene = new Scene {
        createMenuBar
        rootPane.center = mainPane
        rootPane.bottom = bottomBox
        rootPane.setStyle("-fx-background-image: url(de/htwg/se/Tank/aview/gui/giphy.gif); -fx-background-size: 100% Auto;")
        mainPane.prefWidth = WIDTH
        mainPane.prefHeight = HEIGHT
        root = rootPane
      }
    }
  }

}