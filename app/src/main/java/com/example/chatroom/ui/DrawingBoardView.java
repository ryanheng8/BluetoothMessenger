package com.example.chatroom.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.chatroom.R;

/*
 * Drawing functionalities using a bitmap.
 * NOTE: Many of these functions are following Google's finger paint tutorial linked below:
 * https://android.googlesource.com/platform/development/+/master/samples/ApiDemos/src/com/example/android/apis/graphics/FingerPaint.java
 */
public class DrawingBoardView extends View {

    // Creates the canvas, path, bitmap. and paints.

    public Canvas mCanvas;
    private final Path mPath;
    public Bitmap mBitmap;
    private final Paint mBitmapPaint;
    private final Paint mPaint;

    // Initializes the drawing coordinates and tolerance.
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    /*
     * Initializes the background and paint tools.
     */
    public DrawingBoardView(Context context, AttributeSet attributes) {
        super(context, attributes);
        this.setBackgroundColor(ContextCompat.getColor(this.getContext(), R.color.white));

        mBitmapPaint = new Paint();
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    /*
     * Creates the bitmap according to the size.
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    /*
     * Opens a saved bitmap.
     */
    public void setmBitmap(Bitmap bitmap) {
        mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        mCanvas = new Canvas(mBitmap);
        invalidate();
    }

    /*
     * Changes the paint color.
     */
    public void setColorChange(int color) {
        mPaint.setColor(color);
    }

    public void thicknessChanged(float thickness) {
        mPaint.setStrokeWidth(thickness);
    }

    // Default functionality for clicks.
    @Override
    public boolean performClick() {
        return super.performClick();
    }


    /*
     * Sets up the path based on the x and y coordinates.
     */
    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    /*
     * Draws on the path using a quadratic curve if the tolerance is met.
     */
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    /*
     * Draws on the canvas according to the motion event.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Get coordinates and the event
        float xCoord = event.getX();
        float yCoord = event.getY();
        int motion = event.getAction();

        switch(motion) {
            // User has pressed down on the screen
            case MotionEvent.ACTION_DOWN:
                touch_start(xCoord, yCoord);
                invalidate();
                break;
            // User is moving their finger on the screen
            case MotionEvent.ACTION_MOVE:
                touch_move(xCoord, yCoord);
                invalidate();
                break;
            // User lifts their finger
            case MotionEvent.ACTION_UP:
                mPath.lineTo(mX, mY);
                // Draw the path
                mCanvas.drawPath(mPath, mPaint);
                mPath.reset();
                invalidate();
                break;
            // Do nothing
            default:
                break;
        }
        return true;
    }

    /*
     * Draws to the bitmap
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, mPaint);
    }

    /*
     * Retrieves the bitmap to return.
     */
    public Bitmap getBitmap()
    {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);

        return bitmap;
    }
}
