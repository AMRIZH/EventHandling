plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.eventhandling"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eventhandling"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

configurations {
    all {
        resolutionStrategy {
            // Force a specific version of navigation-compose to avoid conflicts
            force("androidx.navigation:navigation-compose:2.7.7")
            
            // Exclude conflicting modules
            exclude(group = "androidx.navigation", module = "navigation-compose-jvmstubs")
            exclude(group = "androidx.navigation", module = "navigation-compose-android")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    
    // Material 3 - latest stable version
    implementation("androidx.compose.material3:material3:1.2.0")
    
    // Navigation Compose - fixed version
    implementation("androidx.navigation:navigation-compose:2.7.7")
    
    // Extended Icons
    implementation("androidx.compose.material:material-icons-extended:1.6.2")
    
    // Window insets for composables
    implementation("androidx.compose.foundation:foundation:1.6.2")
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}