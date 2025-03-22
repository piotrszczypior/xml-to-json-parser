var antlr4Version = "4.13.2"
var googleGuice = "7.0.0"

plugins {
    id("java")
    antlr
}

group = "org.pwr.parser"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    antlr("org.antlr:antlr4:$antlr4Version")

    implementation("com.google.inject:guice:$googleGuice")
}

tasks.generateGrammarSource {
    source = fileTree("src/main/antlr")
    outputDirectory = file("src/main/gen")
    arguments = listOf("-visitor", "-no-listener")
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src/main/gen", "src/main/java"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}