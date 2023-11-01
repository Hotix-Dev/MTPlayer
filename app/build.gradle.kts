plugins {
    id("com.android.application")
}

android {
    namespace = "com.tm.mtplayer"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.tm.mtplayer"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //____Glide : image loading framework____//
    implementation("com.github.bumptech.glide:glide:4.15.1")

    //____Retrofit2____//
    implementation("com.squareup.retrofit2:retrofit:2.4.0")
    implementation("com.squareup.retrofit2:converter-gson:2.4.0")
    implementation("com.google.code.gson:gson:2.9.1")

    //____SDP - a scalable size unit____//
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //____SSP - a scalable size unit for texts____//
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    //____SpinKit - loading animations____//
    implementation("com.github.ybq:Android-SpinKit:1.4.0")

    //____ExoPlayer - media player____//
    implementation("com.google.android.exoplayer:exoplayer:2.19.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}