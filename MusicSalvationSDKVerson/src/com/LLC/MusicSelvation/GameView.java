package com.LLC.MusicSelvation;
//

import org.json.JSONException;
import org.json.JSONObject;

import com.example.musicsalvationsdkverson.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class GameView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;

	boolean startFlag=true;
	Bitmap bg;   //背景
	Bitmap sight;  //準星
	Bitmap cpu;
	Bitmap virus;
	Bitmap boss;
	Bitmap game_easy;
	Bitmap game_normal;
	Bitmap game_hard;
	Bitmap freely;

	Bitmap circle;
	Bitmap square;
	Bitmap xx;
	Bitmap triangle;
	Bitmap grey_circle;
	Bitmap grey_square;
	Bitmap grey_xx;
	Bitmap grey_triangle;

	Bitmap track_leftdown;  //軌道
	Bitmap track_leftup;
	Bitmap track_rightdown;
	Bitmap track_rightup;

	Bitmap nice;
	Bitmap miss;
	Bitmap safe;
	Bitmap hit;

	Bitmap hpbar;  //狀態欄
	Bitmap hp_green;
	Bitmap hp_red;
	Bitmap hpfont;
	Bitmap hpfont_red;

	//Bitmap[] Cyan = new Bitmap [5];

	Bottom btn_circle;
	Bottom btn_square;
	Bottom btn_xx;
	Bottom btn_triangle;


	int hp_max = 35;
	int red_hpmax = 35;
	int hp = 15;
	int red_hp = 15;


	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標


	Paint paint;			//畫筆的參考
	int i=0,j=5;
	MainActivity activity;

	SoundPool sp;
	int sp_id[];
	
	static MediaPlayer mp;
	gameChartScan cs;
	
	static JSONObject 
	BtR=new JSONObject()
	,BtS=new JSONObject()
	,BtT=new JSONObject()
	,BtX=new JSONObject();
	
	Bitmap chart_r;
	Bitmap chart_s;
	Bitmap chart_t;
	Bitmap chart_x;
	
	int chartObject=20;
	gameChartBottom 
	cr_btm[]=new gameChartBottom[chartObject]
			,cs_btm[]=new gameChartBottom[chartObject]
					,ct_btm[]=new gameChartBottom[chartObject]
							,cx_btm[]=new gameChartBottom[chartObject];
	static int time_dis=3000;

	int temp;

	public GameView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者


	}
	//動畫實作方法
	/*public void CyanAnime(int num,float s,int a){


	}*/

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒

		bg = 	Graphic.bitSize(LoadBitmap(R.drawable.gamemap01), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		sight =	Graphic.bitSize(LoadBitmap(R.drawable.sight), 80, 80);
		cpu   = Graphic.bitSize(LoadBitmap(R.drawable.cpu_chips), 162, 162);


		circle = Graphic.bitSize(LoadBitmap(R.drawable.btn_circle), 128, 128);
		square = Graphic.bitSize(LoadBitmap(R.drawable.btn_square), 128, 128);
		triangle = Graphic.bitSize(LoadBitmap(R.drawable.btn_triangle), 128, 128);
		xx = Graphic.bitSize(LoadBitmap(R.drawable.btn_x), 128, 128);
		grey_circle = Graphic.bitSize(LoadBitmap(R.drawable.grey_circle), 128, 128);
		grey_square = Graphic.bitSize(LoadBitmap(R.drawable.grey_square), 128, 128);
		grey_triangle = Graphic.bitSize(LoadBitmap(R.drawable.grey_tirangle), 128, 128);
		grey_xx = Graphic.bitSize(LoadBitmap(R.drawable.grey_x), 128, 128);

		track_leftdown = Graphic.bitSize(LoadBitmap(R.drawable.track_leftdown), 645, 100);
		track_leftup = Graphic.bitSize(LoadBitmap(R.drawable.track_leftup), 645, 100);
		track_rightdown = Graphic.bitSize(LoadBitmap(R.drawable.track_rightdown), 645, 100);
		track_rightup = Graphic.bitSize(LoadBitmap(R.drawable.track_rightup), 645, 100);

		nice = Graphic.bitSize(LoadBitmap(R.drawable.nice), 175, 55);
		hit = Graphic.bitSize(LoadBitmap(R.drawable.hit), 175, 55);
		safe = Graphic.bitSize(LoadBitmap(R.drawable.safe), 175, 55);
		miss = Graphic.bitSize(LoadBitmap(R.drawable.miss), 175, 55);

		hpbar = Graphic.bitSize(LoadBitmap(R.drawable.hpbar), 1280, 63);
		hp_green = Graphic.bitSize(LoadBitmap(R.drawable.hp_green), 30, 23);
		hp_red = Graphic.bitSize(LoadBitmap(R.drawable.hp_red), 30, 23);
		hpfont = Graphic.bitSize(LoadBitmap(R.drawable.hpfont), 80, 25);
		hpfont_red = Graphic.bitSize(LoadBitmap(R.drawable.hpfont_red), 80, 25);
		freely = Graphic.bitSize(LoadBitmap(R.drawable.freely), 260, 30);
		boss = Graphic.bitSize(LoadBitmap(R.drawable.boss1), 200, 185);

		//特效光（測試中）

		/*Cyan[0] = Graphic.bitSize(LoadBitmap(R.drawable.cyan01), 200, 200);
		Cyan[1] = Graphic.bitSize(LoadBitmap(R.drawable.cyan02), 200, 200);
		Cyan[2] = Graphic.bitSize(LoadBitmap(R.drawable.cyan03), 200, 200);
		Cyan[3] = Graphic.bitSize(LoadBitmap(R.drawable.cyan04), 200, 200);
		Cyan[4] = Graphic.bitSize(LoadBitmap(R.drawable.cyan05), 200, 200);
		Cyan[5] = Graphic.bitSize(LoadBitmap(R.drawable.cyan06), 200, 200);*/

		btn_circle = new Bottom(activity, grey_circle, circle, 1191, 528);
		btn_square = new Bottom(activity, grey_square, square, 212, 656);
		btn_triangle = new Bottom(activity, grey_triangle, triangle, 80, 524);
		btn_xx = new Bottom(activity, grey_xx, xx, 1063, 656);
		
		chart_r=Graphic.bitSize(LoadBitmap(R.drawable.btn_circle), 100, 100);
		chart_s=Graphic.bitSize(LoadBitmap(R.drawable.btn_square), 100, 100);
		chart_t=Graphic.bitSize(LoadBitmap(R.drawable.btn_triangle), 100, 100);
		chart_x=Graphic.bitSize(LoadBitmap(R.drawable.btn_x), 100, 100);
		
		for(int i=0;i<chartObject;i++){
			cr_btm[i]=new gameChartBottom(1180,725, 1280/2, 247, activity, chart_r, chart_r, 189);
			cx_btm[i]=new gameChartBottom(1180,725,1280/2, 247,activity, chart_x, chart_x,306);
			ct_btm[i]=new gameChartBottom(-100,553,1280/2,247, activity, chart_t, chart_t,  189);
			cs_btm[i]=new gameChartBottom(-100,553,1280/2,247, activity, chart_s, chart_s, 306);
		}

		//TODO 載入音樂
		mp=MediaPlayer.create(this.getContext(), R.raw.freely_tomorrow);
		mp.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
				//TODO 切換至計分畫面
				activity.changeView(2);
            }
            });
		
		sp=new SoundPool(5, AudioManager.STREAM_MUSIC, 5);
		sp_id=new int[5];
		sp_id[0]=sp.load(activity, R.raw.tambourine, 1);
		sp_id[1]=sp.load(activity, R.raw.drum, 1);
		sp_id[2]=sp.load(activity, R.raw.drum, 1);
		sp_id[3]=sp.load(activity, R.raw.drum, 1);
		sp_id[4]=sp.load(activity, R.raw.drum, 1);

		Constant.Flag=true;
		new Thread(){
			@SuppressLint("WrongCall")
			public void run()
			{
				while(Constant.Flag){
					/*try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					SurfaceHolder myholder=GameView.this.getHolder();
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
			if(startFlag){
				Uri uri = activity.sendUri();
				if(uri!=null){
					JSONObject json=null;
					json=activity.read( uri);
					if(json==null){

					}else{
						try {
							BtR=json.getJSONObject("R");
							BtS=json.getJSONObject("S");
							BtT=json.getJSONObject("T");
							BtX=json.getJSONObject("X");
						} catch (JSONException e) {
							Log.e("GameView", "JSON load fail");
							e.printStackTrace();
						}
						cs=new gameChartScan(BtR,BtS,BtT,BtX);
						cs.Start();
					}	
				}
				
				mp.setVolume(activity.mp_Voiume, activity.mp_Voiume);
				mp.start();
				startFlag=false;
			}
			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.BLACK);//界面設定為黑色
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//背景
			Graphic.drawPic(canvas, hpbar, 641, 31, 0, 255, paint);
			Graphic.drawPic(canvas, hpfont, 95, 50, 0, 255, paint);
			Graphic.drawPic(canvas, freely, 132, 20, 0, 255, paint);
			//Graphic.drawPic(canvas, hpfont_red, 95, 50, 0, 255, paint);


			Graphic.drawPic(canvas, track_leftdown, 315, 290, 0, 255, paint);
			Graphic.drawPic(canvas, track_leftup, 315, 210, 0, 255, paint);
			Graphic.drawPic(canvas, track_rightdown, 963, 290, 0, 255, paint);
			Graphic.drawPic(canvas, track_rightup, 963, 209, 0, 255, paint);
			
			int now_time=mp.getCurrentPosition();
			for(int i=0;i<chartObject;i++){
				if(cr_btm[i].getFlag()){
					cr_btm[i].drawChartBottom(now_time, canvas, paint);
				}
				if(cs_btm[i].getFlag()){
					cs_btm[i].drawChartBottom(now_time, canvas, paint);
				}
				if(ct_btm[i].getFlag()){
					ct_btm[i].drawChartBottom(now_time, canvas, paint);
				}
				if(cx_btm[i].getFlag()){
					cx_btm[i].drawChartBottom(now_time, canvas, paint);
				}
			}

			Graphic.drawPic(canvas, sight, 725, 306, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 725, 189, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 553, 306, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 553, 189, 0, 255, paint);
			Graphic.drawPic(canvas, cpu, 640, 247, 0, 255, paint);


			btn_circle.drawBtm(canvas, paint);
			btn_square.drawBtm(canvas, paint);
			btn_triangle.drawBtm(canvas, paint);
			btn_xx.drawBtm(canvas, paint);

		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();

		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN://按下
			if(deJump==true){//防止彈跳part1
				if(btn_circle.isIn(pointx, pointy)){
					sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					for(int i=0;i<chartObject;i++){
						if(!cr_btm[i].getFlag()){
							cr_btm[i].start(mp.getCurrentPosition(), time_dis, mp.getCurrentPosition()/10);
							break;
						}
					}
					btn_circle.setBottomTo(true);
				}
				if(btn_square.isIn(pointx, pointy)){
					sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					btn_square.setBottomTo(true);
				}
				if(btn_triangle.isIn(pointx, pointy)){
					sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					btn_triangle.setBottomTo(true);
				}
				if(btn_xx.isIn(pointx, pointy)){
					sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					btn_xx.setBottomTo(true);
				}
				/*if(startbtm.isIn(pointx, pointy)){
						//進入地圖畫面
						activity.changeView(2);
						this.toEditView = 0;
					}
					/*this.toEditView++;
				if(this.toEditView>10){
					Constant.Flag=false;
					activity.changeView(6);
				}*/
			}
			deJump=false;
			break;

		case MotionEvent.ACTION_UP:
			if(deJump==false){
				if(btn_circle.isIn(pointx, pointy)){
					btn_circle.setBottomTo(false);
				}
				if(btn_square.isIn(pointx, pointy)){
					btn_square.setBottomTo(false);
				}
				if(btn_triangle.isIn(pointx, pointy)){
					btn_triangle.setBottomTo(false);
				}
				if(btn_xx.isIn(pointx, pointy)){
					btn_xx.setBottomTo(false);
				}

				/*if(.isIn(pointx, pointy)){
						//TODO 離開遊戲未寫
					}*/
			}
			deJump=true;
			break;
		}

		return true;
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
		//TODO 暫停選單
		mp.stop();
		startFlag=true;
		Constant.Flag=false;
	}


}
