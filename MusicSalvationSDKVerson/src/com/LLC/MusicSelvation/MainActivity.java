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
	
	Handler myHandler = new Handler(){//�B�z�U��SurfaceView�ǰe���T��
		public void handleMessage(Message msg) {
			switch(msg.what)//
			{
			case 0:
				goToStartView();//��l
				break;
			case 1:
				goToMainView();//�D�n
				break;
			case 2:
				goToMapView();//�a��
				break;
			case 3:
				goToGameView();//����
				break;
			case 4:
				goToScoreView();//�o��
				break;
			case 5:
				goToLastView();//����
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
		mainview.requestFocus();//���o�J�I
		mainview.setFocusableInTouchMode(true);//�]���iĲ��
	}
	private void goToMapView() {
		// TODO �۰ʲ��ͪ���k Stub

	}
	private void goToGameView() {
		// TODO �۰ʲ��ͪ���k Stub

	}
	private void goToScoreView() {
		// TODO �۰ʲ��ͪ���k Stub

	}
	private void goToLastView() {
		// TODO �۰ʲ��ͪ���k Stub

	}

	public void callToast(String what)//Toast�T���ǰe
	{
		Message msg = toastHandler.obtainMessage(1,what); 
		toastHandler.sendMessage(msg);
	} 
	Handler toastHandler = new Handler(){//�B�z�U��SurfaceView�ǰe��Toast�T��
		public void handleMessage(Message msg) {
			createToast((String)msg.obj);
		}
	};
	public void createToast(String msg){//���Toast
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//�����L�{���u�e�\�վ�h�C�魵�q�A�Ӥ��e�\�վ�q�ܭ��q
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//�h�����D
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//�h�����Y
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//�j����

		//���o�ѪR��
		DisplayMetrics dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		//���`�����O�����ù����M�e������
		if(dm.widthPixels>dm.heightPixels)
		{
			Constant.SCREEN_WIDTH=dm.widthPixels;
			Constant.SCREEN_HIGHT=dm.heightPixels;
		}else
		{
			Constant.SCREEN_HIGHT=dm.widthPixels;
			Constant.SCREEN_WIDTH=dm.heightPixels;
		}
		if(Constant.SCREEN_HIGHT>Constant.SCREEN_WIDTH/16*9)//�N�ù��T�w��16:9
			Constant.SCREEN_HIGHT=Constant.SCREEN_WIDTH/16*9;
		else
			Constant.SCREEN_WIDTH=Constant.SCREEN_HIGHT/9*16;

		Constant.GAME_WIDTH_UNIT= ((float)Constant.SCREEN_WIDTH/Constant.DEFULT_WITH);
		Constant.SCREEN_HEIGHT_UNIT= ((float)Constant.SCREEN_HIGHT/Constant.DEFULT_HIGHT);
		//Toast.makeText(this, "widthPixels"+dm.widthPixels+"heightPixels"+dm.heightPixels, Toast.LENGTH_LONG).show();
		changeView(0);//�i�J"�w��ɭ�"
	}




	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		if(keyCode==4)
		{
			switch(nowActivity)
			{
			case 2://�o���ɭ�
			case 3://�̫᪺�ɭ�
			case 4://�����ɭ�
			case 6://�Э��s�边
				Constant.Flag=false;
				this.changeView(1);//�^��D�ɭ�
				break;
			case 0://�w��ɭ�
			case 1://�D����ɭ�
				System.exit(0);//���}����
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
	public void chooseFile(){//�ɮ׿�ܾ��]�w
		Intent intent = new Intent( Intent.ACTION_GET_CONTENT);//ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI );						// �إ� "����ɮ� Action" �� Intent
		intent.setType("audio/*");														// �L�o�ɮ׮榡
		Intent deintent = Intent.createChooser(intent, "����ɮ�");		// �إ� "�ɮ׿�ܾ�" �� Intent  (�ĤG�ӰѼ�: ��ܾ������D)
		startActivityForResult( deintent, 0 );									// �������ɮ׿�ܾ� (�����B�z���G, �|Ĳ�o onActivityResult �ƥ�)
	}
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        
        // ������ɮ�
       // Looper.prepare();
        if ( resultCode == RESULT_OK )
        {
            // ���o�ɮת� Uri
            uri = data.getData();
            if( uri != null )
            {
            	Toast.makeText(this, "�ɮפw���!", Toast.LENGTH_SHORT).show();
            	this.changeView(6);
            	Constant.Flag=true;
            }
            else
            {
            	Toast.makeText(this, "�L�Ī��ɮ׸��| !", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
        	Toast.makeText(this, "��������ɮ� !", Toast.LENGTH_SHORT).show();
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
