package com.example.ls.shoppingmall.utils.layoututils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
/**
 * Created by chong on 2017/12/29.
 */

public class RingView extends View{

    //默认的线宽
    private static final float  DEFAULT_LINE_WIDHT = auto(5);
    //默认的圆环色
    private static final int  DEFAULT_RING_COLOR = Color.parseColor("#ffe2e2e2");
    //默认的圆环填充色
    private static final int  DEFAULT_RING_SOLID_COLOR = Color.parseColor("#ffff0000");
    //默认的Text
    private static final String  DEFAULT_TEXT = "完成度: 0%";
    //默认的文字颜色
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#ffff0000");
    //默认的文字大小
    private static final int DEFAULT_TEXT_SIZE = auto(8);


    private float mLineWidht = DEFAULT_LINE_WIDHT;
    private int mRingSolidColor = DEFAULT_RING_SOLID_COLOR;
    private int mRingColor = DEFAULT_RING_COLOR;
    private String mText = DEFAULT_TEXT;
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private int mTextSize = DEFAULT_TEXT_SIZE;
    private Paint mPaint;
    private float mProgress;
    private int mWidth, mHeight;
    private int mCentreX, mCentreY;


    public RingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCentreX = mWidth / 2;
        mCentreY = mHeight / 2;
    }


    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(mLineWidht);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawText(canvas);

        drawRing(canvas);
    }

    private void drawText(Canvas canvas) {
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);

        Rect rect = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), rect);
        canvas.drawText(mText, mCentreX - rect.width() / 2, mCentreY + rect.height()/ 2, mPaint);
    }

    private void drawRing(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(mLineWidht / 2, mLineWidht / 2 , mWidth - mLineWidht / 2, mHeight - mLineWidht / 2);

        mPaint.setColor(mRingColor);
        canvas.drawArc(rectF, 0, 360, false, mPaint);


        mPaint.setShader(new SweepGradient(mCentreX, mCentreY, new int[]{Color.YELLOW, Color.RED}, null));
        canvas.drawArc(rectF, 120, 120, false, mPaint);
        mPaint.setShader(null);
    }


    public void setProgress(float progress) {
        mProgress = progress < 0 ? 0 : progress > 1 ? 1 : progress;
        mText = "完成度: " + ((int) (mProgress * 100)) + "%";
        invalidate();
    }

    private static int auto(float v){
        return (int) (v * SPUtils.getScreenWide() / CodeVal.APP_WIDE);
    }
}
