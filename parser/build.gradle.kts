var antlr4Version = "4.13.2"

plugins {
    id("java")
    antlr
}

group = "org.pwr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    antlr("org.antlr:antlr4:$antlr4Version")
}

tasks.test {
    useJUnitPlatform()
}