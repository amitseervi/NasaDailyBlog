// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.androidTest) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//
//buildscript {
//    ext.kotlin_version = '1.3.71'
//    repositories {
//        google()
//        jcenter()
//
//    }
//    dependencies {
//        classpath 'com.android.tools.build:gradle:3.6.4'
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
//
//        // NOTE: Do not place your application dependencies here; they belong
//        // in the individual module build.gradle files
//    }
//}
//
//allprojects {
//    repositories {
//        google()
//        jcenter()
//        maven { url 'https://jitpack.io' }
//
//    }
//}
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
//
//
//ext {
//    configuration = [
//            package                  : "com.amit.nasablog",
//            compileSdkVersion        : 29,
//            targetSdkVersion         : 29,
//            minSdkVersion            : 21,
//            buildToolsVersion        : "29.0.3",
//            versionName              : "1.0",
//            versionCode              : 1,
//            testInstrumentationRunner: "androidx.test.runner.AndroidJUnitRunner"
//    ]
//
//    flavourConfig = [
//            qa    : [
//                    appName          : "Qa-NasaBlog",
//                    appIdSuffix      : ".qa",
//                    versionNameSuffix: "-qa",
//                    nasaApi          : "\"https://api.nasa.gov/\"",
//                    apiKey           : "\"oY1dy9PtR6sCT0qRf9MztEpqoX3ZfSSnVXfLxEWi\"",
//                    cacheSize        : "5L * 1024 * 1024",
//                    maxAge           : "60L * 60L",
//                    maxStale         : "60L * 60L * 24L * 7L"
//            ],
//            prod  : [
//                    appName          : "NasaBlog",
//                    appIdSuffix      : "",
//                    versionNameSuffix: "",
//                    nasaApi          : "\"https://api.nasa.gov/\"",
//                    apiKey           : "\"oY1dy9PtR6sCT0qRf9MztEpqoX3ZfSSnVXfLxEWi\"",
//                    cacheSize        : "10L * 1024 * 1024",
//                    maxAge           : "60L * 5L",
//                    maxStale         : "60L * 60L * 24L * 7L"
//            ],
//            dev   : [
//                    appName          : "dev-NasaBlog",
//                    appIdSuffix      : ".dev",
//                    versionNameSuffix: "-dev",
//                    nasaApi          : "\"https://api.nasa.gov/\"",
//                    apiKey           : "\"oY1dy9PtR6sCT0qRf9MztEpqoX3ZfSSnVXfLxEWi\"",
//                    cacheSize        : "1L * 1024 * 1024",
//                    maxAge           : "60L * 60L * 12",
//                    maxStale         : "60L * 60L * 24L * 7L"
//            ],
//            logged: [
//                    logEnabled: "true"
//            ],
//            nolog : [
//                    logEnabled: "false"
//            ]
//    ]
//
//    libVersions = [
//            androidX        : "1.1.0",
//            kotlin          : "1.2.0",
//            room            : "2.2.5",
//            constraintLayout: "1.1.3",
//            lifecycle       : "2.2.0",
//            dagger          : "2.27",
//            navigation      : "2.3.0-alpha04",
//            gson            : "2.8.6",
//            rxAndroid       : "2.1.1",
//            rxJava          : "2.2.9",
//            coroutine       : "1.3.0",
//            junit           : "4.12",
//            junitExtension  : "1.1.1",
//            espressoVersion : "3.2.0",
//            mockito         : "2.0.2-beta",
//            timber          : "4.7.1",
//            leakCanary      : "2.2",
//            stetho          : "1.5.1",
//            playServiceAuth : "18.0.0",
//            facebookSdk     : "6.3.0",
//            retrofit        : "2.5.0",
//            apolloGraphql   : "1.4.4",
//            camerax         : "1.0.0-beta03",
//            cameraView      : "1.0.0-alpha10",
//            cameraExt       : "1.0.0-alpha10",
//            fresco          : "1.13.0",
//            glide           : "4.11.0",
//            paging          : "2.1.2",
//            materialDesign  : "1.2.0-beta01",
//            touchView       : "3.0.2",
//            exoplayer       : "2.9.5",
//            youtubeExtractor: "v1.7.0"
//    ]
//
//    libPackages = [
//            impl               : [
//                    kotlinJdk                       : "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${libVersions.kotlin}",
//                    anrdoidxCompat                  : "androidx.appcompat:appcompat:${libVersions.androidX}",
//                    kotlinCore                      : "androidx.core:core-ktx:${libVersions.kotlin}",
//                    constraintLayout                : "androidx.constraintlayout:constraintlayout:${libVersions.constraintLayout}",
//                    lifecycleExt                    : "androidx.lifecycle:lifecycle-extensions:${libVersions.lifecycle}",
//                    preferenceJava                  : "androidx.preference:preference:${libVersions.androidX}",
//                    preferenceKtx                   : "androidx.preference:preference-ktx:${libVersions.androidX}",
//                    junit                           : "junit:junit:${libVersions.junit}",
//                    junitExt                        : "androidx.test.ext:junit:${libVersions.junitExtension}",
//                    espresso                        : "androidx.test.espresso:espresso-core:${libVersions.espressoVersion}",
//                    dagger                          : "com.google.dagger:dagger-android-support:${libVersions.dagger}",
//                    room                            : "androidx.room:room-runtime:${libVersions.room}",
//                    roomktx                         : "androidx.room:room-ktx:${libVersions.room}",
//                    roomRxjava                      : "androidx.room:room-rxjava2:${libVersions.room}",
//                    roomTest                        : "androidx.room:room-testing:${libVersions.room}",
//                    recyclerView                    : "androidx.recyclerview:recyclerview:${libVersions.androidX}",
//                    navigationFragment              : "androidx.navigation:navigation-fragment:${libVersions.navigation}",
//                    navigationUi                    : "androidx.navigation:navigation-ui:${libVersions.navigation}",
//                    navigationFragmentKtx           : "androidx.navigation:navigation-fragment-ktx:${libVersions.navigation}",
//                    navigationUiKtx                 : "androidx.navigation:navigation-ui-ktx:${libVersions.navigation}",
//                    navigationDynamicFeatureFragment: "androidx.navigation:navigation-dynamic-features-fragment:${libVersions.navigation}",
//                    navigationTesting               : "androidx.navigation:navigation-testing:${libVersions.navigation}",
//                    kotlinCoroutineCore             : "org.jetbrains.kotlinx:kotlinx-coroutines-core:${libVersions.coroutine}",
//                    kotlinCoroutineAndroid          : "org.jetbrains.kotlinx:kotlinx-coroutines-android:${libVersions.coroutine}",
//                    leakCanary                      : "com.squareup.leakcanary:leakcanary-android:${libVersions.leakCanary}",
//                    timber                          : "com.jakewharton.timber:timber:${libVersions.timber}",
//                    gson                            : "com.google.code.gson:gson:${libVersions.gson}",
//                    rxJava                          : "io.reactivex.rxjava2:rxjava:${libVersions.rxJava}",
//                    rxAndroid                       : "io.reactivex.rxjava2:rxandroid:${libVersions.rxAndroid}",
//                    mockito                         : "org.mockito:mockito-all:${libVersions.mockito}",
//                    stetho                          : "com.facebook.stetho:stetho:${libVersions.stetho}",
//                    stethoOkhttp                    : "com.facebook.stetho:stetho-okhttp3:${libVersions.stetho}",
//                    googleAuth                      : "com.google.android.gms:play-services-auth:${libVersions.playServiceAuth}",
//                    facebookSdk                     : "com.facebook.android:facebook-android-sdk:${libVersions.facebookSdk}",
//                    retrofit                        : "com.squareup.retrofit2:retrofit:${libVersions.retrofit}",
//                    retrofitJsonConverter           : "com.squareup.retrofit2:converter-gson:${libVersions.retrofit}",
//                    retrofitRxJava                  : "com.squareup.retrofit2:adapter-rxjava2:${libVersions.retrofit}",
//                    apolloGraphql                   : "com.apollographql.apollo:apollo-runtime:${libVersions.apolloGraphql}",
//                    apolloGraphqlAndroid            : "com.apollographql.apollo:apollo-android-support:${libVersions.apolloGraphql}",
//                    apolloGraphqlAndroidRx          : "com.apollographql.apollo:apollo-rx2-support:${libVersions.apolloGraphql}",
//                    camera2                         : "androidx.camera:camera-camera2:${libVersions.camerax}",
//                    cameraLifecycle                 : "androidx.camera:camera-lifecycle:${libVersions.camerax}",
//                    cameraView                      : "androidx.camera:camera-view:${libVersions.cameraView}",
//                    cameraExt                       : "androidx.camera:camera-extensions:${libVersions.cameraExt}",
//                    fresco                          : "com.facebook.fresco:fresco:${libVersions.fresco}",
//                    glide                           : "com.github.bumptech.glide:glide:${libVersions.glide}",
//                    pagingRuntime                   : "androidx.paging:paging-runtime:${libVersions.paging}",
//                    pagingRuntimeKtx                : "androidx.paging:paging-runtime-ktx:${libVersions.paging}",
//                    pagingCommon                    : "androidx.paging:paging-common:${libVersions.paging}",
//                    pagingRxJava                    : "androidx.paging:paging-rxjava2:${libVersions.paging}",
//                    materialDesign                  : "com.google.android.material:material:${libVersions.materialDesign}",
//                    touchView                       : "com.github.MikeOrtiz:TouchImageView:${libVersions.touchView}",
//                    exoplayer                       : "com.google.android.exoplayer:exoplayer:${libVersions.exoplayer}",
//                    youtubeExtractor                : "com.github.HaarigerHarald:android-youtubeExtractor:${libVersions.youtubeExtractor}"
//            ],
//
//            annotationProcessor: [
//                    dagger       : "com.google.dagger:dagger-compiler:${libVersions.dagger}",
//                    daggerAndroid: "com.google.dagger:dagger-android-processor:${libVersions.dagger}",
//                    room         : "androidx.room:room-compiler:${libVersions.room}",
//                    glide        : "com.github.bumptech.glide:compiler:${libVersions.glide}"
//            ]
//    ]
//}
