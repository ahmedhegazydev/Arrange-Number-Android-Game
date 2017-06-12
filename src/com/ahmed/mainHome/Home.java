package com.ahmed.mainHome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		
		
		ImageButton imageButton = (ImageButton) findViewById(R.id.startHome);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(Home.this, com.ahmed.level0.GameLevel0.class);
				startActivity(intent);
				
			}
		});
		
		
	}
	
	

}
