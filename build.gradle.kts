plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
//	id("org.springframework.boot") version "3.2.10" // in SB versions 3.2 it works
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

//extra["netflixDgsVersion"] = "8.5.3" // this works, see AutoConfigurationTest
extra["netflixDgsVersion"] = "8.5.4" // this does not work, see AutoConfigurationTest
//extra["netflixDgsVersion"] = "9.1.2" // this does not work, see AutoConfigurationTest

dependencies {
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")

// this works with all versions of DGS because ExcludeAutoConfigurationsEnvironmentPostProcessor is not present
//	implementation("org.springframework.boot:spring-boot-starter")
//	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")



	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
		mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${property("netflixDgsVersion")}")
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
