package com.LLC.MusicSelvation;

import com.example.musicsalvationsdkverson.R;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Video extends SurfaceView {
	MainActivity activity;
	MediaPlayer mp;
	SurfaceHolder sh;
	public Video(final MainActivity activity){
		super(activity.getApplicationContext());
		this.activity=activity;
		sh=this.getHolder();
		sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		sh.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) {
				if(mp!=null){
					mp.release();
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder arg0) {
				switch(activity.video_select){
				case 0:
					mp=MediaPlayer.create(activity, R.raw.llc);
				break;
				}
				mp.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						switch(activity.video_select){
						case 0:
							activity.changeView(1);
						break;
						}
					}
				});
				mp.setDisplay(sh);
				mp.start();
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
}
