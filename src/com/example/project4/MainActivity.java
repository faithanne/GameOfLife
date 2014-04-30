/*
 * Faith-Anne Kocadag
 * February, 19. 2014
 * Assignment 4
 * 
 */

package com.example.project4;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	CountDownTimer cdt;
	GameView g;

	Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
        	g.iterate();
        	g.invalidate();
            timerHandler.postDelayed(this, 500);
        }
    };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		g = (GameView) findViewById(R.id.gameView1);
		Button one = (Button) findViewById(R.id.run_1_button);
		Button twenty = (Button) findViewById(R.id.run_20_button);
		Button run = (Button) findViewById(R.id.run_button);
		
		one.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				timerHandler.removeCallbacks(timerRunnable);
				g.iterate();
				g.invalidate();
			}
		});

		twenty.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				timerHandler.removeCallbacks(timerRunnable);
				cdt.start();
			}
		});

		run.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				timerHandler.postDelayed(timerRunnable, 500);
			}
		});

		cdt = new CountDownTimer(10500, 500) {
			@Override
			public void onFinish() {
			}

			@Override
			public void onTick(long arg0) {
				g.iterate();
				g.invalidate();
			}
		};

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
