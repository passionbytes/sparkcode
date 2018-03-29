package com.passion

import org.apache.spark.sql.SparkSession

object TotalBabies {

  def main(args: Array[String]) {

    val sparkSession = SparkSession.builder.
      master("local[*]")
      .appName("totalbabies")
      .getOrCreate()

    val sc = sparkSession.sparkContext
    import sparkSession.implicits._

val names =  sc.textFile("file:///Users/ravishankarnair/sparkcode/data/babynames.csv")
val header = names.first
val rowswoheader = names.filter(row =>  row != header)
val totalcount = rowswoheader.map(row => row(4).toInt).sum.toInt
println(s"The total number of babies is:  $totalcount");
 }
}
