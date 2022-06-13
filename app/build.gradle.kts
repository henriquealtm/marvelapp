plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    kotlin("android")
}

android {
    compileSdkVersion(AppConfig.compileSdk)

    defaultConfig {
        applicationId = "com.example.marvelapp"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.customInstrumentedRunner
    }

    buildTypes {
        getByName(AppConfig.release) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(AppConfig.proguardOptimize),
                AppConfig.proguardRules
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
    viewBinding {
        android.buildFeatures.dataBinding = true
    }
}

dependencies {
    implementation(project(":analytics"))
    implementation(project(":commons"))
    implementation(project(":network"))

    implementation(AppDependencies.coreLibraries)
    implementation(AppDependencies.koinLibraries)
    implementation(AppDependencies.lifecycleLibraries)
    implementation(AppDependencies.navigationLibraries)
    implementation(AppDependencies.uiLibraries)

    implementation(project.dependencies.platform(AppDependencies.firebasePlatform))
    implementation(AppDependencies.firebaseAnalytics)
    implementation(AppDependencies.retrofit)

    testImplementation(AppDependencies.unitTestLibraries)

    androidTestImplementation(AppDependencies.androidTestLibraries)
}