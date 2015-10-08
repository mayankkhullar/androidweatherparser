package com.example.xmlpull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class XMLResponseHandler {
   private String country = "county";
   private String temperature = "temperature";
   String temp = "";
   int tem;
  
   private String urlString = null;
   private XmlPullParserFactory xmlFactoryObject;
   public volatile boolean parsingComplete = true;
   
   public XMLResponseHandler(String url){
      this.urlString = url;
   }
   
   public String getCountry(){
      return country;
   }
   
   public String getTemperature(){
      return temperature;
   }
   
  
   
   public void parseXMLAndStoreIt(XmlPullParser myParser) {
      int event;
      String text=null;
      
      try {
         event = myParser.getEventType();
         
         while (event != XmlPullParser.END_DOCUMENT) {
            String name=myParser.getName();
         
            switch (event){
               case XmlPullParser.START_TAG:
            	   if(name.equals("temperature")){
                       temperature = myParser.getAttributeValue(null,"value");
                       
                       
                    }
        
            	  
            	   
            	   break;
            
               case XmlPullParser.TEXT:
               text = myParser.getText();
               break;
            
               case XmlPullParser.END_TAG:
               if(name.equals("country")){
                  country = text;
               }
            
    
            
               else{
               }
               break;
            }
            event = myParser.next();
         }
         parsingComplete = false;
      }
      
      catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   private void conertValue() {
	// TODO Auto-generated method stub
	tem =  Integer.parseInt(temp);
	tem = tem-273;
	temperature = Integer.toString(tem);
	
	
}

public void fetchXML(){
      Thread thread = new Thread(new Runnable(){
         @Override
         public void run() {
            try {
               URL url = new URL(urlString);
               HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            
               conn.setReadTimeout(10000 /* milliseconds */);
               conn.setConnectTimeout(1500000 /* milliseconds */);
               conn.setRequestMethod("GET");
               conn.setDoInput(true);
               conn.connect();
            
               InputStream stream = conn.getInputStream();
               xmlFactoryObject = XmlPullParserFactory.newInstance();
               XmlPullParser myparser = xmlFactoryObject.newPullParser();
               
               myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
               myparser.setInput(stream, null);
               
               parseXMLAndStoreIt(myparser);
               stream.close();
            }
            catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
      thread.start();
   }
}