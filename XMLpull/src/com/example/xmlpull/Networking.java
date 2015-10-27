package com.example.xmlpull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class Networking extends Activity {
   EditText ed2,ed3,ed4,ed5; 
   private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
   private String url2 = "&mode=xml&units=metric&appid=bd82977b86bf27fb59a04b61b657fb6f";
   private XMLResponseHandler obj;
   String finalurl;
   Button b1;
   TextView tv;
   ArrayList<String> list = new ArrayList<String>();
   AutoCompleteTextView ed1;
   String[] languages;
   
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      b1=(Button)findViewById(R.id.button);
      ed2=(EditText)findViewById(R.id.editText2);
      ed3=(EditText)findViewById(R.id.editText3);
      ed1 = (AutoCompleteTextView) findViewById(R.id.editText);
      try {
		CheckValidity(Networking.this);
		languages = list.toArray(new String[0]);
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
      b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String url = ed1.getText().toString();
            
            // check url 
            if(url.contains(" "))
			 {
				 String s = url.replaceAll("\\s","%20");
				 finalurl = url1 + s + url2;
			 }
            else{
				 finalurl = url1 + url + url2;
			 }
			
			//System.out.println(list);
			 if(list.contains(url))
			 { 
			    //  ed2.setText(finalurl);
			      obj = new XMLResponseHandler(finalurl);
			      obj.fetchXML();
			     
			      while(obj.parsingComplete);
			     ed2.setText(obj.getCountry());
			      ed3.setText(obj.getTemperature());
			  }
         
			 else {ed2.setText("Not a Valid City!!");}
           
           }
      });
     
 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, languages);
      
      ed1.setThreshold(1);
      ed1.setAdapter(adapter);
     
   }
      private void CheckValidity(Context context) throws IOException , FileNotFoundException
      { 
    	  
    	 
    	  try {
    	   list = new ArrayList<String>();
    	  
    	 InputStream in = context.getAssets().open("citylist.txt");
			if (in != null) {
		InputStreamReader input = new InputStreamReader(in);	      
		BufferedReader br = new BufferedReader(input);	     
		String line =br.readLine();
		while((line = br.readLine()) !=null)
		{
			list.add(line);
		}
		
		in.close();
		
			  }else{ System.out.println("It's the assests");}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
      }
      
      }
