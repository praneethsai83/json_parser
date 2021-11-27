package scala.main.json_parser

import scala.util.parsing.json.JSON

case class json_configuration(field_name: String, field_metadata: String, field_retain: Boolean, sequence: Int)

class ReadConfiguration {
  /*
   * Defining private variables
   */
  private var _path: String = _
  private var _configuration: Array[json_configuration] = _

  /**
   * Class Private method to set the value for private variable _path
   * @author HP
   * @return returns the class variable
   */
  
  private def setPath(value: String): this.type = {
    _path = value
    this
  }

  /**
   * Class Private method to set the value for private variable _configuration
   * @author HP
   * @return returns the class variable
   */
  
  private def setConfiguration: this.type = {
    var configuration_map: Map[String, Any] = Map()
    JSON.parseFull(scala.io.Source.fromFile(_path).mkString) match {
      case Some(valid_json) => { configuration_map = valid_json.asInstanceOf[Map[String, Any]] }
      case None             => println("invalid json")
    }
    var configuration_array: Array[json_configuration] = Array()
    if (!configuration_map.isEmpty && configuration_map.contains("field")) {
      if (configuration_map("field").isInstanceOf[List[Any]]) {
        configuration_map("field").asInstanceOf[List[Any]].foreach { json =>
          var json_map = json.asInstanceOf[Map[String, String]]
          configuration_array :+= json_configuration(json_map("name"), json_map("metadata"), json_map("retain").equalsIgnoreCase("Y"), json_map("sequence").toInt)
        }
      }
    }
    _configuration = configuration_array
    this
  }
  
  def getConfiguration: Array[json_configuration] = {
    _configuration
  }
}

object ReadConfiguration {
  def apply(path: String = System.getProperty("user.dir") + "\\resources\\configuration.json"): ReadConfiguration = {
    new ReadConfiguration().setPath(path)
      .setConfiguration
  }
}