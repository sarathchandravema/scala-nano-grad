package com.nanograd.app

import scala.util.Random

class Neuron(val numberOfInputs: Int) {

  var weights:Seq[Value] = (0 until numberOfInputs).map(x => new Value(Random.nextDouble()))
  var bias: Value = new Value(Random.nextDouble())

  override def toString: String = s"Neuron{$numberOfInputs}"

  def apply[T](input: Seq[T])(implicit num: Numeric[T]): Value = {
    val inputValues = input.map(num.toDouble)
    inputValues.zip(this.weights).map(x => x._1 * x._2).fold(this.bias)(_ + _).tanh()
  }

  def apply(input: Seq[Value]): Value = {
    input.zip(this.weights).map(x => x._1 * x._2).fold(this.bias)(_ + _).tanh()
  }

  def parameters(): Seq[Value] = this.weights ++ Seq(this.bias)
}
