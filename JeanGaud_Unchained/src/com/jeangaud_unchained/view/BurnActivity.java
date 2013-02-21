package com.jeangaud_unchained.view;

import com.example.jeangaud_unchained.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BurnActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_burn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_burn, menu);
		return true;
	}

}
