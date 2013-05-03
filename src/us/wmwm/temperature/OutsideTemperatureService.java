package us.wmwm.temperature;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;

public class OutsideTemperatureService extends Service {
	
	LocationManager locationManager;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		PendingIntent pending = PendingIntent.getService(getApplicationContext(), 0, new Intent(this,OutsideTemperatureService.class), 0);
		locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 1800000, 3218, pending);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
