plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.projecto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projecto"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}


dependencies {
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("androidx.test.ext:truth:1.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation ("com.makeramen:roundedimageview:2.3.0")
    implementation ("com.airbnb.android:lottie:4.1.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("androidx.fragment:fragment:1.4.0")


    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core:1.4.0")
    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("androidx.test.espresso:espresso-core:3.4.0")
    // Android instrumented tests dependencies
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Unit testing dependencies
    testImplementation("androidx.test:runner:1.4.0")

    // Mockito dependencies
    implementation("org.mockito:mockito-core:5.12.0")  //core Mockito functionalities.
    testImplementation("org.mockito:mockito-core:4.0.0")  //core Mockito functionalities.
    testImplementation("org.mockito:mockito-inline:4.0.0") //to support mocking of final classes and methods.
    testImplementation("org.mockito:mockito-android:4.0.0")  //Mockito integration with Android.
    testImplementation ("org.mockito:mockito-junit-jupiter:4.0.0")

    // Robolectric dependencies for unit tests
    testImplementation("org.robolectric:robolectric:4.7.3")  //to run Android tests in the JVM without the need for an emulator or real device.

    // AndroidX Test - Instrumentation Testing
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    // Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Mockito
    testImplementation("org.mockito:mockito-core:5.12.0")
    androidTestImplementation("org.mockito:mockito-android:4.0.0")
    // Activity Scenario
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}