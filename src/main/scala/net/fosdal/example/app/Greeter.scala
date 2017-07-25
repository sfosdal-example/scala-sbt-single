package net.fosdal.example.app

case class Greeter(greeting: String) {

  def apply(name: String): String = s"$greeting $name"

}
