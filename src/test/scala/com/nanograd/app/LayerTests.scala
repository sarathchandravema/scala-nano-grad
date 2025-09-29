package com.nanograd.app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class LayerTests extends AnyFlatSpec with Matchers {

  "Layers" should "take Values as inputs and calculate output" in {
    val layer = new Layer(2, 3)
    val inputs = Seq(new Value(1.236), new Value(2.969))
    layer.neurons = Seq(new Neuron(2), new Neuron(2) )

    layer.neurons(0).weights = Seq(new Value(0.145), new Value(0.134))
    layer.neurons(0).bias = new Value(0.187)

    layer.neurons(1).weights = Seq(new Value(0.224), new Value(-0.589))
    layer.neurons(1).bias = new Value(0.126)

    layer.parameters().size shouldBe 6
    layer(inputs = inputs) shouldBe Seq( new Value( 0.6434656782077777 ), new Value(-0.8730766185815197 ))
    }

    it should "take Double as inputs and calculate output" in {
      val layer = new Layer(2, 4)
      val inputs = Seq(0.675, 1.276)
      layer.neurons = Seq(new Neuron(2), new Neuron(2) )

      layer.neurons(0).weights = Seq(new Value(0.153), new Value(0.194))
      layer.neurons(0).bias = new Value(0.125)

      layer.neurons(1).weights = Seq(new Value(0.291), new Value(-0.156))
      layer.neurons(1).bias = new Value(0.586)

      layer.parameters().size shouldBe 6
      layer(inputs = inputs) shouldBe Seq(new Value( 0.44288894742914414 ), new Value( 0.5251097757261329 ))
    }
  }


