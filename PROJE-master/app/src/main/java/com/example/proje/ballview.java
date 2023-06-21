package com.example.proje;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class ballview extends View {

    private static final int BALL_RADIUS = 100;

    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private Paint paint;
    private RectF ballBounds;
    private Random random;
    private int[] colors = {Color.WHITE, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN};
    private int currentColorIndex;

    public ballview(Context context) {
        super(context);
        init();
    }

    public ballview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ballview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        resetBall();
        ballBounds = new RectF();
        random = new Random();
        currentColorIndex = random.nextInt(colors.length);
    }
    private void resetBall() {
        if (getWidth() > 0 && getHeight() > 0) {
            x = random.nextInt(getWidth() - BALL_RADIUS);
            y = random.nextInt(getHeight() - BALL_RADIUS);
            xSpeed = 15;
            ySpeed = 15;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetBall();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x += xSpeed;
        y += ySpeed;
        if (x < 0 || x > getWidth() - BALL_RADIUS) {
            xSpeed *= -1;
            changeColor();
        }
        if (y < 0 || y > getHeight() - BALL_RADIUS) {
            ySpeed *= -1;
            changeColor();
        }
        ballBounds.set(x, y, x + BALL_RADIUS, y + BALL_RADIUS);
        paint.setColor(colors[currentColorIndex]);
        canvas.drawOval(ballBounds, paint);
        postInvalidateDelayed(10);
    }

    private void changeColor() {
        currentColorIndex = (currentColorIndex + 1) % colors.length;
    }
}

