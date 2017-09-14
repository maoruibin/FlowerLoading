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

package name.gudong.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import name.gudong.loading.LoadingDrawable;
import name.gudong.loading.LoadingView;

public class MainActivity extends AppCompatActivity {
    private LoadingView lvLoading;
    private TextView tvLoading;
    private LoadingView mTitleLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvLoading = (LoadingView) findViewById(R.id.lv_loading);
        tvLoading = (TextView) findViewById(R.id.tv_loading);

        //init loading drawable
        LoadingDrawable loadingDrawable = new LoadingDrawable(this);
        loadingDrawable.setLoadingDrawable(R.drawable.loading_progress_grey);
        loadingDrawable.setDivideCount(30);
        loadingDrawable.setDuration(500);
        //set loading drawable
        tvLoading.setBackground(loadingDrawable);
        // start loading anim
        loadingDrawable.start();

        setUpTitleLoading();
    }

    private void setUpTitleLoading(){
        mTitleLoadingView = new LoadingView(this);
        mTitleLoadingView.setIsAutoPlayAnim(false);
        mTitleLoadingView.setLoadingDrawable(R.drawable.icon_refresh_white);
        ActionBar.LayoutParams lp =new ActionBar.LayoutParams(80, 80, Gravity.RIGHT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.setCustomView(mTitleLoadingView,lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = 0;
        int max = 800;
        mTitleLoadingView.setMax(800);
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                mTitleLoadingView.stop();
                break;
            case MotionEvent.ACTION_MOVE:
                float offset = ev.getY() - y;
                Log.d("=====", "dispatchTouchEvent: "+offset);
                mTitleLoadingView.setProgress((int) offset);
                break;
            case MotionEvent.ACTION_UP:
                mTitleLoadingView.start();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
