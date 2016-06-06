package com.fup.jennyferlopez.proyectokitetkiwe.fragments.nivelcuatro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Jennyfer Lopez on 09/05/2016.
 */
public class DrawingView  extends View {

    public static final int LINE = 1;
    public static final int SMOOTHLINE = 2;

    public static final float TOUCH_TOLERANCE = 4;
    public static final float TOUCH_STROKE_WIDTH = 5;

    public int mCurrentShape;

    protected Path mPath;
    protected Paint mPaint;
    protected Paint mPaintFinal;
    protected Bitmap mBitmap;
    protected Canvas mCanvas;

    float x,y, x1, y1;
    float xt1,yt1, ht1, lt1;
    float xt2,yt2, ht2, lt2;
    protected boolean isDrawing = false;

    protected float mStartX;
    protected float mStartY;

    protected float mx;
    protected float my;

    float[] imgCuatro;
    float[] imgCuatroT;
    UnirNumerosUnoActivity unirNumerosUnoActivity;
    public DrawingView(Context context) {
        super(context);
        unirNumerosUnoActivity=new UnirNumerosUnoActivity();
        init();
    }

    public DrawingView(Context context, float[] cuatro) {
        super(context);
        imgCuatro=cuatro;
        unirNumerosUnoActivity=new UnirNumerosUnoActivity();
        init();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        unirNumerosUnoActivity=new UnirNumerosUnoActivity();
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        if (isDrawing){
            switch (mCurrentShape) {
                case LINE:
                    onDrawLine(canvas);
                    break;
            }
        }
    }

    protected void init() {
        mPath = new Path();

        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(getContext().getResources().getColor(android.R.color.holo_blue_dark));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(TOUCH_STROKE_WIDTH);


        mPaintFinal = new Paint(Paint.DITHER_FLAG);
        mPaintFinal.setAntiAlias(true);
        mPaintFinal.setDither(true);
        mPaintFinal.setColor(getContext().getResources().getColor(android.R.color.holo_orange_dark));
        mPaintFinal.setStyle(Paint.Style.STROKE);
        mPaintFinal.setStrokeJoin(Paint.Join.ROUND);
        mPaintFinal.setStrokeCap(Paint.Cap.ROUND);
        mPaintFinal.setStrokeWidth(TOUCH_STROKE_WIDTH);
    }

    protected void reset() {
        mPath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mx = event.getX();
        my = event.getY();
        switch (mCurrentShape) {
            case LINE:
                onTouchEventLine(event);
                break;
            case SMOOTHLINE:
                onTouchEventSmoothLine(event);
                break;

        }
        return true;
    }

    private void onDrawLine(Canvas canvas) {

        float dx = Math.abs(mx - mStartX);
        float dy = Math.abs(my - mStartY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            canvas.drawLine(mStartX, mStartY, mx, my, mPaint);
        }
    }

    private void onTouchEventLine(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                x=mStartX;
                y=mStartY;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mCanvas.drawLine(mStartX, mStartY, mx, my, mPaintFinal);
                x1=mx;
                y1=my;
                invalidate();
                break;
        }
    }

    private void onTouchEventSmoothLine(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawing = true;
                mStartX = mx;
                mStartY = my;
                mPath.reset();
                mPath.moveTo(mx, my);
                x=mStartX;
                y=mStartY;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(mx - mStartX);
                float dy = Math.abs(my - mStartY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    mPath.quadTo(mStartX, mStartY, (mx + mStartX) / 2, (my + mStartY) / 2);
                    mStartX = mx;
                    mStartY = my;
                }
                mCanvas.drawPath(mPath, mPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isDrawing = false;
                mPath.lineTo(mStartX, mStartY);
                mCanvas.drawPath(mPath, mPaintFinal);
                mPath.reset();
                invalidate();
                x1=mStartX;
                y1=mStartY;

                imgCuatro = new float[4];
                xt1=imgCuatro[0];
                yt1=imgCuatro[1];
                ht1=imgCuatro[2];
                lt1=imgCuatro[3];

                break;
        }
    }



}
