package com.nanograd.app

import scala.collection.mutable.ListBuffer


class Value (var data: Double,
             val children: scala.collection.immutable.Set[Value] = scala.collection.immutable.Set.empty[Value],
             private val _op: Option[String] = None,
             var label: Option[String] = None
            ) {

  override def toString: String = s"Value( data=${this.data} | ${this.label.getOrElse("")} | ${this.grad} )"

  val previous: Set[Value] = children
  val operation: String = _op.getOrElse("")

  var grad: Double = 0.0
  var _backward: () => Unit = () => ()

  def ==(that: Value): Boolean = this.data == that.data && this.label == that.label

  override def equals(that: Any): Boolean = that match {
    case that: Value => that.isInstanceOf[Value] && this == that
    case _ => false
  }

  def +(that: Value): Value = {
    val out = new Value(this.data + that.data, scala.collection.immutable.Set(this, that), Some("+"))

    def backward(): Unit = {
      this.grad += out.grad
      that.grad += out.grad
    }

    out._backward = backward
    out
  }

  def +[T: Numeric](that: T)(implicit num: Numeric[T]): Value = {
    val thatValue = new Value(num.toDouble(that))
    this + thatValue
  }

  def -(that: Value): Value = this + (that * -1)

  def -[T: Numeric](that: T)(implicit num: Numeric[T]): Value = {
    val thatValue = new Value(num.toDouble(that))
    this - thatValue
  }

  def *(that: Value): Value = {
    val out = new Value(this.data * that.data, scala.collection.immutable.Set(this, that), Some("*"))
    def backward(): Unit = {
      this.grad += out.grad * that.data
      that.grad += out.grad * this.data
    }

    out._backward = backward
    out
  }

  def *[T: Numeric](that: T)(implicit num: Numeric[T]): Value = {
    val thatValue = new Value(num.toDouble(that))
    this * thatValue
  }

  def /(that: Value): Value = this * (that**(-1))

  def /[T: Numeric](that: T)(implicit num: Numeric[T]): Value = {
    val thatValue = new Value(num.toDouble(that))
    this / thatValue
  }

  def **[@specialized(Double, Int, Long) T](that: T)(implicit num: Numeric[T]): Value = {
    val out = new Value(scala.math.pow(this.data, num.toDouble(that)), scala.collection.immutable.Set(this), Some("power"))

    def backward(): Unit = {
      this.grad += (scala.math.pow(this.data, num.toInt(that) - 1) * num.toInt(that)) * out.grad
    }

    out._backward = backward
    out
  }

  def tanh(): Value = {
    val x = this.data
    val t = (scala.math.exp(2*x) - 1) / (scala.math.exp(2*x) + 1)
    val out = new Value(t, scala.collection.immutable.Set(this), Some("tanh"))

    def backward(): Unit = {
      this.grad += out.grad * (1 - scala.math.pow(t, 2))
    }

    out._backward = backward
    out
  }

  def exp(): Value = {
    val x = this.data
    val out = new Value(scala.math.exp(x))

    def backward():Unit = {
      this.grad += out.grad
    }

    out._backward = backward
    out
  }

  def backward(): Unit = {

    var topo = new ListBuffer[Value]
    val visited: scala.collection.mutable.Set[Value] = scala.collection.mutable.Set()

    def buildTopo(v: Value): Unit = {
      if (!visited.contains(v)) {
        visited.add(v)
        v.children.foreach(buildTopo)
        topo += v
      }
    }
    buildTopo(this)
    this.grad = 1.0
    val reversed = topo.reverse
    reversed.foreach(_._backward())
  }
}

object Value {

  implicit class NumPlusValue[T](val n: T) extends AnyVal {

    def +(that: Value)(implicit num: Numeric[T]): Value = {
      val thisValue = new Value(num.toDouble(this.n))
      thisValue + that
    }

    def -(that: Value)(implicit num: Numeric[T]): Value = {
      val thisValue = new Value(num.toDouble(this.n))
      thisValue - that
    }

    def *(that: Value)(implicit num: Numeric[T]): Value = {
      val thisValue = new Value(num.toDouble(this.n))
      thisValue * that
    }

    def /(that: Value)(implicit num: Numeric[T]): Value = {
      val thisValue = new Value(num.toDouble(this.n))
      thisValue / that
    }
  }
}