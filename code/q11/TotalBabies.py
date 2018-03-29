import sys
import csv
from pyspark.sql import SparkSession
from pyspark import SparkContext
if __name__ == "__main__":
  sparkSession = SparkSession \
    .builder \
    .master("local[*]") \
    .appName("Total Count of Babies") \
    .config("spark.some.config.option", "some-value") \
    .getOrCreate()

  sc = sparkSession.sparkContext
  names =  sc.textFile("file:///Users/ravishankarnair/sparkcode/data/abynames.csv")
  header = names.first()
  print "The header is %s" % header
  rowswoheader = names.filter(lambda row :  row != header)
  pythonrddwocommas = rowswoheader.mapPartitions(lambda row: csv.reader(row))
  test = pythonrddwocommas.first()
  print "The number is %d" % int(test[4])
  totalcount = pythonrddwocommas.map(lambda row : int(row[4])).sum()
  print "Total number of babies:  %d." % totalcount




