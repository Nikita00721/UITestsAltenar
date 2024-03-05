plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.18.1")

    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.qameta.allure:allure-junit5:2.24.0")
    testImplementation("io.qameta.allure:allure-commandline:2.24.0")
    testImplementation("io.qameta.allure:allure-assertj:2.24.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.24.0")
    testImplementation("io.qameta.allure:allure-java-commons:2.24.0")
    testImplementation("org.aspectj:aspectjweaver:1.9.21")

}

tasks.test {
    useJUnitPlatform()
}