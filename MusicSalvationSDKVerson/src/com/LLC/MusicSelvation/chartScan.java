package com.LLC.MusicSelvation;

import java.util.Timer;
import java.util.TimerTask;

public class chartScan {
	
	int scan_time_flag;
	Timer timer;
	TimerTask task;

	public chartScan(){
		timer = new Timer();
		scan_time_flag=0;
	}
	public void Start(){
		timer.schedule( new TimerTask(){
			@Override
			public void run() {
				mainScan();
			}
		},0,1 );
	}
	public void pause(){
		timer.cancel();
	}
	public void resume(){
		timer.schedule( new TimerTask(){
			@Override
			public void run() {
				mainScan();
			}
		},0,1 );
	}
	public void check(int currentTime){
		if(currentTime!=scan_time_flag){
			scan_time_flag=currentTime;
		}
	}
	public void Stop(){
		timer.cancel();
		scan_time_flag=0;
	}
	
	public void mainScan(){
		scan_time_flag++;
	}
}
