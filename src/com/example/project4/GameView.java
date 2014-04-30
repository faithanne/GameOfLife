package com.example.project4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {

	private boolean[][] cells1 = new boolean[20][20];
	private boolean[][] cells2 = new boolean[20][20];

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				cells1[i][j] = Math.random() < 0.5;
				cells2[i][j] = false;
			}
		}
	}

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		myPaint(paint, canvas);
	}

	private int neighbors(boolean[][] cells, int x, int y) {
		int x1 = (x > 0) ? x - 1 : x;
		int x2 = (x < 19) ? x + 1 : x;
		int y1 = (y > 0) ? y - 1 : y;
		int y2 = (y < 19) ? y + 1 : y;
		int count = 0;
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				count += (cells[i][j]) ? 1 : 0;
			}
		}
		if (cells[x][y])
			count--;
		return count;
	}

	public void iterate() {
		boolean[][] cells = cells1;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				cells2[i][j] = cells[i][j];
				int nb = neighbors(cells, i, j);
				if (nb == 3)
					cells2[i][j] = true;
				if (nb < 2 || nb > 3)
					cells2[i][j] = false;
			}
		}

		cells1 = cells2;
		cells2 = cells;
	}

	public void myPaint(Paint paint, Canvas canvas) {
		paint.setColor(0xff000000); // black
		int p = 0;
		int width = 50;
		for (int i = 0; i < 21; i++) {
			canvas.drawLine(0, p, 1000, p, paint); // horizontal lines
			canvas.drawLine(p, 0, p, 1000, paint); // vertical lines
			p += width;
		}

		int radius = 25;
		paint.setColor(0xffff0000); // red
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (cells1[i][j]) {
					int x = i * width + 25;
					int y = j * width + 25;
					canvas.drawCircle(x, y, radius, paint);
				}
			}
		}
	}

	public void setView() {
		Paint paint = new Paint();
		Canvas canvas = new Canvas();
		myPaint(paint, canvas);
	}
}
