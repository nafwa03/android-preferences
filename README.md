# Android-Preferences

Android-Preferences is a simple library developed to provide a easy way to implement your application preferences.

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

[![Release](https://jitpack.io/v/ChargeMap/android-preferences.svg)](https://jitpack.io/v/ChargeMap/android-preferences.svg)

Add this to your specific module `build.gradle` file:

```gradle
dependencies {
	...
	compile 'com.github.ChargeMap:android-preferences:LAST_RELEASE'
	// See the badge to get the current release version number
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

### 2 - Start using the lib

#### Using provided activity

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

#### Using provided fragment

```java
    Fragment fragment = new SettingsBuilder()
                        .fromActivity(MainActivity.this) // Pass your current activity
                        .setSettings(settings) // Your setting list
                        .setAccentColor(R.color.colorAccent) // Your accent color
                        .getFragment(); // Returns the fragment
                        
    // Do what you want with your fragment
```

#### Using your own recyclerview

```java
    new SettingsBuilder()
                        .fromActivity(MainActivity.this) // Pass your current activity
                        .setSettings(settings) // Your setting list
                        .setAccentColor(R.color.colorAccent) // Your accent color
                        .setupRecyclerView(myRecyclerView); // Use your recyclerview to display the list
                        
    // You list is now populated with your settings 
```

### 3 - That's it 

There is only two step for you to start your custom settings activity.

## ChargeMap ( [http://chargemap.com](https://chargemap.com) )

<img src="https://chargemap.com/img/logo.png" />

 This library has been developed and released by ChargeMap.

## License

This library is distributed under the apache 2.0 license.