package com.jeangaud_unchained.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.jeangaud_unchained.R;
import com.example.jeangaud_unchained.R.id;
import com.example.jeangaud_unchained.R.layout;
import com.example.jeangaud_unchained.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Uri imageUri;
	private Intent intentPhoto;
	private static final int CAPTURE_PHOTO_ACTIVITY_REQUEST_CODE = 200;
	private static final int BURN_ACTIVITY_CODE = 200;
	private Bitmap bitmapImage;
	private Button quitter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // create Intent to take a picture and return control to the calling application
        intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        File photo = new File(Environment.getExternalStorageDirectory(),  "photo.jpg");
        imageUri = Uri.fromFile(photo);
        intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // set the image file name
        
        quitter = (Button) findViewById(R.id.button2);
        quitter.setOnClickListener(monEcouteurQ);
        
       Button btnTakePhoto =  (Button)findViewById(R.id.takePhotoBtn);
       btnTakePhoto.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				takePhoto();
			}
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void takePhoto() {
        startActivityForResult(intentPhoto, CAPTURE_PHOTO_ACTIVITY_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_PHOTO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
            	try {
					bitmapImage = android.provider.MediaStore.Images.Media
					        .getBitmap(getContentResolver(), data.getData());
					startBurnActivity();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else if (resultCode == RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                // Image capture failed, advise user
            }
        } else if(requestCode == BURN_ACTIVITY_CODE) {
        	 Toast.makeText(this, "Return ", Toast.LENGTH_LONG).show();
        }
    }
    
    private void startBurnActivity() {
    	Intent burnActivity = new Intent(this.getApplicationContext(), BurnActivity.class);
    	burnActivity.putExtra("photo", imageUri);
    	startActivity(burnActivity);
    }
    
    private OnClickListener monEcouteurQ = new OnClickListener() {
   	 
    	public void onClick(View arg0) {
			 	finish();
     }
    };
    
}
