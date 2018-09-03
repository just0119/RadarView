package com.banshou.mradarview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;

/**
 * Created by cjq on 2018/9/3.
 * Email: stoic_yb@139.com
 * features:
 */
public class RadarView extends FrameLayout {
    private int rippleColor = Color.parseColor("#9DBDFF");//水波纹的颜色
    private int radius = 0;//水波纹圆的半径
    private long anim_duration = 6000;//动画执行的时间
    private int water_ripple_count = 6;
    private int scale = 5;//动画缩放比例
    private long animDelay;//动画延迟的时间
    private Paint paint;
    private AnimatorSet animatorSet = new AnimatorSet();
    private ArrayList<Animator> animatorList = new ArrayList<Animator>();
    private ArrayList<WaterRipple> rippleViewList=new ArrayList<WaterRipple>();
    private FrameLayout.LayoutParams rippleParams;
    private boolean isAnimRunning = false;
    private Context context;
    public RadarView(Context context) {
        this(context,null);
    }
    public RadarView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }
    public RadarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initPaint();
        addChildView();
        initAnim();
    }
    /**
     * 初始化动画
     */
    private void initAnim() {
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorList.clear();
        for(int i=0;i<rippleViewList.size();i++){//几个水波纹
            final ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(rippleViewList.get(i), "ScaleX", 2f, scale);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleXAnimator.setStartDelay(i * animDelay);
            scaleXAnimator.setDuration(anim_duration);
            animatorList.add(scaleXAnimator);
            final ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(rippleViewList.get(i), "ScaleY", 2f, scale);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleYAnimator.setRepeatMode(ObjectAnimator.RESTART);
            scaleYAnimator.setStartDelay(i * animDelay);
            scaleYAnimator.setDuration(anim_duration);
            animatorList.add(scaleYAnimator);
            final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(rippleViewList.get(i), "Alpha", 0.4f, 0f);
            alphaAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            alphaAnimator.setRepeatMode(ObjectAnimator.RESTART);
            alphaAnimator.setStartDelay(i * animDelay);
            alphaAnimator.setDuration(anim_duration);
            animatorList.add(alphaAnimator);
        }
        animatorSet.playTogether(animatorList);
    }

    /**
     * 添加水波纹子view
     */
    private void addChildView() {
        radius = dip2px(context,30);
        animDelay=1200;
        rippleParams=new FrameLayout.LayoutParams((int)(2*(radius)),(int)(2*(radius)));
        rippleParams.gravity = Gravity.CENTER;
        for(int i=0;i<water_ripple_count;i++){//几个水波纹
            WaterRipple rippleView=new WaterRipple(getContext(),paint);
            addView(rippleView,rippleParams);
            rippleViewList.add(rippleView);
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 初始化画笔
     */
    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(rippleColor);
        paint.setStyle(Paint.Style.FILL);
    }
    /**
     * 开启动画
     */
    public void startRippleAnimation(){
        if(!isRunning()){
            if(rippleViewList.size() == 0){
                addChildView();
                initAnim();
            }

            for(WaterRipple wateRipple:rippleViewList){
                wateRipple.setVisibility(VISIBLE);
            }
            animatorSet.start();
            isAnimRunning=true;
        }
    }
    /**
     * 动画停止运行
     */
    public void stopAnimation(){
        if(isRunning()){
            animatorSet.cancel();
            isAnimRunning=false;
            for (int i = 0;i<rippleViewList.size();i++){
                removeView(rippleViewList.get(i));
            }
            rippleViewList.clear();
        }
    }
    /**
     * 判断是否动画在运行
     */
    public boolean isRunning(){
        return isAnimRunning;
    }

    /**
     * ui不可见时关闭动画
     */
    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if(visibility== View.INVISIBLE||visibility ==View.GONE){
            stopAnimation();
        }
    }

}
