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
      this.grad = out.grad
      that.grad = out.grad
    }

    out._backward = backward
    out
  }

  def *(that: Value): Value = {
    val out = new Value(this.data * that.data, scala.collection.immutable.Set(this, that), Some("*"))
    def backward(): Unit = {
      this.grad = out.grad * that.data
      that.grad = out.grad * this.data
    }

    out._backward = backward
    out
  }

  def **(that: Int): Value = {
    val out = new Value(scala.math.pow(this.data, that), scala.collection.immutable.Set(this), Some("power"))

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
