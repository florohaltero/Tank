
case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y

case class Position( x: Int, y: Int) {
  def move(x:Int, y:Int): Position = copy(x,y)
}

val newPostion = Position(100, 100)
val newPostion2 = newPostion.move(200, 200)
newPostion2.x

val s = "Hallo"
val d = "Wie geht's?"
val newString = s.concat(", " + d)
print(newString)
var x = 0
def y(x:Int): Int = {
  if (1>0) {
    return x + 2
  }
  0
}
var e = y(5)
case class Player(name: String) {
  override def toString:String = name
}


case class CanonAngle(x: Double, y: Double) {
  def moveCanon(x:Double, y:Double): CanonAngle = copy(x,y)
}

case class Tank(pos: Position, lp: Int, canonAngle: CanonAngle) {
  def moveLeft(): Tank = {
    copy(pos.move(pos.x - 1, pos.y), lp, canonAngle)
  }
}
val r = Position(10,10)
var t = Tank(r,100, CanonAngle(10,10))
t = t.moveLeft()

t.pos.x
t.pos