package parsetitanic

import org.apache.spark.sql.functions.mean
import org.apache.spark.sql.{DataFrame, SparkSession, DataFrameWriter, SaveMode}
import parsetitanic.sources.TitanicFileSource


case class ParseTitanic(sparkSession: SparkSession, titanicDf: DataFrame)  {

  import sparkSession.implicits._

  case class resultSet(sexe: String, avgage: Int)


  val moyPerSexe = titanicDf.select($"Sex", $"age").groupBy($"Sex").agg(mean($"Age"))

  val savemode = SaveMode.Overwrite

  moyPerSexe.coalesce(1)
    .write
    .mode(savemode)
    .option("header", "true")
    .csv("/tmp/out.csv")

}

object ParseTitanic {
  def main(args:Array[String]) : Unit = {


    System.setProperty("hadoop.home.dir", "/Users/vfoucault/tools/hadoop-2.7.3")


    val sparkSession = SparkSession.builder
      .master("local[*]")
      .appName("mytitanic Test")
      .enableHiveSupport()
      .getOrCreate()

    val titanic = TitanicFileSource(sparkSession).load()
    ParseTitanic(sparkSession, titanic)
  }



}


