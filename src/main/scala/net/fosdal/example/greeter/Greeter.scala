package net.fosdal.example.typical_app

case class Greeter(greeting: String) {

  def greet(name: String): String = s"$greeting $name"

}
