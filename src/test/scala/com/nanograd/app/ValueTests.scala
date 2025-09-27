package com.nanograd.app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValueTests extends AnyFlatSpec with Matchers {

  "Values" should "add correctly return the expected value" in {

    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(-1.0)

    a + b shouldBe c
  }

  it should "multiply correctly and return the expected value" in {
    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(-6.0)

    a * b shouldBe c
  }

  it should "return the list of operations and the values resulting it" in {
    val a = new Value(2.0)
    val b = new Value(-3.0)
    val c = new Value(10.0)

    val d = a*b
    val e = d + c
    val g = new Value(-6.0)
    val f = new Value(4.0)

    d shouldBe g
    d.previous shouldBe Set(a, b)
    d.operation shouldBe "*"

    e shouldBe f
    e.previous shouldBe Set(c, d)
    e.operation shouldBe "+"
  }

  it should "return the power operations and the values resulting it" in {
    val a = new Value(-3.0)

    val b = a**2
    val c = new Value(9.0)

    b shouldBe c
    b.previous shouldBe Set(a)
    b.operation shouldBe "power"
  }

  it should "return the list of operations and the values resulting it after including label in Value class" in {
    val a = new Value(2.0, label = Some("a"))
    val b = new Value(-3.0, label = Some("b"))
    val c = new Value(10.0, label = Some("c"))
    val f = new Value(-2.0, label = Some("f"))

    val e = a * b; e.label = Some("e")
    val d = e + c; d.label = Some("d")
    val L = d * f; L.label = Some("L")

    d.previous shouldBe Set(e, c)
    d.operation shouldBe "+"
    e.previous shouldBe Set(a, b)
    e.operation shouldBe "*"
    L.previous shouldBe Set(d, f)
    L.operation shouldBe "*"
  }

  it should "return the tanh value for the input" in {
    val a = new Value(0.8813735870195432)
    val b = new Value(0.7071067811865476)
    val c = a.tanh()

    c shouldBe b
    c.previous shouldBe Set(a)
    c.operation shouldBe "tanh"
  }
}
