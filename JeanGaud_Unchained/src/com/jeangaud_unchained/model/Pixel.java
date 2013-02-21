package com.jeangaud_unchained.model;

import android.graphics.Color;

public class Pixel {
	private int x,y,color;
	
	public Pixel(int x, int y, int color){
		this.x=x;
		this.y=y;
		this.color=color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public boolean isBlack(){
		int r,g,b;
		
		r=Color.red(this.getColor());
		g=Color.green(this.getColor());
		b=Color.blue(this.getColor());
		
		if((r+b+g)==0){
			return true;
		}else return false;
	}
	
	
}
