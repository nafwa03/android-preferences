# Android-BottomBar

Android-BottomBar is a simple library developed to provide a nice bottom bar following Google material design guidelines.

[![Release](https://jitpack.io/v/ChargeMap/android-preferences.svg)](https://jitpack.io/v/ChargeMap/android-preferences.svg)

---

<img src="https://github.com/ChargeMap/android-preferences/blob/master/art/demo.png" width="300"/>

## Gradle Dependency

### Repository

Add this in your root (global) `build.gradle` file :

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

### Dependency

Add this to your specific module `build.gradle` file:

```gradle
dependencies {
	...
	compile 'com.github.ChargeMap:android-preferences:1.6'
}
```

## Initialisation

### 1 -  Create your setting list

```java
    final ArrayList<Setting> settings = new ArrayList<>();
    
    	// Add a simple text setting
        settings.add(new TextSetting()
                .setContext(context)
                .setLabel("Section 1") // Section title
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause)) // Setting icon
        );
        
        // Add a setting with radio buttons
        List<String> options = new ArrayList<>();
        stringList.add("Meters");
        stringList.add("Miles");

        settings.add(((RadioSetting) new RadioSetting()
                .setContext(context)
                .setTitle("Distance unit")
                .setIconDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_pause))
                )
                .setRadioSettingItemList(options)
                .setDefaultRadioPosition(0)
        );

		// Add slider setting
        settings.add(((SliderSetting) new SliderSetting()
                .setContext(context)
                .setLabel("Section 2")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
            	)
                .setMinValue(3)
                .setMaxValue(18)
                .setDefaultValue(12)
        );

		// Add checkbox setting
        settings.add(((CheckBoxSetting) new CheckBoxSetting()
                .setContext(context)
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_delete))
            	)
                .setChecked(true)
        );
```

### 2 - Start the setting activity


```java
    new SettingsBuilder()
                        .fromActivity(MainActivity.this) // Pass your current activity
                        .setSettings(settings) // Your setting list
                        .setPrimaryColor(R.color.colorPrimary) // Your primary color
                        .setAccentColor(R.color.colorAccent) // Your accent color
                        .setToolbarTextColor(R.color.md_white_1000) // The color for the toolbar text and icons
                        .setTitle("Settings custom") // The toolbar text
                        .start(); // Start the activity
```

### 3 - That's it 

There is only two step for you to start your custom settings activity.

## ChargeMap ( [http://chargemap.com](https://chargemap.com) )

<img src="https://chargemap.com/img/logo.png" />

 This library has been developed and released by ChargeMap.

## License

This library is distributed under the apache 2.0 license.