package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalin.weatherb.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends Activity {
	private EditText usernameEdit;
	private EditText passwordEdit;
	private EditText repasswordEdit;
	private EditText emailEdit;
	
	private final String USER_TABLE ="Profiles";
	private String username,password,email;
	//regular expression pattern for email validation
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private ParseObject users,contacts,profiles; //the object handles the table users
	
	private Pattern pattern;
	private Matcher matcher;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		//Initialize PARSE 
		//The arguments are the APP_ID, and the CLient_ID from parse.com
		Parse.initialize(this, "udumMDhauwhzIXHbWK1Bm3twUIP63F4y2Eq1BGVO", "FXKoOICrczFFrYLMMvn6qRg3bB4rLCkzKdkS1qOU");
		ParseAnalytics.trackAppOpened(getIntent());	
		
		//Create a new ParseObject that will handle the users table
		users = new ParseObject(USER_TABLE);
		//Get username and password editText from layout
		usernameEdit=(EditText)findViewById(R.id.username);
		passwordEdit=(EditText)findViewById(R.id.password);
		repasswordEdit=(EditText)findViewById(R.id.repassword);
		emailEdit=(EditText)findViewById(R.id.email);
		
		//Regular expression email validator
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
	
	public void signup(View view){
		//Fetch the username and password from the edittext
		username=usernameEdit.getText().toString();
		password=passwordEdit.getText().toString();
		email=emailEdit.getText().toString();
		if(username.length()==0)
			Toast.makeText(getApplicationContext(), "Please insert username !", Toast.LENGTH_LONG).show();
		//if it DOES NOT validates the reg expression for email
    	if(pattern.matcher(email).matches()==false)
    		Toast.makeText(getApplicationContext(), "Please insert a valid email address !", Toast.LENGTH_LONG).show();
    	else
    	if(password.length()==0)
    			Toast.makeText(getApplicationContext(), "Please insert password !", Toast.LENGTH_LONG).show();
    	else	
    	if(!password.equals(repasswordEdit.getText().toString()))
        		Toast.makeText(getApplicationContext(), "Passwords are not the same", Toast.LENGTH_LONG).show();
    	else
    	{
    		//Make a query to check wether the user already exists
    		ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);
    		//Select where username is @username 
    		query.whereEqualTo("username",username);
    		query.findInBackground(new FindCallback<ParseObject>() {
    			public void done(List<ParseObject> userList, ParseException e) {
    				if (e == null) {
    					if(userList.size()>0)
    						Toast.makeText(getApplicationContext(), "Username "+username+" already exists !", Toast.LENGTH_LONG).show();
    					else   	
    					{
    						Toast.makeText(getApplicationContext(), "Account created !", Toast.LENGTH_SHORT).show();
    						users.put("username", username);
    						users.put("password", password);
    						users.put("username",username);
    						users.put("Email",email);
    						users.saveInBackground();
    						finish();
    					}
    				} else {
    					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    				}
    			}
    		});
    	}
	}
}
