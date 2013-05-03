package us.wmwm.temperature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity {

	ViewPager pager;
	
	FragmentPagerAdapter adapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				int cnt = DBHelper.getInstance().countDays();
				Log.d("COUNT", String.valueOf(cnt));
				return cnt;
			}
			@Override
			public Fragment getItem(int arg0) {
				FragmentTemperature t = new FragmentTemperature();
				return t;
			}
		});
		int count = adapter.getCount();
		if(count>0) {
			pager.setCurrentItem(adapter.getCount()-1);
		}
	}
	
}
