package com.chyang.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by caihuiyang on 16/8/1.
 */
public class ShrinkScrollerView extends ViewGroup {


    public static final int SCREEN_OPEN = 0;
    public static final int SCREEN_DOWN_EXIT = 1;
    private static final int SCREEN_INVALID = -1;

    private VelocityTracker mVelocityTracker;

    private int mTouchSlop;

    private int mCurrentScreen = SCREEN_OPEN;
    private Scroller mScroller;

    public ShrinkScrollerView(Context context) {
        this(context, null, 0);
    }

    public ShrinkScrollerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShrinkScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if(childCount > 1) throw new IllegalStateException("The childCount of SpringView must be 1");
        ViewGroup childView = (ViewGroup) getChildAt(0);
        childView.layout(l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup childView = (ViewGroup) getChildAt(0);
        childView.measure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        System.out.println(y);
        super.scrollTo(x, y);
        postInvalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.translate(0, getScrollY());
    }


    public void  snapToScreen(int whichScreen) {
        int newY = getChildAt(0).getHeight();
        if(whichScreen == SCREEN_OPEN && mCurrentScreen == SCREEN_DOWN_EXIT) {
            //  newY = newY - getScrollY();
            //   System.out.println(getScrollY()+"----==="+newY);
            System.out.println(getScrollY()+"=--"+newY);
            mScroller.startScroll(0, getScrollY(),  0 ,Math.abs(getScrollY()),2000);
            mCurrentScreen = SCREEN_OPEN;
        } else if(whichScreen == SCREEN_DOWN_EXIT && mCurrentScreen == SCREEN_OPEN) {
            newY = (- newY) - getScrollY();
            //System.out.println(getScrollY()+"======"+newY);;
            mScroller.startScroll(0,getScrollY(), 0, newY, 2000);
            mCurrentScreen = SCREEN_DOWN_EXIT;
        }
        invalidate();
    }
}
