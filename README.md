# MAD Practical-6 — Frame by Frame Animation & Splash Screen

## Aim

Create an Android application to demonstrate:

- Frame by Frame Animation
- Twin Animation
- Splash Screen
- Edge-to-Edge content display
- `AnimationDrawable`
- `AnimationUtils`
- `loadAnimation()`
- `setAnimationListener()`
- `overridePendingTransition()`
- `finish()`

---

## Practical Requirements

This practical demonstrates the following Android concepts:

1. `ImageView`
2. Frame by Frame Animation using `<animation-list>`
3. `oneShot` attribute
4. Twin Animation using `<set>`
5. `startOffset`
6. `duration`
7. `<scale>`
8. `<translate>`
9. `<rotate>`
10. `<alpha>`
11. Splash Screen
12. Gradient rectangle background
13. Edge-to-edge display

---

## Project Structure

The important files/folders used in this practical are:

```text
app/
└── src/
    └── main/
        ├── java/com/example/yourpackage/
        │   ├── MainActivity.kt
        │   └── SplashActivity.kt
        │
        └── res/
            ├── anim/
            │   └── twin_animation.xml
            │
            ├── drawable/
            │   └── splash_background.xml
            │
            └── drawable/
                └── frame_animation.xml
```

> Keep the package name and file names according to your Android Studio project.

---

# 1. MainActivity

`MainActivity` is used to display the main UI and demonstrate the frame-by-frame animation.

Example:

```kotlin
package com.example.yourpackage

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)

        imageView.setBackgroundResource(R.drawable.frame_animation)

        val animation = imageView.background as AnimationDrawable
        animation.start()
    }
}
```

---

# 2. Frame by Frame Animation

Frame by Frame Animation displays a sequence of images one after another.

It can be created using the `<animation-list>` tag.

Example:

```xml
<animation-list xmlns:android="http://schemas.android.com/apk/res/android"
    android:oneshot="false">

    <item
        android:drawable="@drawable/image1"
        android:duration="200" />

    <item
        android:drawable="@drawable/image2"
        android:duration="200" />

    <item
        android:drawable="@drawable/image3"
        android:duration="200" />

</animation-list>
```

### Important Attribute

`android:oneshot="false"` means that the animation keeps repeating.

If:

```xml
android:oneshot="true"
```

the animation runs only once.

---

# 3. SplashActivity

`SplashActivity` is displayed when the application starts.

After the splash screen animation finishes, `MainActivity` is opened.

Example:

```kotlin
package com.example.yourpackage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val imageView = findViewById<ImageView>(R.id.imageView)

        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.twin_animation
        )

        animation.setAnimationListener(object :
            android.view.animation.Animation.AnimationListener {

            override fun onAnimationStart(animation: android.view.animation.Animation?) {
            }

            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
                overridePendingTransition(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )
            }

            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {
            }
        })

        imageView.startAnimation(animation)
    }
}
```

---

# 4. Splash Background

Create:

```text
res/drawable/splash_background.xml
```

Use a rectangular radial gradient with:

- Shape: Rectangle
- Type: Radial
- Center X: `0.9`
- Center Y: `0.9`
- Radius: `1500`
- Start Color: Pink
- End Color: Blue

Example:

```xml
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">

    <gradient
        android:type="radial"
        android:centerX="0.9"
        android:centerY="0.9"
        android:gradientRadius="1500"
        android:startColor="#FF69B4"
        android:endColor="#0000FF" />

</shape>
```

---

# 5. Twin Animation

Twin Animation means applying two or more animations together or in sequence.

The `<set>` tag is used to combine animations.

Create:

```text
res/anim/twin_animation.xml
```

Example:

```xml
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <scale
        android:fromXScale="0.5"
        android:toXScale="1.0"
        android:fromYScale="0.5"
        android:toYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="1000" />

    <translate
        android:fromXDelta="0"
        android:toXDelta="100"
        android:fromYDelta="0"
        android:toYDelta="0"
        android:startOffset="100"
        android:duration="1000" />

    <rotate
        android:fromDegrees="0"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="1000" />

    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0"
        android:duration="1000" />

</set>
```

This demonstrates the required:

- `<set>`
- `<scale>`
- `<translate>`
- `<rotate>`
- `<alpha>`
- `startOffset`
- `duration`

---

# 6. Edge-to-Edge Display

Edge-to-edge allows the application content to extend behind the system bars.

For example:

```kotlin
enableEdgeToEdge()
```

is used in the activity.

The activity can then display content across the available screen area while handling system-bar insets when required.

---

# 7. Required Manifest Entries

Both activities should be declared in `AndroidManifest.xml`.

Example:

```xml
<application
    ...>

    <activity
        android:name=".MainActivity"
        android:exported="false" />

    <activity
        android:name=".SplashActivity"
        android:exported="true">

        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>

    </activity>

</application>
```

---

# 8. Animation Flow

```text
Application Start
       ↓
SplashActivity
       ↓
Splash Background
       ↓
Twin Animation
       ↓
Animation Ends
       ↓
MainActivity
       ↓
Frame by Frame Animation
```

---

# 9. Important Methods and Classes

| Method / Class | Purpose |
|---|---|
| `ImageView` | Displays images |
| `AnimationDrawable` | Runs frame-by-frame animation |
| `AnimationUtils` | Loads XML animations |
| `loadAnimation()` | Loads animation from `res/anim` |
| `setAnimationListener()` | Detects animation events |
| `overridePendingTransition()` | Applies transition between activities |
| `finish()` | Closes the current activity |
| `enableEdgeToEdge()` | Enables edge-to-edge display |
| `<animation-list>` | Creates frame animation |
| `<set>` | Combines animations |
| `<scale>` | Changes size |
| `<translate>` | Moves the view |
| `<rotate>` | Rotates the view |
| `<alpha>` | Changes transparency |

---

# 10. Output

![Practical-6 Output](output.png)

**Output:** The application displays the required files `alarm1.jpg` and `logo.png` as part of the practical output.

# Conclusion

Thus, the Android application successfully demonstrates **Frame by Frame Animation, Twin Animation, Splash Screen, Gradient Background, and Edge-to-Edge content display** using Kotlin and XML.
