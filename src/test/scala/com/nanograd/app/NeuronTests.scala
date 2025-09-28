package com.nanograd.app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class NeuronTests extends AnyFlatSpec with Matchers {

  "Neuron" should "take Values as inputs and bias and calculate output" in {
    val neuron = new Neuron(4)
    val inputs = Seq(new Value(1.5), new Value(2.5), new Value(3.5), new Value(-2.0))
    neuron.weights = Seq(new Value(0.1), new Value(0.15), new Value(0.2), new Value(0.25) )
    neuron.bias = new Value(0.3)

    neuron.parameters().size shouldBe 5
    neuron(input = inputs) shouldBe new Value(0.7718952374404183)
  }

  it should "take Double as inputs and bias and calculate output" in {
    val neuron = new Neuron(4)
    val inputs = Seq(1.5, 2.5, 3.5, -2.0)
    neuron.weights = Seq(new Value(0.1), new Value(0.15), new Value(0.2), new Value(0.25) )
    neuron.bias = new Value(0.3)

    neuron.parameters().size shouldBe 5
    neuron(input = inputs) shouldBe new Value(0.7718952374404183)
  }
}

