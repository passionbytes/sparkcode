package com.passion;

import org.apache.spark.SparkConf     
import org.apache.spark.streaming.StreamingContext        
import org.apache.spark.streaming.Seconds        
import twitter4j.conf.ConfigurationBuilder    
import twitter4j.auth.OAuthAuthorization    
import twitter4j.Status
import org.apache.spark.streaming.twitter.TwitterUtils
 
object PassionTwitter {    
  def main(args: Array[String]) {    
 
   /* if (args.length < 4) {    
      System.err.println("Usage: TwitterData <ConsumerKey><ConsumerSecret><accessToken><accessTokenSecret>" +    
        "[<filters>]")
      System.exit(1)   
    } */
 
    val appName = "PassionTwitter"    
    val conf = new SparkConf()    
    conf.setAppName(appName).setMaster("local[*]")    
 
    val ssc = new StreamingContext(conf, Seconds(5))    
    val cb = new ConfigurationBuilder    
    cb.setDebugEnabled(true).setOAuthConsumerKey("kEQDb90oROABtzix5hAvLw")    
      .setOAuthConsumerSecret("EzzcDNOZsH1dFYObRqHXVOy4JCD7MFyMYTRtiUrAlOM")    
      .setOAuthAccessToken("158483808-ko5RWuMKfSqWBWyFFoah1rGjxPa0XNYJLtaMFlYN")
      .setOAuthAccessTokenSecret("6miV8B5xEr2slhwmgQWHERaXR1TykeXwu5dJFyvUpfo")    
      
    val auth = new OAuthAuthorization(cb.build)    
    val tweets = TwitterUtils.createStream(ssc, Some(auth))    
    val englishTweets = tweets.filter(_.getLang() == "en")    
    englishTweets .saveAsTextFiles("tweets", "json")
    ssc.start()    
    ssc.awaitTermination()    
 
  }  
}

//--consumerKey kEQDb90oROABtzix5hAvLw  --consumerSecret EzzcDNOZsH1dFYObRqHXVOy4JCD7MFyMYTRtiUrAlOM   --accessToken 158483808-ko5RWuMKfSqWBWyFFoah1rGjxPa0XNYJLtaMFlYN      --accessTokenSecret 6miV8B5xEr2slhwmgQWHERaXR1TykeXwu5dJFyvUpfo

