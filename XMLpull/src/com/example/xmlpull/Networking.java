package com.example.xmlpull;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class Networking extends Activity {
   EditText ed1,ed2,ed3;
   
   private String url1 = "http://api.openweathermap.org/data/2.5/weather?q=";
   private String url2 = "&mode=xml&unit=metric&appid=bd82977b86bf27fb59a04b61b657fb6f";
   private XMLResponseHandler obj;
   Button b1;
   TextView tv;
   
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
            String url = ed1.getText().toString();
            String finalUrl = url1 + url + url2;
            ed2.setText(finalUrl);
            
            obj = new XMLResponseHandler(finalUrl);
            obj.fetchXML();
            
            while(obj.parsingComplete);
           ed2.setText(obj.getCountry());
            ed3.setText(obj.getTemperature());
          
         }
      });
   }
   
  }
