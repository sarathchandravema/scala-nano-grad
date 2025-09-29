package com.nanograd.app

class Layer(val nins: Int, val nout: Int) {

  var neurons: Seq[Neuron] = (0 until nout).map(_ => new Neuron(nins))

  def apply[T](inputs: Seq[T])(implicit num: Numeric[T]): Seq[Value] = neurons.map(_.apply(inputs))

  def apply(inputs: Seq[Value]): Seq[Value] = neurons.map(_.apply(inputs))

  def parameters(): Seq[Value] = this.neurons.flatMap(_.parameters())
}