plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.example.nfc3"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.nfc3"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes { release { isMinifyEnabled = false } }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}
dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("com.google.android.material:material:1.12.0")
    // appcompat НЕ обязателен для варианта с android.app.Activity
    // если захочешь AppCompatActivity, добавь:
    // implementation("androidx.appcompat:appcompat:1.7.0")
}
