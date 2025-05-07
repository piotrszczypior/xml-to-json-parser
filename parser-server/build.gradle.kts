plugins {
    java
    kotlin("jvm") version "2.1.10"
    kotlin("kapt") version "2.1.10"
    id("com.google.protobuf") version "0.9.4"
    id("io.ktor.plugin") version "3.1.3"
    application
}

group = "org.pwr.parserserver"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val protobufVersion = "4.30.2"
var grpcVersion = "1.63.0"
val grpcKotlinVersion = "1.4.1"

dependencies {
    // grpc
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protobufVersion")

    // kotlin coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Kotlin Standard Library
    implementation(kotlin("stdlib"))

    // logger
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("ch.qos.logback:logback-classic:1.4.14")

    // parser
    implementation("org.pwr:parser:1.0.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobufVersion"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpcVersion"
        }
        create("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpcKotlinVersion:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}

kapt {
    showProcessorStats = true
}

application {
    mainClass.set("org.pwr.parserserver.ParserServerApplicationKt")
}