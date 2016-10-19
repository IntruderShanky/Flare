# Flare
Flare provide the easy implementation of circular indicator with ViewPager. (ViewPager Indicator)

[![](https://jitpack.io/v/IntruderShanky/Flare.svg)](https://jitpack.io/#IntruderShanky/Flare)
# Preview
![Screenshot](flare.gif)

# Usage
Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```
Step 2. Add the dependency
```groovy
dependencies {
      compile 'com.github.IntruderShanky:Flare:1.0.2'
}
 ```
# Implementation
###XML Implementation:
```xml
  <com.intrusoft.indicator.Flare
        android:id="@+id/indicator"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        flare:indicator_color="@color/indicator_color"
        flare:indicator_gap="20dp" />
```
###Attributes
####Indicator Color
```xml
 flare:indicator_color="@color/indicator_color"
```
####Indicator Gap
Distance between the centers of two indicators
```xml
flare:indicator_gap="20dp"
```


###Java Implementation:
```java
Flare indicator = (Flare) findViewById(R.id.indicator);
ViewPager pager = (ViewPager) findViewById(R.id.pager);

//setting Adapter
pager.setAdapter(new PagerAdapter() /* Your PagerAdapter */);

//setting PageChangeListener
pager.addOnPageChangeListener(this /* OnPageChangeListener */);

//setting Flare
indicator.setUpWithViewPager(pager);
indicator.addOnPageChangeListener(this /* OnPageChangeListener */);

//Customize Flare
indicator.setIndicatorColor(Color.BLUE);
indicator.setIndicatorGap(20);
```
#Licence
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
