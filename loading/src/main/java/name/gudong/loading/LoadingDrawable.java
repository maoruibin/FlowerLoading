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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;

import java.lang.ref.WeakReference;


/**
 * author  : gudong (gudong.name@gmail.com)
 * version : 1.0.0
 * create  : 2017/7/6 - 上午10:37.
 */
public class LoadingDrawable extends Drawable implements ILoading {
    private static final int sDefaultStep = 30;

    private static final int sDefaultDuration = 100;

    private Context mContext;

    private Handler mHandler;

    private int mAlpha;
    // rotate the picture
    private Bitmap mSource;
    // for rotate
    private Matrix mMatrix = new Matrix();
    // incremental value for each rotation angle
    private int mRotateStep = sDefaultStep;
    // each time rotate the animation
    private int mDuration = sDefaultDuration;
    // current rotate degree
    private int mCurrentDegree = 0;

    private float mScaleY = 1;
    private float mScaleX = 1;
    private int mOffset = -1;
    // drawable center position
    private int mCenterXY;
    // anim is running
    private boolean isAnimRunning = false;

    private LoadingTask mLoadingTask;

    public LoadingDrawable(Context context) {
        this(context, R.drawable.loading_drawable);
    }

    public LoadingDrawable(Context context, int resDrawable) {
        this.mContext = context;
        this.mSource = BitmapFactory.decodeResource(mContext.getResources(), resDrawable);
        if (mSource.getWidth() != mSource.getHeight()) {
            throw new IllegalStateException("drawable must have same width and height.");
        }
        mHandler = new Handler();
        mLoadingTask = new LoadingTask(this);
    }


    private static class LoadingTask implements Runnable {
        WeakReference<LoadingDrawable> mWeakDrawable;

        LoadingTask(LoadingDrawable drawable) {
            this.mWeakDrawable = new WeakReference<>(drawable);
        }

        @Override
        public void run() {
            LoadingDrawable mDrawable = mWeakDrawable.get();
            if (mDrawable == null) {
                return;
            }
            mDrawable.mCurrentDegree += mDrawable.mRotateStep;
            if (mDrawable.mCurrentDegree >= 360) {
                mDrawable.mCurrentDegree = 0;
            }
            mDrawable.doRotateLoading(mDrawable.mCurrentDegree);
            if (mDrawable.isRunning()){
                mDrawable.mHandler.postDelayed(this, mDrawable.mDuration);
            }
        }
    }

    private void doRotateLoading(int degree) {
        mMatrix.reset();
        mMatrix.postScale(mScaleX, mScaleY);
        if (mOffset >= 0) {
            mMatrix.postTranslate(mOffset, mOffset);
        }
        mMatrix.postRotate(degree, mCenterXY, mCenterXY);

        invalidateSelf();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(getBounds().left, getBounds().top);
        canvas.drawBitmap(mSource, mMatrix, null);
        canvas.restore();
    }

    @Override
    public void setAlpha(int alpha) {
        this.mAlpha = alpha;
    }

    @Override
    public int getAlpha() {
        return mAlpha;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public void start() {
        if (!isRunning()) {
            mHandler.post(mLoadingTask);
            isAnimRunning = true;
        }
    }

    @Override
    public void stop() {
        if (isRunning()) {
            mHandler.removeCallbacks(mLoadingTask);
            isAnimRunning = false;
        }
    }

    @Override
    public boolean isRunning() {
        return isAnimRunning;
    }

    @Override
    protected void onBoundsChange(Rect drawBounds) {
        super.onBoundsChange(drawBounds);
        int w = drawBounds.width();
        int h = drawBounds.height();
        onSizeChanged(w, h, mSource.getWidth(), mSource.getHeight());
    }

    /**
     * init drawable status
     *
     * @param w              View's width
     * @param h              View's height
     * @param drawableWidth  Loading Drawable's width
     * @param drawableHeight Loading Drawable's height
     */
    private void onSizeChanged(int w, int h, int drawableWidth, int drawableHeight) {
        if (w != h) {
            throw new IllegalStateException("view must have same width and height, now w = " + w
                    + " h =" + h);
        }
        mCenterXY = w / 2;

        // when view's size little than loading image size, the drawable need scale
        if (w <= drawableWidth) {
            mScaleY = h / (float) drawableWidth;
            mScaleX = w / (float) drawableHeight;
        } else {
            // if view size bigger than drawable size, drawable will not scale bigger, now i will keep
            // drawable size and make it center, but as default drawable will be left top, so now i move it to center position
            mOffset = (w - drawableWidth) / 2;
        }
        initLoadingStatus();
    }

    private void initLoadingStatus() {
        setCurrentDegree(0);
    }

    @Override
    public void setLoadingDrawable(int resDrawable) {
        mSource = BitmapFactory.decodeResource(mContext.getResources(), resDrawable);
    }

    //set Loading drawable
    public void setLoadingDrawable(Drawable drawable) {
        mSource = drawableToBitmap(drawable);
    }

    @Override
    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    @Override
    public void setRotateStep(int rotateStep) {
        this.mRotateStep = rotateStep;
    }


    @Override
    public int getIntrinsicWidth() {
        return mSource.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mSource.getHeight();
    }

    public void setCurrentDegree(int currentDegree) {
        this.mCurrentDegree = currentDegree;
        doRotateLoading(currentDegree);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap
            // will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
