package com.passion;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
public class CountJacksons {

  public static void main(String[] args) throws Exception{

    SparkSession sparkSession = SparkSession.builder().
      master("local[*]").appName("TotalJacksons")
      .getOrCreate();

    SparkContext sc = sparkSession.sparkContext() ;
    JavaSparkContext jsc = JavaSparkContext.fromSparkContext(sc);
 JavaRDD<String>  names =  jsc.textFile("file:///Users/ravishankarnair/sparkcode/data/babynames.csv");
long numJackson = names.filter(new Function<String, Boolean>() {
      public Boolean call(String s) { return s.contains("JACKSON"); }
    }).count();

 System.out.println("The number of babies with name JACKSON: " + numJackson);
 }
}
