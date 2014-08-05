
class Base
class D1 extends Base
class D2 extends Base

class Cell[T](private var _x: T) {
  def x = _x
  def x_=(that: T) = _x = that
}

class Cell1[+T](val x: T) {
  def update[U >: T](that: U): Cell1[U] = new Cell1(that)
}


val c1: Cell1[D1] = new Cell1(new D1)
val c: Cell1[Base] = c1

c.x
val dd: Cell1[Base] = c.update(new D2)
c.x
