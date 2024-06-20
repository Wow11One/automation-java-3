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
    testImplementation("org.assertj:assertj-core:3.26.0")
    api("org.apache.commons:commons-lang3:3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("checkConfigFile") {
    doFirst{
        assert(file("./src/main/resources/validation-config.json").exists())
    }
}