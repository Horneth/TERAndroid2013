package com.jeangaud_unchained.control;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;


// Classe qui permet de faire des effets sur la photo
public class managePhoto {

	// Transforme une imageView en Bitmap
	public Bitmap imageViewToBitmap(ImageView img){
		return (((BitmapDrawable)img.getDrawable()).getBitmap());
	}
	
	
	// Transforme une photo en sepia
	public Bitmap toSephia(Bitmap bmpOriginal)
	{        
	    int width, height, r,g, b, c, gry;
	    height = bmpOriginal.getHeight();
	    width = bmpOriginal.getWidth();
	    int depth = 20;

	    Bitmap bmpSephia = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(bmpSephia);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setScale(.3f, .3f, .3f, 1.0f);   
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    canvas.drawBitmap(bmpOriginal, 0, 0, paint);
	    for(int x=0; x < width; x++) {
	        for(int y=0; y < height; y++) {
	            c = bmpOriginal.getPixel(x, y);

	            r = Color.red(c);
	            g = Color.green(c);
	            b = Color.blue(c);

	            gry = (r + g + b) / 3;
	            r = g = b = gry;

	            r = r + (depth * 2);
	            g = g + depth;

	            if(r > 255) {
	              r = 255;
	            }
	            if(g > 255) {
	              g = 255;
	            }
	            bmpSephia.setPixel(x, y, Color.rgb(r, g, b));
	        }
	    }      
	    return bmpSephia;
	}
}

