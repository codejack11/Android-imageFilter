package utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class NonSwipeableViewPager extends ViewPager {

    public NonSwipeableViewPager(Context context){
        super(context);
        setMyScroller();
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
        //never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //never allow swiping to switch between pages
        return false;
    }

    //down one is added for smooth scrolling

    private void setMyScroller(){
        try {
            Class<?> viewPager = ViewPager.class;
            Field scroller = viewPager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller{
        public MyScroller(Context context){
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration){
            super.startScroll(startX, startY, dx, dy, 350);
        }
    }
}
