package com.LLC.MusicSelvation;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
//import android.widget.Toast;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity{
	int nowActivity=0;
	StartView startview;
	MainView mainview;
	EditView editview;

	Intent intent;
	Intent deintent;
	Uri uri;

	public void changeView(int what)//
	{
		Message msg = myHandler.obtainMessage(what); 
		myHandler.sendMessage(msg);
		nowActivity=what;
	} 
	
	Handler myHandler = new Handler(){//處理各個SurfaceView傳送的訊息
		public void handleMessage(Message msg) {
			switch(msg.what)//
			{
			case 0:
				goToStartView();//初始
				break;
			case 1:
				goToMainView();//主要
				break;
			case 2:
				goToMapView();//地圖
				break;
			case 3:
				goToGameView();//游戲
				break;
			case 4:
				goToScoreView();//得分
				break;
			case 5:
				goToLastView();//結束
				break;
			case 6:
				goToEditView();
				break;
			case 7:
				chooseFile();
				break;
			}
		}
	};
	

	protected void goToEditView() {
		if(editview==null)
		{
			editview=new EditView(this);
		}
		setContentView(editview);
		editview.requestFocus();
		editview.setFocusableInTouchMode(true);
	}
	private void goToStartView() {
		if(startview==null)
		{
			startview=new StartView(this);
		}
		setContentView(startview);
	}
	private void goToMainView() {
		if(mainview==null)
		{
			mainview=new MainView(this);
		}
		setContentView(mainview);
		mainview.requestFocus();//取得焦點
		mainview.setFocusableInTouchMode(true);//設為可觸控
	}
	private void goToMapView() {
		// TODO 自動產生的方法 Stub

	}
	private void goToGameView() {
		// TODO 自動產生的方法 Stub

	}
	private void goToScoreView() {
		// TODO 自動產生的方法 Stub

	}
	private void goToLastView() {
		// TODO 自動產生的方法 Stub

	}

	public void callToast(String what)//Toast訊息傳送
	{
		Message msg = toastHandler.obtainMessage(1,what); 
		toastHandler.sendMessage(msg);
	} 
	Handler toastHandler = new Handler(){//處理各個SurfaceView傳送的Toast訊息
		public void handleMessage(Message msg) {
			createToast((String)msg.obj);
		}
	};
	public void createToast(String msg){//顯示Toast
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//游戲過程中只容許調整多媒體音量，而不容許調整通話音量
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉標題
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉標頭
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//強制橫屏

		//取得解析度
		DisplayMetrics dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//給常數類別中的螢幕高和寬給予值
		if(dm.widthPixels>dm.heightPixels)
		{
			Constant.SCREEN_WIDTH=dm.widthPixels;
			Constant.SCREEN_HIGHT=dm.heightPixels;
		}else
		{
			Constant.SCREEN_HIGHT=dm.widthPixels;
			Constant.SCREEN_WIDTH=dm.heightPixels;
		}
		if(Constant.SCREEN_HIGHT>Constant.SCREEN_WIDTH/16*9)//將螢幕固定為16:9
			Constant.SCREEN_HIGHT=Constant.SCREEN_WIDTH/16*9;
		else
			Constant.SCREEN_WIDTH=Constant.SCREEN_HIGHT/9*16;

		Constant.GAME_WIDTH_UNIT= ((float)Constant.SCREEN_WIDTH/Constant.DEFULT_WITH);
		Constant.SCREEN_HEIGHT_UNIT= ((float)Constant.SCREEN_HIGHT/Constant.DEFULT_HIGHT);
		//Toast.makeText(this, "widthPixels"+dm.widthPixels+"heightPixels"+dm.heightPixels, Toast.LENGTH_LONG).show();
		changeView(0);//進入"歡迎界面"
	}




	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		if(keyCode==4)
		{
			switch(nowActivity)
			{
			case 2://得分界面
			case 3://最後的界面
			case 4://游戲界面
			case 6://譜面編輯器
				Constant.Flag=false;
				this.changeView(1);//回到主界面
				break;
			case 0://歡迎界面
			case 1://主控制界面
				System.exit(0);//離開游戲
				break;

			}
			return true;
		}
		/*if(keyCode==e.KEYCODE_HOME){
			 System.exit(0);
			return true;
		}*/
		return false;

	}
	public void chooseFile(){//檔案選擇器設定
		Intent intent = new Intent( Intent.ACTION_GET_CONTENT);//ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI );						// 建立 "選擇檔案 Action" 的 Intent
		intent.setType("audio/*");														// 過濾檔案格式
		Intent deintent = Intent.createChooser(intent, "選擇檔案");		// 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
		startActivityForResult( deintent, 0 );									// 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        
        // 有選擇檔案
       // Looper.prepare();
        if ( resultCode == RESULT_OK )
        {
            // 取得檔案的 Uri
            uri = data.getData();
            if( uri != null )
            {
            	Toast.makeText(this, "檔案已選擇!", Toast.LENGTH_SHORT).show();
            	this.changeView(6);
            	Constant.Flag=true;
            }
            else
            {
            	Toast.makeText(this, "無效的檔案路徑 !", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
        	Toast.makeText(this, "取消選擇檔案 !", Toast.LENGTH_SHORT).show();
        }
        //Looper.loop();
    }
	/*public String checkType(Uri uri){
		ContextWrapper context = null;
		ContentResolver cR = context.getContentResolver();
		MimeTypeMap mime = MimeTypeMap.getSingleton();
		String type = mime.getExtensionFromMimeType(cR.getType(uri));
		return type;
	}*/
	public Uri sendUri(){
		return uri;
	}
	

	@Override 
	public void onResume(){
		Constant.setFlag(true);
		super.onResume();
	}
	@Override 
	public void onPause(){
		Constant.setFlag(false);
		super.onPause();		
	}
}
