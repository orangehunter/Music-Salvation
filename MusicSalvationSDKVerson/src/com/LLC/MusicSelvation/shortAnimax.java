package com.LLC.MusicSelvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class shortAnimax {
	
	Boolean animax_flag=false;
	
	double animax_count_flag=0;	
	int pic_number=0;
	int x;
	int y;
	
	Bitmap []pic;
	
	public shortAnimax(Bitmap pic[]){
		this.pic=new Bitmap [pic.length];
		this.pic=pic;
		this.pic_number=this.pic.length;
	}
	
	public void setPosition(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void start(){
		animax_flag=true;
	}
	
	public void drawEffect(double speed,Canvas canvas,Paint paint){
		if(animax_flag){
			animax_count_flag+=speed;
			if(((int)animax_count_flag)<pic_number){
				Graphic.drawPic(canvas, pic[((int)animax_count_flag)], x, y, 0, 255, paint);
			}else{
				animax_flag=false;
				animax_count_flag=0;
			}
		}
	}
	
	public Boolean getFlag(){
		return animax_flag;
	}
	
	public int getCountFlag(){
		return ((int)animax_count_flag);
	}
	public void recycle(){
		for(int i=0;i<pic.length;i++){
			pic[i].recycle();
		}		
	}
}
