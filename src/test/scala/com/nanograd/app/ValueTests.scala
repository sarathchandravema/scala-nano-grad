package com.nanograd.app

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ValueTests extends AnyFlatSpec with Matchers {

  "Values" should "add correctly return the added value" in {
    val a = new Value(2.0)
    val b = new Value(3.0)

    val c = new Value(5.0)
    a + b shouldBe c
  }
}
