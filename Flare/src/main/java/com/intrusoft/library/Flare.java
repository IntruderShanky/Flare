package com.intrusoft.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * {@link Flare} is used to provide the circular indicator for the viewPager
 */
public class Flare extends View implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private float radiusUnselected;
    private float radiusSelected;
    private float margin;
    private float gap = 0;
    private float y;
    private Paint paint;
    private int n = 0;
    private int currentPosition = 0;
    private int indicatorColor;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public Flare(Context context) {
        super(context);
        init(context, null);
    }

    public Flare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Flare(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null)
            try {
                TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Flare);
                indicatorColor = array.getColor(R.styleable.Flare_indicator_color, Color.WHITE);
                gap = array.getDimensionPixelSize(R.styleable.Flare_indicator_gap, 0);
                array.recycle();
            } catch (Exception e) {
                e.printStackTrace();
            }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(indicatorColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (pager != null) {
            float width = getMeasuredWidth();
            float height = getMeasuredHeight();
            n = pager.getAdapter().getCount();
            y = height / 2;
            if (width - (height * n) > 0) {
                if (gap == 0)
                    gap = height;
                radiusSelected = height * 35 / 200;
                radiusUnselected = height * 25 / 200;
            } else {
                if (gap == 0)
                    gap = width / (n + 2);
                radiusSelected = width / (n + 2) * 35 / 200;
                radiusUnselected = width / (n + 2) * 25 / 200;
            }
            margin = (width - (gap * n)) / 2;
        }
    }

    @Override
    public void draw(final Canvas canvas) {
        super.draw(canvas);
        if (pager != null) {
            float x = margin + (gap / 2);
            for (int i = 0; i < n; i++, x += gap) {
                canvas.drawCircle(x, y, radiusUnselected, paint);
            }
            x = (int) ((margin + (gap / 2)) + (currentPosition * gap));
            canvas.drawCircle(x, y, radiusSelected, paint);
        }
    }

    /**
     * Set up {@link Flare} with the viewpager
     * @param pager
     */
    public void setUpWithViewPager(ViewPager pager) {
        this.pager = pager;
        pager.addOnPageChangeListener(this);
    }


    /**
     * Set your own onPageChangeListener
     * @param onPageChangeListener
     */
    public void addOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (onPageChangeListener != null)
            onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);

    }

    @Override
    public void onPageSelected(int position) {
        if (onPageChangeListener != null)
            onPageChangeListener.onPageSelected(position);
        currentPosition = position;
        invalidate();

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (onPageChangeListener != null)
            onPageChangeListener.onPageScrollStateChanged(state);
    }

    /**
     * set The color of {@link Flare} Indicator
     * @param indicatorColor
     */
    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        invalidate();
    }

    /**
     * set the distance between the indicators
     * @param gap is the distance between the centers of indicators
     */
    public void setIndicatorGap(float gap) {
        this.gap = gap;
        invalidate();
    }
}
