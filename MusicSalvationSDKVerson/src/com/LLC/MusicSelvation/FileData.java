package com.LLC.MusicSelvation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.SparseArray;

@SuppressLint("UseSparseArrays")
public class FileData {

	public static String turnUriToName(Uri u){
		String a=u.toString(),b="";
		for(int i=a.length();i>0;i--){
			if(a.substring(i-1, i).equals("/")){
				break;
			}else if(a.substring(i-3, i).equals("%20")){
				b+=" ";
				i-=2;
			}else b+=a.substring(i-1, i);
		}

		return Reversion(b);
	}
	public static String Reversion(String temp){
		String c="";
		for(int i=temp.length();i>0;i--){
			c+=temp.subSequence(i-1, i);
		}
		return c;
	}

	public static boolean IfData(MainActivity activity){
		File vSDCard = null;

		try {
			// 判斷 SD Card 有無插入
			if( Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED) ){
				activity.callToast("偵測不到SD卡");
			}else{
				// 取得 SD Card 位置
				vSDCard = Environment.getExternalStorageDirectory();
			}

			// 判斷目錄是否存在
			File vPath = new File( vSDCard.getParent() + vSDCard.getName()  );
			if( vPath.exists() ){
				return true;
			}else{
				try{
				vPath.mkdirs();
				activity.callToast("創建資料夾");
				return true;
				}catch(Exception e) {
					activity.callToast("創建資料夾失敗");
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
	}


	@SuppressWarnings("resource")
	public static void read(MainActivity activity,Uri uri) throws IOException{
		SparseArray<Boolean> tempR=new SparseArray<Boolean>();
		SparseArray<Boolean> tempS=new SparseArray<Boolean>();
		SparseArray<Boolean> tempT=new SparseArray<Boolean>();
		SparseArray<Boolean> tempX=new SparseArray<Boolean>();
		FileReader fr = null;
		String stemp="a";
		boolean btemp;
		File vSDCard = Environment.getExternalStorageDirectory();
		File read=new File( vSDCard.getParent() + vSDCard.getName() +turnUriToName(uri)+".txt" );

		if(read.exists()){
			try {
				fr = new FileReader(read);
				BufferedReader br=new BufferedReader(fr);
				while(stemp!=""){
					String arrytemp="",R="",S="",T="",X="";
					int scanFlag=0;
					stemp=br.readLine();
					if(stemp=="")
						break;
					for(int i=0;i<stemp.length();i++){
						switch(scanFlag){

						case 0:
							if(stemp.subSequence(i-1, i).equals("R")){
								scanFlag=1;
								break;
							}
							arrytemp+= stemp.subSequence(i-1, i);
							break;

						case 1:
							if(stemp.subSequence(i-1, i).equals("S")){
								scanFlag=2;
								break;
							}
							R+= stemp.subSequence(i-1, i);
							break;

						case 2:
							if(stemp.subSequence(i-1, i).equals("T")){
								scanFlag=3;
								break;
							}
							S+= stemp.subSequence(i-1, i);
							break;

						case 3:
							if(stemp.subSequence(i-1, i).equals("X")){
								scanFlag=4;
								break;
							}
							T+= stemp.subSequence(i-1, i);
							break;

						case 4:
							X+= stemp.subSequence(i-1, i);
							break;
						}	
					}

					if(Reversion(R).equals("true"))
						btemp=true;
					else
						btemp=false;
					tempR.append(Integer.valueOf(Reversion(arrytemp)), btemp);


					if(Reversion(S).equals("true"))
						btemp=true;
					else
						btemp=false;
					tempS.append(Integer.valueOf(Reversion(arrytemp)), btemp);


					if(Reversion(T).equals("true"))
						btemp=true;
					else
						btemp=false;
					tempT.append(Integer.valueOf(Reversion(arrytemp)), btemp);


					if(Reversion(X).equals("true"))
						btemp=true;
					else
						btemp=false;
					tempX.append(Integer.valueOf(Reversion(arrytemp)), btemp);

				}
				EditView.BtR=tempR;
				EditView.BtS=tempS;
				EditView.BtT=tempT;
				EditView.BtX=tempX;

				activity.callToast("譜面檔讀取成功");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				activity.callToast("譜面檔讀取失敗");
			}
		}else{
			activity.callToast("找不到譜面檔");
		}
	}


	public static void write(MainActivity activity,Uri uri,SparseArray<Boolean> sR,SparseArray<Boolean> sS,SparseArray<Boolean> sT,SparseArray<Boolean> sD){
		FileWriter fw=null;
		BufferedWriter bw = null;
		File vSDCard = Environment.getExternalStorageDirectory();
		File file=new File( vSDCard.getParent() + vSDCard.getName() +turnUriToName(uri)+".txt" );
		if(file.exists()){
			new AlertDialog.Builder(activity)
			.setTitle("+++警告+++")
			.setMessage("檔案已存在，要覆寫嗎?")
			.setPositiveButton("YES", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Constant.FileDataFlag=true;
				}
			})
			.setNegativeButton("NO", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Constant.FileDataFlag=false;
				}
			})
			.show();
		}else{
			/*try {
				file.createNewFile();
				Constant.FileDataFlag=true;
				activity.callToast("建立新檔");
			} catch (IOException e) {
				e.printStackTrace();
				Constant.FileDataFlag=false;
				activity.callToast("建檔失敗");
			}*/
			Constant.FileDataFlag=true;
		}
		if(Constant.FileDataFlag){
			try{
				fw=new FileWriter( vSDCard.getParent() + vSDCard.getName() +turnUriToName(uri)+".txt" ,false);
				bw=new BufferedWriter(fw);
				String temp = null;
				for(int i=0;i<=sR.size();i++){
					temp=(i+"R"+sR.get(i)+"S"+sS.get(i)+"T"+sT.get(i)+"X"+sD.get(i));
					bw.write(temp);
					bw.newLine();
				}
				bw.close();
				activity.callToast("存檔成功");
			}catch(IOException e){
				e.printStackTrace();
				activity.callToast("存檔失敗");
			}
		}else{
			activity.callToast("取消存檔");
		}

	}


}
