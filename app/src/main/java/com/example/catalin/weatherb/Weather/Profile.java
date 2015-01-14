package com.example.catalin.weatherb.Weather;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catalin.weatherb.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Profile extends Activity {
	private TextView usernameView,fullnameView,ageView,cityView,emailView,phoneView,passwordView;
	private Spinner genderView,languageView;
	private final String USERS_TABLE ="Profiles";
	private String ID,password;
	private ParseObject user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		//The arguments are the APP_ID, and the CLient_ID from parse.com
		Parse.initialize(this, "udumMDhauwhzIXHbWK1Bm3twUIP63F4y2Eq1BGVO", "FXKoOICrczFFrYLMMvn6qRg3bB4rLCkzKdkS1qOU");
		//Get textviews and spinners
		genderView = (Spinner) findViewById(R.id.gender);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.genders, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		genderView.setAdapter(adapter);
		
		//Init language spinner
		languageView=(Spinner)findViewById(R.id.language);
		ArrayAdapter<CharSequence> ladapter=ArrayAdapter.createFromResource(this,
		        R.array.languages, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		ladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		languageView.setAdapter(ladapter);
		
		usernameView=(TextView)findViewById(R.id.username);
		fullnameView=(TextView)findViewById(R.id.fullname);
		passwordView=(TextView)findViewById(R.id.password);
		ageView=(TextView)findViewById(R.id.age);
		cityView=(TextView)findViewById(R.id.city);
		emailView=(TextView)findViewById(R.id.email);
		phoneView=(TextView)findViewById(R.id.phone);
		// Get the message from the intent
	    Intent intent = getIntent();
	    String username = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
	    //Query the database for the rest of the information
		ParseQuery<ParseObject> query = ParseQuery.getQuery(USERS_TABLE);
		//Select where username is @username and password is @password
		query.whereEqualTo("username", username);
		try{
			List<ParseObject> userList=query.find();
			//Fetch the data from the database into the fields on the acitivty
     		ParseObject user=userList.get(0);
     		ID=user.getObjectId();
     		usernameView.setText(user.getString("username"));
     		password=user.getString("password");
     		fullnameView.setText(user.getString("fullname"));
			genderView.setSelection(user.getInt("Gender"));
			ageView.setText(Integer.toString(user.getInt("Age")));
			cityView.setText(user.getString("City"));
     		languageView.setSelection(user.getInt("Language"));
     		emailView.setText(user.getString("Email"));
			phoneView.setText(user.getString("Phone"));
		}
		catch(ParseException e){
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	    
	}
	
	public void popDialog(View view){
		// get prompts.xml view
		user = ParseObject.createWithoutData(USERS_TABLE, ID);
		LayoutInflater li = LayoutInflater.from(this);
	    View promptsView = li.inflate(R.layout.password, null);
	    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	    alertDialogBuilder.setView(promptsView);

	    final EditText oldpass = (EditText) promptsView
	            .findViewById(R.id.oldpass);
	    final EditText newpass = (EditText) promptsView
	            .findViewById(R.id.newpass);

	    // set dialog message
	    //add button and action listeners
	    alertDialogBuilder
	        .setCancelable(false)
	        .setNegativeButton("Go",
	          new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int id) {
	            	//Chheck the old passwrod to correspond to the inserted one
	            	//Make sure new password is not null
	            	 if(oldpass.getText().toString().equals(password) && newpass.getText().length()>0){
	           		  user.put("password",newpass.getText().toString());
	           		  user.saveInBackground();}
	           	  else
	           		  Toast.makeText(getApplicationContext(), "Invalid old/new password", Toast.LENGTH_SHORT).show();

	                     }})
	         
	        .setPositiveButton("Cancel",
	          new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog,int id) {
	            dialog.dismiss();
	            }

	          }

	        );

	    // create alert dialog
	    AlertDialog alertDialog = alertDialogBuilder.create();

	    // show it
	    alertDialog.show();
	}
	public void saveData(View view){
		user = ParseObject.createWithoutData(USERS_TABLE, ID);

		// Update database 
		user.put("fullname", fullnameView.getText().toString());
		user.put("Gender", genderView.getSelectedItemPosition());
		user.put("Age", Integer.parseInt(ageView.getText().toString()));
		user.put("City", cityView.getText().toString());
		user.put("Language", languageView.getSelectedItemPosition());
		user.put("Email",emailView.getText().toString());
		user.put("Phone",phoneView.getText().toString());
		// Save
		Toast.makeText(getApplicationContext(),"Saving...", Toast.LENGTH_SHORT).show();
		try {
			user.save();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
	}
	
}
