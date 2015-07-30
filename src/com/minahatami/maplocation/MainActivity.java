package com.minahatami.maplocation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText address;
	String location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		address = (EditText) findViewById(R.id.address);
	}

	public void showMapClick(View v) {
		String location = address.getText().toString();
		location = location.replace(' ', '+');

		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
				+ location));
		if (i.resolveActivity(getPackageManager()) == null) {
			Toast.makeText(getApplicationContext(),
					"There is not any app to open maps!", Toast.LENGTH_SHORT)
					.show();
			return;
		} else if (isNetworkAvailable() == false) {
			Log.v(TAG, "isNetworkAvailable(): " + isNetworkAvailable());
			Toast.makeText(getApplicationContext(), "No network available!",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			startActivity(i);
		}
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	/*
	 * public boolean hasActiveInternetConnection() { if (isNetworkAvailable())
	 * { try { HttpURLConnection urlc = (HttpURLConnection) (new URL(
	 * "http://www.maps.google.com").openConnection());
	 * urlc.setRequestProperty("User-Agent", "Test");
	 * urlc.setRequestProperty("Connection", "close");
	 * urlc.setConnectTimeout(1500); urlc.connect(); return
	 * (urlc.getResponseCode() == 200); } catch (IOException e) { Log.e(TAG,
	 * "Error checking internet connection", e); } } else { Log.v(TAG,
	 * "No network available!"); Toast.makeText(getApplicationContext(),
	 * "No network available!", Toast.LENGTH_SHORT).show(); } return false; }
	 */
}
