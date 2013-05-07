package us.wmwm.temperature;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FragmentTemperature extends Fragment {

	WebView webview;

	View root;

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		
//	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled(true);
		webview.loadUrl("file:///android_asset/linechart.html");
		webview.addJavascriptInterface(this, "NATIVE");
	}
}
