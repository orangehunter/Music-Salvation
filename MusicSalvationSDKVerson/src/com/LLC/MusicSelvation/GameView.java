package com.LLC.MusicSelvation;
//

import java.io.IOException;

import com.example.musicsalvationsdkverson.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class GameView extends SurfaceView
implements SurfaceHolder.Callback{

	boolean deJump=true;

	boolean startFlag=true;
	Bitmap bg;   //�I��
	Bitmap sight;  //�ǬP
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
	
	Bitmap track_leftdown;  //�y�D
	Bitmap track_leftup;
	Bitmap track_rightdown;
	Bitmap track_rightup;
	
	Bitmap nice;
	Bitmap miss;
	Bitmap safe;
	Bitmap hit;
	
	Bitmap hpbar;  //���A��
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
	

	int pointx;//Ĳ����ù���x�y��
	int pointy;//Ĳ����ù���y�y��
	

	Paint paint;			//�e�����Ѧ�
	int i=0,j=5;
	MainActivity activity;
	
	MediaPlayer mp;
	
	int temp;

	public GameView(MainActivity mainActivity) {
		super(mainActivity);
		this.activity = mainActivity;
		this.getHolder().addCallback(this);//�]�w�ͩR�P���^�ձ��f����{��


	}
	//�ʵe��@��k
	/*public void CyanAnime(int num,float s,int a){

	
	}*/

	public Bitmap LoadBitmap(int r){
		return BitmapFactory.decodeResource(getResources(), r);
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();//�إߵe��
		paint.setAntiAlias(true);//�}�ҧܿ���
		
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
		
		//�S�ĥ��]���դ��^
		
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
		
		//TODO ���J����
		mp=MediaPlayer.create(this.getContext(), R.raw.freely_tomorrow);

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
					Canvas canvas = myholder.lockCanvas();//���o�e��
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
	protected void onDraw(Canvas canvas) {//���s�w�q��ø���k
		if(canvas!=null){
			if(startFlag){
				mp.setVolume(activity.mp_Voiume, activity.mp_Voiume);
				mp.start();
				startFlag=false;
			}else if(mp.getCurrentPosition()==mp.getDuration()){
				//TODO �����ܭp���e��
				activity.changeView(2);
			}
			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//�u�b�ù��d��ø��Ϥ�
			canvas.drawColor(Color.BLACK);//�ɭ��]�w���¦�
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//�I��
			Graphic.drawPic(canvas, hpbar, 641, 31, 0, 255, paint);
			Graphic.drawPic(canvas, hpfont, 95, 50, 0, 255, paint);
			Graphic.drawPic(canvas, freely, 132, 20, 0, 255, paint);
			//Graphic.drawPic(canvas, hpfont_red, 95, 50, 0, 255, paint);
			
			
			Graphic.drawPic(canvas, track_leftdown, 315, 290, 0, 255, paint);
			Graphic.drawPic(canvas, track_leftup, 315, 210, 0, 255, paint);
			Graphic.drawPic(canvas, track_rightdown, 963, 290, 0, 255, paint);
			Graphic.drawPic(canvas, track_rightup, 963, 209, 0, 255, paint);
			Graphic.drawPic(canvas, cpu, 640, 247, 0, 255, paint);
			
			Graphic.drawPic(canvas, sight, 715, 306, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 715, 189, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 563, 306, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 563, 189, 0, 255, paint);
			
			
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
			case MotionEvent.ACTION_DOWN://���U
				if(deJump==true){//����u��part1
					if(btn_circle.isIn(pointx, pointy)){
						btn_circle.setBottomTo(true);
					}
					if(btn_square.isIn(pointx, pointy)){
						btn_square.setBottomTo(true);
					}
					if(btn_triangle.isIn(pointx, pointy)){
						btn_triangle.setBottomTo(true);
					}
					if(btn_xx.isIn(pointx, pointy)){
						btn_xx.setBottomTo(true);
					}
					/*if(startbtm.isIn(pointx, pointy)){
						//�i�J�a�ϵe��
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
					
					//����u��part2
					/*this.toEditView++;
					if(this.toEditView>2){
						//Constant.Flag=false;
						this.toEditView=1;
						activity.changeView(6);
					}*/
					
					/*if(.isIn(pointx, pointy)){
						//TODO ���}�C�����g
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

	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�I�s
		mp.stop();
		startFlag=true;
		Constant.Flag=false;
	}


}