package com.passion;

import com.passion.CustomMQReceiver;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import java.util.*;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by RaviShankar on 12/07/2017.
 */
public class MQStreaming implements Serializable {

    public static void main(String[] args) {

        try {

            SparkConf sparkConf = new SparkConf().setAppName("TestMQStreaming").setMaster("local[2]");

            JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, new Duration(1000));

            JavaDStream<String> customReceiverStream = ssc.receiverStream(new CustomMQReceiver("10.1.10.49", 1414, "PERCIPIENT.MANAGER.1", "CHANNEL1", "QUEUE1"));

//Start Kafka

        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("zk.connect", "2181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");

        String TOPIC = "realtime.mq";
        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

 //Start Ravi
    //   customReceiverStream.print();
 //       KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC,customReceiverStream);
   //    producer.send(data); 
 //Manage DStream here

customReceiverStream.foreachRDD(new VoidFunction<JavaRDD<String> >() {
        public void call(JavaRDD<String> rdd) throws Exception {
            if(rdd!=null)
            {
                List<String> result = rdd.collect();


                for (String temp : result) {
KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC,temp);
       producer.send(data);
                }
            }
 //           return null;
}});



//End DStream       
     
 //End Ravi
           ssc.start();
            ssc.awaitTermination();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

}
