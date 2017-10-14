# FlowerLoading [ ![Download](https://api.bintray.com/packages/gudong/maven/loading/images/download.svg) ](https://bintray.com/gudong/maven/loading/_latestVersion)   ![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)  [ ![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

一个常见的转菊花动画效果 | [English](/doc/Readme-en.md)

![demo](http://7xr9gx.com1.z0.glb.clouddn.com/flower_real_right.gif)

## 原理

对一张原始的菊花图片不停的做固定角度的旋转，从而产生视觉上的的转菊花动画效果。

## 使用

```gradle
    compile "name.gudong:loading:1.1.1"
```

通过布局引入 LoadingView，如下所示

```xml
    <name.gudong.loading.LoadingView
        android:id="@+id/lv_loading"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

FlowerLoading 默认提供一个灰色的菊花资源，转一圈默认时长 1200ms.

为了提供更加独立的绘制特性，FlowerLoading 还提供了对应 LoadingDrawable, 以便在其他 View 上通过background 设置转菊花效果，如 [ActionButton](https://github.com/maoruibin/ActionButton) 这个项目，通过引入该库，灵活的实现了加载效果。


LoadingDrawable 使用方法如下所示：

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

## 方法介绍

### 共有方法
LoadingView 跟 LoadingDrawable 的大多数方法一致，只是 LoadingView 会有少数几个特殊的方法。

方法名 | 介绍
---- | ---
setLoadingDrawable | 设置旋转图片资源 
setDuration |  设置旋转一圈的时长
setDivideCount |  设置一次动画被切割成多少份
start | 播放动画 
stop | 停止动画 
isRunning | 动画是否在执行 

### LoadingView 特有方法

方法名 | 介绍
---- | ---
enableAutoPlayAnim | 设置是否启用自动播放动画的功能 
setMax | 设置旋转最大进度 
setProgress | 设置当前旋转进度 

enableAutoPlayAnim 介绍
> 默认只要 LoadingView 处于显示状态，动画就会执行，隐藏则动画自动停止。如果不想使用该特性，可以通过 enableAutoPlayAnim 方法进行关闭。


更多细节，请查看[源码](./loading/src/main/java/name/gudong/loading/LoadingDrawable.java)。

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



