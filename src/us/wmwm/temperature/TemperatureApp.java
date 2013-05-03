package us.wmwm.temperature;

import android.app.Application;

public class TemperatureApp extends Application {
	
	public void onCreate() {
		new DBHelper(this);
	};
	
}
