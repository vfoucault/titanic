package parsetitanic

import org.apache.spark.{SparkContext, SparkConf}


case class ParseTitanic(spark) extends ParseTitanic {

}

object ParseTitanic {
  def main(args:Array[String]) : Unit = {
    System.setProperty("hadoop.home.dir", "/Users/vfoucault/tools/hadoop-2.7.3")
    val conf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val tf = sc.textFile(args(0))
    val splits = tf.flatMap(line => line.split(" ")).map(word =>(word,1))
    val counts = splits.reduceByKey((x,y)=>x+y)

    splits.saveAsTextFile(args(1))
    counts.saveAsTextFile(args(2))
  }
}


