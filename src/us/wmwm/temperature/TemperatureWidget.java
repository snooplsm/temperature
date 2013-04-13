package us.wmwm.temperature;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TemperatureWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("TEMP", "onUpdate");
		Intent intent = new Intent(context.getApplicationContext(),
				TemperatureService.class);
		context.startService(intent);

	}
}
