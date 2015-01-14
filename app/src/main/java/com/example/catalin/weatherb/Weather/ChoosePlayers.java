package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.catalin.weatherb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChoosePlayers extends Activity {

    Item item;
    EditText age;
    JSONObject object;
    public static String JSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_players);
        age=(EditText)findViewById(R.id.editText2);
        item=new Item();
        object = new JSONObject();
        makeSpinner();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_players, menu);
        return true;
    }
    public void makeSpinner(){

        List<String> list = new ArrayList<String>();
        Spinner spinner,spinner2;
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        list.add("Top 10");
        list.add("Top 20");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        //aduaga un eveniment la selectare unui element din lists
        //pentru a actualiza campurile cu ip si port
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
                         item.setScore(10*(pos+1));

               }

               @Override
               public void onNothingSelected(AdapterView<?> arg0) {
                   // TODO Auto-generated method stub
               }


           }
        );
        list=new ArrayList<String>();
        list.add("M");
        list.add("F");
        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        //aduaga un eveniment la selectare unui element din lists
        //pentru a actualiza campurile cu ip si port
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
                  item.setGender(parent.getItemAtPosition(pos).toString());
              }

              @Override
              public void onNothingSelected(AdapterView<?> arg0) {
                  // TODO Auto-generated method stub
              }


          }
        );
    }
    public void openTennis(View v){
        try {
            int a = Integer.parseInt(age.getText().toString());

            try {

                object.put("age", Integer.parseInt(age.getText().toString()));
                object.put("score", item.getScore());
                object.put("gender", item.getGender());
                JSON = object.toString();
                Intent intent = new Intent(this, Players.class);
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Please insert a valid number for age !",Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
