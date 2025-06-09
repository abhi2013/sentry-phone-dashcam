plugins {
    kotlin("multiplatform") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.11"
    id("com.android.library") version "8.2.0"
}

kotlin {
    android()
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
                implementation("org.opencv:opencv-android:4.9.0")
                implementation("androidx.core:core-ktx:1.12.0")
                implementation("androidx.camera:camera-core:1.3.2")
                implementation("androidx.camera:camera-camera2:1.3.2")
                implementation("androidx.camera:camera-lifecycle:1.3.2")
                implementation("androidx.camera:camera-view:1.3.2")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.compose.ui:ui-test-junit4:1.5.11")
            }
        }
    }
}

android {
    namespace = "com.example.dashcam"
    compileSdk = 33

    defaultConfig {
        minSdk = 26
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
