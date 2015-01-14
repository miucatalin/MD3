package com.example.catalin.weatherb.Weather;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.catalin.weatherb.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by catalin on 10.01.2015.
 */
public class Players extends ListActivity {

    private ItemArrayAdapter adapter;
    private ArrayList<Item> players=new ArrayList<Item>();
    private int age,score;
    private String gender;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        players.add(new Item("Novak Djokovic",27,11000,"M",R.drawable.novak));
        players.add(new Item("Grigor Dimitrov",23,3000,"M",R.drawable.dimitri));
        players.add(new Item("Simona Halep",24,6000,"F",R.drawable.simona));
        players.add(new Item("Sharapova Maria",28,7300,"F",R.drawable.maria));
        fill();
    }
    private void fill()
    {
        List<Item>fls = new ArrayList<Item>();
        try{
            JSONObject reader=new JSONObject(ChoosePlayers.JSON);
            age=reader.getInt("age");
            score=reader.getInt("score");
            gender=reader.getString("gender");
            for(Item i:players){
                if( i.getGender().equals(gender) &&i.getAge()<age)

                        fls.add(new Item(i.getName(),i.getAge(),i.getScore(),i.getGender(),i.getImg()));

            }
        }
        catch (JSONException e){

        }


        adapter = new ItemArrayAdapter(Players.this, R.layout.item,fls);
        this.setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

    }

}