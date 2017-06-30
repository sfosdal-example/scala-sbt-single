package net.fosdal.example.greeter

import org.scalatest.{Matchers, WordSpec}

class GreeterSpec extends WordSpec with Matchers {

  "Greeter" when {
    "given a greeting and a name" must {
      "greet that name" in new Fixture {
        val greeter = Greeter(greeting)
        val msg     = greeter.greet(name)
        msg shouldBe "Hello World"
      }
    }
  }

  trait Fixture {
    val greeting = "Hello"
    val name     = "World"
  }

}
