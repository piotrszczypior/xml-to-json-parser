var antlr4Version = "4.13.2"
var googleGuiceVersion = "7.0.0"
var jsonVersionVersion = "20250107";


var grammarPackage = "org.pwr.grammar"

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

    implementation("com.google.inject:guice:$googleGuiceVersion")
    implementation("org.json:json:$jsonVersionVersion")
}

tasks.generateGrammarSource {
    source = fileTree("src/main/antlr")
    outputDirectory = file("src/main/gen/$grammarPackage")
    arguments = listOf("-visitor", "-no-listener", "-package", grammarPackage)
}

tasks.compileJava {
    dependsOn(tasks.generateGrammarSource)
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

tasks.build {
    dependsOn(tasks.generateGrammarSource)
}
