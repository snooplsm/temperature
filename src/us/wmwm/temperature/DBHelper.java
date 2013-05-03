package us.wmwm.temperature;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	
	private static DBHelper instance;
	
	public DBHelper(Context context) {
		super(context, "database.db", null, VERSION);
		instance = this;
	}
	
	public static DBHelper getInstance() {
		return instance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table temperature(created integer, year integer, month integer, day, hour integer, minute integer, celsius real, fahrenheit real)");
	}
	
	public void saveTemperature(float celcius) {
		ContentValues cv = new ContentValues();
		cv.put("created", System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cv.put("year", cal.get(Calendar.YEAR));
		cv.put("month", cal.get(Calendar.MONTH));
		cv.put("day", cal.get(Calendar.DAY_OF_MONTH));
		cv.put("hour", cal.get(Calendar.HOUR));
		cv.put("minute", cal.get(Calendar.MINUTE));
		cv.put("celsius", celcius);
		cv.put("fahrenheit", (celcius*9)/5f+32);
		getWritableDatabase().insert("temperature", null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
