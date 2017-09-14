package com.example.myview;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author 赵欣
 * @date 2015-7-10下午2:38:50
 */
public class PointView extends View {

	private LayerDrawable mDrawables;

	public PointView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mDrawables = (LayerDrawable) getBackground();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final float x = event.getX();
		final float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Drawable drawable = findDrawable(x, y);
			if (drawable != null)
				drawable.setColorFilter(randomColor(), Mode.SRC_IN);
		}
		return super.onTouchEvent(event);
	}

	private int randomColor() {
		Random random = new Random();
		int color = Color.argb(255, random.nextInt(256), random.nextInt(256),
				random.nextInt(256));
		return color;
	}

	private Drawable findDrawable(float x, float y) {
		final int numberOfLayers = mDrawables.getNumberOfLayers();
		Drawable drawable = null;
		Bitmap bitmap = null;
		for (int i = numberOfLayers - 1; i >= 0; i--) {
			drawable = mDrawables.getDrawable(i);
			bitmap = ((BitmapDrawable) drawable).getBitmap();
			try {
				int pixel = bitmap.getPixel((int) x, (int) y);
				if (pixel == Color.TRANSPARENT) {
					continue;
				}
			} catch (Exception e) {
				continue;
			}
			return drawable;
		}
		return null;
	}

}
