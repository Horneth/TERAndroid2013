package com.jeangaud_unchained.view;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.jeangaud_unchained.R;
import com.jeangaud_unchained.control.AccelerationController;
import com.jeangaud_unchained.model.CircleView;

public class BurnActivity extends Activity implements SensorEventListener {
	/*** valeur courante de l'acc�l�rom�tre*/
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
	
	private CircleView burningPoint;
	
	private AccelerationController accelerationController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_burn);
		Uri uriPhoto = (Uri)getIntent().getParcelableExtra("photo");
		Toast.makeText(getApplicationContext(), String.valueOf(uriPhoto), Toast.LENGTH_LONG).show();
		burningPoint = (CircleView)findViewById(R.id.circleView1);

		
		accelerationController = new AccelerationController();
		
		// Instancier le SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// Instancier l'acc�l�rom�tre
		accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		geomagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		
		// Et enfin instancier le display qui conna�t l'orientation de l'appareil
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
		
		int[] deltas = new int[2];
		accelerationController.computeDeltas(orientationVector, deltas);
		
		burningPoint.updatePosition(deltas[0], deltas[1]);
		burningPoint.invalidate();

	}
	
	@Override
	protected void onPause() {
	// d�senregistrer tous le monde
	sensorManager.unregisterListener(this, accelerometer);
	sensorManager.unregisterListener(this, geomagnetic);
	super.onPause();
	}
	
	/* * (non-Javadoc) *  * @see android.app.Activity#onResume() */
	@Override
	protected void onResume() {
	/* Ce qu'en dit Google :
	* �  Ce n'est pas n�cessaire d'avoir les �v�nements des capteurs � un rythme trop rapide.
	 * En utilisant un rythme moins rapide (SENSOR_DELAY_UI), nous obtenons un filtre
	 * automatique de bas-niveau qui "extrait" la gravit�  de l'acc�l�ration.
	 * Un autre b�n�fice �tant que l'on utilise moins d'�nergie et de CPU. �
	 */
	sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
	sensorManager.registerListener(this, geomagnetic, SensorManager.SENSOR_DELAY_UI);
	super.onResume();
	}

}
