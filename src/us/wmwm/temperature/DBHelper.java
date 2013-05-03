package us.wmwm.temperature;

import java.text.DateFormat;
import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		db.execSQL("create table temperature(created integer, ymd varchar(12), year integer, month integer, day, hour integer, minute integer, celsius real, fahrenheit real)");
	}

	private static DateFormat YMD = DateFormat
			.getDateInstance(DateFormat.SHORT);

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
		cv.put("fahrenheit", (celcius * 9) / 5f + 32);
		cv.put("ymd", YMD.format(cal.getTime()));
		getWritableDatabase().insert("temperature", null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public int countDays() {
		Cursor c = getReadableDatabase().rawQuery(
				"select count(distinct ymd) from temperature",null);
		try {
			if (c.moveToNext()) {
				return c.getInt(0);
			}
			return 0;
		} finally {
			c.close();
		}

	}

}
