/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package com.ryandens.javaagent

import com.google.cloud.tools.jib.gradle.JibExtension
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * A simple unit test for the 'com.ryandens.javaagent.attach' plugin.
 */
class JavaagentJibPluginTest {
    @Test fun `plugin registers task`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("application")
        project.plugins.apply("com.google.cloud.tools.jib")
        project.plugins.apply("com.ryandens.javaagent-jib")

        // Verify the result
        assertNotNull(project.configurations.findByName("javaagent"))

        val ext = project.extensions.getByType(JibExtension::class.java)
        assertTrue(ext.pluginExtensions.get().any { it.implementation == "com.ryandens.javaagent.JavaagentJibExtension" })
    }

    @Test fun `plugin adds layer to build plan`() {
        val project = ProjectBuilder.builder().build()
        project.plugins.apply("application")
        project.plugins.apply("com.google.cloud.tools.jib")
        project.plugins.apply("com.ryandens.javaagent-jib")
        project.dependencies.add("javaagent", ":simple-agent")

        val ext = project.extensions.getByType(JibExtension::class.java)
        assertTrue(ext.pluginExtensions.get().any { it.implementation == "com.ryandens.javaagent.JavaagentJibExtension" })
    }
}
