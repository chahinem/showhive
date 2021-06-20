![minSdkVersion](https://img.shields.io/badge/minSdk-24-red.svg)
![compileSdkVersion](https://img.shields.io/badge/compileSdkVersion-30-green.svg)
![Version](https://img.shields.io/badge/version-alpha-blue.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

![ShowHive](base/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

# Overview

Small Trakt client. WIP

Light Theme                |  Dark Theme
:-------------------------:|:-------------------------:
![](art/light-splash.png)  |  ![](art/dark-splash.png)
![](art/light-home-calendar.png)  |  ![](art/dark-home-calendar.png)
![](art/light-home-discover.png)  |  ![](art/dark-home-discover.png)

# Project Setup

## Cloning

```
$> git clone git@github.com:chahine/showhive.git
```

## Required keys

```
# local.properties
TRAKT_CLIENT_ID     = 
TRAKT_CLIENT_SECRET = 
TMDB_API_KEY        = 
```

You can either uncomment the signing config for both debug and release variant or provide the following properties to sign the build.

### Debug


```
# debug.properties

DEBUG_STORE_FILE     =
DEBUG_STORE_PASSWORD =
DEBUG_KEY_ALIAS      =
DEBUG_KEY_PASSWORD   =
```

### Release

```
# release.properties

RELEASE_STORE_FILE     =
RELEASE_STORE_PASSWORD =
RELEASE_KEY_ALIAS      =
RELEASE_KEY_PASSWORD   =
```

## Build

### Clean Build

```
$> ./gradlew clean build
```

### Release variant

```
$> ./gradlew clean assembleRelease
```

### Code analysis and tests

```
$> ./gradlew spotlessCheck detekt
$> ./gradlew testDebugUnitTest
```

# Android Specs

### Architecture

- Retrofit + OkHttp
- Dagger Hilt
- Jetpack ViewModel
- AndroidX Paging
- Kotlin Coroutines and Flow

### Continuous Integration

TODO

## License

    MIT License
    
    Copyright (c) 2018 Chahine
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
