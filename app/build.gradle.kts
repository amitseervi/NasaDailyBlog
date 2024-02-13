@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}
object FlavourConfig {

    object QA {
        const val APP_NAME = "QA-NasaBlog"
        const val APP_ID_SUFFIX = ".qa"
        const val VERSION_NAME_SUFFIX = "-qa"
        const val NASA_API = "\"https://api.nasa.gov/\""
        const val API_KEY = "\"oY1dy9PtR6sCT0qRf9MztEpqoX3ZfSSnVXfLxEWi\""
        const val CACHE_SIZE = "5L * 1024 * 1024"
        const val MAX_AGE = "60L * 60L"
        const val MAX_STALE = "60L * 60L * 24L * 7L"
    }

    object PROD {
        const val APP_NAME = "NasaBlog"
        const val APP_ID_SUFFIX = ""
        const val VERSION_NAME_SUFFIX = ""
        const val NASA_API = "\"https://api.nasa.gov/\""
        const val API_KEY = "\"oY1dy9PtR6sCT0qRf9MztEpqoX3ZfSSnVXfLxEWi\""
        const val CACHE_SIZE = "10L * 1024 * 1024"
        const val MAX_AGE = "60L * 5L"
        const val MAX_STALE = "60L * 60L * 24L * 7L"
    }

    object Key {
        const val FLAVOUR_APP_NAME_KEY = "flavour_app_name"
        const val NASA_BASE_API_URL = "NASA_BASE_URL"
        const val NASA_API_KEY = "NASA_API_KEY"
        const val LOGGING_ENABLED_KEY = "logEnabled"
        const val NETWORK_CACHE_SIZE = "NETWORK_CACHE_SIZE"
        const val MAX_AGE = "MAX_AGE"
        const val MAX_STALE = "MAX_STALE"
    }
}


android {
    namespace = "com.amit.nasablog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.amit.nasablog"
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

    flavorDimensions += "env"

    productFlavors {

        create("qa") {
            dimension = "env"
            applicationIdSuffix = FlavourConfig.QA.APP_ID_SUFFIX
            versionNameSuffix = FlavourConfig.QA.VERSION_NAME_SUFFIX
            resValue("string", FlavourConfig.Key.FLAVOUR_APP_NAME_KEY, FlavourConfig.QA.APP_NAME)
            buildConfigField(
                "String",
                FlavourConfig.Key.NASA_BASE_API_URL,
                FlavourConfig.QA.NASA_API
            )
            buildConfigField("String", FlavourConfig.Key.NASA_API_KEY, FlavourConfig.QA.API_KEY)
            buildConfigField(
                "Long",
                FlavourConfig.Key.NETWORK_CACHE_SIZE,
                FlavourConfig.QA.CACHE_SIZE
            )
            buildConfigField("Long", FlavourConfig.Key.MAX_AGE, FlavourConfig.QA.MAX_AGE)
            buildConfigField("Long", FlavourConfig.Key.MAX_STALE, FlavourConfig.QA.MAX_STALE)
        }

        create("prod") {
            dimension = "env"
            applicationIdSuffix = FlavourConfig.PROD.APP_ID_SUFFIX
            versionNameSuffix = FlavourConfig.PROD.VERSION_NAME_SUFFIX
            resValue("string", FlavourConfig.Key.FLAVOUR_APP_NAME_KEY, FlavourConfig.PROD.APP_NAME)
            buildConfigField(
                "String",
                FlavourConfig.Key.NASA_BASE_API_URL,
                FlavourConfig.PROD.NASA_API
            )
            buildConfigField("String", FlavourConfig.Key.NASA_API_KEY, FlavourConfig.PROD.API_KEY)
            buildConfigField(
                "Long",
                FlavourConfig.Key.NETWORK_CACHE_SIZE,
                FlavourConfig.PROD.CACHE_SIZE
            )
            buildConfigField("Long", FlavourConfig.Key.MAX_AGE, FlavourConfig.PROD.MAX_AGE)
            buildConfigField("Long", FlavourConfig.Key.MAX_STALE, FlavourConfig.PROD.MAX_STALE)
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
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.hilt.android)
    ksp(libs.androidx.room.compiler)
    kapt(libs.hilt.android.compiler)
    implementation(libs.glide)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.android.youtubeExtractor)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.timber)
    implementation(libs.logging.interceptor)
}


