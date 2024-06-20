plugins {
    id("java-library")
}

group = "com.ukma"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    api("org.apache.commons:commons-lang3:3.0")
}

tasks.test {
    useJUnitPlatform()
}