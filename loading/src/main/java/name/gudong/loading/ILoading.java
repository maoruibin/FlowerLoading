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

import android.graphics.drawable.Animatable;

/**
 * author  : gudong (gudong.name@gmail.com)
 * version : 1.0.0
 * create  : 2017/7/6.
 */
interface ILoading extends Animatable {
    /**
     * set duration time for anim
     * @param duration time
     */
    void setDuration(int duration);

    /**
     * set rotate the incremental angle
     * @param rotateStep
     */
    void setRotateStep(int rotateStep);

    void setLoadingDrawable(int resDrawable);

}
