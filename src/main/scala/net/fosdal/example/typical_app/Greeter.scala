package net.fosdal.example.typical_app

case class Greeter(greeting: String) {

  def apply(name: String): String = s"$greeting $name"

}
