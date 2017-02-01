package parsetitanic.sources

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Created by vfoucault on 01/02/2017.
  */
case class TitanicFileSource(sparkSession: SparkSession) {

  import sparkSession.implicits._

  def load(): DataFrame = {
    sparkSession.read.option("header","true")
      .csv("src/main/resources/data/titanic.csv")
  }

}
