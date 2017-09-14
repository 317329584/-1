package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author 赵欣
 * @date 2015-7-29下午4:32:52
 */
public class TrigonView extends View {
	private int trigonLength;// 三角形的边长
	private int color1; // 普通颜色
	private int color2;// 选中颜色
	private Paint paint;//
	private PointBean pointTop;// 三角形的顶点
	private PointBean pointLeft;// 三角形的左顶点
	private PointBean pointRight;// 三角形的右顶点
	private int PADDING = 15;// 边距
	private float progress = 0;// 当前进度
	private float I = 100f / 3;

	public TrigonView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	/**
	 * 初始化参数 void
	 */
	private void init() {
		color1 = 007700;
		color2 = 000077;
		paint = new Paint();
		paint.setAntiAlias(true);
		trigonLength = 300;
		progress = 5;
		PADDING = (int) (trigonLength * 0.05);
		int ver = (int) (-Math.sin(30) * trigonLength);

		// 计算出三角形的三个点
		pointTop = new PointBean(trigonLength / 2 + PADDING, 0 + PADDING);
		pointLeft = new PointBean(0 + PADDING, ver + PADDING);
		pointRight = new PointBean(trigonLength + PADDING, ver + PADDING);
		start((int) progress);
	}

	public TrigonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setStrokeWidth((int) (trigonLength * 0.1));
		paint.setColor(getResources().getColor(R.color.black));
		canvas.drawLine(pointTop.x, pointTop.y, pointRight.x, pointRight.y,
				paint);
		canvas.drawLine(pointTop.x, pointTop.y, pointLeft.x, pointLeft.y, paint);
		canvas.drawLine(pointLeft.x, pointLeft.y, pointRight.x, pointRight.y,
				paint);
		// paint.setColor(getResources().getColor(R.color.dd));
		canvas.drawCircle(pointLeft.x, pointLeft.y,
				(int) (trigonLength * 0.1) / 2, paint);
		canvas.drawCircle(pointTop.x, pointTop.y,
				(int) (trigonLength * 0.1) / 2, paint);
		canvas.drawCircle(pointRight.x, pointRight.y,
				(int) (trigonLength * 0.1) / 2, paint);

		int x, y;
		if (progress > 0 && progress < I) {
			x = (int) ((pointRight.x - pointLeft.x) * (progress / I));
			y = pointRight.y;
			paint.setColor(getResources().getColor(R.color.dd));
			canvas.drawLine(pointRight.x, pointRight.y, pointRight.x - x, y,
					paint);

			canvas.drawCircle(pointRight.x, pointRight.y,
					(int) (trigonLength * 0.1) / 2, paint);
		} else if (progress > 0 && progress < I * 2) {
			float j = (progress - I) / I;
			x = (int) ((pointTop.x - pointLeft.x) * j);
			y = (int) ((pointTop.y - pointLeft.y) * j);
			paint.setColor(getResources().getColor(R.color.dd));
			canvas.drawLine(pointLeft.x, pointLeft.y, pointRight.x,
					pointRight.y, paint);
			canvas.drawLine(pointLeft.x, pointLeft.y, x + pointLeft.x, y
					+ pointLeft.y, paint);

			canvas.drawCircle(pointLeft.x, pointLeft.y,
					(int) (trigonLength * 0.1) / 2, paint);
			canvas.drawCircle(pointRight.x, pointRight.y,
					(int) (trigonLength * 0.1) / 2, paint);
		} else if (progress > 0 && progress <= 100) {
			float j = (progress - I * 2) / I;
			x = (int) ((pointTop.x - pointRight.x) * j);
			y = (int) ((pointTop.y - pointRight.y) * j);
			paint.setColor(getResources().getColor(R.color.dd));
			canvas.drawLine(pointLeft.x, pointLeft.y, pointRight.x,
					pointRight.y, paint);
			canvas.drawLine(pointLeft.x, pointLeft.y, pointTop.x, pointTop.y,
					paint);
			canvas.drawLine(pointTop.x, pointTop.y, pointTop.x - x, pointTop.y
					- y, paint);

			canvas.drawCircle(pointLeft.x, pointLeft.y,
					(int) (trigonLength * 0.1) / 2, paint);
			canvas.drawCircle(pointTop.x, pointTop.y,
					(int) (trigonLength * 0.1) / 2, paint);
			canvas.drawCircle(pointRight.x, pointRight.y,
					(int) (trigonLength * 0.1) / 2, paint);
		}
	}

	public void setProgress(int i) {
		progress = i;
		if (i > 100) {
			progress = 100;
		}
		start(progress);
	}

	/**
	 * 
	 * void
	 */
	public int times = 1;

	private void start(float i) {
		final float d = i;
		try {
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					float i = (float) d / (float) times;
					for (int j = 0; j < times; j++) {
						if (j == times - 1) {
							progress = d;
						} else {
							progress = (float) (i * j);
						}
						postInvalidate();
						try {
							Thread.sleep((1200 / times));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			th.start();
		} catch (Exception e) {
		}
	}
}
