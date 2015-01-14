package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.catalin.weatherb.R;


public class HomePage extends Activity {
    public final static String EXTRA_MESSAGE="";
    TextView tUser;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //get the username form the intent
        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        tUser=(TextView)findViewById(R.id.textView2);
        tUser.setText(username);
    }
    public void openProfile(View v){
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }
    public void openPlayers(View v){
        Intent intent = new Intent(this,ChoosePlayers.class);
        startActivity(intent);
    }
    public void openFavouriteLinks(View v){
        Intent intent = new Intent(this, FavouritesLinks.class);
        intent.putExtra(EXTRA_MESSAGE, username);
        startActivity(intent);
    }
    public void openWeather(View v){
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
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
