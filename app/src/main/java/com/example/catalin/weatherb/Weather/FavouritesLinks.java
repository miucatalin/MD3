package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catalin.weatherb.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FavouritesLinks extends Activity {
    private String username;
    private final String USERS_TABLE ="Links";

    private ListView list;
    private TextView user;
    private EditText eLink;
    Pattern pattern;
    Matcher matcher;
    private String url="^(http(?:s)?\\:\\/\\/[a-zA-Z0-9\\-]+(?:\\.[a-zA-Z0-9\\-]+)*\\.[a-zA-Z]{2,6}(?:\\/?|(?:\\/[\\w\\-]+)*)(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_links);

        list=(ListView)findViewById(R.id.listView);
        user=(TextView)findViewById(R.id.textView3);
        eLink=(EditText)findViewById(R.id.editText);
        Parse.initialize(this, "udumMDhauwhzIXHbWK1Bm3twUIP63F4y2Eq1BGVO", "FXKoOICrczFFrYLMMvn6qRg3bB4rLCkzKdkS1qOU");
        Intent intent = getIntent();
        username = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        user.setText(username+"'s");
        getLinks(username);

        pattern = Pattern.compile(url);
    }
    private void getLinks(String username) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(USERS_TABLE);
        List<String> links = new ArrayList<String>();
        query.whereEqualTo("username", username);
        try {
            List<ParseObject> userList = query.find();
            //Fetch the data from the database into the fields on the acitivty
            for (ParseObject user : userList)
                links.add(user.getString("link"));
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, links);
            list.setAdapter(adapter);
        } catch (ParseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
     public void addLinks(View v){
         String link = eLink.getText().toString();
         if(pattern.matcher(link.toLowerCase()).matches()==true) {


             //Make a query to check wether the user already exists
             ParseQuery<ParseObject> query = ParseQuery.getQuery(USERS_TABLE);
             //Select where username is @username

             query.whereEqualTo("username", username);
             query.whereEqualTo("link", link);
             try {
                 if (query.find().isEmpty()) {
                     ParseObject p = new ParseObject(USERS_TABLE);
                     p.put("username", username);
                     p.put("link", link);
                     p.save();
                     getLinks(username);
                 }

             } catch (ParseException e) {
                 Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
             }
         }
         else
             Toast.makeText(getApplicationContext(),"Invalid URL !", Toast.LENGTH_LONG).show();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourites_links, menu);
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