//apply plugin: 'com.android.application'
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'
//apply plugin: 'kotlin-kapt'
//
//android {
//    def config = rootProject.ext.configuration
//
//    compileSdkVersion config.compileSdkVersion
//    buildToolsVersion config.buildToolsVersion
//
//    defaultConfig {
//        applicationId config.package
//        minSdkVersion config.minSdkVersion
//        targetSdkVersion config.targetSdkVersion
//        versionCode config.versionCode
//        versionName config.versionName
//        testInstrumentationRunner config.testInstrumentationRunner
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled true
//            debuggable false
//            testCoverageEnabled true
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//
//        debug {
//            debuggable true
//        }
//    }
//
//    flavorDimensions "environment", "loging"
//
//    productFlavors {
//        def FLAVOUR_APP_NAME_KEY = "flavour_app_name"
//        def NASA_BASE_API_URL = "NASA_BASE_URL"
//        def NASA_API_KEY = "NASA_API_KEY"
//        def LOGGING_ENABLED_KEY = "logEnabled"
//        def flavourConfig = rootProject.ext.flavourConfig
//        def NETWORK_CACHE_SIZE = "NETWORK_CACHE_SIZE"
//        def MAX_AGE = "MAX_AGE"
//        def MAX_STALE = "MAX_STALE"
//
//        dev {
//            // Assigns this product flavor to the "version" flavor dimension.
//            // If you are using only one dimension, this property is optional,
//            // and the plugin automatically assigns all the module's flavors to
//            // that dimension.
//            dimension "environment"
//            applicationIdSuffix flavourConfig.dev.appIdSuffix
//            versionNameSuffix flavourConfig.dev.versionNameSuffix
//            resValue("string", FLAVOUR_APP_NAME_KEY, flavourConfig.dev.appName)
//            buildConfigField("String", NASA_BASE_API_URL, flavourConfig.dev.nasaApi)
//            buildConfigField("String", NASA_API_KEY, flavourConfig.dev.apiKey)
//            buildConfigField("Long", NETWORK_CACHE_SIZE, flavourConfig.dev.cacheSize)
//            buildConfigField("Long", MAX_AGE, flavourConfig.dev.maxAge)
//            buildConfigField("Long", MAX_STALE, flavourConfig.dev.maxStale)
//
//        }
//
//        qa {
//            dimension "environment"
//            applicationIdSuffix flavourConfig.qa.appIdSuffix
//            versionNameSuffix flavourConfig.qa.versionNameSuffix
//            resValue("string", FLAVOUR_APP_NAME_KEY, flavourConfig.qa.appName)
//            buildConfigField("String", NASA_BASE_API_URL, flavourConfig.qa.nasaApi)
//            buildConfigField("String", NASA_API_KEY, flavourConfig.qa.apiKey)
//            buildConfigField("Long", NETWORK_CACHE_SIZE, flavourConfig.dev.cacheSize)
//            buildConfigField("Long", MAX_AGE, flavourConfig.qa.maxAge)
//            buildConfigField("Long", MAX_STALE, flavourConfig.qa.maxStale)
//        }
//
//        prod {
//            dimension "environment"
//            applicationIdSuffix flavourConfig.prod.appIdSuffix
//            versionNameSuffix flavourConfig.prod.versionNameSuffix
//            resValue("string", FLAVOUR_APP_NAME_KEY, flavourConfig.prod.appName)
//            buildConfigField("String", NASA_BASE_API_URL, flavourConfig.prod.nasaApi)
//            buildConfigField("String", NASA_API_KEY, flavourConfig.prod.apiKey)
//            buildConfigField("Long", NETWORK_CACHE_SIZE, flavourConfig.dev.cacheSize)
//            buildConfigField("Long", MAX_AGE, flavourConfig.prod.maxAge)
//            buildConfigField("Long", MAX_STALE, flavourConfig.prod.maxStale)
//        }
//
//        logged {
//            dimension "loging"
//            buildConfigField("boolean", LOGGING_ENABLED_KEY, flavourConfig.logged.logEnabled)
//        }
//
//        nolog {
//            dimension "loging"
//            buildConfigField("boolean", LOGGING_ENABLED_KEY, flavourConfig.nolog.logEnabled)
//        }
//    }
//
//    dataBinding {
//        enabled = true
//    }
//
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//
//}
//
//dependencies {
//    def libs = rootProject.ext.libPackages
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//
//    implementation libs.impl.kotlinJdk
//    implementation libs.impl.anrdoidxCompat
//    implementation libs.impl.kotlinCore
//    implementation libs.impl.constraintLayout
//    implementation libs.impl.lifecycleExt
//    androidTestImplementation libs.impl.junitExt
//    testImplementation libs.impl.junit
//    androidTestImplementation libs.impl.espresso
//    implementation libs.impl.recyclerView
//    implementation libs.impl.navigationFragmentKtx
//    implementation libs.impl.navigationUiKtx
//    implementation libs.impl.navigationDynamicFeatureFragment
//    androidTestImplementation libs.impl.navigationTesting
//    debugImplementation libs.impl.leakCanary
//    implementation libs.impl.stetho
//
//    kapt libs.annotationProcessor.dagger
//    kapt libs.annotationProcessor.daggerAndroid
//    implementation libs.impl.dagger
//
//    implementation libs.impl.timber
//    implementation libs.impl.rxJava
//    implementation libs.impl.rxAndroid
//    testImplementation libs.impl.mockito
//    implementation libs.impl.retrofit
//    implementation libs.impl.retrofitJsonConverter
//    implementation libs.impl.preferenceKtx
//    implementation libs.impl.stethoOkhttp
//    implementation libs.impl.retrofitRxJava
//    implementation libs.impl.glide
//    //implementation libs.impl.pagingRuntimeKtx
//    //testImplementation libs.impl.pagingCommon
//    //implementation libs.impl.pagingRxJava
//
//    kapt libs.annotationProcessor.dagger
//    kapt libs.annotationProcessor.daggerAndroid
//
//    implementation libs.impl.materialDesign
//    implementation libs.impl.c
//    implementation libs.impl.exoplayer
//    implementation libs.impl.youtubeExtractor
//}
