# 🗺 Google Maps Chart for Android

[![Build Status](https://travis-ci.org/aminyazdanpanah/google-maps-android-charts.svg?branch=master)](https://travis-ci.org/aminyazdanpanah/google-maps-android-charts)
[![](https://jitpack.io/v/aminyazdanpanah/google-maps-android-charts.svg)](https://jitpack.io/#aminyazdanpanah/google-maps-android-charts)

A Google Maps Android library to render marker clusters as charts categorized on the Google Maps Android with specified values.

Available Charts: [Pie Chart](#pie-chart), [Bar Chart](#bar-chart) and [Donut Chart](#donut-chart)

![Demo](/docs/demo.gif?raw=true "Demo")

## Installation

1. Make sure you have JCenter in your repository list:
```groovy
repositories {
    jcenter()
}
```
2. Add a dependency to your build.gradle:
```groovy
dependencies {
    implementation 'com.aminyazdanpanah:google-maps-android-charts:1.3'
}
```

## Usage

This library is a wrapper around "[Google Maps Android API utility library](https://github.com/googlemaps/android-maps-utils)" and renderer marker cluster as charts on th Google Maps. You need to implement `com.google.maps.android:android-maps-utils:0.5+` and `com.google.android.gms:play-services-maps:16.1.0` in your build.gradle dependencies.

- **NOTE:** Before using this library, please read the "[Google Maps Android API utility library documentation](https://developers.google.com/maps/documentation/android-sdk/utility/)" .

- **NOTE:** Before Building the demo, you must [get an API key](https://developers.google.com/maps/documentation/android-sdk/signup) which you can then add to your demo app.


### Marker

First, you need to generate markers that have a name.
You must initialize `CMaker` class that is implemented by `ClusterItem` and pass the LarLng, the name of marker and the drawable Id. Also you can set the title and snippet of the marker.

```java

CMarker marker = new CMarker(new LatLng(52.22222, 36.25622), "Ford", R.drawable.marker_red); //(Location of marker, specifed name, the drawable marker ID)
marker.setTitle("somthing you want to display when you tap on marker");


CMarker marker2 = new CMarker(new LatLng(52.25552, 36.25624), "Toyota", R.drawable.marker_green); //(Location of marker, specifed name, the drawable marker ID)
marker.setTitle("somthing you want to display when you tap on marker");
 
//...
```
Add the marker to the `addItem()` method;

```java
clusterManager.addItem(marker);
clusterManager.addItem(marker2);
//...
```

### Chart

This library uses the the name to calculate the values of charts. In face, the number of  each slice of chart is sum of marker's name.
Also you must set the names of markers and the colors you would like to render. The length of names array must be the same as length of colors array.
 ```java
 
String[] names = {"Toyota", "Ford", "Honda", "Dodge"};
int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

```
#### Pie Chart

```java
PieChartRenderer chart = new PieChartRenderer(getApplicationContext(), googleMap, clusterManager);
chart.colors(colors);
chart.names(names);
```

#### Bar Chart


```java
BarChartRenderer chart = new BarChartRenderer(getApplicationContext(), googleMap, clusterManager);
chart.colors(colors);
chart.names(names);
```

#### Donut Chart


```java
DonutChartRenderer chart = new DonutChartRenderer(getApplicationContext(), googleMap, clusterManager);
chart.colors(colors);
chart.names(names);
```

At the end, you must pass `chart` object to `setRenderer` method in `ClusterManager` class:

```java
clusterManager.setRenderer(chart);
```

### Customize Values

You can customize the values of chart by extending `ChartRenderer` class and override the `values` method:

```java
class customPieChartRenderer extends PieChartRenderer {
        @Override
        float[] values(Cluster<CMarker> cluster){
            // Return any value
        }
}
```
## Contributing

I'd love your help in improving, correcting, adding to the specification.
Please [file an issue](https://github.com/aminyazdanpanah/google-maps-android-charts/issues)
or [submit a pull request](https://github.com/aminyazdanpanah/google-maps-android-charts/pulls).

Please see [Contributing File](https://github.com/aminyazdanpanah/google-maps-android-charts/blob/master/CONTRIBUTING.md) for more information.

## Security

If you discover a security vulnerability within this package, please send an e-mail to Amin Yazdanpanah via:
contact [AT] aminyazdanpanah • com.

## Credits

- [Amin Yazdanpanah](http://www.aminyazdanpanah.com/?u=github.com/aminyazdanpanah/google-maps-android-charts)

## License

    Copyright 2019 Amin Yazdanpanah<www.aminyazdanpanah.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. 


