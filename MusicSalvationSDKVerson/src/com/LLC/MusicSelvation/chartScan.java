package com.LLC.MusicSelvation;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;


public class chartScan {

	int scan_time_flag;
	Timer timer;
	TimerTask task;
	

	boolean R_scan_flag=false;
	boolean S_scan_flag=false;
	boolean T_scan_flag=false;
	boolean X_scan_flag=false;


	JSONObject 
	BtR=new JSONObject()
	,BtS=new JSONObject()
	,BtT=new JSONObject()
	,BtX=new JSONObject();

	public chartScan(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		build(R,S,T,X);
	}
	public void build(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		timer = new Timer();
		scan_time_flag=0;
		BtR=R;
		BtS=S;
		BtT=T;
		BtX=X;
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
	public void resume(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		build(R,S,T,X);
		Start();
	}
	public void checkTime(int currentTime){
		if(currentTime!=scan_time_flag){
			scan_time_flag=currentTime;
		}
	}
	public void checkChart(JSONObject R,JSONObject S,JSONObject T,JSONObject X){
		if(!R.equals(BtR)){
			BtR=R;
		}
		if(!S.equals(BtS)){
			BtS=S;
		}
		if(!T.equals(BtT)){
			BtT=T;
		}
		if(!X.equals(BtX)){
			BtX=X;
		}
	}
	public void Stop(){
		timer.cancel();
		scan_time_flag=0;
	}

	public void mainScan(){
		scan_time_flag++;
		if(BtR.optBoolean(Integer.toString(scan_time_flag))){
			R_scan_flag=true;
		}
		if(BtS.optBoolean(Integer.toString(scan_time_flag))){
			S_scan_flag=true;
		}
		if(BtT.optBoolean(Integer.toString(scan_time_flag))){
			T_scan_flag=true;
		}
		if(BtX.optBoolean(Integer.toString(scan_time_flag))){
			X_scan_flag=true;
		}
	}
	public int getTime(){
		return scan_time_flag;
	}
}
