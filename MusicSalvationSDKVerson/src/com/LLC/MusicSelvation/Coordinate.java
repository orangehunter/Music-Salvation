package com.LLC.MusicSelvation;

public class Coordinate {
	static float GAME_WIDTH_UNIT=(float) (Constant.SCREEN_WIDTH/1280.0);
	static float SCREEN_HEIGHT_UNIT=(float) (Constant.SCREEN_HIGHT/720.0);

	public static float CoordinateX(int x){
		return  (x*GAME_WIDTH_UNIT);
	}
	public static int DeCoordinateX(float x){
		return  (int)(x/GAME_WIDTH_UNIT);
	}

	public static float CoordinateY(int y){
		return  (y*SCREEN_HEIGHT_UNIT);
	}
	public static int DeCoordinateY(float y){
		return  (int)(y/SCREEN_HEIGHT_UNIT);
	}
	
	public static int AnalogSpeedMove(int now,int tomove){
		if(now>tomove){
			if(now-tomove>20)
				now-=(now-tomove)/10;
			if(now-tomove<=20)
				now-=1;
		}
		if(tomove>now){
			if(tomove-now>20)
				now+=(tomove-now)/10;
			if(tomove-now<=20)
				now+=1;
		}
		return now;
	}
}
