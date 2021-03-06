package com.LLC.MusicSelvation;
//

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

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
	Bitmap title;


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
	smallAnimax  []
			Effect_Cyan		=new smallAnimax[Effect_numbers],
			Effect_Red		=new smallAnimax[Effect_numbers],
			Effect_Green	=new smallAnimax[Effect_numbers],
			Effect_Yellow	=new smallAnimax[Effect_numbers],
			Effect_Blue		=new smallAnimax[Effect_numbers];
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
	smallAnimax change_enebar;
	boolean ene_flag;
	//能量條切換---------------------------------------------


	int boss_show;
	int boss_kill;
	int boss_kill_delay=3000;
	boolean boss_Flag;
	boolean boss_attack_Flag;
	boolean beam_attack;
	int boss_y=242;
	int boss_x;
	int boss_x_side=1025;
	int boss_x_middle=640;

	//BOSS 前警告==
	int warning_time=2000;
	Bitmap warning;
	int warning_alpha;
	int warning_alpha_flag;
	int warning_sound;
	boolean warning_flag;
	//BOSS 前警告--

	//BOSS 攻擊==
	gameChartBottom attack;
	Bitmap attack_pic_round;
	Bitmap attack_pic;
	Bitmap attack_sight;
	boolean attack_flag;
	boolean attack_flag2;

	bigAnimax beam;
	int beam_num=20;
	int beam_time=2500;
	int beam_sound;
	boolean beam_flag;

	bigAnimax boss_del;
	int boss_del_num=28;
	int boss_del_time=3000;
	int boss_del_sound;
	boolean boss_del_flag;
	//BOSS 攻擊--


	Number score;

	double hp_point[][]={{0.5,0.3,0.2,0.3},{0.3,0.1,0.1,0.5},{0.1,0.1,0.0,0.8}};
	double hp;
	int hp_max=20;
	int hp_x;
	int hp_x_last;
	int hp_color=Color.GREEN;
	int hp_to_yellow=12;
	int hp_to_red = 6;

	double en_point[]={5,3,1.5};
	double en;
	double en_II;
	int en_max=100;
	int en_x;
	int en_color=Color.CYAN;


	int combo;
	int maxcombo;
	int sc_nice;
	int sc_hit;
	int sc_safe;
	int sc_miss;
	int percent;
	int sc_score;

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

	/*public Bitmap LoadBitmap(int r,int scale){
		try{
		 InputStream inputStream = getResources().openRawResource(r);
		    return BitmapFactory.decodeStream(inputStream, null, getBitmapOptions(scale));
		}catch(OutOfMemoryError e){
			return null;
		}
		//return BitmapFactory.decodeResource(getResources(), r);
	}
	public Bitmap LoadBitmap(int r){
		try{
		 InputStream inputStream = getResources().openRawResource(r);
		    return BitmapFactory.decodeStream(inputStream, null, getBitmapOptions(1));
		}catch(OutOfMemoryError e){
			return null;
		}
		//return BitmapFactory.decodeResource(getResources(), r);
	}
	public BitmapFactory.Options getBitmapOptions(int scale){
	    BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig=Bitmap.Config.ARGB_4444;
	    options.inPurgeable = true;
	    options.inInputShareable = true;
	    options.inSampleSize = scale;
	    return options;
	}*/
	public void Reset(){
		combo=0;
		maxcombo = 0;
		sc_nice= 0;
		sc_hit= 0;
		sc_safe= 0;
		sc_miss= 0;
		percent = 0;
		sc_score = 0;
		this.hp=this.hp_max;
		hp_x=190;
		hp_x_last=190;
		en=0;
		if(activity.speed==0){
			activity.speed=1;
		}
		time_dis=3000/activity.speed;
		warning_alpha=0;
		warning_flag=false;
		attack_flag=false;
		attack_flag2=false;
		beam_attack=false;
		beam_flag=true;
		boss_del_flag=false;
		Constant.Flag=true;
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		sp=new SoundPool(4, AudioManager.STREAM_MUSIC, 5);

		paint = new Paint();//建立畫筆
		paint.setAntiAlias(true);//開啟抗鋸齒

		score =new Number(getResources());
		if(activity.level<2){
			bg=Graphic.LoadBitmap(getResources(), R.drawable.gamemap01, 1280, 720);
		}else{
			bg=Graphic.LoadBitmap(getResources(), R.drawable.stage03_bg, 1280, 720);
		}

		sight =	Graphic.LoadBitmap(getResources(), R.drawable.sightv2, 100, 100);
		//cpu   = Graphic.LoadBitmap(LoadBitmap(R.drawable.cpu_chips), 162, 162);



		game_easy = Graphic.LoadBitmap(getResources(), R.drawable.easyv2, 205, 78);
		game_normal = Graphic.LoadBitmap(getResources(), R.drawable.normalv2psd, 205, 78);
		game_hard = Graphic.LoadBitmap(getResources(), R.drawable.hardv2, 205, 78);



		//pause圖片載入=====================================================================
		pause = Graphic.LoadBitmap(getResources(), R.drawable.pause, 205, 78);
		pause2 = Graphic.LoadBitmap(getResources(), R.drawable.pause2, 205, 78);
		pause_back = Graphic.LoadBitmap(getResources(), R.drawable.pasue_back, 450, 310);
		pause_black = Graphic.LoadBitmap(getResources(), R.drawable.pause_black, 1280, 720);

		re_map = Graphic.LoadBitmap(getResources(), R.drawable.return_map, 390, 75);
		re_play = Graphic.LoadBitmap(getResources(), R.drawable.re_play, 390, 75);
		re_start = Graphic.LoadBitmap(getResources(), R.drawable.re_start, 390, 75);

		//pause圖片載入-----------------------------------------------------------------------


		circle = Graphic.LoadBitmap(getResources(), R.drawable.btn_circle_v2, 200, 200);
		square = Graphic.LoadBitmap(getResources(), R.drawable.btn_square_v2, 200, 200);
		triangle = Graphic.LoadBitmap(getResources(), R.drawable.btn_triangle_v2, 200, 200);
		xx = Graphic.LoadBitmap(getResources(), R.drawable.btn_x_v2, 200, 200);
		grey_circle = Graphic.LoadBitmap(getResources(), R.drawable.grey_circle, 200, 200);
		grey_square = Graphic.LoadBitmap(getResources(), R.drawable.grey_square, 200, 200);
		grey_triangle = Graphic.LoadBitmap(getResources(), R.drawable.grey_tirangle, 200, 200);
		grey_xx = Graphic.LoadBitmap(getResources(), R.drawable.grey_x, 200, 200);


		//TAG 浮游砲與光束=======================================================================
		d_blue = Graphic.LoadBitmap(getResources(), R.drawable.d_blue, 250, 170);
		d_red = Graphic.LoadBitmap(getResources(), R.drawable.d_red, 250, 170);
		d_green = Graphic.LoadBitmap(getResources(), R.drawable.d_green, 260, 130);
		d_yellow = Graphic.LoadBitmap(getResources(), R.drawable.d_yellow, 260, 130);
		//浮游砲與光束------------------------------------------------------------------------


		track = Graphic.LoadBitmap(getResources(), R.drawable.track_v2, 80, 660);

		virus_blue = Graphic.LoadBitmap(getResources(), R.drawable.virus_blue, 80, 80);
		virus_red = Graphic.LoadBitmap(getResources(), R.drawable.virus_red, 80, 80);
		virus_yellow = Graphic.LoadBitmap(getResources(), R.drawable.virus_yello, 80, 80);
		virus_green = Graphic.LoadBitmap(getResources(), R.drawable.virus_green, 80, 80);

		nice = Graphic.LoadBitmap(getResources(), R.drawable.nice, 175, 55);
		hit = Graphic.LoadBitmap(getResources(), R.drawable.hit, 175, 55);
		safe = Graphic.LoadBitmap(getResources(), R.drawable.safe, 175, 55);
		miss = Graphic.LoadBitmap(getResources(), R.drawable.miss, 175, 55);
		hits =  Graphic.LoadBitmap(getResources(), R.drawable.hits, 155, 80);

		titlebar = Graphic.LoadBitmap(getResources(), R.drawable.titlebar, 1280, 63);
		hpbar = Graphic.LoadBitmap(getResources(), R.drawable.hpbar2, 1100, 23);
		hpfont = Graphic.LoadBitmap(getResources(), R.drawable.hpfont0, 80, 25);
		hpfont_red = Graphic.LoadBitmap(getResources(), R.drawable.hpfont_red0, 80, 25);
		title = Graphic.LoadBitmap(getResources(), R.drawable.game_title_1+activity.level, 260, 30);
		boss = Graphic.LoadBitmap(getResources(), R.drawable.boss1, 200, 185);

		rank_f = Graphic.LoadBitmap(getResources(), R.drawable.r_f, 86, 146);
		rank_e = Graphic.LoadBitmap(getResources(), R.drawable.r_e, 99, 152);
		rank_d = Graphic.LoadBitmap(getResources(), R.drawable.r_d, 124, 152);
		rank_c = Graphic.LoadBitmap(getResources(), R.drawable.r_c, 117, 176);
		rank_b = Graphic.LoadBitmap(getResources(), R.drawable.r_b, 92, 152);
		rank_a = Graphic.LoadBitmap(getResources(), R.drawable.r_a, 133, 182);
		rank_s = Graphic.LoadBitmap(getResources(), R.drawable.r_s, 309, 257);
		//特效光（測試中）

		Cyan[0] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan00, 150, 150);
		Cyan[1] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan01, 150, 150);
		Cyan[2] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan02, 150, 150);
		Cyan[3] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan03, 150, 150);
		Cyan[4] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan04, 150, 150);
		Cyan[5] 	= Graphic.LoadBitmap(getResources(), R.drawable.cyan05, 150, 150);

		Red[0] 		= Graphic.LoadBitmap(getResources(), R.drawable.red00, 150, 150);
		Red[1] 		= Graphic.LoadBitmap(getResources(), R.drawable.red01, 150, 150);
		Red[2] 		= Graphic.LoadBitmap(getResources(), R.drawable.red02, 150, 150);
		Red[3] 		= Graphic.LoadBitmap(getResources(), R.drawable.red03, 150, 150);
		Red[4] 		= Graphic.LoadBitmap(getResources(), R.drawable.red04, 150, 150);
		Red[5] 		= Graphic.LoadBitmap(getResources(), R.drawable.red05, 150, 150);

		Yellow[0] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow00, 150, 150);
		Yellow[1] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow01, 150, 150);
		Yellow[2] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow02, 150, 150);
		Yellow[3] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow03, 150, 150);
		Yellow[4] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow04, 150, 150);
		Yellow[5] 	= Graphic.LoadBitmap(getResources(), R.drawable.yellow05, 150, 150);

		Green[0] 	= Graphic.LoadBitmap(getResources(), R.drawable.green00, 150, 150);
		Green[1] 	= Graphic.LoadBitmap(getResources(), R.drawable.green01, 150, 150);
		Green[2] 	= Graphic.LoadBitmap(getResources(), R.drawable.green02, 150, 150);
		Green[3] 	= Graphic.LoadBitmap(getResources(), R.drawable.green03, 150, 150);
		Green[4] 	= Graphic.LoadBitmap(getResources(), R.drawable.green04, 150, 150);
		Green[5] 	= Graphic.LoadBitmap(getResources(), R.drawable.green05, 150, 150);

		Blue[0] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue00, 150, 150);
		Blue[1] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue01, 150, 150);
		Blue[2] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue02, 150, 150);
		Blue[3] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue03, 150, 150);
		Blue[4] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue04, 150, 150);
		Blue[5] 	= Graphic.LoadBitmap(getResources(), R.drawable.blue05, 150, 150);

		//能量條圖片
		enebar[0] = Graphic.LoadBitmap(getResources(), R.drawable.enebar00, 1280, 28);
		enebar[1] = Graphic.LoadBitmap(getResources(), R.drawable.enebar01, 1280, 28);
		enebar[2] = Graphic.LoadBitmap(getResources(), R.drawable.enebar02, 1280, 28);
		enebar[3] = Graphic.LoadBitmap(getResources(), R.drawable.enebar03, 1280, 28);
		enebar[4] = Graphic.LoadBitmap(getResources(), R.drawable.enebar04, 1280, 28);
		enebar[5] = Graphic.LoadBitmap(getResources(), R.drawable.enebar05, 1280, 28);
		enebar[6] = Graphic.LoadBitmap(getResources(), R.drawable.enebar06, 1280, 28);
		enebar[7] = Graphic.LoadBitmap(getResources(), R.drawable.enebar07, 1280, 28);
		enebar[8] = Graphic.LoadBitmap(getResources(), R.drawable.enebar08, 1280, 28);
		enebar[9] = Graphic.LoadBitmap(getResources(), R.drawable.enebar09, 1280, 28);

		//BOSS 前警告==
		warning=Graphic.LoadBitmap(getResources(), R.drawable.warning, 1280, 178);
		warning_sound=sp.load(activity, R.raw.warning, 1);
		//BOSS 前警告--

		//BOSS 攻擊==
		attack_pic=Graphic.LoadBitmap(getResources(), R.drawable.boss_sight2, 288, 284);
		attack_pic_round=Graphic.LoadBitmap(getResources(), R.drawable.boss_sight, 288, 284);
		attack_sight=Graphic.LoadBitmap(getResources(), R.drawable.boss_sihgt_gray, 288, 284);
		attack=new gameChartBottom(-200, 495, 900, activity, attack_pic, attack_pic, 1280/2);

		beam=new bigAnimax(activity, beam_num, 900, 450, R.drawable.beam_00);
		beam.setPosition(1280/2, 720/2);
		beam_sound=sp.load(getContext(), R.raw.beam_cut, 1);


		boss_del=new bigAnimax(activity, boss_del_num, 640, 500, R.drawable.boss_del00008);
		boss_del.setPosition(640, 242);
		boss_del_sound=sp.load(getContext(), R.raw.boss_del, 1);
		//BOSS 攻擊--

		for(int i=0;i<Effect_numbers;i++){
			Effect_Cyan[i]=new smallAnimax(Cyan);

			Effect_Red[i]=new smallAnimax(Red);
			Effect_Red[i].setPosition(450,600);

			Effect_Yellow[i]=new smallAnimax(Yellow);
			Effect_Yellow[i].setPosition(575, 600);

			Effect_Green[i]=new smallAnimax(Green);
			Effect_Green[i].setPosition(700, 600);

			Effect_Blue[i]=new smallAnimax(Blue);
			Effect_Blue[i].setPosition(825, 600);
		}

		change_enebar = new smallAnimax(enebar);
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

		chart_r=Graphic.LoadBitmap(getResources(), R.drawable.virus_red, 80, 80);
		chart_s=Graphic.LoadBitmap(getResources(), R.drawable.virus_yello, 80, 80);
		chart_t=Graphic.LoadBitmap(getResources(), R.drawable.virus_green, 80, 80);
		chart_x=Graphic.LoadBitmap(getResources(), R.drawable.virus_blue, 80, 80);

		for(int i=0;i<chartObject;i++){
			cr_btm[i]=new gameChartBottom(-100,600, 820,activity, chart_r, chart_r,450);
			cs_btm[i]=new gameChartBottom(-100,600,820, activity, chart_s, chart_s,575);
			ct_btm[i]=new gameChartBottom(-100,600,820, activity, chart_t, chart_t,700);
			cx_btm[i]=new gameChartBottom(-100,600,820,activity, chart_x, chart_x,825);
		}

		int song[]=new int[3];
		song[0]=R.raw.freely_tomorrow;
		song[1]=R.raw.spica;
		song[2]=R.raw.kokoronashi;
		mp=MediaPlayer.create(this.getContext(), song[activity.level]);
		mp.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				activity.changeView(4);
			}
		});


		sp_id=new int[6];
		sp_id[0]=sp.load(activity, R.raw.tambourine, 1);
		sp_id[1]=sp.load(activity, R.raw.drum_cymbal, 1);
		sp_id[2]=sp.load(activity, R.raw.drum_snare, 1);
		sp_id[3]=sp.load(activity, R.raw.fall, 1);
		sp_id[4]=sp.load(activity, R.raw.voice_dog, 1);
		
		//PAUSE用效果音
		sp_id[5]=sp.load(activity, R.raw.stagebtn, 1);

		Reset();
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
				String difficulty[]={"_easy","_normal","_hard"};
				switch(activity.level){//關卡
				case 0 :
					Log.v("Load Charts", "freely_tomorrow"+difficulty[activity.difficulty]);
					this.boss_show=158000;//TAG BOSS進場時間
					boss_kill=231000;
					json=activity.read( "freely_tomorrow"+difficulty[activity.difficulty]);
					break;
				case 1 :
					Log.v("Load Charts", "spica"+difficulty[activity.difficulty]);
					this.boss_show=164000;
					boss_kill=230000;
					//this.boss_show=5000;
					//boss_kill=10000;
					percent=50000;
					en=90;
					json=activity.read( "spica"+difficulty[activity.difficulty]);
					break;
				case 2 :
					Log.v("Load Charts", "kokoronashi"+difficulty[activity.difficulty]);
					this.boss_show=222000;
					boss_kill=260000;
					json=activity.read( "kokoronashi"+difficulty[activity.difficulty]);
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
					cs=new chartScan(activity,BtR,BtS,BtT,BtX,time_dis,"GameView");
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


			int now_time=mp.getCurrentPosition()+(activity.timing*10);
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
			if(!boss_attack_Flag){
				btn_circle.drawBtm(canvas, paint);
				btn_square.drawBtm(canvas, paint);
				btn_triangle.drawBtm(canvas, paint);
				btn_xx.drawBtm(canvas, paint);
			}else{
				Graphic.drawPic(canvas, d_red, 100, 495, 315, 255, paint);
				Graphic.drawPic(canvas,  d_yellow, 280, 625, 330, 255, paint);
				Graphic.drawPic(canvas,  d_blue, 1180, 495, 45, 255, paint);
				Graphic.drawPic(canvas,  d_green, 1000, 625, 30, 255, paint);
			}
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

			Graphic.drawPic(canvas, titlebar, 641, 31, 0, 255, paint);
			if(!ene_flag){
				hp_x=Coordinate.AnalogSpeedMove(hp_x, 190+(int)hp*55);
				hp_color=Color.GREEN;
				if(hp_x!= 190+(int)hp*55){
					if(hp_x<hp_x_last){
						hp_color=Color.argb(255, 132, 0, 20);
					}else{
						hp_color=Color.argb(255, 181,230, 29);
					}
				}else {
					hp_x_last=hp_x;
					if(hp<hp_to_yellow&& hp>hp_to_red){
						hp_color=Color.YELLOW;
					}
					if(hp<=hp_to_red){
						hp_color=Color.RED;
					}
				}
				Graphic.drawLine(canvas, hp_color, 190, 50, hp_x, 50, 16, paint);
			}else{
				en_x=Coordinate.AnalogSpeedMove(en_x, 190+(int)en*11);
				Graphic.drawLine(canvas, en_color, 190, 50, en_x, 50, 16, paint);
			}
			//Graphic.drawPic(canvas, hpbar, 730, 50, 0, 255, paint);
			//Graphic.drawPic(canvas, hpfont, 95, 50, 0, 255, paint);
			//TAG HP檢查==========================================================
			if(hp_x<=190+0*55){
				activity.changeView(4);
			}
			//HP檢查----------------------------------------------------------
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
				if(!boss_del_flag){
					Graphic.drawPic(canvas, boss, boss_x, boss_y, 0, 255, paint);
				}
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
					}else if(mp.getCurrentPosition()>boss_kill-boss_kill_delay){
						boss_Flag=true;
					}
				}
			}
			// BOSS 模式狀態-------------------------------------------------------------------------------------------------------------------------

			Graphic.drawPic(canvas, title, 132, 20, 0, 255, paint);
			score.setSize(20, 30);
			score.drawNumberRightStart(1250, 20, sc_score, Number.Wite, canvas, paint);


			//TAG combo顯示============================================================
			Graphic.drawPic(canvas, hits, 290, 200, 0, 255, paint);
			score.setSize(50, 70);
			score.drawNumberRightStart(230, 190, combo, Number.Cyan, canvas, paint);
			//combo顯示-------------------------------------------------------------

			//Graphic.drawPic(canvas, hpfont_red, 95, 50, 0, 255, paint);

			//難易度
			if(activity.difficulty == 0)
			{
				Graphic.drawPic(canvas, game_easy, 1180, 105, 0, 255, paint);
			}else if(activity.difficulty == 1){
				Graphic.drawPic(canvas, game_normal, 1180, 105, 0, 255, paint);
			}else if(activity.difficulty == 2){
				Graphic.drawPic(canvas, game_hard, 1180, 105, 0, 255, paint);
			}

			//TAG BOSS 前警告=========================================
			if(mp.getCurrentPosition()>boss_show-warning_time&&mp.getCurrentPosition()<boss_show&&!warning_flag){
				sp.play(warning_sound, activity.mp_Voiume, activity.mp_Voiume, 0, 0, 1);
				warning_flag=true;
			}
			if(warning_flag){
				if(warning_alpha<=10){
					warning_alpha_flag=255;
				}
				if(warning_alpha>240){
					warning_alpha_flag=0;
				}
				warning_alpha=Coordinate.AnalogSpeedMove(warning_alpha, warning_alpha_flag);
				Graphic.drawPic(canvas, warning, 1280/2, 720/2, 0, warning_alpha, paint);
				if(mp.getCurrentPosition()>boss_show&&warning_alpha<=10){
					warning_flag=false;
				}
			}
			//BOSS 前警告------------------------------------------------------------------------

			//BOSS 攻擊============================================
			if(boss_attack_Flag&&!attack_flag&&!attack_flag2){
				attack_flag=true;
				attack_flag2=true;
				attack.start(mp.getCurrentPosition(), boss_kill_delay, 0);
			}
			if(attack_flag){
				Graphic.drawPic(canvas, attack_pic_round, 1280/2, 495, (mp.getCurrentPosition()/10)%360, 255, paint);
				Graphic.drawPic(canvas, attack_sight, 1280/2, 495, 0, 255, paint);
				attack.drawChartBottom(mp.getCurrentPosition(), canvas, paint);
				if(!attack.getFlag()){
					activity.changeView(4);
				}
			}
			if(beam_attack){
				if(beam_flag){
					beam.startByTime(mp.getCurrentPosition(), beam_time);
					sp.play(beam_sound, activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					beam_flag=false;
				}else{
					if(!beam.getFlag()){
						if(en_II>=80){
							activity.level_clear[activity.level][activity.difficulty]=true;
							activity.boss_delete=true;
							boss_del_flag=true;
							boss_del.startByTime(mp.getCurrentPosition(), boss_del_time);
							sp.play(boss_del_sound, activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
						}else{
							boss_del_flag=false;
							activity.boss_delete=false;
						}
						beam_attack=false;
					}
				}
				beam.drawAnimax(mp.getCurrentPosition(), canvas, paint);
			}
			if(boss_del_flag){
				boss_del.drawAnimax(mp.getCurrentPosition(), canvas, paint);
			}
			//BOSS 攻擊----------------------------------------------------------------------------

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
					sp.play(sp_id[5], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					pause();
					btn_pause.setBottomTo(true);
					
				}
				else if(btn_pause.getBottom()){
					resume();
					btn_pause.setBottomTo(false);
				}
			}
			//PAUSE按鈕功能----------------------------------------------

			//BOSS 攻擊按鈕=============================================
			if(attack.btm.isIn(pointx, pointy)&&attack_flag){
				if((attack.start_time+3000)-mp.getCurrentPosition()<300&&(attack.start_time+3000)-mp.getCurrentPosition()>-300){
					attack_flag=false;
					beam_attack=true;
					en_II=en;
					en=0;
				}
			}
			//BOSS 攻擊按鈕--------------------------------------------------------------------------

			//返回遊戲、從頭開始、返回關卡選擇按鈕功能================================
			if(btn_pause.getBottom()){   //必須在PAUSE按鈕為TRUE的時候才生效

				if(btn_re_play.isIn(pointx, pointy)){
					sp.play(sp_id[5], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					resume();
					btn_pause.setBottomTo(false);
				}
				if(btn_re_start.isIn(pointx, pointy)){
					sp.play(sp_id[5], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
					Reset();
					mp.seekTo(0);
					resume();
					btn_pause.setBottomTo(false);
				}
				if(btn_re_map.isIn(pointx, pointy)){
					sp.play(sp_id[5], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
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
		if(dis<3){
			if(!ene_flag){
				if(hp<hp_max){
					hp+=hp_point[activity.difficulty][dis];
				}
			}else{
				if(en<en_max){
					en+=en_point[activity.difficulty];
				}
			}
		}
		switch(dis){
		case 0:  //NICE
			percent++;
			combo++;
			sc_score+=200*2;
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
			sc_score+=100*2;
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
			sc_score += 50*2;
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
		if(!ene_flag){
			this.hp-=hp_point[activity.difficulty][3];
		}
		combo = 0;
		sc_miss++;
		Hitflag = 4;
		Hitcount = 255;

	}

	public void playSP(){
		if(activity.sp_num!=-1)
			sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
	}
	public void pause(){
		if(mp.isPlaying()){
			mp.pause();
		}
	}
	public void resume(){
		if(!mp.isPlaying()){
			mp.start();
		}
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

		//warning.recycle();

		bg.recycle();   //背景
		sight.recycle();  //準星
		boss.recycle();
		game_easy.recycle();
		game_normal.recycle();
		game_hard.recycle();
		title.recycle();


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

		//BOSS 前警告==
		warning.recycle();
		//BOSS 前警告--
		//BOSS 攻擊==
		attack.recycle();
		attack_pic_round.recycle();
		attack_pic.recycle();
		attack_sight.recycle();
		beam.recycle();
		boss_del.recycle();
		//BOSS 攻擊--
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

		System.gc();
		Constant.Flag=false;
	}


}
