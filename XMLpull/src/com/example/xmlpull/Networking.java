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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Networking extends Activity {
   EditText ed1,ed2,ed3,ed4,ed5;
   
   private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
   private String url2 = "&mode=xml&units=metric&appid=bd82977b86bf27fb59a04b61b657fb6f";
   
   private XMLResponseHandler obj;
   Button b1;
   TextView tv;
   ArrayList<String> list = new ArrayList<String>();
   
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      b1=(Button)findViewById(R.id.button);
      ed1=(EditText)findViewById(R.id.editText);
      ed2=(EditText)findViewById(R.id.editText2);
      ed3=(EditText)findViewById(R.id.editText3);
     
    
      
      b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            
            try {
               String url = ed1.getText().toString();
			      CheckValidity(Networking.this);
				//System.out.println(list);
				 if(list.contains(url))
		    	  {
		    		  String finalUrl = url1 + url + url2;
		              ed2.setText(finalUrl);
		              obj = new XMLResponseHandler(finalUrl);
		              obj.fetchXML();
   	              while(obj.parsingComplete);
		              ed2.setText(obj.getCountry());
		              ed3.setText(obj.getTemperature());
		  }
				 else {ed2.setText("Not a Valid City!! please Enter Again");}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           }
      });
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
			  }   else
			   {
			        System.out.println("It's the assests");
			   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
      }
