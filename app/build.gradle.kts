plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

val compose_ui_version = "1.2.0"

android {
    namespace = "me.ismartin.musicstoptimer"
    compileSdk = 33

    defaultConfig {
        applicationId = "me.ismartin.musicstoptimer"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
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
    buildFeatures.compose = true
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.compose.ui:ui:$compose_ui_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_ui_version")
    implementation("androidx.compose.material:material:1.4.1")

    implementation("androidx.navigation:navigation-compose:2.6.0-alpha09")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.activity:activity-ktx:1.7.0")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core-ktx:1.5.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_ui_version")
    androidTestImplementation("androidx.test:runner")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_ui_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_ui_version")
}

kapt {
    correctErrorTypes = true
}

setupKtlint()

fun Project.setupKtlint() {
    val ktlintConfiguration by configurations.creating {
        attributes {
            attribute(
                Bundling.BUNDLING_ATTRIBUTE,
                objects.named(Bundling::class.java, Bundling.EXTERNAL)
            )
        }
    }
    dependencies {
        ktlintConfiguration("com.pinterest:ktlint:0.45.2")
    }

    val directoriesWithSource = listOf(
        "**/*.kt",
        "!build/**/*.kt",
        "!*/build/**/*.kt"
    )

    tasks.register("ktlint", JavaExec::class) {
        group = JavaBasePlugin.VERIFICATION_GROUP
        val cliArgs = project.findProject("ktlint_args") as? String
        val ktlintArgs = cliArgs?.split(" ") ?: (listOf(
            "-v",
            "--reporter=plain?group_by_file",
            "--reporter=checkstyle,output=$buildDir/ktlint.xml"
        ) + directoriesWithSource)
        description = "Check Kotlin code style."
        classpath = ktlintConfiguration
        main = "com.pinterest.ktlint.Main"
        args = ktlintArgs
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }

    tasks.register("ktlintFormat", JavaExec::class) {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = ktlintConfiguration
        main = "com.pinterest.ktlint.Main"
        args = listOf("-F") + directoriesWithSource
        jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
    }
}