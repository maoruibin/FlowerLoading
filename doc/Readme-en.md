# FlowerLoading [ ![Download](https://api.bintray.com/packages/gudong/maven/loading/images/download.svg) ](https://bintray.com/gudong/maven/loading/_latestVersion)   ![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)  [ ![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

A common flower animation effect | [中文](../Readme.md)

![demo](http://7xr9gx.com1.z0.glb.clouddn.com/flower_real_right.gif)

## Theory

On a picture of the original flower kept a fixed angle of rotation, resulting in the visual effect of the flower animation.

## Usage

```gradle
    compile "name.gudong:loading:1.1.0"
```

Introduce the LoadingView through the layout, as shown below:

```xml
    <name.gudong.loading.LoadingView
        android:id="@+id/lv_loading"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

FlowerLoading acquiescence to provide a gray flower resources, turn the default length of 1200ms.

In order to provide more independent rendering features, FlowerLoading also provides the corresponding LoadingDrawable, in order to set the other View through the background to turn the flower effect, just like [ActionButton](https://github.com/maoruibin/ActionButton) this project，through the introduction of the library, the flexibility to achieve the loading effect.


usage for LoadingDrawable ：

```java
    //init target view
    tvLoading = (TextView) findViewById(R.id.tv_loading);
    
    //init loading drawable
    LoadingDrawable loadingDrawable = new LoadingDrawable(this);
    
    //set loading drawable as view's background 
    tvLoading.setBackground(loadingDrawable);
    
    // start loading anim
    loadingDrawable.start();
```

## Method

### common method

method | introduce
---- | ---
setLoadingDrawable | set res image for origin drawable 
setDuration |  set duration time 
setDivideCount |  Set how many times an animation is cut
start | start anim 
stop | stop anim 
isRunning | anim is running or not 

### LoadingView 

method | introduce
---- | ---
setIsAutoPlayAnim | Set whether to enable auto-play animation 
setMax | set max progress 
setProgress | set current progress 

setIsAutoPlayAnim introduce
> By default, if the LoadingView is in the display state, the animation will be executed and the animation will automatically stop. If you do not want to use this feature, you can use the setIsAutoPlayAnim method to close.


For more usage info, feel free to watch [source code](./loading/src/main/java/name/gudong/loading/LoadingDrawable.java)。

## Author

- blog&nbsp;&nbsp;&nbsp;&nbsp;[http://gudong.name](http://gudong.name)

- github [https://github.com/maoruibin](https://github.com/maoruibin)

- weibo&nbsp;&nbsp;[http://weibo.com/maoruibin](http://weibo.com/maoruibin)

## License

    Copyright 2017 GuDong

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



