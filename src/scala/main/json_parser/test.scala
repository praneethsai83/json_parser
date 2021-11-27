package scala.main.json_parser

import scala.util.parsing.json.JSON

object test extends App {
  val json_configuration = ReadConfiguration().getConfiguration
  println(json_configuration.toList)
}