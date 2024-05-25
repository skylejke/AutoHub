plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.autohub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.autohub"
        minSdk = 24
        targetSdk = 34
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

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.test:monitor:1.6.1")
    androidTestImplementation("org.testng:testng:6.9.6")
    val navVersion = "2.7.7"
    val retrofitVersion = "2.9.0"
    val lifecycleVersion = "2.8.0"
    val roomVersion = "2.6.1"
    val koinVersion = "3.5.3"

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    implementation("io.coil-kt:coil:1.4.0")

    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.0.0")

    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")


    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    testImplementation("io.insert-koin:koin-test:$koinVersion")

    //JUnit dependency for unit tests
    testImplementation("junit:junit:4.13.2")

    // Mockito dependency for unit tests
    testImplementation("org.mockito:mockito-core:4.0.0")

    // Optional: Mockito Kotlin extension if you use Kotlin
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // AndroidX Test - Core library
    testImplementation("androidx.test:core:1.5.0")

    // AndroidX Test - JUnit support
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Optional: AndroidX Test - Mockito Android support (for instrumented tests)
    androidTestImplementation("org.mockito:mockito-android:4.0.0")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}