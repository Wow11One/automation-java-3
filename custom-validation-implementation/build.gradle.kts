import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

plugins {
    id("java")
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
    implementation(project(":custom-validation-interface"))
    implementation(project(":string-validation-utils"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
}

buildscript {
    dependencies {
        classpath("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("checkConfigFile") {
    doFirst{
        if(!file("./src/main/resources/validation-config.json").exists()) {
            throw GradleException("'resources/validation-config.json' file is not found!")
        }
    }
}

tasks.register("checkConfigFileContent") {
    doFirst {
        val filePath = "./src/main/resources/validation-config.json"
        val file = file(filePath)
        val mapper = jacksonObjectMapper()
        val resultMap = mapper.readValue(file, object : TypeReference<HashMap<String, Any>>() {})
        if (!resultMap.containsKey("nameMaxSize")) {
            resultMap["nameMaxSize"] = 12
            mapper.writeValue(file, resultMap)
        }
    }
    dependsOn("checkConfigFile")
}

tasks.named("checkConfigFile") {
    finalizedBy("checkConfigFileContent")
}

tasks.named("assemble") {
    finalizedBy("checkConfigFile")
}