plugins {
    kotlin("multiplatform") version "1.9.10"
    id("org.jetbrains.compose") version "1.6.10"
    id("com.android.application") version "8.10.1"
}

kotlin {
    androidTarget()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.kotest:kotest-framework-engine:5.8.1")
                implementation("io.kotest:kotest-framework-api:5.8.1")
                implementation("io.kotest:kotest-assertions-core:5.8.1")
            }
        }
        val jvmMain by getting
        val androidMain by getting {
            dependencies {
                val composeBom = platform("androidx.compose:compose-bom:2025.05.00")
                implementation(composeBom)

                implementation("com.quickbirdstudios:opencv:4.5.3.0")
                implementation("androidx.compose.ui:ui:1.8.2")
                implementation("androidx.preference:preference:1.2.1")
                implementation("androidx.compose.foundation:foundation")
                implementation("androidx.activity:activity-compose:1.10.1")
                implementation("androidx.lifecycle:lifecycle-service")
                implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.1")
                // Android Studio Preview support
                implementation ("androidx.compose.ui:ui-tooling-preview")
                implementation ("androidx.compose.material3:material3")
                implementation("androidx.core:core-ktx:1.16.0")
                implementation("androidx.camera:camera-core:1.4.2")
                implementation("androidx.camera:camera-camera2:1.4.2")
                implementation("androidx.camera:camera-lifecycle:1.4.2")
                implementation("androidx.camera:camera-view:1.4.2")
                implementation("androidx.camera:camera-video:1.4.2")
                implementation("io.coil-kt:coil-compose:2.6.0")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.compose.ui:ui-test-junit4:1.5.11")
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation("androidx.test.ext:junit:1.2.1")
                implementation("androidx.test.espresso:espresso-core:3.6.1")
                implementation("androidx.compose.ui:ui-test-junit4:1.8.2")
                implementation("androidx.compose.ui:ui-test-junit4")
                implementation("io.cucumber:cucumber-android:8.14.1")
                implementation("io.cucumber:cucumber-picocontainer:8.14.1")
            }
        }

    }
}

android {
    namespace = "com.example.dashcam"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dashcam"
        minSdk = 26
        targetSdk = 34
        testInstrumentationRunner = "com.example.dashcam.CucumberTestRunner"
        testInstrumentationRunnerArgument("cucumberOptions", "--plugin pretty")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.desktop {
    application {
        mainClass = "com.example.dashcam.MainKt"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17"
}

dependencies {
    debugImplementation ("androidx.compose.ui:ui-tooling")
    debugImplementation ("androidx.compose.ui:ui-test-manifest")
}