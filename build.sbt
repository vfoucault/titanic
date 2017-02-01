lazy val root = (project in file(".")).
  settings(
    name := "titanic",
    version := "1.0",
    scalaVersion := "2.11.1",
    mainClass in Compile := Some("titanic.ParseTitanic")
  )



libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.1.0" % "provided" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0" % "provided" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.hadoop" % "hadoop-common" % "2.7.0" % "provided" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-sql_2.11" % "2.1.0" % "provided" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-hive_2.11" % "2.1.0" % "provided" exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-yarn_2.11" % "2.1.0" % "provided"exclude ("org.apache.hadoop","hadoop-yarn-server-web-proxy")
)


mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
{
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
}