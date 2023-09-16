plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation("org.junit.jupiter:junit-jupiter")
    implementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.1.2")
    implementation("io.github.bonigarcia:webdrivermanager:5.1.0")
    implementation("com.codeborne:selenide:6.3.5")

    implementation("org.aspectj:aspectjweaver:1.9.5")
    implementation("io.qameta.allure:allure-junit5:2.12.1")
    implementation("io.qameta.allure:allure-commandline:2.12.1")
    implementation("io.qameta.allure:allure-assertj:2.12.1")
    implementation("io.qameta.allure:allure-rest-assured:2.12.1")
    implementation("io.qameta.allure:allure-java-commons:2.12.1")
    implementation("io.qameta.allure:allure-selenide:2.12.1")
}

tasks.test {
    useJUnitPlatform()
}