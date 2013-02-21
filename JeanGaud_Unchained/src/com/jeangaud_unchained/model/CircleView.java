package com.jeangaud_unchained.model;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class CircleView extends View {
	
	private Paint paint;

	int x=100;
	int y=100;
	int width = 100;
	int MAX_HEIGHT;
	int MAX_WIDTH;
	
	private ShapeDrawable mDrawable;

	public CircleView(Context context) {
		super(context);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		MAX_WIDTH = display.getWidth();
		MAX_HEIGHT = display.getHeight(); 
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(Color.BLACK);
		initPos();
	}
	
	public void initPos() {
		this.y = MAX_HEIGHT/2;
		this.x = MAX_WIDTH/2;
	}
	
	public CircleView(Context context, AttributeSet attr) {
		super(context, attr);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		MAX_WIDTH = display.getWidth();
		MAX_HEIGHT = display.getHeight(); 
		mDrawable = new ShapeDrawable(new OvalShape());
		mDrawable.getPaint().setColor(Color.BLACK);
		initPos();
	}
	
	protected void onDraw(Canvas canvas) {
		mDrawable.setBounds(x, y, x + width, y + width);

		mDrawable.draw(canvas);
	}
	
	public void updatePosition(int xDelta, int yDelta) {
		if((xDelta > 0 && x < MAX_WIDTH-width/2) || (xDelta < 0 && x > -width/2)) x += xDelta;
		if((yDelta > 0 && y < MAX_HEIGHT-200+width/2) || (yDelta < 0 && y > -width/2)) y += yDelta;
	}

}
