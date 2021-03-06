# Persian Extend Date Dialog
[![](https://jitpack.io/v/hamsaadev/Persian-Date-Extender-Dialog.svg)](https://jitpack.io/#hamsaadev/Persian-Date-Extender-Dialog)

![Hero Image](https://raw.githubusercontent.com/hamsaadev/Persian-Date-Extender-Dialog/master/shot/heroimage.jpg)

## Description

Persian Extend Date Dialog for Android that helps you easily pick next date for extend current date.

## Usage

To use persian extend date dialog you must add it as a dependency in your Gradle build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

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
	compile 'com.github.hamsaadev:Persian-Date-Extender-Dialog:V1.0.0'
}
```

Then add the this to your java code:

```java
    PersianExtendDialog dialog = new PersianExtendDialog.Builder(this, new Date(), R.string.title)
                    .withOkButton("تایید")
                    .withCancelButton("انصراف")
                    .withOnDatePickedListener(new OnExtendedDatePicked() {
                        @Override
                        public void onDate(Date date, int num) {
                            Log.d(TAG, "onDate: " + date.toString() + " num: " + num);
                        }

                        @Override
                        public void onDismissed() {
                            Log.d(TAG, "onDismissed");
                        }
                    }).build();
            dialog.show();
```

Note: builder 3rd parameter is an string with String placeholder for title.

## Changelog

### v1.0.0

 * Initial release

## License
```
   
The MIT License (MIT)

Copyright (c) 2017 Hamsaa.ir

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

```
