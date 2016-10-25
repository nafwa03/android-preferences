# Android-BottomBar

Android-BottomBar is a simple library developed to provide a nice bottom bar following Google material design guidelines.

[![Release](https://jitpack.io/v/User/Repo.svg)](https://jitpack.io/v/ChargeMap/android-preferences.svg)

---

<img src="https://github.com/ChargeMap/android-preferences/blob/master/art/demo.png" alt="Demo" style="width: 200px;"/>

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
	compile 'com.github.ChargeMap:android-preferences:1.0'
}
```

## Initialisation

### 1 -  Create your setting list


```java
    final ArrayList<Setting> settings = new ArrayList<>();

        settings.add(new TextSetting()
                .setLabel("Section 1") // Section title
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause)) // Setting icon
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                    	// Executed when the setting row is clicked
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                })
        );

        settings.add(((RadioSetting) new RadioSetting()
                .setTitle("Distance unit")
                .setIconDrawable(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setRadioSettingItemList(new ArrayList<String>() {{
                    add("Meters");
                    add("Miles");
                }})
                .setDefaultRadioPosition(0)
        );

        settings.add(((SliderSetting) new SliderSetting()
                .setLabel("Section 2")
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_media_pause))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
                .setMinValue(3)
                .setMaxValue(18)
                .setDefaultValue(12)
        );

        settings.add(((CheckBoxSetting) new CheckBoxSetting()
                .setTitle("Title")
                .setSubtitle("Subtitle")
                .setIconDrawable(ContextCompat.getDrawable(MainActivity.this, android.R.drawable.ic_delete))
                .setCallback(new SettingCallback() {
                    @Override
                    public void onClick(SettingAdapter.VH vh) {
                        Toast.makeText(getApplicationContext(), "Item clicked", Toast.LENGTH_LONG).show();
                    }
                }))
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