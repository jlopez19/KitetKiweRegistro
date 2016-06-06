package com.fup.jennyferlopez.proyectokitetkiwe.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;

public class ElServicio extends Service {

	private MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		player = MediaPlayer.create(this, R.raw.himno);
		player.setLooping(true);
	}

	@Override
	public void onDestroy() {
		player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		player.start();
	}



}
