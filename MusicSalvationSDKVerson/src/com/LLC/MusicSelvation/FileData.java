package com.LLC.MusicSelvation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.SparseArray;

@SuppressLint("UseSparseArrays")
public class FileData {
	static File file;

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

	public static boolean IfData(Uri uri){
		String path=uri.toString()+".txt";
		file=new File(path);
		if(!file.exists())
			return true;
		else
			return false;
	}


	@SuppressWarnings("resource")
	public static SparseArray<Boolean> read(Uri uri,String name) throws IOException{
		SparseArray<Boolean> temp=new SparseArray<Boolean>();
		FileReader fr = null;
		String stemp="a";
		boolean btemp;

		try {
			fr = new FileReader(file);
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
				if(name.equals("R")){
					if(Reversion(R).equals("true"))
						btemp=true;
					else
						btemp=false;
					temp.append(Integer.valueOf(Reversion(arrytemp)), btemp);
				}
				if(name.equals("S")){
					if(Reversion(S).equals("true"))
						btemp=true;
					else
						btemp=false;
					temp.append(Integer.valueOf(Reversion(arrytemp)), btemp);
				}
				if(name.equals("T")){
					if(Reversion(T).equals("true"))
						btemp=true;
					else
						btemp=false;
					temp.append(Integer.valueOf(Reversion(arrytemp)), btemp);
				}
				if(name.equals("X")){
					if(Reversion(X).equals("true"))
						btemp=true;
					else
						btemp=false;
					temp.append(Integer.valueOf(Reversion(arrytemp)), btemp);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return temp;
	}


	public static void write(Uri uri,SparseArray<Boolean> sR,SparseArray<Boolean> sS,SparseArray<Boolean> sT,SparseArray<Boolean> sD){
		FileWriter fw=null;
		BufferedWriter bw = null;
		if(file.exists()){
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try{
			fw=new FileWriter(file,true);
			bw=new BufferedWriter(fw);
			String temp = null;
			for(int i=0;i<=sR.size();i++){
				temp=(i+"R"+sR.get(i)+"S"+sS.get(i)+"T"+sT.get(i)+"X"+sD.get(i));
				bw.write(temp);
				bw.newLine();
			}
			bw.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}


}
