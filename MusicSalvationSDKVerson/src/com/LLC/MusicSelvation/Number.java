package com.LLC.MusicSelvation;

import com.example.musicsalvationsdkverson.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Number {
	int default_width;
	int default_height;
	int width;
	int height;

	Bitmap origin[]=new Bitmap[7];
	Bitmap num_blue[]=new Bitmap[10];
	Bitmap num_cyan[]=new Bitmap[10];
	Bitmap num_green[]=new Bitmap[10];
	Bitmap num_gray[]=new Bitmap[10];
	Bitmap num_red[]=new Bitmap[10];
	Bitmap num_white[]=new Bitmap[10];
	Bitmap num_yellow[]=new Bitmap[10];
	public Number(Resources res){
		origin[0]=LoadBitmap(res,R.drawable.num_blue);
		origin[1]=LoadBitmap(res,R.drawable.num_cyan);
		origin[2]=LoadBitmap(res,R.drawable.num_green);
		origin[3]=LoadBitmap(res,R.drawable.num_gray);
		origin[4]=LoadBitmap(res,R.drawable.num_red);
		origin[5]=LoadBitmap(res,R.drawable.num_white);
		origin[6]=LoadBitmap(res,R.drawable.num_yellow);
		default_width=origin[0].getWidth()/10;
		default_height=origin[0].getHeight();
		for(int i=0;i<10;i++){
			num_blue[i]=Graphic.CutArea(origin[0], 0+i*width, 0, width, height);
			num_cyan[i]=Graphic.CutArea(origin[1], 0+i*width, 0, width, height);
			num_green[i]=Graphic.CutArea(origin[2], 0+i*width, 0, width, height);
			num_gray[i]=Graphic.CutArea(origin[3], 0+i*width, 0, width, height);
			num_red[i]=Graphic.CutArea(origin[4], 0+i*width, 0, width, height);
			num_white[i]=Graphic.CutArea(origin[5], 0+i*width, 0, width, height);
			num_yellow[i]=Graphic.CutArea(origin[6], 0+i*width, 0, width, height);
		}
		for(int i=0;i<7;i++){
			origin[i].recycle();
		}
		this.reset();
	}
	public Bitmap LoadBitmap(Resources res,int r){
		return BitmapFactory.decodeResource(res, r);
	}
	public void setSize(int width,int height){
		this.width=width;
		this.height=height;
	}
	public void drawNumberFrontStart(int left,int top){

	}
	public void drawNumberBackStart(int right,int top){

	}
	public void reset(){
		this.width=this.default_width;
		this.height=this.default_height;
	}
}
