import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.openapi.generator") version "6.0.0"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.security:spring-security-test")
	implementation("com.ibm.mq:mq-jms-spring-boot-starter:2.6.7")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName.set("kotlin")
	inputSpec.set("$rootDir/src/main/resources/api-docs.yaml")
	outputDir.set("$buildDir/generated")
	apiPackage.set("com.pluralsight.conferencedemo.api")
	invokerPackage.set("com.pluralsight.conferencedemo.invoker")
	modelPackage.set("com.pluralsight.conferencedemo.model")
}

val jar by tasks.getting(Jar::class) {
	manifest {
		attributes["Main-Class"] = "com.pluralsight.conferencedemo.ConferenceDemoApplication"
	}
}

springBoot {
	mainClass.set("com.pluralsight.conferencedemo.ConferenceDemoApplicationKt")
}

// to get api documentation open this: http://localhost:8080/swagger-ui/index.html
// to get json open this: http://localhost:8080/v3/api-docs
// to get yaml open this: http://localhost:8080/v3/api-docs.yaml
// to generate classes from yaml file add openApiGenerate in build.gradle and run gradle openApiGenerate