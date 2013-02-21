package com.example.jeangaud_unchained;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class BurnActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_burn);
		Uri uriPhoto = (Uri)getIntent().getParcelableExtra("photo");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText(uriPhoto.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_burn, menu);
		return true;
	}
	
	

}
