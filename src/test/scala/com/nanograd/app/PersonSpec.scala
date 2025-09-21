package com.nanograd.app

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

//class PersonTests extends AnyFunSuite with Matchers {
class PersonSpec extends AnyFlatSpec with Matchers {

  // these first two instances should be equal
  val nimoy = new Person("Leonard Nimoy", 82)
  val nimoy2 = new Person("Leonard Nimoy", 82)
  val shatner = new Person("William Shatner", 82)
  val stewart = new Person("Patrick Stewart", 47)

  // all tests pass
//  test("nimoy   != null")    { assert(nimoy != null) }
//
//  // these should be equal
//  test("nimoy   == nimoy")   { assert(nimoy == nimoy) }
//  test("nimoy   == nimoy2")  { assert(nimoy == nimoy2) }
//  test("nimoy2  == nimoy")   { assert(nimoy2 == nimoy) }
//
//  // these should not be equal
//  test("nimoy   != shatner") { assert(nimoy != shatner) }
//  test("shatner != nimoy")   { assert(shatner != nimoy) }
//  test("nimoy   != String")  { assert(nimoy != "Leonard Nimoy") }
//  test("nimoy   != stewart") { assert(nimoy != stewart) }

  "nimoy" should "test success" in {
    nimoy.name should not be "nimoy"
  }

//  "nimoy" should "not test success" in {
//    nimoy.name shouldBe "nimoy"
//  }

}