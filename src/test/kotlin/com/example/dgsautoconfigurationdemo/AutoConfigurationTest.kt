package com.example.dgsautoconfigurationdemo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertEquals

/**
 * Test to demonstrate change of autoconfiguration exclusion behaviour.
 *
 * See build.gradle.kts for DGS versions.
 */
@SpringBootTest
@TestPropertySource(
	properties = [
		"spring.autoconfigure.exclude=example.OverriddenAutoConfigurationExclusionInTest",
	],
)
internal class AutoConfigurationTest @Autowired constructor(
	 private val applicationContext: ApplicationContext,
){

	@Test
	fun `spring-autoconfigure-exclude should contain only some-overridden-excluded-AutoConfiguration`() {
		val excludeValue = applicationContext.environment.getProperty("spring.autoconfigure.exclude")
		assertEquals("example.OverriddenAutoConfigurationExclusionInTest", excludeValue)
	}

}
