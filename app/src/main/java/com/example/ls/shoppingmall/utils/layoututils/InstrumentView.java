package com.example.ls.shoppingmall.utils.layoututils;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
public class InstrumentView extends View {
    private String color_outcircle = "#DEDEDE";
    private String color_progress = "#FFFF5A5F";
    private String color_indicator_left = "#E1DCD6";
    private String color_indicator_right = "#979696";
    private String color_changer="#f1277d";
    private SweepGradient sweepGradient;
    /**
     * 当前进度
     */
    private float progress = 50;
    /**
     * 要画的内容的实际宽度
     */
    private int contentWidth;
    /**
     * view的实际宽度
     */
    private int viewWidth;
    /**
     * view的实际高度
     */
    private int viewHeight;
    /**
     * 外环线的宽度
     */
    private int outCircleWidth = 1;
    /**
     * 外环的半径
     */
    private int outCircleRadius = 0;
    /**
     * 内环的半径
     */
    private int inCircleRedius = 0;
    /**
     * 内环与外环的距离
     */
    private int outAndInDistance = 0;
    /**
     * 内环的宽度
     */
    private int inCircleWidth = 0;
    /**
     * 刻度盘距离它外面的圆的距离
     */
    private int dialOutCircleDistance = 0;
    /**
     * 内容中心的坐标
     */
    private int[] centerPoint = new int[2];
    /**
     * 长线的长度
     */
    private int dialLongLength = 0;
    /**
     * 刻度线距离圆心最远的距离
     */
    private int dialRadius = 0;


    /**
     * 圆弧开始的角度
     */
    private int startAngle = 0;
    /**
     * 圆弧划过的角度
     */
    private int allAngle = 0;

    private Paint mPaint;
    private int[] colors = { 0xFFFF6183,0xFFFFE484};
    private BlurMaskFilter mBlur;
    // 选择要应用的反射等级
    private float specular = 6;
    // 向 mask应用一定级别的模糊
    private float blur = 3.5f;
    // 设置光源的方向
    private float[] direction = new float[] {1, 1, 1};

    // 设置环境光亮度
    private float light = 0.4f;
    private EmbossMaskFilter emboss;

    public InstrumentView(Context context) {
        this(context, null);
    }

