plugins {
    kotlin("multiplatform") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.11"
}

kotlin {
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
        val jvmTest by getting {
            dependencies {
                implementation("org.jetbrains.compose.ui:ui-test-junit4:1.5.11")
            }
        }
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
