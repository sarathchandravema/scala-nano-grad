package com.nanograd.app

import scala.language.implicitConversions

class MLP(val numberOfInputs: Int, val numberOfOutputs: Seq[Int]) {

  private val sizes = Seq(numberOfInputs) ++ numberOfOutputs
  private val layers: Seq[Layer] = sizes.sliding(2).map{
    case List(a, b) => (a, b)
  }.map(x => new Layer(x._1, x._2)).toSeq

  def apply(inputs: Seq[Value]): Seq[Value] = {
    var x = inputs
    for (layer <- this.layers) {
      x = layer.apply(x)
    }
    x
  }

  def apply[T](inputs: Seq[T])(implicit num: Numeric[T]): Seq[Value] = {
    var x = this.layers.head.apply(inputs)
    for (layer <- this.layers.tail) {
      x = layer.apply(x)
    }
    x
  }

  def parameters(): Seq[Value] = this.layers.flatMap(_.parameters())
}
