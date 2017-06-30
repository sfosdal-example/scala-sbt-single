package net.fosdal.example.greeter

case class Greeter(greeting: String) {

  def greet(name: String): String = s"$greeting $name"

}
