package com.LLC.MusicSelvation;
//

import com.example.musicsalvationsdkverson.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall" })
public class MapView extends SurfaceView
implements SurfaceHolder.Callback{

	Bitmap wmap;
	Bitmap left_back;
	Bitmap left_exit;    //<<箭頭-淺色  離開MENU用
	Bitmap left_exit2;   //<<箭頭-深色

	Bitmap menubtn;      //>>箭頭-淺色  呼叫MENU用
	Bitmap menubtn2;    //>>箭頭-深色

	Bitmap left_exitback; //離開按鈕背景
	Bitmap left_btmback;  //MENU選項背景
	Bitmap btnvol;
	Bitmap btnvol2;
	Bitmap chbtmse;
	Bitmap chbtmse2;
	Bitmap song_vol;
	Bitmap song_vol2;
	Bitmap remain;
	Bitmap remain2;
	Bitmap offline;

	Bitmap volchback;
	Bitmap volBar;
	Bitmap volbtn;
	Bitmap sechange;
	
	Bitmap se01;
	Bitmap se01l;
	Bitmap se02;
	Bitmap se02l;
	Bitmap se03;
	Bitmap se03l;
	Bitmap se04;
	Bitmap se04l;
	Bitmap se05;
	Bitmap se05l;


	Bottom menubtm; //MENU按鈕
	Bottom left_btm1;
	Bottom left_btm2;
	Bottom left_btm3;
	Bottom left_btm4;
	Bottom left_btm5;
	
	Bottom sebtm1;
	Bottom sebtm2;
	Bottom sebtm3;
	Bottom sebtm4;
	Bottom sebtm5;

	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標
	boolean deJump = true;

	float rot=0;
	int ma=5;
	int x = 0;
	int alpha = 10;
	int menuFlag = 0;

	int mbgx = -500;
	int mbgx2 = 13;

	int leftbtmmx1 = -400;
	int leftbtmmove1 = 77;

	int leftbtmmx2 = -400;
	int leftbtmmove2 = 114;

	int leftbtmmx3 = -400;
	int leftbtmmove3 = 138;

	int leftbtmmx4 = -400;
	int leftbtmmove4 = 114;

	int leftbtmmx5 = -400;
	int leftbtmmove5 = 77;
	
	int sevolmx = -200;
	int songvolmx = -200;
	int sevolmovex = 381;
	int songvolmovex = 425;
	int sechx = -254;
	int sechmovex = 344;
	
	int baralpha = 0;
	int sebaralpha = 0;

	Paint paint;			//畫筆的參考
	int i=0,j=5;
	MainActivity activity;

	public MapView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者


	}

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒
		wmap =Graphic.bitSize(LoadBitmap( R.drawable.wmap), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		left_back=Graphic.bitSize(LoadBitmap( R.drawable.menubackground), 846, 871);
		left_exit = Graphic.bitSize(LoadBitmap( R.drawable.left_exit1), 134, 75);
		left_exit2 = Graphic.bitSize(LoadBitmap( R.drawable.left_exit2), 134, 75);
		left_exitback = Graphic.bitSize(LoadBitmap( R.drawable.left_exitback) , 229, 78);
		menubtn  = Graphic.bitSize(LoadBitmap( R.drawable.menubtn) , 134, 75);
		menubtn2  = Graphic.bitSize(LoadBitmap( R.drawable.menubtn2) , 134, 75);
		left_btmback = Graphic.bitSize(LoadBitmap( R.drawable.left_btmback) , 369, 72);
		btnvol = Graphic.bitSize(LoadBitmap( R.drawable.button_vol) , 215, 67);
		btnvol2 = Graphic.bitSize(LoadBitmap( R.drawable.button_vol2) , 208, 67);
		chbtmse = Graphic.bitSize(LoadBitmap( R.drawable.chbtmse) , 267, 69);
		chbtmse2 = Graphic.bitSize(LoadBitmap( R.drawable.chbtmse2) , 263, 65);
		song_vol = Graphic.bitSize(LoadBitmap( R.drawable.song_vol) , 221, 66);
		song_vol2 = Graphic.bitSize(LoadBitmap( R.drawable.song_voltou) , 215, 66);
		remain = Graphic.bitSize(LoadBitmap( R.drawable.remain) , 270, 72);
		remain2 = Graphic.bitSize(LoadBitmap( R.drawable.remain_tou) , 262, 66);
		offline = Graphic.bitSize(LoadBitmap( R.drawable.offline) , 114, 68);
		volchback =  Graphic.bitSize(LoadBitmap(R.drawable.volchange_back),402 ,67 );
		volBar = Graphic.bitSize(LoadBitmap(R.drawable.volbar),216 ,37 );
		volbtn = Graphic.bitSize(LoadBitmap(R.drawable.volbtn),31 ,31 );
		sechange = Graphic.bitSize(LoadBitmap(R.drawable.sechange),254 ,324 );
		
		se01 = Graphic.bitSize(LoadBitmap(R.drawable.se01),70 ,70 );
		se01l = Graphic.bitSize(LoadBitmap(R.drawable.se01l),70 ,70 );
		se02 = Graphic.bitSize(LoadBitmap(R.drawable.se02),70 ,70 );
		se02l = Graphic.bitSize(LoadBitmap(R.drawable.se02l),70 ,70 );
		se03 = Graphic.bitSize(LoadBitmap(R.drawable.se03),70 ,70 );
		se03l = Graphic.bitSize(LoadBitmap(R.drawable.se03l),70 ,70 );
		se04 = Graphic.bitSize(LoadBitmap(R.drawable.se04),70 ,70 );
		se04l = Graphic.bitSize(LoadBitmap(R.drawable.se04l),70 ,70 );
		se05 = Graphic.bitSize(LoadBitmap(R.drawable.se05),70 ,70 );
		se05l = Graphic.bitSize(LoadBitmap(R.drawable.se05l),70 ,70 );

		menubtm = new Bottom(activity, left_exitback, left_exitback, 110, 35);
		left_btm1= new Bottom(activity, btnvol2, btnvol, 114, 166);
		left_btm2= new Bottom(activity, song_vol2, song_vol, 114, 257);
		left_btm3= new Bottom(activity, chbtmse2, chbtmse, 136, 355);
		left_btm4= new Bottom(activity, remain2, remain, 134, 451);
		left_btm5= new Bottom(activity, offline, offline, 114, 542);
		
		sebtm1 = new Bottom(activity, se01, se01l, 390, 390);
		sebtm2 = new Bottom(activity, se02, se02l, 393, 451);
		sebtm3 = new Bottom(activity, se03, se03l, 393, 511);
		sebtm4 = new Bottom(activity, se04, se04l, 393, 569);
		sebtm5 = new Bottom(activity, se05, se05l, 393, 628);

		Constant.Flag=true;
		new Thread(){
			@SuppressLint("WrongCall")
			public void run()
			{
				while(Constant.Flag){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SurfaceHolder myholder=MapView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();//取得畫布
					onDraw(canvas);
					if(canvas != null){
						myholder.unlockCanvasAndPost(canvas);
					}
				}

			}
		}.start();
	}
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {//重新定義的繪制方法
		if(canvas!=null){
			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.BLACK);//界面設定為黑色
			Graphic.drawPic(canvas, wmap, 1280/2, 720/2, 0, 255, paint);//地圖
			rot-= 0.5;
			if(rot == -360){
				rot = 0;
			}
			//離開按鈕
			x+=alpha;
			if(x >= 250){
				alpha = -5;
			}
			if(x < 10){
				alpha = 5;
			}
			if(menuFlag == 0)
			{
				menubtm.drawBtm(canvas, paint);
				Graphic.drawPic(canvas, menubtn, 66, 34, 0, 255, paint);
				Graphic.drawPic(canvas, menubtn2, 66, 34, 0, x, paint);
				mbgx=Coordinate.AnalogSpeedMove(mbgx, -500);
				leftbtmmx1 = Coordinate.AnalogSpeedMove(leftbtmmx1,-400);
				leftbtmmx2 = Coordinate.AnalogSpeedMove(leftbtmmx2,-400);
				leftbtmmx3 = Coordinate.AnalogSpeedMove(leftbtmmx3,-400);
				leftbtmmx4 = Coordinate.AnalogSpeedMove(leftbtmmx4,-400);
				leftbtmmx5 = Coordinate.AnalogSpeedMove(leftbtmmx5,-400);
				sevolmx = Coordinate.AnalogSpeedMove(sevolmx, -100);
				baralpha = 0;
			}
			Graphic.drawPic(canvas, left_back, mbgx, 374, rot, 255, paint);
			Graphic.drawPic(canvas, volchback,sevolmx, 167, 0, baralpha, paint);
			Graphic.drawPic(canvas, volchback,songvolmx, 259, 0, baralpha, paint);
			Graphic.drawPic(canvas, sechange,344, 504, 0, sebaralpha ,paint);
			if(menuFlag == 1)  
			{
				//左menu背景
				mbgx=Coordinate.AnalogSpeedMove(mbgx, mbgx2);
				menubtm.drawBtm(canvas, paint);
				Graphic.drawPic(canvas, left_exit, 66, 34, 0, 255, paint);
				Graphic.drawPic(canvas, left_exit2, 66, 34, 0, x, paint);
				
				if(left_btm1.getBottom()){
					sevolmx = Coordinate.AnalogSpeedMove(sevolmx, sevolmovex);
					baralpha = 255;
				}
				else if(!left_btm1.getBottom()){
					sevolmx = Coordinate.AnalogSpeedMove(sevolmx, -300);
				}
				if(left_btm2.getBottom()){
					songvolmx = Coordinate.AnalogSpeedMove(songvolmx, songvolmovex);
					baralpha = 255;
				}
				else if(!left_btm2.getBottom()){
					songvolmx = Coordinate.AnalogSpeedMove(songvolmx, -300);
				}
				if(left_btm3.getBottom()){
					sebtm1.drawBtm(canvas, paint);
					sebtm2.drawBtm(canvas, paint);
					sebtm3.drawBtm(canvas, paint);
					sebtm4.drawBtm(canvas, paint);
					sebtm5.drawBtm(canvas, paint);
					sebaralpha = 255;
				}
				else if(!left_btm3.getBottom()){
					sebaralpha = 0;
				}
				
				Graphic.drawPic(canvas, left_btmback,leftbtmmx1, 166, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx2, 257, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx3, 354, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx4, 450, 0, 255, paint);
				Graphic.drawPic(canvas, left_btmback,leftbtmmx5, 542, 0, 255, paint);
				leftbtmmx1 = Coordinate.AnalogSpeedMove(leftbtmmx1, leftbtmmove1);
				leftbtmmx2 = Coordinate.AnalogSpeedMove(leftbtmmx2, leftbtmmove2);
				leftbtmmx3 = Coordinate.AnalogSpeedMove(leftbtmmx3, leftbtmmove3);
				leftbtmmx4 = Coordinate.AnalogSpeedMove(leftbtmmx4, leftbtmmove4);
				leftbtmmx5 = Coordinate.AnalogSpeedMove(leftbtmmx5, leftbtmmove5);

				left_btm1.move(mbgx+101, 166);
				left_btm2.move(mbgx+101, 257);
				left_btm3.move(mbgx+123, 355);
				left_btm4.move(mbgx+120, 451);
				left_btm5.move(mbgx+101, 542);
				left_btm1.drawBtm(canvas, paint);
				left_btm2.drawBtm(canvas, paint);
				left_btm3.drawBtm(canvas, paint);
				left_btm4.drawBtm(canvas, paint);
				left_btm5.drawBtm(canvas, paint);


			}




			//canvas.drawText(String.valueOf(menuFlag), Coordinate.CoordinateX(360), Coordinate.CoordinateY(360), paint);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://按下
			if(deJump == true)
			{
				if(menubtm.isIn(pointx, pointy)){
					if(menuFlag == 0){
						menuFlag = 1;
					}
					else if(menuFlag == 1){
						menuFlag = 0;
					}
				}
				//------------------------------
				if(left_btm1.isIn(pointx, pointy)){
					if(!left_btm1.getBottom()){
					left_btm1.setBottomTo(true);
					left_btm2.setBottomTo(false);
					left_btm3.setBottomTo(false);
					left_btm4.setBottomTo(false);
					left_btm5.setBottomTo(false);
					}
					else if(left_btm1.getBottom()){
						left_btm1.setBottomTo(false);
						
					}
				}
				else if(left_btm2.isIn(pointx, pointy))
				{
					 if(!left_btm2.getBottom()){
						
						left_btm1.setBottomTo(false);
						left_btm2.setBottomTo(true);
						left_btm3.setBottomTo(false);
						left_btm4.setBottomTo(false);
						left_btm5.setBottomTo(false);
					}
					else if(left_btm2.getBottom()){
						left_btm2.setBottomTo(false);
						
					}
				}
				else if(left_btm3.isIn(pointx, pointy)){
					if(!left_btm3.getBottom()){
					left_btm1.setBottomTo(false);
					left_btm2.setBottomTo(false);
					left_btm3.setBottomTo(true);
					left_btm4.setBottomTo(false);
					left_btm5.setBottomTo(false);
					}
					else if(left_btm3.getBottom()){
						left_btm3.setBottomTo(false);
						
					}
					
				}
				else if(left_btm4.isIn(pointx, pointy)){
					if(!left_btm4.getBottom()){
					activity.changeView(1);
					}
				}
				else if(left_btm5.isIn(pointx, pointy)){
					if(!left_btm5.getBottom()){
						
					}
				}
				//-------------音效按鈕切換區------------------
				if(sebtm1.isIn(pointx, pointy)){
					if(!sebtm1.getBottom()){
						sebtm1.setBottomTo(true);
						sebtm2.setBottomTo(false);
						sebtm3.setBottomTo(false);
						sebtm4.setBottomTo(false);
						sebtm5.setBottomTo(false);
					}
					else if(sebtm1.getBottom()){
						sebtm1.setBottomTo(false);
					}
				}
				else if(sebtm2.isIn(pointx, pointy)){
					if(!sebtm2.getBottom()){
						sebtm1.setBottomTo(false);
						sebtm2.setBottomTo(true);
						sebtm3.setBottomTo(false);
						sebtm4.setBottomTo(false);
						sebtm5.setBottomTo(false);
					}
					else if(sebtm2.getBottom()){
						sebtm2.setBottomTo(false);
					}
				}
				else if(sebtm3.isIn(pointx, pointy)){
					if(!sebtm3.getBottom()){
						sebtm1.setBottomTo(false);
						sebtm2.setBottomTo(false);
						sebtm3.setBottomTo(true);
						sebtm4.setBottomTo(false);
						sebtm5.setBottomTo(false);
					}
					else if(sebtm3.getBottom()){
						sebtm3.setBottomTo(false);
					}
				}
				else if(sebtm4.isIn(pointx, pointy)){
					if(!sebtm4.getBottom()){
						sebtm1.setBottomTo(false);
						sebtm2.setBottomTo(false);
						sebtm3.setBottomTo(false);
						sebtm4.setBottomTo(true);
						sebtm5.setBottomTo(false);
					}
					else if(sebtm4.getBottom()){
						sebtm4.setBottomTo(false);
					}
				}
				else if(sebtm5.isIn(pointx, pointy)){
					if(!sebtm5.getBottom()){
						sebtm1.setBottomTo(false);
						sebtm2.setBottomTo(false);
						sebtm3.setBottomTo(false);
						sebtm4.setBottomTo(false);
						sebtm5.setBottomTo(true);
					}
					else if(sebtm5.getBottom()){
						sebtm5.setBottomTo(false);
					}
				}

			}
			deJump = false;
			break;
			//---------------------------------------
		case MotionEvent.ACTION_UP:
			if(deJump == false){
				if(left_btm1.isIn(pointx, pointy)){
				}
				else if(left_btm2.isIn(pointx, pointy)){
					
				}
				else if(left_btm3.isIn(pointx, pointy)){
					
				}
				else if(left_btm4.isIn(pointx, pointy)){
					
				}
				else if(left_btm5.isIn(pointx, pointy)){
					
				}
			}
			deJump  = true;
			break;
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
		Constant.Flag=false;
	}


}
