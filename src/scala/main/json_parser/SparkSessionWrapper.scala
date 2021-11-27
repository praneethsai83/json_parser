package scala.main.json_parser

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkSessionWrapper {
  val conf = new SparkConf().setMaster("local[*]").setAppName("JSONParser")
  val spark = SparkSession.builder().config(conf).config("spark.sql.codegen.WholeStage", "false").getOrCreate()
  import spark.implicits._
  spark.sparkContext.setLogLevel("error")
}