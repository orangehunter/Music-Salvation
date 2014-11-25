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
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility", "NewApi" })
public class GameView extends SurfaceView
implements SurfaceHolder.Callback{


	boolean startFlag=true;
	Bitmap bg;   //背景
	Bitmap sight;  //準星
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

	//TAG 浮游砲宣告===============================================
	Bitmap d_red;
	Bitmap d_blue;
	Bitmap d_yellow;
	Bitmap d_green;

	//浮游砲宣告-----------------------------------------------

	//TAG 光束===================================================
	Bitmap lazer_red;
	Bitmap lazer_blue;
	Bitmap lazer_green;
	Bitmap lazer_yellow;
	//光束----------------------------------------------------

	Bitmap track;  //軌道

	Bitmap virus_red;
	Bitmap virus_yellow;
	Bitmap virus_blue;
	Bitmap virus_green;

	Bitmap nice;
	Bitmap miss;
	Bitmap safe;
	Bitmap hit;
	Bitmap hits;

	Bitmap titlebar;  //狀態欄
	Bitmap hpbar;
	Bitmap hpfont;
	Bitmap hpfont_red;


	//宣告pause所需要的圖片==========================================
	Bitmap pause;
	Bitmap pause2;
	Bitmap pause_back;
	Bitmap pause_black; //pause時要用的黑底
	Bitmap re_play;
	Bitmap re_start;
	Bitmap re_map;
	//宣告pause所需要的圖片-------------------------------------------


	//TAG 特效光宣告================================
	Bitmap[] Cyan 	= new Bitmap [6],
			Red		= new Bitmap [6],
			Green 	= new Bitmap [6],
			Yellow 	= new Bitmap [6],
			Blue 	= new Bitmap [6];

	int Effect_numbers=5;
	double Effect_speed=0.5;
	shortAnimax  []
			Effect_Cyan		=new shortAnimax[Effect_numbers],
			Effect_Red		=new shortAnimax[Effect_numbers],
			Effect_Green	=new shortAnimax[Effect_numbers],
			Effect_Yellow	=new shortAnimax[Effect_numbers],
			Effect_Blue		=new shortAnimax[Effect_numbers];
	//特效光宣告----------------------------------

	Bottom btn_circle;
	Bottom btn_square;
	Bottom btn_xx;
	Bottom btn_triangle;

	//宣告PAUSE、返回遊戲、從頭開始、返回關卡地圖按鈕==================================================
	Bottom btn_pause;
	Bottom btn_re_play;
	Bottom btn_re_start;
	Bottom btn_re_map;
	//宣告PAUSE、返回遊戲、從頭開始、返回關卡地圖按鈕--------------------------------------------------

	//過關等級================================================
	Bitmap rank_s;
	Bitmap rank_a;
	Bitmap rank_b;
	Bitmap rank_c;
	Bitmap rank_d;
	Bitmap rank_e;
	Bitmap rank_f;
	//過關等級------------------------------------------------

	//能量條切換=============================================
	Bitmap enebar[] = new Bitmap[10];

	double enebar_speed = 0.1;
	shortAnimax change_enebar;
	boolean ene_flag;
	//能量條切換---------------------------------------------

	Number score;

	int hp = 20;
	int hp_max=20;
	int hp_x;
	int hp_color=Color.GREEN;
	int hp_to_yellow=12;
	int hp_to_red = 6;


	int boss_show;
	int boss_kill;
	boolean boss_Flag;
	boolean boss_attack_Flag;
	int boss_y=242;
	int boss_x;
	int boss_x_side=1025;
	int boss_x_middle=640;

	int combo=0;
	int maxcombo = 0;
	int sc_nice= 0;
	int sc_hit= 0;
	int sc_safe= 0;
	int sc_miss= 0;
	int percent = 0;
	int sc_score = 0;

	//TAG 控制判定顯示FLAG==============================
	int Hitflag = 0;
	int Hitcount = 0;
	//控制判定顯示FLAG------------------------------

	int pointx;
	int pointy;

	Paint paint;			//畫筆的參考
	int i=0,j=5;
	MainActivity activity;

	SoundPool sp;
	int sp_id[];

	static MediaPlayer mp;
	chartScan cs;

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

	static int time_dis;
	SparseArray<PointF> mActivePointers=new SparseArray<PointF>();
	SparseArray<Integer> btn_pointer=new SparseArray<Integer>();


	public GameView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//設定生命周期回調接口的實現者
	}


	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		combo=0;
		maxcombo = 0;
		sc_nice= 0;
		sc_hit= 0;
		sc_safe= 0;
		sc_miss= 0;
		percent = 0;
		sc_score = 0;
		time_dis=3000/activity.speed;
		this.hp=this.hp_max;
		/*int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
	              | View.SYSTEM_UI_FLAG_FULLSCREEN;
		this.setSystemUiVisibility(uiOptions);*/
		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒

		score =new Number(getResources());

		bg = 	Graphic.bitSize(LoadBitmap(R.drawable.gamemap01), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		sight =	Graphic.bitSize(LoadBitmap(R.drawable.sightv2), 100, 100);
		//cpu   = Graphic.bitSize(LoadBitmap(R.drawable.cpu_chips), 162, 162);



		game_easy = Graphic.bitSize(LoadBitmap(R.drawable.easyv2), 205, 78);
		game_normal = Graphic.bitSize(LoadBitmap(R.drawable.normalv2psd), 205, 78);
		game_hard = Graphic.bitSize(LoadBitmap(R.drawable.hardv2), 205, 78);



		//pause圖片載入=====================================================================
		pause = Graphic.bitSize(LoadBitmap(R.drawable.pause), 205, 78);
		pause2 = Graphic.bitSize(LoadBitmap(R.drawable.pause2), 205, 78);
		pause_back = Graphic.bitSize(LoadBitmap(R.drawable.pasue_back), 450, 310);
		pause_black = Graphic.bitSize(LoadBitmap(R.drawable.pause_black), 1280, 720);

		re_map = Graphic.bitSize(LoadBitmap(R.drawable.return_map), 390, 75);
		re_play = Graphic.bitSize(LoadBitmap(R.drawable.re_play), 390, 75);
		re_start = Graphic.bitSize(LoadBitmap(R.drawable.re_start), 390, 75);

		//pause圖片載入-----------------------------------------------------------------------


		circle = Graphic.bitSize(LoadBitmap(R.drawable.btn_circle_v2), 200, 200);
		square = Graphic.bitSize(LoadBitmap(R.drawable.btn_square_v2), 200, 200);
		triangle = Graphic.bitSize(LoadBitmap(R.drawable.btn_triangle_v2), 200, 200);
		xx = Graphic.bitSize(LoadBitmap(R.drawable.btn_x_v2), 200, 200);
		grey_circle = Graphic.bitSize(LoadBitmap(R.drawable.grey_circle), 200, 200);
		grey_square = Graphic.bitSize(LoadBitmap(R.drawable.grey_square), 200, 200);
		grey_triangle = Graphic.bitSize(LoadBitmap(R.drawable.grey_tirangle), 200, 200);
		grey_xx = Graphic.bitSize(LoadBitmap(R.drawable.grey_x), 200, 200);


		//TAG 浮游砲與光束=======================================================================
		d_blue = Graphic.bitSize(LoadBitmap(R.drawable.d_blue), 250, 170);
		d_red = Graphic.bitSize(LoadBitmap(R.drawable.d_red), 250, 170);
		d_green = Graphic.bitSize(LoadBitmap(R.drawable.d_green), 260, 130);
		d_yellow = Graphic.bitSize(LoadBitmap(R.drawable.d_yellow), 260, 130);
		lazer_red = Graphic.bitSize(LoadBitmap(R.drawable.lazer_r), 220, 125);
		lazer_blue = Graphic.bitSize(LoadBitmap(R.drawable.lazer_b), 215, 112);
		lazer_green = Graphic.bitSize(LoadBitmap(R.drawable.lazer_g2), 290, 50);
		lazer_yellow = Graphic.bitSize(LoadBitmap(R.drawable.lazer_y2), 275, 50);

		//浮游砲與光束------------------------------------------------------------------------


		track = Graphic.bitSize(LoadBitmap(R.drawable.track_v2), 80, 660);

		virus_blue = Graphic.bitSize(LoadBitmap(R.drawable.virus_blue), 80, 80);
		virus_red = Graphic.bitSize(LoadBitmap(R.drawable.virus_red), 80, 80);
		virus_yellow = Graphic.bitSize(LoadBitmap(R.drawable.virus_yello), 80, 80);
		virus_green = Graphic.bitSize(LoadBitmap(R.drawable.virus_green), 80, 80);

		nice = Graphic.bitSize(LoadBitmap(R.drawable.nice), 175, 55);
		hit = Graphic.bitSize(LoadBitmap(R.drawable.hit), 175, 55);
		safe = Graphic.bitSize(LoadBitmap(R.drawable.safe), 175, 55);
		miss = Graphic.bitSize(LoadBitmap(R.drawable.miss), 175, 55);
		hits =  Graphic.bitSize(LoadBitmap(R.drawable.hits), 155, 80);

		titlebar = Graphic.bitSize(LoadBitmap(R.drawable.titlebar), 1280, 63);
		hpbar = Graphic.bitSize(LoadBitmap(R.drawable.hpbar2), 1100, 23);
		hpfont = Graphic.bitSize(LoadBitmap(R.drawable.hpfont0), 80, 25);
		hpfont_red = Graphic.bitSize(LoadBitmap(R.drawable.hpfont_red0), 80, 25);
		freely = Graphic.bitSize(LoadBitmap(R.drawable.freely), 260, 30);
		boss = Graphic.bitSize(LoadBitmap(R.drawable.boss1), 200, 185);

		rank_f = Graphic.bitSize(LoadBitmap(R.drawable.r_f), 86, 146);
		rank_e = Graphic.bitSize(LoadBitmap(R.drawable.r_e), 99, 152);
		rank_d = Graphic.bitSize(LoadBitmap(R.drawable.r_d), 124, 152);
		rank_c = Graphic.bitSize(LoadBitmap(R.drawable.r_c), 117, 176);
		rank_b = Graphic.bitSize(LoadBitmap(R.drawable.r_b), 92, 152);
		rank_a = Graphic.bitSize(LoadBitmap(R.drawable.r_a), 133, 182);
		rank_s = Graphic.bitSize(LoadBitmap(R.drawable.r_s), 309, 257);
		//特效光（測試中）

		Cyan[0] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan00), 150, 150);
		Cyan[1] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan01), 150, 150);
		Cyan[2] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan02), 150, 150);
		Cyan[3] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan03), 150, 150);
		Cyan[4] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan04), 150, 150);
		Cyan[5] 	= Graphic.bitSize(LoadBitmap(R.drawable.cyan05), 150, 150);

		Red[0] 		= Graphic.bitSize(LoadBitmap(R.drawable.red00), 150, 150);
		Red[1] 		= Graphic.bitSize(LoadBitmap(R.drawable.red01), 150, 150);
		Red[2] 		= Graphic.bitSize(LoadBitmap(R.drawable.red02), 150, 150);
		Red[3] 		= Graphic.bitSize(LoadBitmap(R.drawable.red03), 150, 150);
		Red[4] 		= Graphic.bitSize(LoadBitmap(R.drawable.red04), 150, 150);
		Red[5] 		= Graphic.bitSize(LoadBitmap(R.drawable.red05), 150, 150);

		Yellow[0] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow00), 150, 150);
		Yellow[1] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow01), 150, 150);
		Yellow[2] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow02), 150, 150);
		Yellow[3] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow03), 150, 150);
		Yellow[4] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow04), 150, 150);
		Yellow[5] 	= Graphic.bitSize(LoadBitmap(R.drawable.yellow05), 150, 150);

		Green[0] 	= Graphic.bitSize(LoadBitmap(R.drawable.green00), 150, 150);
		Green[1] 	= Graphic.bitSize(LoadBitmap(R.drawable.green01), 150, 150);
		Green[2] 	= Graphic.bitSize(LoadBitmap(R.drawable.green02), 150, 150);
		Green[3] 	= Graphic.bitSize(LoadBitmap(R.drawable.green03), 150, 150);
		Green[4] 	= Graphic.bitSize(LoadBitmap(R.drawable.green04), 150, 150);
		Green[5] 	= Graphic.bitSize(LoadBitmap(R.drawable.green05), 150, 150);

		Blue[0] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue00), 150, 150);
		Blue[1] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue01), 150, 150);
		Blue[2] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue02), 150, 150);
		Blue[3] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue03), 150, 150);
		Blue[4] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue04), 150, 150);
		Blue[5] 	= Graphic.bitSize(LoadBitmap(R.drawable.blue05), 150, 150);

		//能量條圖片
		enebar[0] = Graphic.bitSize(LoadBitmap(R.drawable.enebar00), 1280, 28);
		enebar[1] = Graphic.bitSize(LoadBitmap(R.drawable.enebar01), 1280, 28);
		enebar[2] = Graphic.bitSize(LoadBitmap(R.drawable.enebar02), 1280, 28);
		enebar[3] = Graphic.bitSize(LoadBitmap(R.drawable.enebar03), 1280, 28);
		enebar[4] = Graphic.bitSize(LoadBitmap(R.drawable.enebar04), 1280, 28);
		enebar[5] = Graphic.bitSize(LoadBitmap(R.drawable.enebar05), 1280, 28);
		enebar[6] = Graphic.bitSize(LoadBitmap(R.drawable.enebar06), 1280, 28);
		enebar[7] = Graphic.bitSize(LoadBitmap(R.drawable.enebar07), 1280, 28);
		enebar[8] = Graphic.bitSize(LoadBitmap(R.drawable.enebar08), 1280, 28);
		enebar[9] = Graphic.bitSize(LoadBitmap(R.drawable.enebar09), 1280, 28);



		for(int i=0;i<Effect_numbers;i++){
			Effect_Cyan[i]=new shortAnimax(Cyan);

			Effect_Red[i]=new shortAnimax(Red);
			Effect_Red[i].setPosition(450,600);

			Effect_Yellow[i]=new shortAnimax(Yellow);
			Effect_Yellow[i].setPosition(575, 600);

			Effect_Green[i]=new shortAnimax(Green);
			Effect_Green[i].setPosition(700, 600);

			Effect_Blue[i]=new shortAnimax(Blue);
			Effect_Blue[i].setPosition(825, 600);
		}

		change_enebar = new shortAnimax(enebar);
		change_enebar.setPosition(640, 50);



		btn_circle = new Bottom(activity, grey_circle, circle, 100, 495);
		btn_square = new Bottom(activity, grey_square, square, 280, 625);
		btn_triangle = new Bottom(activity, grey_triangle, triangle, 1000, 625);
		btn_xx = new Bottom(activity, grey_xx, xx, 1180, 495);

		//浮游砲===========================================================
		/*
		btn_circle = new Bottom(activity, d_red, d_red, 125, 450);
		btn_square = new Bottom(activity, grey_square, square, 180, 640);
		btn_triangle = new Bottom(activity, grey_triangle, triangle, 1100, 640);
		btn_xx = new Bottom(activity, d_blue, d_blue, 1155, 450);
		 */

		//浮游砲-----------------------------------------------------------

		//PAUSE按鈕=======================================================
		btn_pause = new Bottom(activity, pause2, pause , 90, 105);
		btn_re_map = new Bottom(activity, re_map, re_map, 640 , 410);
		btn_re_play = new Bottom(activity, re_play, re_play,640 , 315);
		btn_re_start = new Bottom(activity, re_start , re_start,640 ,225);
		//PAUSE按鈕---------------------------------------------------------

		chart_r=Graphic.bitSize(LoadBitmap(R.drawable.virus_red), 80, 80);
		chart_s=Graphic.bitSize(LoadBitmap(R.drawable.virus_yello), 80, 80);
		chart_t=Graphic.bitSize(LoadBitmap(R.drawable.virus_green), 80, 80);
		chart_x=Graphic.bitSize(LoadBitmap(R.drawable.virus_blue), 80, 80);

		for(int i=0;i<chartObject;i++){
			cr_btm[i]=new gameChartBottom(-100,600, 820,activity, chart_r, chart_r,450);
			cs_btm[i]=new gameChartBottom(-100,600,820, activity, chart_s, chart_s,575);
			ct_btm[i]=new gameChartBottom(-100,600,820, activity, chart_t, chart_t,700);
			cx_btm[i]=new gameChartBottom(-100,600,820,activity, chart_x, chart_x,825);
		}

		mp=MediaPlayer.create(this.getContext(), R.raw.freely_tomorrow);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				activity.changeView(4);
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
	protected void onDraw(Canvas canvas) {
		if(canvas!=null){
			// TAG 載入關卡設定及譜面檔===============================================
			if(startFlag){
				JSONObject json=null;
				boss_Flag=false;
				boss_attack_Flag=false;
				ene_flag=false;
				boss_x=boss_x_side;

				switch(activity.level){//關卡
				case 0 :
					this.boss_show=6000;//TAG BOSS進場時間
					boss_kill=9000;
					switch(activity.difficulty){//難度
					case 0 :
						json=activity.read( "freely_tomorrow.mp3");
						break;
					case 1 :

						break;
					case 2 :

						break;
					}
					break;
				case 1 :
					this.boss_show=0;
					boss_kill=0;
					switch(activity.difficulty){
					case 0 :

						break;
					case 1 :

						break;
					case 2 :

						break;
					}
					break;
				case 2 :
					this.boss_show=0;
					switch(activity.difficulty){
					case 0 :

						break;
					case 1 :

						break;
					case 2 :

						break;
					}
					break;
				}
				if(json!=null){
					try {
						BtR=json.getJSONObject("R");
						BtS=json.getJSONObject("S");
						BtT=json.getJSONObject("T");
						BtX=json.getJSONObject("X");
					} catch (JSONException e) {
						Log.e("GameView", "JSON load fail");
						e.printStackTrace();
					}
					activity.virus=BtR.length()+BtS.length()+BtT.length()+BtX.length();
					cs=new chartScan(BtR,BtS,BtT,BtX,time_dis,"GameView");
					cs.Start();
				}else{
					Log.e("GameView","找不到譜面檔");
					activity.changeView(2);
				}

				mp.setVolume(activity.mp_Voiume, activity.mp_Voiume);
				mp.start();
				startFlag=false;
			}
			//載入關卡設定及譜面檔---------------------------------------------------------------------------------------------------------------------------
			//TAG 掃描=================================================================================
			if(cs.R_scan_flag){
				for(int i=0;i<chartObject;i++){
					if(!cr_btm[i].getFlag()){
						cr_btm[i].start(mp.getCurrentPosition(), time_dis, (mp.getCurrentPosition()+time_dis)/100);
						cs.R_scan_flag=false;
						break;
					}
				}
			}
			if(cs.S_scan_flag){
				for(int i=0;i<chartObject;i++){
					if(!cs_btm[i].getFlag()){
						cs_btm[i].start(mp.getCurrentPosition(), time_dis, (mp.getCurrentPosition()+time_dis)/100);
						cs.S_scan_flag=false;
						break;
					}
				}
			}
			if(cs.T_scan_flag){
				for(int i=0;i<chartObject;i++){
					if(!ct_btm[i].getFlag()){
						ct_btm[i].start(mp.getCurrentPosition(), time_dis, (mp.getCurrentPosition()+time_dis)/100);
						cs.T_scan_flag=false;
						break;
					}
				}
			}
			if(cs.X_scan_flag){
				for(int i=0;i<chartObject;i++){
					if(!cx_btm[i].getFlag()){
						cx_btm[i].start(mp.getCurrentPosition(), time_dis, (mp.getCurrentPosition()+time_dis)/100);
						cs.X_scan_flag=false;
						break;
					}
				}
			}//掃描----------------------------------------------------------------------------------------------------------------------------------------------------------

			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//只在螢幕範圍內繪制圖片
			canvas.drawColor(Color.BLACK);//界面設定為黑色
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//背景
			Graphic.drawPic(canvas, track, 450, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 575, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 700, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 825, 390, 0, 255, paint);

			//TAG 判定顯示======================================================
			if(Hitcount > 0)
			{
				switch(Hitflag){  //偵測hitflag目前的狀態

				case 0:

					break;

				case 1:
					Graphic.drawPic(canvas, nice, 650, 140, 0, Hitcount, paint);
					break;
				case 2:
					Graphic.drawPic(canvas, hit, 650, 140, 0, Hitcount, paint);
					break;
				case 3:
					Graphic.drawPic(canvas, safe, 650, 140, 0, Hitcount, paint);
					break;
				case 4:
					Graphic.drawPic(canvas, miss, 650, 140, 0, Hitcount, paint);
					break;
				}
				Hitcount-=15;
			}else if(Hitcount <0){
				Hitcount = 0;
			}
			//判定顯示--------------------------------------------------------
			//TAG HP檢查==========================================================
			if(hp_x==182+0*55){
				activity.changeView(4);
			}
			//HP檢查----------------------------------------------------------

			int now_time=mp.getCurrentPosition();
			for(int i=0;i<chartObject;i++){
				if(cr_btm[i].getFlag()){
					if(cr_btm[i].drawChartBottom(now_time, canvas, paint)){
						scoreLess(); 
					}
				}
				if(cs_btm[i].getFlag()){
					if(cs_btm[i].drawChartBottom(now_time, canvas, paint)){
						scoreLess(); 
					}
				}
				if(ct_btm[i].getFlag()){
					ct_btm[i].drawChartBottom(now_time, canvas, paint);
					if(ct_btm[i].drawChartBottom(now_time, canvas, paint)){
						scoreLess(); 
					}
				}
				if(cx_btm[i].getFlag()){
					if(cx_btm[i].drawChartBottom(now_time, canvas, paint)){
						scoreLess(); 
					}
				}
			}

			Graphic.drawPic(canvas, sight, 450, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 575, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 700, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 825, 600, 0, 255, paint);

			btn_circle.drawBtm(canvas, paint);
			btn_square.drawBtm(canvas, paint);
			btn_triangle.drawBtm(canvas, paint);
			btn_xx.drawBtm(canvas, paint);
			btn_pause.drawBtm(canvas, paint);

			//PAUSE選單控制==========================================================
			if(btn_pause.getBottom()){
				Graphic.drawPic(canvas, pause_black, 640, 360, 0, 255, paint);
				Graphic.drawPic(canvas, pause2, 90, 105, 0, 255, paint);
				Graphic.drawPic(canvas, pause_back, 640, 315, 0, 255, paint);
				btn_re_map.drawBtm(canvas, paint);
				btn_re_play.drawBtm(canvas, paint);
				btn_re_start.drawBtm(canvas, paint);
			}
			//PAUSE選單控制-----------------------------------------------------------


			//combo============================================
			Graphic.drawPic(canvas, hits, 290, 200, 0, 255, paint);
			//combo--------------------------------------------



			//TAG 特效光繪圖===========================================================================
			for(int i=0;i<Effect_numbers;i++){
				if(Effect_Cyan[i].getFlag()){
					Effect_Cyan[i].drawEffect(Effect_speed, canvas, paint);
				}
				if(Effect_Red[i].getFlag()){
					//Graphic.drawPic(canvas, lazer_red, 338, 550, 0, 255, paint);
					Effect_Red[i].drawEffect(Effect_speed, canvas, paint);

				}
				if(Effect_Yellow[i].getFlag()){
					//Graphic.drawPic(canvas, lazer_yellow, 432, 619, 0, 255, paint);
					Effect_Yellow[i].drawEffect(Effect_speed, canvas, paint);
				}
				if(Effect_Green[i].getFlag()){
					//Graphic.drawPic(canvas, lazer_green, 846, 622, 0, 255, paint);
					Effect_Green[i].drawEffect(Effect_speed, canvas, paint);
				}
				if(Effect_Blue[i].getFlag()){
					//Graphic.drawPic(canvas, lazer_blue, 937, 545, 0, 255, paint);
					Effect_Blue[i].drawEffect(Effect_speed, canvas, paint);
				}
			}
			//特效光繪圖----------------------------------------------------------------------------



			hp_x=Coordinate.AnalogSpeedMove(hp_x, 182+hp*55);
			hp_color=Color.GREEN;
			if(hp_x!= 182+hp*55){
				hp_color=Color.argb(255, 132, 0, 20);
			}else if(hp<hp_to_yellow&& hp>hp_to_red){
				hp_color=Color.YELLOW;
			}else if(hp<=hp_to_red){
				hp_color=Color.RED;
			}
			Graphic.drawPic(canvas, titlebar, 641, 31, 0, 255, paint);
			Graphic.drawLine(canvas, hp_color, 182, 50, hp_x, 50, 16, paint);
			//Graphic.drawPic(canvas, hpbar, 730, 50, 0, 255, paint);
			//Graphic.drawPic(canvas, hpfont, 95, 50, 0, 255, paint);
			//能量條切換特效==========================================================================
			if(!change_enebar.getFlag()){
				if(ene_flag){
					Graphic.drawPic(canvas, enebar[9], 640, 50, 0, 255, paint);
				}else{
					Graphic.drawPic(canvas, enebar[0], 640, 50, 0, 255, paint);
				}
			}
			change_enebar.drawEffect(enebar_speed, canvas, paint);
			//能量條切換特效--------------------------------------------------------------------------
			// TAG BOSS 模式狀態=======================================================================
			if(mp.getCurrentPosition()>boss_show){
				Graphic.drawPic(canvas, boss, boss_x, boss_y, 0, 255, paint);
				if(!ene_flag){
					change_enebar.start();
					ene_flag=true;
				}
				if(!boss_attack_Flag){
					if(boss_Flag){
						if(boss_x!=boss_x_middle){
							boss_x=Coordinate.AnalogSpeedMove(boss_x, boss_x_middle);
						}else{
							boss_attack_Flag=true;
						}
					}else if(mp.getCurrentPosition()>boss_kill){
						boss_Flag=true;
					}
				}
			}
			// BOSS 模式狀態-------------------------------------------------------------------------------------------------------------------------

			Graphic.drawPic(canvas, freely, 132, 20, 0, 255, paint);
			score.setSize(20, 30);
			score.drawNumberRightStart(1250, 20, sc_score, Number.Wite, canvas, paint);


			//TAG combo顯示============================================================
			Graphic.drawPic(canvas, hits, 290, 200, 0, 255, paint);
			score.setSize(50, 70);
			score.drawNumberRightStart(230, 190, combo, Number.Cyan, canvas, paint);
			//combo顯示-------------------------------------------------------------

			//Graphic.drawPic(canvas, hpfont_red, 95, 50, 0, 255, paint);

			//難易度
			if(activity.modelFlag == 0)
			{
				Graphic.drawPic(canvas, game_easy, 1180, 105, 0, 255, paint);
			}else if(activity.modelFlag == 1){
				Graphic.drawPic(canvas, game_normal, 1180, 105, 0, 255, paint);
			}else if(activity.modelFlag == 2){
				Graphic.drawPic(canvas, game_hard, 1180, 105, 0, 255, paint);
			}





			btn_circle.setBottomTo(false);	
			btn_square.setBottomTo(false);	
			btn_triangle.setBottomTo(false);	
			btn_xx.setBottomTo(false);
			for(int i=0;i<btn_pointer.size();i++){
				int st=4;
				try{
					st=btn_pointer.valueAt(i);
				}catch(Exception e){
					Log.e("touch problem", ""+e);
				}
				switch(st){
				case 0:
					btn_circle.setBottomTo(true);
					break;
				case 1:
					btn_square.setBottomTo(true);
					break;
				case 2:
					btn_triangle.setBottomTo(true);
					break;
				case 3:
					btn_xx.setBottomTo(true);
					break;
				}
			}
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int pointerIndex = event.getActionIndex();

		// get pointer ID
		int pointerId = event.getPointerId(pointerIndex);

		pointx=(int) event.getX();
		pointy=(int) event.getY();

		switch(event.getActionMasked())
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN://按下
			PointF f = new PointF();
			f.x = event.getX(pointerIndex);
			f.y = event.getY(pointerIndex);
			mActivePointers.put(pointerId, f);

			if(btn_circle.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<Effect_numbers;i++){
					if(!Effect_Red[i].getFlag()){
						Effect_Red[i].start();
						break;
					}
				}
				for(int i=0;i<chartObject;i++){
					if(cr_btm[i].getFlag()){
						int cr_dis=Math.abs(cr_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cr_dis<3){
							scoreAdd(cr_dis);
							cr_btm[i].stop();
							break;
						}
					}
				}

				btn_pointer.put(pointerId, 0);
			}
			if(btn_square.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<Effect_numbers;i++){
					if(!Effect_Yellow[i].getFlag()){
						Effect_Yellow[i].start();
						break;
					}
				}
				for(int i=0;i<chartObject;i++){
					if(cs_btm[i].getFlag()){
						int cs_dis=Math.abs(cs_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cs_dis<3){
							scoreAdd(cs_dis);
							cs_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 1);
			}
			if(btn_triangle.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<Effect_numbers;i++){
					if(!Effect_Green[i].getFlag()){
						Effect_Green[i].start();
						break;
					}
				}
				for(int i=0;i<chartObject;i++){
					if(ct_btm[i].getFlag()){
						int ct_dis=Math.abs(ct_btm[i].getId()-mp.getCurrentPosition()/100);
						if(ct_dis<3){
							scoreAdd(ct_dis);
							ct_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 2);
			}
			if(btn_xx.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<Effect_numbers;i++){
					if(!Effect_Blue[i].getFlag()){
						Effect_Blue[i].start();
						break;
					}
				}
				for(int i=0;i<chartObject;i++){
					if(cx_btm[i].getFlag()){
						int cx_dis=Math.abs(cx_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cx_dis<3){
							scoreAdd(cx_dis);
							cx_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 3);
			}

			//PAUSE按鈕功能==============================================
			if(btn_pause.isIn(pointx, pointy)){
				if(!btn_pause.getBottom()){
					btn_pause.setBottomTo(true);
				}
				else if(btn_pause.getBottom()){
					btn_pause.setBottomTo(false);
				}
			}
			//PAUSE按鈕功能----------------------------------------------

			//返回遊戲、從頭開始、返回關卡選擇按鈕功能================================
			if(btn_pause.getBottom()){   //必須在PAUSE按鈕為TRUE的時候才生效

				if(btn_re_play.isIn(pointx, pointy)){
					//TODO 返回遊戲
					btn_pause.setBottomTo(false);
				}
				if(btn_re_start.isIn(pointx, pointy)){
					//TODO 從頭開始
					btn_pause.setBottomTo(false);
				}
				if(btn_re_map.isIn(pointx, pointy)){
					activity.changeView(2);
					btn_pause.setBottomTo(false);

				}
			}

			//返回遊戲、從頭開始、返回關卡選擇按鈕功能---------------------------------



			//if(startbtm.isIn(pointx, pointy)){
			//進入地圖畫面
			//activity.changeView(2);
			//this.toEditView = 0;
			//	}
			//this.toEditView++;
			//if(this.toEditView>10){
			//Constant.Flag=false;
			//activity.changeView(6);
			//}
			break;
		case MotionEvent.ACTION_MOVE:
			for (int size = event.getPointerCount(), i = 0; i < size; i++) {
				PointF point = mActivePointers.get(event.getPointerId(i));
				if (point != null) {
					point.x = event.getX(i);
					point.y = event.getY(i);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
		case MotionEvent.ACTION_CANCEL: 
			f = new PointF();
			f.x = event.getX(pointerIndex);
			f.y = event.getY(pointerIndex);

			mActivePointers.remove(pointerId);
			btn_pointer.remove(pointerId);

			break;
		}

		return true;
	}
	//TAG 分數
	public void scoreAdd(int dis){
		if(this.hp!=this.hp_max){
			if(this.hp<this.hp_max){
				this.hp++;
			}
		}
		switch(dis){
		case 0:  //NICE

			percent++;
			combo++;
			sc_score+=200;
			sc_nice++;
			Hitflag = 1;
			if(combo > maxcombo)
			{
				maxcombo = combo;
			}
			break;
		case 1: //HIT
			percent++;
			combo++;
			sc_score+=100;
			sc_hit++;
			Hitflag = 2;
			if(combo > maxcombo)
			{
				maxcombo = combo;
			}
			break;
		case 2:  //SAFE
			percent++;
			combo = 0;
			sc_score += 50;
			sc_safe++;
			Hitflag = 3;
			if(combo > maxcombo)
			{
				maxcombo = combo;
			}
			break;
		}
		Hitcount = 255;
	}
	public void scoreLess(){
		this.hp--;
		combo = 0;
		sc_miss++;
		Hitflag = 4;
		Hitcount = 255;

	}

	public void playSP(){
		if(activity.sp_num!=-1)
			sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//銷毀時被呼叫
		//TODO 暫停選單
		mp.stop();
		activity.combo = maxcombo;
		activity.nice = sc_nice;
		activity.hit = sc_hit;
		activity.safe = sc_safe;
		activity.miss = sc_miss;
		activity.score = sc_score;
		activity.percent = percent;
		startFlag=true;

		bg.recycle();   //背景
		sight.recycle();  //準星
		boss.recycle();
		game_easy.recycle();
		game_normal.recycle();
		game_hard.recycle();
		freely.recycle();


		circle.recycle();
		square.recycle();
		xx.recycle();
		triangle.recycle();
		grey_circle.recycle();
		grey_square.recycle();
		grey_xx.recycle();
		grey_triangle.recycle();

		//浮游砲宣告===============================================

		d_red.recycle();
		d_blue.recycle();
		d_yellow.recycle();
		d_green.recycle();

		//浮游砲宣告-----------------------------------------------

		//光束===================================================
		lazer_red.recycle();
		lazer_blue.recycle();
		lazer_green.recycle();
		lazer_yellow.recycle();
		//光束----------------------------------------------------

		track.recycle();  //軌道

		virus_red.recycle();
		virus_yellow.recycle();
		virus_blue.recycle();
		virus_green.recycle();

		nice.recycle();
		miss.recycle();
		safe.recycle();
		hit.recycle();
		hits.recycle();

		titlebar.recycle();  //狀態欄
		hpbar.recycle();
		hpfont.recycle();
		hpfont_red.recycle();

		//宣告pause所需要的圖片==========================================
		pause.recycle();
		pause2.recycle();
		pause_back.recycle();
		pause_black.recycle(); //pause時要用的黑底
		re_play.recycle();
		re_start.recycle();
		re_map.recycle();
		//宣告pause所需要的圖片-------------------------------------------

		//特效光宣告================================
		for(int i=0;i<6;i++){
			Cyan[i].recycle();
			Red[i]	.recycle();
			Green[i].recycle();
			Yellow[i].recycle();
			Blue[i].recycle();
		}

		//過關等級================================================
		rank_s.recycle();
		rank_a.recycle();
		rank_b.recycle();
		rank_c.recycle();
		rank_d.recycle();
		rank_e.recycle();
		rank_f.recycle();
		//過關等級------------------------------------------------
		chart_r.recycle();
		chart_s.recycle();
		chart_t.recycle();
		chart_x.recycle();

		for(int i=0;i<6;i++){
			Cyan[i].recycle();
			Red[i].recycle();
			Green[i].recycle();
			Yellow[i].recycle();
			Blue[i].recycle();
		}
		for(int i=0;i<Effect_numbers;i++){
			Effect_Cyan[i].recycle();
			Effect_Red[i].recycle();
			Effect_Green[i].recycle();
			Effect_Yellow[i].recycle();
			Effect_Blue[i].recycle();
		}

		btn_circle.recycle();
		btn_square.recycle();
		btn_xx.recycle();
		btn_triangle.recycle();

		//宣告PAUSE、返回遊戲、從頭開始、返回關卡地圖按鈕==================================================
		btn_pause.recycle();
		btn_re_play.recycle();
		btn_re_start.recycle();
		btn_re_map.recycle();
		//宣告PAUSE、返回遊戲、從頭開始、返回關卡地圖按鈕--------------------------------------------------


		for(int i=0;i<10;i++){
			enebar[i].recycle() ;
		}

		change_enebar.recycle() ;
		score.recycle();

		Constant.Flag=false;
	}


}
