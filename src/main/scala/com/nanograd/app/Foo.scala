package com.nanograd.app

class Foo(val b: BigDecimal) {
  override def toString: String = s"Foo: $b"
  def +(f: Foo): Foo = new Foo(b + f.b)
//  def +(f: Int):Foo = new Foo(b + f)
  def +[T: Numeric](that: T): Foo = {
    val nums = implicitly[Numeric[T]]
    val thatValue = new Foo(nums.toDouble(that))
    this + thatValue
  }
}



object Foo {

  implicit class IntPlusMyInt(val n: Int) extends AnyVal {
    def +(myInt: Foo): Foo = new Foo(n + myInt.b)
  }

//  implicit class NumPlusValue[T](val n: T)(implicit num: Numeric[T]) extends AnyVal {
//    //  implicit class NumPlusValue[T](val n: T)(implicit num: Numeric[T]) {
//
//    def +(that: Value): Value = {
//      val thisValue = new Value(num.toDouble(this.n))
//      thisValue + that
//    }
//  }

  implicit val numericFoo: Numeric[Foo] = new Numeric[Foo] {
    override def plus(x: Foo, y: Foo): Foo = new Foo(x.b + y.b)
    override def minus(x: Foo, y: Foo): Foo = new Foo(x.b - y.b)
    override def times(x: Foo, y: Foo): Foo = new Foo(x.b * y.b)
    override def negate(x: Foo): Foo = new Foo(-x.b)
    override def fromInt(x: Int): Foo = new Foo(x)
    override def toInt(x: Foo): Int = x.b.toInt
    override def toLong(x: Foo): Long = x.b.toLong
    override def toFloat(x: Foo): Float = x.b.toFloat
    override def toDouble(x: Foo): Double = x.b.toDouble
    override def compare(x: Foo, y: Foo): Int = x.b.compare(y.b)

    override def parseString(str: String): Option[Foo] = Some(new Foo(str.toDouble))
  }
}
