package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalin.weatherb.R;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class LoginActivity extends Activity {
	private EditText usernameEdit;
	private EditText passwordEdit;
	private final String USER_TABLE ="Profiles";
	public final static String EXTRA_MESSAGE="";
	private String message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//The arguments are the APP_ID, and the CLient_ID from parse.com
		Parse.initialize(this, "udumMDhauwhzIXHbWK1Bm3twUIP63F4y2Eq1BGVO", "FXKoOICrczFFrYLMMvn6qRg3bB4rLCkzKdkS1qOU");
		ParseAnalytics.trackAppOpened(getIntent());	
		//Get username and password editText from layout
		usernameEdit=(EditText)findViewById(R.id.usernameEdit);
		passwordEdit=(EditText)findViewById(R.id.passwordEdit);
	}
	public void signin(View view){
		//Fetch the username and password from the edittext
		String username=usernameEdit.getText().toString();
		String password=passwordEdit.getText().toString();
		if(username.length()==0)
    		Toast.makeText(getApplicationContext(), "Please insert username !", Toast.LENGTH_SHORT).show();
		else
		if(password.length()==0)
	  		Toast.makeText(getApplicationContext(), "Please insert password !", Toast.LENGTH_SHORT).show();
		else
		{
			//Initiate a parse query
			ParseQuery<ParseObject> query = ParseQuery.getQuery(USER_TABLE);

			//Select where username is @username and password is @password
			query.whereEqualTo("username", username);
			query.whereEqualTo("password", password);

			//make the query
			try{
				List<ParseObject> userList=query.find();
				//if the username was found open Profile activity
				if(userList.size()>0)
				{

					message=username;
					Toast.makeText(getApplicationContext(),"Redirecting...", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(this, HomePage.class);
					//send the user name via intent to be used in profile acitivity
					intent.putExtra(EXTRA_MESSAGE, message);
					startActivity(intent);
				}
				else
					Toast.makeText(getApplicationContext(), "Invalid username/password !", Toast.LENGTH_SHORT).show();
				
					
			
			}
			catch(ParseException e){
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
		passwordEdit.setText("");
	}
	public void signup(View view){
		//start the signup activity 
		Intent intent = new Intent(this, Signup.class);
		this.startActivity(intent);
	}
}
