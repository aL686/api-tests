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

    implementation("io.qameta.allure:allure-junit5:2.12.1")
    implementation("io.qameta.allure:allure-commandline:2.12.1")
    implementation("io.qameta.allure:allure-assertj:2.12.1")
    implementation("io.qameta.allure:allure-rest-assured:2.12.1")
    implementation("io.qameta.allure:allure-java-commons:2.12.1")
    implementation("io.rest-assured:rest-assured:5.5.0")
    compileOnly("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}