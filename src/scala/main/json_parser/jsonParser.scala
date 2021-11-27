package scala.main.json_parser

object jsonParser extends SparkSessionWrapper with App {
  var df = spark.read.json("C:\\Users\\HP\\Documents\\workspace\\json_parser\\resources\\data.json")
  df.show
}