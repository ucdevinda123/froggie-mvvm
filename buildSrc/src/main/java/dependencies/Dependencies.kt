package dependencies

object Dependencies {

 const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
 const val ktxCoreLibrary = "androidx.core:core-ktx:${Versions.ktxVersion}"
 const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
 const val androidMaterialDesign = "com.google.android.material:material:${Versions.materialVersion}"
 const val constraintLayout= "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
 const val androidSupportDependency = "androidx.legacy:legacy-support-v4:${Versions.androidSupportVersion}"

 const val junit =  "junit:junit:${Versions.junitVersion}"
 const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunitVersion}"
 const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

 const val gradleBuildToolsClassPath = "com.android.tools.build:gradle:${Versions.gradleVersion}"
 const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
 const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationKtxVersion}"

 const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
 const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImageView}"


 // Retrofit + GSON
 const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
 const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

 // Room Db
 const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
 const val roomKtx =  "androidx.room:room-ktx:${Versions.roomVersion}"
 const val roomCompiler  = "androidx.room:room-compiler:${Versions.roomVersion}"

 // Navigation
 const val navigationFragment =  "androidx.navigation:navigation-fragment-ktx:${Versions.navigationKtxVersion}"
 const val navigationUi =  "androidx.navigation:navigation-ui-ktx:${Versions.navigationKtxVersion}"

 const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltAndroid}"
 const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}"
 const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidXVersion}"
 const val hiltAndroidGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroid}"


 const val hiltLifeCycleViewModel ="androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltAndroidXVersion}"
 const val hiltDaggerAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

 const val lottie =  "com.airbnb.android:lottie:${Versions.lottieVersion}"
 const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmerVersion}"
 const val toggleButton =  "nl.bryanderidder:themed-toggle-button-group:${Versions.toggleButton}"

 const val lifeCycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleLiveDataKtxVersion}"
}