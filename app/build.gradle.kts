plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.autohub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.autohub"
        minSdk = 26
        targetSdk = 35
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
    implementation(project(":feature"))
    implementation(project(":core:storage"))
    implementation(project(":core:utils"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:search"))
    implementation(project(":feature:authentication"))
    implementation(project(":feature:car_offers"))
    implementation(project(":feature:navigation"))
    implementation(project(":feature:common"))


    implementation(libs.bundles.dataStore)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)
    implementation(libs.coil)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.bundles.koin)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.mockito.mockito.android)
    implementation(libs.androidx.monitor)
    androidTestImplementation(libs.testng)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}