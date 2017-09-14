/*
 *     Copyright 2017 GuDong
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *
 *
 */

package name.gudong.loading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * author  : gudong (gudong.name@gmail.com)
 * version : 1.0.0
 * create  : 2017/7/26 - 上午10:37.
 */
public class LoadingView extends ImageView implements ILoading {
    private static final int default_max = 100;
    private int max = default_max;

    /**
     * 是不是根据 View 显示隐藏状态自动开启停止动画
     */
    private boolean isAutoAnim = true;

    private LoadingDrawable mDrawable;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mDrawable = new LoadingDrawable(context);
        //when set src attr
        if (getDrawable() != null) {
            mDrawable.setLoadingDrawable(getDrawable());
        }
        setImageDrawable(mDrawable);
    }

    /**
     * set is auto play anim flag
     * @param flag if true, anim will depend on View's visible status, visible anim will play else will be stop, if false,
     *             anim status is control by user, use start() or stop
     */
    public void setIsAutoPlayAnim(boolean flag) {
        this.isAutoAnim = flag;
    }

    public boolean isAutoAnim() {
        return isAutoAnim;
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (!isAutoAnim()) {
            return;
        }
        if(visibility == View.VISIBLE){
            start();
        }else{
            stop();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public void setDuration(int duration) {
        mDrawable.setDuration(duration);
    }

    @Override
    public void setDivideCount(int divideCount) {
        mDrawable.setDivideCount(divideCount);
    }

    @Override
    public void setLoadingDrawable(int resDrawable) {
        mDrawable.setLoadingDrawable(resDrawable);
    }

    @Override
    public void start() {
        mDrawable.start();
    }

    @Override
    public void stop() {
        mDrawable.stop();
    }

    @Override
    public boolean isRunning() {
        return mDrawable.isRunning();
    }

    public void setMax(int max) {
        if(max <= 0){
            throw new IllegalArgumentException("max value must bigger than 0");
        }
        this.max = max;
    }

    public void setProgress(int progress) {
        if(isAutoAnim){
            throw new IllegalStateException("please make auto anim flag as false, use setIsAutoPlayAnim method");
        }
        if(max != 0){
            int degree = (int) (360 * (progress/(float)max));
            mDrawable.setCurrentDegree(degree);
        }
    }
}
