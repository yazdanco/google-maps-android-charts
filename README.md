# Google Maps Android 3D Pie Chart - Bar Chart Marker Clustering Java.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Google%20Maps%20Android%203D%20Pie%20Chart-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5748)
[![Build Status](https://travis-ci.org/aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java.svg?branch=master)](https://travis-ci.org/aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![](https://jitpack.io/v/aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java.svg)](https://jitpack.io/#aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java)
[![codebeat badge](https://codebeat.co/badges/96e44347-d1d9-41d1-9a3b-8651a810895f)](https://codebeat.co/projects/github-com-aminyazdanpanah-google-maps-3d-pie-chart-marker-clustering-java-master)
[![Known Vulnerabilities](https://snyk.io/test/github/aminyazdanpanah/google-maps-3d-pie-chart-marker-clustering-java/badge.svg)](https://snyk.io/test/github/aminyazdanpanah/google-maps-3d-pie-chart-marker-clustering-java)
[![BCH compliance](https://bettercodehub.com/edge/badge/aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java?branch=master)](https://bettercodehub.com/)

This open-source library for marker clustering that has a beautiful 3D Pie chart and also bar chart for clustering which can useful for statistical regions.


- **Display 3D pie chart for clustering**
- **Display Bar chart for clustering**

![Demo](/doc/demo.gif?raw=true "Demo")


### Usage

First add jitpack to your projects build.gradle file

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
   	}
}
```

Then add the dependency in modules build.gradle file

```gradle
dependencies {
    compile 'com.github.aminyazdanpanah:google-maps-3D-pie-chart-marker-clustering-java:v1.2'
}
```

## Installation Demo
Before build the demo, you should get an [API key][API] and replace it in [AndroidManifest.xml][manifest]. 

# Developed By

* [Amin Yazdanpanah](https://www.linkedin.com/in/aminyazdanpanah/) 


# License

    Copyright 2017 Amin Yazdanpanah

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. 


For more information, check out [my website][my-website].

[my-website]: http://www.aminyazdanpanah.com 
[manifest]: https://github.com/aminyazdanpanah/google-maps-3D-pie-chart-marker-clustering-java/blob/master/demo/src/main/AndroidManifest.xml#L39
[API]:https://developers.google.com/maps/documentation/android-api/signup
