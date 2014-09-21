package com.LLC.MusicSelvation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Graphic {

	static Bitmap bitSize(Bitmap bf,int f,int g){//圖片縮放
		int bw=0;
		int bh=0;
		float scaleWidth=0;
		float scaleHeight=0;
		// 取得想要缩放的matrix参数
		Matrix matrix = new Matrix();
		while(scaleWidth<=0&&scaleHeight<=0){
			bw=bf.getWidth();
			bh=bf.getHeight();
			scaleWidth = Coordinate.CoordinateX(f) / bw;
			scaleHeight = Coordinate.CoordinateY(g)/ bh;
		}
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bit=Bitmap.createBitmap(bf, 0,0,bw,bh, matrix, true);//縮放圖片
		matrix.reset();
		//bf.recycle();//銷毀原圖
		//bf=null;

		return bit;
	}

	static void drawPic(Canvas canvas,Bitmap bit,int mid_x,int mid_y,float rot,int alpha,Paint paint){
		paint.setAntiAlias(true);
		paint.setAlpha(alpha);
		float x=Coordinate.CoordinateX(mid_x),y=Coordinate.CoordinateY(mid_y);
		if(rot%360==0)
			canvas.drawBitmap(bit, x-(bit.getWidth()/2), y-(bit.getHeight()/2), paint);
		else{
			Matrix matrix = new Matrix();
			matrix.preRotate(rot, (bit.getWidth()/2), (bit.getHeight()/2));
			matrix.postTranslate( x-(bit.getWidth()/2), y-(bit.getHeight()/2));
			canvas.drawBitmap(bit, matrix, paint);
			matrix.reset();
		}
		paint.reset();
	}

	static void drawLine(Canvas canvas,int color,int start_x,int start_y,int end_x,int end_y,int with,Paint paint){
		paint.setColor(color);																	//設定顏色
		paint.setStrokeWidth(with);    //設定線寬
		canvas.drawLine(Coordinate.CoordinateX(start_x), Coordinate.CoordinateY(start_y), Coordinate.CoordinateX(end_x),Coordinate.CoordinateY( end_y), paint);      //繪製直線
		paint.reset();
	}
}