    public InstrumentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InstrumentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initValues();
    }

    /**
     * 初始化尺寸
     */
    private void initValues() {
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
        contentWidth = viewWidth > viewHeight ? viewHeight : viewWidth;
        outCircleRadius = contentWidth / 2 - outCircleWidth;
        outAndInDistance = (int) (contentWidth / 26.5);
        inCircleWidth = (int) (contentWidth / 18.7);
        centerPoint[0] = viewWidth / 2;
        centerPoint[1] = viewHeight / 2;
        inCircleRedius = outCircleRadius - outAndInDistance - inCircleWidth / 2;
        startAngle = 150;
        allAngle = 240;
        dialOutCircleDistance = inCircleWidth;
        dialLongLength = (int) (dialOutCircleDistance / 1.2);
        dialRadius = inCircleRedius - dialOutCircleDistance;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBlur = new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL);
        drawStatic(canvas);
        drawDynamic(canvas);

    }

    /**
     * 绘制静态的部分
     *
     * @param canvas
     */
    private void drawStatic(Canvas canvas) {
        drawCircleWithRound(startAngle, allAngle, inCircleWidth, inCircleRedius, color_outcircle, canvas);
    }

    /**
     * 绘制动态的部分
     *
     * @param canvas
     */
    private void drawDynamic(Canvas canvas) {
        drawProgress(progress, canvas);
        drawIndicator(progress, canvas);
    }

    /**
     * 画指针以及他的背景
     *
     * @param progress
     * @param canvas
     */
    private void drawIndicator(float progress, Canvas canvas) {
        drawPointer(canvas);
    }

    /**
     * 指针的最远处的半径和刻度线的一样
     */
    private void drawPointer(Canvas canvas) {
        RectF rectF = new RectF(centerPoint[0] - (int) (outCircleRadius / 3f / 2 / 2),
                centerPoint[1] - (int) (outCircleRadius / 3f / 2 / 2), centerPoint[0] + (int) (outCircleRadius / 3f / 2 / 2), centerPoint[1] + (int) (outCircleRadius / 3f / 2 / 2));
        int angle = (int) ((allAngle) / (100 * 1f) * progress) + startAngle;
        //指针的定点坐标
        int[] peakPoint = getPointFromAngleAndRadius(angle, dialRadius);
        //顶点朝上，左侧的底部点的坐标
        int[] bottomLeft = getPointFromAngleAndRadius(angle - 90, (int) (outCircleRadius / 3f / 2 / 2));
        //顶点朝上，右侧的底部点的坐标
        int[] bottomRight = getPointFromAngleAndRadius(angle + 90, (int) (outCircleRadius / 3f / 2 / 2));
        Path path = new Path();
        mPaint.setShader(null);
        mPaint.setColor(Color.parseColor(color_indicator_left));
        path.moveTo(centerPoint[0], centerPoint[1]);
        path.lineTo(peakPoint[0], peakPoint[1]);
        path.lineTo(bottomLeft[0], bottomLeft[1]);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawArc(rectF, angle - 180, 100, true, mPaint);
        Log.e("InstrumentView", "drawPointer" + angle);


        mPaint.setColor(Color.parseColor(color_indicator_right));
        path.reset();
        path.moveTo(centerPoint[0], centerPoint[1]);
        path.lineTo(peakPoint[0], peakPoint[1]);
        path.lineTo(bottomRight[0], bottomRight[1]);
        path.close();
        canvas.drawPath(path, mPaint);

        canvas.drawArc(rectF, angle + 80, 100, true, mPaint);


    }
    /**
     * 根据进度画进度条
     *
     * @param progress 最大进度为100.最小为0
     */
    private void drawProgress(float progress, Canvas canvas) {
        sweepGradientInit();
        float ratio = progress / 100f;
        int angle = (int) (allAngle * ratio);
            drawCircleWithRound(startAngle, angle, inCircleWidth, inCircleRedius, color_progress, canvas);

    }
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
    /**
     * 渐变初始化
     */

    public void sweepGradientInit() {
        //渐变颜色
        //旋转 不然是从0度开始渐变
        Matrix matrix = new Matrix();
        matrix.setRotate(-90,inCircleRedius, inCircleRedius);
        sweepGradient = new SweepGradient(inCircleRedius, inCircleRedius, colors, null);

        sweepGradient.setLocalMatrix(matrix);
    }
    /**
     * 画一个两端为圆弧的圆形曲线
     *
     * @param startAngle 曲线开始的角度
     * @param allAngle   曲线走过的角度
     * @param radius     曲线的半径
     * @param width      曲线的厚度
     */
    private void drawCircleWithRound(int startAngle, int allAngle, int width, int radius, String color, Canvas canvas) {
        emboss = new EmbossMaskFilter(direction, light, specular, blur);
        mPaint.setStrokeWidth(width);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor(color));
        mPaint.setShader(null);
      //  mPaint.setShader(sweepGradient);
       // mPaint.setMaskFilter(mBlur);
        mPaint.setMaskFilter(emboss);
        RectF rectF = new RectF(centerPoint[0] - radius, centerPoint[1] - radius, centerPoint[0] + radius, centerPoint[1] + radius);
        canvas.drawArc(rectF, startAngle, allAngle, false, mPaint);
        drawArcRoune(radius, startAngle, width, canvas);
        drawArcRoune(radius, startAngle + allAngle, width, canvas);
    }

    /**
     * 绘制圆弧两端的圆
     *
     * @param radius 圆弧的半径
     * @param angle  所处于圆弧的多少度的位置
     * @param width  圆弧的宽度
     */
    private void drawArcRoune(int radius, int angle, int width, Canvas canvas) {
        int[] point = getPointFromAngleAndRadius(angle, radius);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(point[0], point[1], width / 2, mPaint);
    }

    /**
     * 根据角度和半径，求一个点的坐标
     *
     * @param angle
     * @param radius
     * @return
     */
    private int[] getPointFromAngleAndRadius(int angle, int radius) {
        double x = radius * Math.cos(angle * Math.PI / 180) + centerPoint[0];
        double y = radius * Math.sin(angle * Math.PI / 180) + centerPoint[1];
        return new int[]{(int) x, (int) y};
    }

}
