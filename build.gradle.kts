import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.0.RC1"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    war
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    kotlin("plugin.jpa") version "1.3.50"
}

group = "iv.root"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("mysql:mysql-connector-java")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    // Lombok
    implementation("org.projectlombok:lombok:1.18.10")
    // Inject
    implementation("javax.inject:javax.inject:1")
    // NonNull
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    // Security
    implementation("org.springframework.security:spring-security-core:5.2.0.RELEASE")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
