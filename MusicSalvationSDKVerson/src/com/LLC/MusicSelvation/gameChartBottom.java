package com.LLC.MusicSelvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class gameChartBottom {
	int ID;
	int start;
	int startY;
	int target;
	int endX;
	int endY;
	int pointx,pointy;
	int y_flag;
	boolean flag,moveY_flag;
	int start_time;
	//int time_dis;
	double move_unit,move;
	double move_y_unit,move_y_time,moveY;
	Bottom btm;

	public gameChartBottom(int start,int target,int endX,int endY,MainActivity activity,Bitmap on,Bitmap off,int y){
		this.startY=y;
		this.pointy=y;
		btm=new Bottom(activity, on, off, -1000, startY);
		btm.setBottomTo(false);
		this.start=start;
		this.target=target;
		this.endX=endX;
		this.endY=endY;
		this.flag=false;
		if(start>target)
			y_flag=0;
		else
			y_flag=1;
	}
	public void start(int start_time,int time_dis,int id){
		Log.v("chartBottom", "start");
		this.ID=id;
		//this.time_dis=time_dis;
		this.start_time=start_time;
		move_unit=(target-start)/(time_dis*1.0);
		move_y_time=(endX-target)/move_unit;
		move_y_unit=(this.endY-this.startY)/move_y_time;
		moveY_flag=true;
		pointy=startY;
		this.flag=true;
	}
	public void drawChartBottom(int now_time,Canvas canvas,Paint paint){
		move=(now_time*1.0)-(start_time);
		pointx=(int)(start+move_unit*move);
		if(y_flag==1){
			if(pointx>target){
				if(moveY_flag){
					moveY=move*-1;
					moveY_flag=false;
				}
				pointy=(int)(startY+move_y_unit*(moveY+move));
			}
		}else{
			if(pointx<target){
				if(moveY_flag){
					moveY=move*-1;
					moveY_flag=false;
				}
				pointy=(int)(startY+move_y_unit*(moveY+move));
			}
		}
		btm.move(pointx, pointy);
		btm.drawBtm(canvas, paint);
		Log.v("chartBottom", "draw x"+pointx+" y "+pointy);
		if(y_flag==1){
			if(pointx>=endX){
				this.flag=false;
			}
		}else{
			if(pointx<=endX){
				this.flag=false;
			}
		}
	}
	public boolean getFlag(){
		return flag;
	}
	public int getId(){
		return ID;
	}
}
