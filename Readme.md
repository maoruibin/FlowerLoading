# FlowerLoading
Android loading or progress view, just like iOS loading view.

![demo](http://wx1.sinaimg.cn/mw690/6fb50cedly1fiiiyp7vvfj20k00zkt9s.jpg)

## Usage
use loading view directly
```java
    <name.gudong.loading.LoadingView
        android:id="@+id/lv_loading"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

use loading drawable

```java
        tvLoading = (TextView) findViewById(R.id.tv_loading);
        //init loading drawable
        LoadingDrawable loadingDrawable = new LoadingDrawable(this);
        //set loading drawable
        tvLoading.setBackground(loadingDrawable);
        // start loading anim
        loadingDrawable.start();
```

## Author
[http://gudong.name](http://gudong.name)

[https://github.com/maoruibin](https://github.com/maoruibin)

[http://weibo.com/maoruibin](http://weibo.com/maoruibin)

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



