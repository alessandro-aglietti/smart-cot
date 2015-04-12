Setup firebase

> build.gradle

dependencies {
    compile 'com.firebase:firebase-client-android:2.2.3+'
}

android {
    ...
    packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE-FIREBASE.txt'
        exclude 'META-INF/NOTICE'
    }
}

> AndroidManifest.xml

<uses-permission android:name="android.permission.INTERNET" />

> Activity

@Override
public void onCreate() {
    super.onCreate();
    Firebase.setAndroidContext(this);
    // other setup code
}
