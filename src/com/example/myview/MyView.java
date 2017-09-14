package com.example.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * @author 赵欣
 * @date 2015-6-25下午5:23:09
 */
public class MyView extends View {

	private String name;
	private int circleWidth = 20;
	private int defalustSize = 100;
	private int width;
	private int center;
	private int radious;

	private float progress = 0;

	public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray arr = context.obtainStyledAttributes(attrs,
				R.styleable.myview2);

		name = arr.getString(R.styleable.myview2_text2);
		arr.recycle();
		Toast.makeText(context, name, Toast.LENGTH_LONG).show();
		new Thread(new Runnable() {

			@Override
			public void run() {
				float i = progress / 140;
				while (progress < 280) {
					progress += 2;
					try {
						Thread.sleep(10);
						postInvalidate();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyView(Context context) {
		this(context, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = getWidth() - circleWidth;
		center = width / 2;
		radious = width / 2 - circleWidth / 2;
		Toast.makeText(getContext(), radious + "=" + width + "=" + center,
				Toast.LENGTH_LONG).show();
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);// 设置圆圈模式
		paint.setStrokeWidth(circleWidth);// 设置圆圈的宽度
		paint.setAntiAlias(true);

		paint.setColor(Color.BLACK);
		canvas.drawCircle(center, center, radious, paint);

		paint.setColor(Color.BLUE);
		RectF rect1 = new RectF(center - radious, center - radious, center
				+ radious, center + radious);
		canvas.drawArc(rect1, 0, progress, false, paint);

		paint.setTextSize(14);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextAlign(Align.CENTER);
		FontMetrics fm = paint.getFontMetrics();
		float baseLine = center - fm.descent + (fm.descent - fm.ascent)/2;
		canvas.drawText("" + progress, center, baseLine
				, paint);
		canvas.save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureHanlder(widthMeasureSpec),
				measureHanlder(heightMeasureSpec));
	}

	public int measureHanlder(int measureSpec) {
		int result;
		int mode = MeasureSpec.getMode(measureSpec);
		int size = MeasureSpec.getSize(measureSpec);

		switch (mode) {
		case MeasureSpec.AT_MOST:
			result = Math.min(size, defalustSize);
			break;
		case MeasureSpec.EXACTLY:
			result = size;
			break;

		case MeasureSpec.UNSPECIFIED:
			result = defalustSize;
			break;
		default:
			result = size;
			break;
		}
		return result;
	}

}
