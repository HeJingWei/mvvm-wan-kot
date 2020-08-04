package hjw.dependencies

object Versions {
    const val compileSdk = 29
    const val buildTools = "29.0.3"
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.3.72"
    const val coroutines = "1.3.0"
    const val coroutines_android = "1.3.8"
    const val androidxArch = "2.0.0"
    const val mockito = "2.23.0"

    const val appcompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val retrofit = "2.7.2"
    const val retrofit_converter_gson = "2.6.2"
    const val okhttp_logging_interceptor = "4.0.0"
    const val swipeRefreshLayout = "1.1.0-rc01"
    const val material = "1.2.0-beta01"
    const val circleImageview = "2.2.0"
    const val leakcanary = "2.0-alpha-3"
    const val baseRecyclerViewAdapterHelper = "3.0.4"
    const val banner = "1.4.10"
    const val glide = "4.11.0"
    const val glide_compiler = "4.11.0"
    const val glideTransformations = "4.1.0"
    const val cardView = "1.0.0"
    const val flowLayout = "1.1.2"
    const val persistentCookieJar = "v1.0.1"
    const val livedata_ktx = "2.2.0"
    const val viewPager2 = "1.0.0"
    const val koin = "2.0.1"
    const val core_ktx = "1.3.0"
    const val navigation = "2.2.2"
    const val recyclerView = "1.1.0"
    const val viewmodel_ktx = "2.2.0"
    const val lifecycle_extension = "2.2.0"
    const val loading_helper = "2.1.0"
    const val l_dialog = "V1.0.4"
    const val room = "2.2.5"
    const val auto_size = "1.2.1"
}

object Deps {

    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    // LiveData
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    // ViewModel
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.livedata_ktx}"
    const val lifecycle_common_java8 ="androidx.lifecycle:lifecycle-common-java8:${Versions.livedata_ktx}"
//    @Deprecated("lifecycle-extensions 已弃用,不要使用 ViewModelProviders.of 的方式")
//    const val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extension}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    //room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"

    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieJar}"

    //glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideTransformations = "jp.wasabeef:glide-transformations:${Versions.glideTransformations}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"

    // third
    const val circleimageview = "de.hdodenhof:circleimageview:${Versions.circleImageview}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"
    const val banner = "com.youth.banner:banner:${Versions.banner}"
    const val flowLayout = "com.hyman:flowlayout-lib:${Versions.flowLayout}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val loading_helper = "com.dylanc:loadinghelper:${Versions.loading_helper}"
    const val l_dialog = "com.github.liys666666:LDialog:${Versions.l_dialog}"
    const val auto_size = "me.jessyan:autosize:${Versions.auto_size}"

}