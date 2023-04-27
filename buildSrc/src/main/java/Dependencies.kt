import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    object Android {
        val activity = "androidx.activity:activity-ktx:${Versions.activityKtx}"
        val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"
        val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
        val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"
        val testRunner = "androidx.test:runner"
    }

    object Compose {
        val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        val core = "androidx.compose.ui:ui:${Versions.compose}"
        val debugManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
        val debugTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        val junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        val material = "androidx.compose.material:material:${Versions.composeMaterial}"
        val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        val tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    }

    object Coroutines {
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    }

    object Espresso {
        val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Hilt {
        val core = "com.google.dagger:hilt-android:${Versions.hilt}"
        val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object JUnit {
        val core = "junit:junit:${Versions.junit}"
        val ext = "androidx.test.ext:junit:${Versions.junitExt}"
        val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Pinterest {
        val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    }

    val appLibraries = arrayListOf<String>(
        Android.activity,
        Android.coreKtx,
        Android.dataStore,
        Android.lifecycleKtx,
        Compose.activity,
        Compose.core,
        Compose.tooling,
        Compose.material,
        Compose.navigation,
        Hilt.core,
    )

    val kaptLibraries = arrayListOf<String>(
        Hilt.compiler,
    )

    val testLibraries = arrayListOf<String>(
        Android.testCoreKtx,
        Coroutines.test,
        JUnit.core,
    )

    val androidTestLibraries = arrayListOf<String>(
        Android.testRunner,
        Compose.junit,
        Espresso.core,
        JUnit.ext,
    )

    val debugLibraries = arrayListOf<String>(
        Compose.debugManifest,
        Compose.debugTooling,
    )
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}