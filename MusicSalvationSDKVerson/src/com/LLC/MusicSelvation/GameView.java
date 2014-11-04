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
import android.net.Uri;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint({ "ViewConstructor", "WrongCall", "ClickableViewAccessibility" })
public class GameView extends SurfaceView
implements SurfaceHolder.Callback{


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

	Bitmap track;  //�y�D

	Bitmap virus_red;
	Bitmap virus_yellow;
	Bitmap virus_blue;
	Bitmap virus_green;

	Bitmap nice;
	Bitmap miss;
	Bitmap safe;
	Bitmap hit;

	Bitmap titlebar;  //���A��
	Bitmap hpbar;
	Bitmap hp_green;
	Bitmap hp_red;
	Bitmap hpfont;
	Bitmap hpfont_red;

	//�S�ĥ��ŧi================================
	Bitmap[] Cyan = new Bitmap [6];
	Bitmap[] Red = new Bitmap [6];
	Bitmap[] Green = new Bitmap [6];
	Bitmap[] Yellow = new Bitmap [6];
	Bitmap[] Blue = new Bitmap [6];
	//�S�ĥ��ŧi----------------------------------

	Bottom btn_circle;
	Bottom btn_square;
	Bottom btn_xx;
	Bottom btn_triangle;
	
	Number score;

	int hp = 20;
	int hp_x;
	int hp_color=Color.GREEN;
	int hp_to_yellow=12;
	int hp_to_red = 6;
	
	//����P�w���FLAG==============================
	int Hitflag = 0;
	int Hitcount = 0;
	//����P�w���FLAG------------------------------

	Paint paint;			//�e�����Ѧ�
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

	static int time_dis=3000;
	SparseArray<PointF> mActivePointers=new SparseArray<PointF>();
	SparseArray<Integer> btn_pointer=new SparseArray<Integer>();

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
		
		score =new Number(getResources());

		bg = 	Graphic.bitSize(LoadBitmap(R.drawable.gamemap01), Constant.DEFULT_WITH, Constant.DEFULT_HIGHT);
		sight =	Graphic.bitSize(LoadBitmap(R.drawable.sightv2), 100, 100);
		//cpu   = Graphic.bitSize(LoadBitmap(R.drawable.cpu_chips), 162, 162);

		game_easy = Graphic.bitSize(LoadBitmap(R.drawable.game_easy), 118, 25);
		game_normal = Graphic.bitSize(LoadBitmap(R.drawable.game_normal), 118, 25);
		game_hard = Graphic.bitSize(LoadBitmap(R.drawable.game_hard), 118, 25);

		circle = Graphic.bitSize(LoadBitmap(R.drawable.btn_circle_v2), 150, 150);
		square = Graphic.bitSize(LoadBitmap(R.drawable.btn_square_v2), 150, 150);
		triangle = Graphic.bitSize(LoadBitmap(R.drawable.btn_triangle_v2), 150, 150);
		xx = Graphic.bitSize(LoadBitmap(R.drawable.btn_x_v2), 150, 150);
		grey_circle = Graphic.bitSize(LoadBitmap(R.drawable.grey_circle), 150, 150);
		grey_square = Graphic.bitSize(LoadBitmap(R.drawable.grey_square), 150, 150);
		grey_triangle = Graphic.bitSize(LoadBitmap(R.drawable.grey_tirangle), 150, 150);
		grey_xx = Graphic.bitSize(LoadBitmap(R.drawable.grey_x), 150, 150);

		track = Graphic.bitSize(LoadBitmap(R.drawable.track_v2), 80, 660);

		virus_blue = Graphic.bitSize(LoadBitmap(R.drawable.virus_blue), 80, 80);
		virus_red = Graphic.bitSize(LoadBitmap(R.drawable.virus_red), 80, 80);
		virus_yellow = Graphic.bitSize(LoadBitmap(R.drawable.virus_yello), 80, 80);
		virus_green = Graphic.bitSize(LoadBitmap(R.drawable.virus_green), 80, 80);

		nice = Graphic.bitSize(LoadBitmap(R.drawable.nice), 175, 55);
		hit = Graphic.bitSize(LoadBitmap(R.drawable.hit), 175, 55);
		safe = Graphic.bitSize(LoadBitmap(R.drawable.safe), 175, 55);
		miss = Graphic.bitSize(LoadBitmap(R.drawable.miss), 175, 55);

		titlebar = Graphic.bitSize(LoadBitmap(R.drawable.titlebar), 1280, 63);
		hpbar = Graphic.bitSize(LoadBitmap(R.drawable.hpbar2), 1100, 23);
		hpfont = Graphic.bitSize(LoadBitmap(R.drawable.hpfont0), 80, 25);
		hpfont_red = Graphic.bitSize(LoadBitmap(R.drawable.hpfont_red0), 80, 25);
		freely = Graphic.bitSize(LoadBitmap(R.drawable.freely), 260, 30);
		boss = Graphic.bitSize(LoadBitmap(R.drawable.boss1), 200, 185);

		//�S�ĥ��]���դ��^
		Cyan[0] = Graphic.bitSize(LoadBitmap(R.drawable.cyan01), 150, 150);
		Cyan[1] = Graphic.bitSize(LoadBitmap(R.drawable.cyan02), 150, 150);
		Cyan[2] = Graphic.bitSize(LoadBitmap(R.drawable.cyan03), 150, 150);
		Cyan[3] = Graphic.bitSize(LoadBitmap(R.drawable.cyan04), 150, 150);
		Cyan[4] = Graphic.bitSize(LoadBitmap(R.drawable.cyan05), 150, 150);
		Cyan[5] = Graphic.bitSize(LoadBitmap(R.drawable.cyan06), 150, 150);
		
		
		Red[0] = Graphic.bitSize(LoadBitmap(R.drawable.red00), 150, 150);
		Red[1] = Graphic.bitSize(LoadBitmap(R.drawable.red01), 150, 150);
		Red[2] = Graphic.bitSize(LoadBitmap(R.drawable.red02), 150, 150);
		Red[3] = Graphic.bitSize(LoadBitmap(R.drawable.red03), 150, 150);
		Red[4] = Graphic.bitSize(LoadBitmap(R.drawable.red04), 150, 150);
		Red[5] = Graphic.bitSize(LoadBitmap(R.drawable.red05), 150, 150);
		
		Yellow[0] = Graphic.bitSize(LoadBitmap(R.drawable.yellow00), 150, 150);
		Yellow[1] = Graphic.bitSize(LoadBitmap(R.drawable.yellow01), 150, 150);
		Yellow[2] = Graphic.bitSize(LoadBitmap(R.drawable.yellow02), 150, 150);
		Yellow[3] = Graphic.bitSize(LoadBitmap(R.drawable.yellow03), 150, 150);
		Yellow[4] = Graphic.bitSize(LoadBitmap(R.drawable.yellow04), 150, 150);
		Yellow[5] = Graphic.bitSize(LoadBitmap(R.drawable.yellow05), 150, 150);
		
		Green[0] = Graphic.bitSize(LoadBitmap(R.drawable.green00), 150, 150);
		Green[1] = Graphic.bitSize(LoadBitmap(R.drawable.green01), 150, 150);
		Green[2] = Graphic.bitSize(LoadBitmap(R.drawable.green02), 150, 150);
		Green[3] = Graphic.bitSize(LoadBitmap(R.drawable.green03), 150, 150);
		Green[4] = Graphic.bitSize(LoadBitmap(R.drawable.green04), 150, 150);
		Green[5] = Graphic.bitSize(LoadBitmap(R.drawable.green05), 150, 150);
		
		Blue[0] = Graphic.bitSize(LoadBitmap(R.drawable.blue00), 150, 150);
		Blue[1] = Graphic.bitSize(LoadBitmap(R.drawable.blue01), 150, 150);
		Blue[2] = Graphic.bitSize(LoadBitmap(R.drawable.blue02), 150, 150);
		Blue[3] = Graphic.bitSize(LoadBitmap(R.drawable.blue03), 150, 150);
		Blue[4] = Graphic.bitSize(LoadBitmap(R.drawable.blue04), 150, 150);
		Blue[5] = Graphic.bitSize(LoadBitmap(R.drawable.blue05), 150, 150);
		
		
		

		btn_circle = new Bottom(activity, grey_circle, circle, 80, 500);
		btn_square = new Bottom(activity, grey_square, square, 230, 645);
		btn_triangle = new Bottom(activity, grey_triangle, triangle, 1050, 645);
		btn_xx = new Bottom(activity, grey_xx, xx, 1200, 500);

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
				//TODO �����ܭp���e��
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
				JSONObject json=null;
				json=activity.read( "freely_tomorrow.mp3");
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
					cs=new chartScan(BtR,BtS,BtT,BtX,time_dis,"GameView");
					cs.Start();
				}else{
					Log.e("GameView","�䤣���Э���");
					activity.changeView(2);
				}

				mp.setVolume(activity.mp_Voiume, activity.mp_Voiume);
				mp.start();
				startFlag=false;
			}
			//TODO ���y=================================================================================
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
			}//���y----------------------------------------------------------------------------------------------------------------------------------------------------------

			super.onDraw(canvas);
			canvas.clipRect(new Rect(0,0,Constant.SCREEN_WIDTH,Constant.SCREEN_HIGHT));//�u�b�ù��d��ø��Ϥ�
			canvas.drawColor(Color.BLACK);//�ɭ��]�w���¦�
			Graphic.drawPic(canvas, bg, 1280/2, 720/2, 0, 255, paint);//�I��


			Graphic.drawPic(canvas, track, 450, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 575, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 700, 390, 0, 255, paint);
			Graphic.drawPic(canvas, track, 825, 390, 0, 255, paint);
			
			//�P�w���======================================================
			Hitcount--;
			if(Hitcount <=0){
				Hitcount = 0;
			}
			if(Hitcount > 0)
				{
				switch(Hitflag){  //����hitflag�ثe�����A
				
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
			}
			//�P�w���--------------------------------------------------------

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

			Graphic.drawPic(canvas, sight, 450, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 575, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 700, 600, 0, 255, paint);
			Graphic.drawPic(canvas, sight, 825, 600, 0, 255, paint);


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
			Graphic.drawPic(canvas, hpbar, 730, 50, 0, 255, paint);
			Graphic.drawPic(canvas, hpfont, 95, 50, 0, 255, paint);
			Graphic.drawPic(canvas, freely, 132, 20, 0, 255, paint);
			score.setSize(20, 30);
			score.drawNumberRightStart(1250, 20, activity.score, Number.Wite, canvas, paint);
			
			//Graphic.drawPic(canvas, hpfont_red, 95, 50, 0, 255, paint);

			//������
			Graphic.drawPic(canvas, game_easy, 635, 20, 0, 255, paint);
			Graphic.drawPic(canvas, game_normal, 635, 20, 0, 255, paint);
			Graphic.drawPic(canvas, game_hard, 635, 20, 0, 255, paint);

			btn_circle.setBottomTo(false);	
			btn_square.setBottomTo(false);	
			btn_triangle.setBottomTo(false);	
			btn_xx.setBottomTo(false);
			for(int i=0;i<btn_pointer.size();i++){
				int st=btn_pointer.valueAt(i);
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


			btn_circle.drawBtm(canvas, paint);
			btn_square.drawBtm(canvas, paint);
			btn_triangle.drawBtm(canvas, paint);
			btn_xx.drawBtm(canvas, paint);

		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event){
		int pointerIndex = event.getActionIndex();

		// get pointer ID
		int pointerId = event.getPointerId(pointerIndex);

		switch(event.getActionMasked())
		{
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN://���U
			PointF f = new PointF();
			f.x = event.getX(pointerIndex);
			f.y = event.getY(pointerIndex);
			mActivePointers.put(pointerId, f);

			if(btn_circle.isIn(f.x, f.y)){
				hp++;
				playSP();
				for(int i=0;i<chartObject;i++){
					if(cr_btm[i].getFlag()){

						int cr_dis=Math.abs(cr_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cr_dis<3){
							scoreCount(cr_dis);
							cr_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 0);
			}
			if(btn_square.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<chartObject;i++){
					if(cs_btm[i].getFlag()){
						int cs_dis=Math.abs(cs_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cs_dis<3){
							scoreCount(cs_dis);
							cs_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 1);
			}
			if(btn_triangle.isIn(f.x, f.y)){
				playSP();
				for(int i=0;i<chartObject;i++){
					if(ct_btm[i].getFlag()){
						int ct_dis=Math.abs(ct_btm[i].getId()-mp.getCurrentPosition()/100);
						if(ct_dis<3){
							scoreCount(ct_dis);
							ct_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 2);
			}
			if(btn_xx.isIn(f.x, f.y)){
				hp--;
				playSP();
				for(int i=0;i<chartObject;i++){
					if(cx_btm[i].getFlag()){
						int cx_dis=Math.abs(cx_btm[i].getId()-mp.getCurrentPosition()/100);
						if(cx_dis<3){
							scoreCount(cx_dis);
							cx_btm[i].stop();
							break;
						}
					}
				}
				btn_pointer.put(pointerId, 3);
			}
			//if(startbtm.isIn(pointx, pointy)){
				//�i�J�a�ϵe��
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
			//if(.isIn(pointx, pointy)){
			//TODO ���}�C�����g
			//}
			break;
		}

		return true;
	}
	public void scoreCount(int dis){
		switch(dis){
		case 0:
			activity.score+=200;
			activity.nice++;
			Hitflag = 1;
			Hitcount = 255;
			break;
		case 1:
			activity.score+=100;
			activity.hit++;
			Hitflag = 2;
			Hitcount = 255;
			break;
		case 2:
			activity.score += 50;
			activity.safe++;
			Hitflag = 3;
			Hitcount = 255;
			break;
		}
	}
	
	public void playSP(){
		if(activity.sp_num!=-1)
			sp.play(sp_id[activity.sp_num], activity.sp_Voiume, activity.sp_Voiume, 0, 0, 1);
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//�P���ɳQ�I�s
		//TODO �Ȱ����
		mp.stop();
		startFlag=true;
		Constant.Flag=false;
	}


}
