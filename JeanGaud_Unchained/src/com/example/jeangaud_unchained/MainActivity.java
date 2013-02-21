package com.example.jeangaud_unchained;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Uri imageUri;
	private Intent intentPhoto;
	private static final int CAPTURE_PHOTO_ACTIVITY_REQUEST_CODE = 200;
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // create Intent to take a picture and return control to the calling application
        intentPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
        File photo = new File(Environment.getExternalStorageDirectory(),  "photo.jpg");
        imageUri = Uri.fromFile(photo);
        intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // set the image file name
        
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
    
    private void startBurnActivity() {
    	
    }
    
}
