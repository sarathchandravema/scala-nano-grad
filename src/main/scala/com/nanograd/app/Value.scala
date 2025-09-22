package com.nanograd.app


class Value (var data: Double,
             val children: scala.collection.immutable.Set[Value] = scala.collection.immutable.Set.empty[Value],
             private val _op: Option[String] = None,
             var label: Option[String] = None
            ) {

  override def toString: String = s"Value( data=${this.data} | ${this.label.getOrElse("")} | ${this.grad} )"

  val previous: Set[Value] = children
  val operation: String = _op.getOrElse("")

  var grad: Double = 0.0

  def ==(that: Value): Boolean = this.data == that.data && this.label == that.label

  override def equals(that: Any): Boolean = that match {
    case that: Value => that.isInstanceOf[Value] && this == that
    case _ => false
  }

  def +(that: Value): Value = {
    val out = new Value(this.data + that.data, scala.collection.immutable.Set(this, that), Some("+"))

    out
  }

  def *(that: Value): Value = {
    val out = new Value(this.data * that.data, scala.collection.immutable.Set(this, that), Some("*"))

    out
  }

  def **(that: Int): Value = {
    val out = new Value(scala.math.pow(this.data, that))

    out
  }
}
