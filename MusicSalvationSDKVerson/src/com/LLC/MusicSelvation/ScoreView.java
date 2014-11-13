package com.LLC.MusicSelvation;
//

import com.example.musicsalvationsdkverson.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class ScoreView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;
	
	Bitmap bg;
	Bitmap titlebar;
	Bitmap rightbar;
	Bitmap leftbar;
	Bitmap rankbar;
	Bitmap clearbar;
	Bitmap line;
	
	Bitmap freely;
	Bitmap totalvirus;
	Bitmap nice;
	Bitmap hit;
	Bitmap safe;
	Bitmap miss;
	Bitmap max_combo;
	Bitmap score;
	Bitmap highscore;
	Bitmap quest_stage;
	Bitmap bossclear;
	Bitmap rank;
	Bitmap clear;
	Bitmap failed;
	
	Bitmap easy;
	Bitmap normal;
	Bitmap hard;
	
	Bitmap y;
	Bitmap n;
	
	//過關等級================================================
	Bitmap R_S;
	Bitmap R_A;
	Bitmap R_B;
	Bitmap R_C;
	Bitmap R_D;
	Bitmap R_E;
	Bitmap R_F;
	//過關等級------------------------------------------------
	
	Bitmap num_grey;
	Bitmap num_white;
	Bitmap num_red;
	Bitmap num_yellow;
	Bitmap num_green;
	Bitmap num_cyan;
	Bitmap num_blue;
	
	Bitmap rank_s;
	Bitmap rank_a;
	Bitmap rank_b;
	Bitmap rank_c;
	Bitmap rank_d;
	Bitmap rank_e;
	Bitmap rank_f;
	
	//成績數字==============================================================================
	Number num;
	//---------------------------------------------------------------------------------------
	
	int pointx;//觸控到螢幕的x座標
	int pointy;//觸控到螢幕的y座標
	
	//過關判定============
	int percent = 0;
	//過關判定------------------
	
	//儲存遊戲判定用參數=======================================
	int sc_nice = 0;
	int sc_hit = 0;
	int sc_safe = 0;
	int sc_miss = 0;
	int sc_combo = 0;
	int sc_score = 0;
	//儲存遊戲判定用參數---------------------------------------

	Paint paint;			//畫筆的參考
	int i=0,j=10;
	MainActivity activity;

	public ScoreView(MainActivity mainActivity) {
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
		
		num =new Number(getResources());
		
		
		bg=Graphic.bitSize(LoadBitmap( R.drawable.sv_background), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		titlebar = Graphic.bitSize(LoadBitmap(R.drawable.title_bar), 1280, 90);
		rightbar = Graphic.bitSize(LoadBitmap(R.drawable.right_bar), 625, 75);
		leftbar  = Graphic.bitSize(LoadBitmap(R.drawable.left_bar), 620, 75);
		rankbar  = Graphic.bitSize(LoadBitmap(R.drawable.rank_bar), 615, 185);
		clearbar = Graphic.bitSize(LoadBitmap(R.drawable.clear_bar), 620, 150);
		line     = Graphic.bitSize(LoadBitmap(R.drawable.line), 625, 2);
		
		freely   = Graphic.bitSize(LoadBitmap(R.drawable.title), 560, 65);
		totalvirus = Graphic.bitSize(LoadBitmap(R.drawable.totalvirus), 410, 60);
		nice = Graphic.bitSize(LoadBitmap(R.drawable.sv_nice), 165, 60);
		hit  = Graphic.bitSize(LoadBitmap(R.drawable.sv_hit), 125, 60);
		safe = Graphic.bitSize(LoadBitmap(R.drawable.sv_safe), 165, 60);
		miss = Graphic.bitSize(LoadBitmap(R.drawable.sv_miss), 165, 60);
		max_combo = Graphic.bitSize(LoadBitmap(R.drawable.max_combo), 340, 60);
		quest_stage = Graphic.bitSize(LoadBitmap(R.drawable.quest_stage), 415, 60);
		bossclear = Graphic.bitSize(LoadBitmap(R.drawable.boss_clear), 375, 60);
		score = Graphic.bitSize(LoadBitmap(R.drawable.score), 170, 55);
		highscore = Graphic.bitSize(LoadBitmap(R.drawable.highscore), 335, 55);
		clear = Graphic.bitSize(LoadBitmap(R.drawable.clear), 395, 140);
		failed = Graphic.bitSize(LoadBitmap(R.drawable.failed), 475, 140);
		rank = Graphic.bitSize(LoadBitmap(R.drawable.rank), 190, 95);
		
		easy = Graphic.bitSize(LoadBitmap(R.drawable.sv_easy), 280, 75);
		normal = Graphic.bitSize(LoadBitmap(R.drawable.sv_normal), 280, 75);
		hard = Graphic.bitSize(LoadBitmap(R.drawable.sv_hard), 280, 75);
		
		y = Graphic.bitSize(LoadBitmap(R.drawable.y), 30, 50);
		n = Graphic.bitSize(LoadBitmap(R.drawable.n), 30, 50);
		
		num_grey = Graphic.bitSize(LoadBitmap(R.drawable.num_gray), 350, 50);
		num_red = Graphic.bitSize(LoadBitmap(R.drawable.num_red), 350, 50);
		num_yellow = Graphic.bitSize(LoadBitmap(R.drawable.num_yellow), 350, 50);
		num_green = Graphic.bitSize(LoadBitmap(R.drawable.num_green), 350, 50);
		num_blue = Graphic.bitSize(LoadBitmap(R.drawable.num_blue), 350, 50);
		num_cyan = Graphic.bitSize(LoadBitmap(R.drawable.num_cyan), 350, 50);
		
		rank_f = Graphic.bitSize(LoadBitmap(R.drawable.r_f), 86, 146);
		rank_e = Graphic.bitSize(LoadBitmap(R.drawable.r_e), 99, 152);
		rank_d = Graphic.bitSize(LoadBitmap(R.drawable.r_d), 124, 152);
		rank_c = Graphic.bitSize(LoadBitmap(R.drawable.r_c), 117, 176);
		rank_b = Graphic.bitSize(LoadBitmap(R.drawable.r_b), 92, 152);
		rank_a = Graphic.bitSize(LoadBitmap(R.drawable.r_a), 133, 182);
		rank_s = Graphic.bitSize(LoadBitmap(R.drawable.r_s), 309, 257);
		
		
		


		Constant.Flag=true;
		new Thread(){
			@SuppressLint("WrongCall")
			public void run()
			{
				while(Constant.Flag){
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					SurfaceHolder myholder=ScoreView.this.getHolder();
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
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//背景
			Graphic.drawPic(canvas, titlebar, 1280/2, 45, 0, 255, paint);
			Graphic.drawPic(canvas, clearbar, 950, 165, 0, 255, paint);
			
			Graphic.drawPic(canvas, leftbar, 320, 178, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 270, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 356, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 438, 0, 255, paint);
			Graphic.drawPic(canvas, leftbar, 320, 517, 0, 255, paint);
			
			Graphic.drawPic(canvas, rightbar, 960, 300, 0, 255, paint);
			Graphic.drawPic(canvas, rightbar, 960, 390, 0, 255, paint);
			Graphic.drawPic(canvas, rightbar, 960, 480, 0, 255, paint);
			Graphic.drawPic(canvas, rankbar, 960, 625, 0, 255, paint);
			
			Graphic.drawPic(canvas, freely, 290, 40, 0, 255, paint);
			
			//難易度
			Graphic.drawPic(canvas, easy, 1100, 40, 0, 255, paint);
			Graphic.drawPic(canvas, normal, 1100, 40, 0, 255, paint);
			Graphic.drawPic(canvas, hard, 1100, 40, 0, 255, paint);
			
			Graphic.drawPic(canvas, line, 310, 625, 0, 255, paint);
			Graphic.drawPic(canvas, line, 310, 625, 0, 255, paint);
			
			Graphic.drawPic(canvas, totalvirus, 240, 180, 0, 255, paint);
			Graphic.drawPic(canvas, nice, 120, 275, 0, 255, paint);
			Graphic.drawPic(canvas, hit, 100, 360, 0, 255, paint);
			Graphic.drawPic(canvas, safe, 120, 440, 0, 255, paint);
			Graphic.drawPic(canvas, miss, 120, 520, 0, 255, paint);
			Graphic.drawPic(canvas, max_combo, 845, 300, 0, 255, paint);
			Graphic.drawPic(canvas, quest_stage, 880, 390, 0, 255, paint);
			Graphic.drawPic(canvas, bossclear, 860, 490, 0, 255, paint);
			Graphic.drawPic(canvas, score, 120, 590, 0, 255, paint);
			Graphic.drawPic(canvas, highscore, 200, 665, 0, 255, paint);
			Graphic.drawPic(canvas, rank, 760, 645, 0, 255, paint);
			
			
			//數字====================================================================================
			
			if(sc_nice != activity.nice){
				sc_nice++;
			}
			if(sc_hit != activity.hit){
				sc_hit++;
			}
			if(sc_safe != activity.safe){
				sc_safe++;
			}
			if(sc_miss != activity.miss){
				sc_miss++;
			}
			if(sc_score != activity.score){
				sc_score+=50;
			}
			if(sc_combo != activity.combo){
				sc_combo++;
			}
			
			num.setSize(35, 60);
			num.drawNumberRightStart(630, 180, activity.virus, Number.Gray, canvas, paint);
			num.drawNumberRightStart(630, 270, sc_nice, Number.Yellow, canvas, paint);
			num.drawNumberRightStart(630, 360, sc_hit, Number.Cyan, canvas, paint);
			num.drawNumberRightStart(630, 440, sc_safe, Number.Green, canvas, paint);
			num.drawNumberRightStart(630, 520, sc_miss, Number.Red, canvas, paint);
			num.drawNumberRightStart(1250, 300, sc_combo, Number.Blue, canvas, paint);

			num.setSize(30, 55);
			num.drawNumberRightStart(620, 590, sc_score, Number.Wite, canvas, paint);
			num.drawNumberRightStart(620, 660, activity.score, Number.Wite, canvas, paint);
			//數字------------------------------------------------------------------------------------
			
			//判定是否過關==============================================================================
			if(activity.percent > ((int)activity.virus*0.7) )  //如果打擊率超過70%
			{
				Graphic.drawPic(canvas, clear, 950, 165, 0, 255, paint);
				//FULL COMBO判定為S級
				if(activity.combo == activity.virus){   
					Graphic.drawPic(canvas, rank_s, 1030, 630, 0, 255, paint);
				}
				//打擊率超過90% 為A
					else if(activity.percent > ((int)activity.virus*0.9))  
					{
						Graphic.drawPic(canvas, rank_a, 1030, 630, 0, 255, paint);
					}
				//打擊率超過80% 低於90%為B
					else if(activity.percent > ((int)activity.virus*0.8) && activity.percent < ((int)activity.virus*0.9))
					{
						Graphic.drawPic(canvas, rank_b, 1030, 630, 0, 255, paint);
					}
				//打擊率超過70% 低於80%為C
					else if(activity.percent > ((int)activity.virus*0.7) && activity.percent < ((int)activity.virus*0.8)) //大於70% 小於80%
					{
						Graphic.drawPic(canvas, rank_c, 1030, 630, 0, 255, paint);
					}
				
			}
			else
			{
				Graphic.drawPic(canvas, failed, 950, 160, 0, 255, paint);
				//打擊率超過60% 低於70%為D
				if(activity.percent > ((int)activity.virus*0.6) && activity.percent < ((int)activity.virus*0.7))
				{
					Graphic.drawPic(canvas, rank_d, 1030, 630, 0, 255, paint);
				}
				//打擊率超過50% 低於60%為E
				else if(activity.percent > ((int)activity.virus*0.5) && activity.percent < ((int)activity.virus*0.6))
				{
					Graphic.drawPic(canvas, rank_e, 1030, 630, 0, 255, paint);
				}
				//低於50%以下一律為F
				else
				{
					Graphic.drawPic(canvas, rank_f, 1030, 630, 0, 255, paint);
				}
					
			}
			//判定是否過關------------------------------------------------------------------------------
			
			
			
			
			/*擊殺BOSS與否
			Graphic.drawPic(canvas, y, 1225, 490, 0, 255, paint);
			Graphic.drawPic(canvas, n, 1225, 490, 0, 255, paint);
			*/
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		pointx=(int) event.getX();
		pointy=(int) event.getY();
		
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN://按下
				if(deJump == true){
				
				}
				deJump = false;
				break;
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2
					
				}
				deJump = true;
				break;
			}
		
		
			switch(event.getAction())
			{
			//......................................................................................
			case MotionEvent.ACTION_DOWN://按下
				if(deJump==true){//防止彈跳part1
				}
				deJump=false;
				break;
			//.....................................................................................
			case MotionEvent.ACTION_UP://抬起
				if(deJump==false){//防止彈跳part2
					
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
		Constant.Flag=false;
	}


}
