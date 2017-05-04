package com.tong.util.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;


@SuppressLint ("AppCompatCustomView")
public class BatteryView extends ImageView
{

    private static final String TAG = "BatteryView";

    /**
     * 画笔信息
     */
    private Paint mPowerPaint;

    private float mBatteryStroke = 2f;

    /**
     * 屏幕高宽
     */
    private int measureWidth;

    private int measureHeigth;

    /**
     * 电池参数
     */
    private float left;

    private float right;

    private float bottom;

    /**
     * 电池电量
     */
    private float mPower = 0f;

    private float hundred = 100f;

    private Context mContext;

    public BatteryView(Context context)
    {
        super(context);
        mContext = context;
        initView();
    }

    public BatteryView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView();
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView()
    {
        /**
         * 设置电量画笔
         */
        mPowerPaint = new Paint();
        //默认颜色是红色
        mPowerPaint.setColor(Color.RED);
        //去锯齿
        mPowerPaint.setAntiAlias(true);
        //实心填充
        mPowerPaint.setStyle(Paint.Style.FILL);
        //设置空心线宽度
        mPowerPaint.setStrokeWidth(mBatteryStroke);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        float top;

        left = Utils.dip2px(mContext, 2);

        //        top = Utils.dip2px(mContext,4);//电量：100 Max
        //       float top = Utils.dip2px(mContext,17);//电量：0
        top = Utils.dip2px(mContext, 17 - 13 * mPower / hundred);
        right = Utils.dip2px(mContext, 10);
        bottom = Utils.dip2px(mContext, 17);

        canvas.drawRect(left, top, right, bottom, mPowerPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(measureWidth, measureHeigth);
    }

    /**
     * @param power
     */
    public void setPower(Context context, float power)
    {
        if (null == mContext) {
            mContext = context;
        }
        mPower = power;
        //异常电量
        if (mPower < 0) {
            mPower = 0;
        }
        if (mPower > hundred) {
            mPower = hundred;
        }

        //当电量小于10%时，绘制红色电池
        if (mPower <= 10) {
            mPowerPaint.setColor(Color.RED);
        }
        else {
            //其他情况，绘制50%白色电池
            mPowerPaint.setColor(Color.WHITE);
        }

        invalidate();
    }
}
