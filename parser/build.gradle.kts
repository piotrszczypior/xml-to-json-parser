var antlr4Version = "4.13.2"
var googleGuiceVersion = "7.0.0"
var jsonVersionVersion = "20250107"
var lombokVersion = "1.18.36"
var stringTemplatesVersion = "4.3.4"


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
    // antlr
    antlr("org.antlr:antlr4:$antlr4Version")

    // string templates
    implementation("org.antlr:ST4:$stringTemplatesVersion")

    // guice
    implementation("com.google.inject:guice:$googleGuiceVersion")

    // lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
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
