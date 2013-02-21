package com.jeangaud_unchained.view;

import com.example.jeangaud_unchained.R;
import com.example.jeangaud_unchained.R.id;
import com.example.jeangaud_unchained.R.layout;
import com.example.jeangaud_unchained.R.menu;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class BurnActivity extends Activity implements SensorEventListener {
	/*** valeur courante de l'accéléromètre*/
	float[] gravityVector = new float[3];
	/*** valeur courante du vecteur geomagnetique*/
	float[] geomagVector = new float[3];
	
	float[] orientationVector = new float[3];

	private Display mDisplay;
	private SensorManager sensorManager;
	private Sensor accelerometer;
	private Sensor geomagnetic;

	private int sensorType;
	private static final int ACCELE = 0;
	private static final int Gravity = 1;
	private static final int LINEAR_ACCELE = 2;
	private static final int GEOMAG = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_burn);
		Uri uriPhoto = (Uri)getIntent().getParcelableExtra("photo");
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText(uriPhoto.toString());
		
		// Instancier le SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// Instancier l'accéléromètre
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		geomagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		// Et enfin instancier le display qui connaît l'orientation de l'appareil
		mDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_burn, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	    // update only when your are in the right case:
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			gravityVector[0] = event.values[0];
			gravityVector[1] = event.values[1];
			gravityVector[2] = event.values[2];
		}
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			geomagVector[0] = event.values[0];
			geomagVector[1] = event.values[1];
			geomagVector[2] = event.values[2];
		}
		
		float[] rotationMatrix = new float[9];

		SensorManager.getRotationMatrix(rotationMatrix, null, gravityVector, geomagVector);
		SensorManager.getOrientation(rotationMatrix, orientationVector);
		
		TextView xTV = (TextView)findViewById(R.id.textView1);
		TextView yTV = (TextView)findViewById(R.id.textView2);
		TextView zTV = (TextView)findViewById(R.id.textView3);
		
		xTV.setText(String.valueOf(orientationVector[0]));
		yTV.setText(String.valueOf(orientationVector[1]));
		zTV.setText(String.valueOf(orientationVector[2]));
	}
	
	@Override
	protected void onPause() {
	// désenregistrer tous le monde
	sensorManager.unregisterListener(this, accelerometer);
	sensorManager.unregisterListener(this, geomagnetic);
	super.onPause();
	}
	
	/* * (non-Javadoc) *  * @see android.app.Activity#onResume() */
	@Override
	protected void onResume() {
	/* Ce qu'en dit Google :
	* «  Ce n'est pas nécessaire d'avoir les évènements des capteurs à un rythme trop rapide.
	 * En utilisant un rythme moins rapide (SENSOR_DELAY_UI), nous obtenons un filtre
	 * automatique de bas-niveau qui "extrait" la gravité  de l'accélération.
	 * Un autre bénéfice étant que l'on utilise moins d'énergie et de CPU. »
	 */
	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
	sensorManager.registerListener(this, geomagnetic, SensorManager.SENSOR_DELAY_UI);
	super.onResume();
	}

}
