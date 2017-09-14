package com.example.myview;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.app.Activity;

public class MainActivity extends Activity {

	private String[] str = new String[] { "aasdf", "aaadvs", "adf", "aaaadfs" };
	private TrigonView trigon;
	private int i = 0;
	private RoundProgressBar roundProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		trigon = (TrigonView) findViewById(R.id.progress);
		trigon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				trigon.setProgress(i += 10);
			}
		});
		// AutoCompleteTextView autoText = (AutoCompleteTextView)
		// findViewById(R.id.autotext);
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_dropdown_item_1line, str);
		// autoText.setAdapter(adapter);

		roundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
		handd.sendEmptyMessageDelayed(1, 20);
	}

	Handler handd = new Handler() {
		public void handleMessage(android.os.Message msg) {
			roundProgressBar.setProgress(msg.what);
			int a = msg.what + 1;
			trigon.setProgress(a);
			if(a>  100){
				 a = 1;
			}
			handd.sendEmptyMessageDelayed(a, 20);

		};
	};
}
