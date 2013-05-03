package us.wmwm.temperature;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class TemperatureService extends Service implements SensorEventListener {

	SensorManager manager;

	List<Float> lastFive = new ArrayList<Float>(10);

	DecimalFormat DF = new DecimalFormat("##.#");

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("TEMP", "onCreate");
		manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i("TEMP", "onstartcommand");
		List<Sensor> sensors = manager
				.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);

		if (!sensors.isEmpty()) {
			manager.registerListener(this, sensors.get(0),
					SensorManager.SENSOR_DELAY_NORMAL);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		Log.i("TEMP", "onsensorchanged");
		lastFive.add(event.values[0]);
		if (lastFive.size() == 5) {
			manager.unregisterListener(this);
			float tmp = 0;
			for (float f : lastFive) {
				tmp += f;
			}
			tmp = tmp / (float) lastFive.size();
			DBHelper.getInstance().saveTemperature(tmp);
			tmp = (tmp*9)/5f+32;
			lastFive.clear();
			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(this.getApplicationContext());

			ComponentName thisWidget = new ComponentName(
					getApplicationContext(), TemperatureWidget.class);
			int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);
			String temp = DF.format(tmp) + "°";
			for (int widgetId : allWidgetIds2) {
				RemoteViews remoteViews = new RemoteViews(this
						.getApplicationContext().getPackageName(),
						R.layout.widget_temperature);
				remoteViews.setTextViewText(R.id.text, temp);
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
			}
			stopSelf();
		}
	}

}
