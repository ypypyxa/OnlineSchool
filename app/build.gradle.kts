plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.pivnoydevelopment.onlineschool"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pivnoydevelopment.onlineschool"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        viewBinding {
            enable = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Core Android libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)

    // Material Design Components
    implementation(libs.material)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Glide for image loading
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Dependency Injection with Koin
    implementation(libs.koin.android)

    // Fragment utilities
    implementation(libs.androidx.fragment.ktx)

    // ViewModel
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // ViewPager2 for swipeable views
    implementation(libs.androidx.viewpager2)

    // FlexboxLayout — позволяет удобно располагать элементы в стиле flex-wrap, как в CSS (для адаптивных тегов/чипов)
    implementation(libs.flexbox)

    // Kotlin Coroutines for asynchronous programming
    implementation(libs.kotlinx.coroutines.android)

    // Room for database management
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Retrofit for networking
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)

    implementation(project(":common"))
}