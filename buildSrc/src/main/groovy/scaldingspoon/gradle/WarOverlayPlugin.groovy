package scaldingspoon.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.bundling.War

/**
 * Plugin class to support WAR overlay
 */
class WarOverlayPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.plugins.apply(WarPlugin)

        def warOverlay = project.extensions.create(
                'warOverlay',
                WarOverlayPluginExtension
        )

        project.tasks.withType(War).configureEach { War war ->
            war.duplicatesStrategy = DuplicatesStrategy.EXCLUDE

            war.doFirst {
                war.classpath = war.classpath.filter {
                    !it.name.endsWith('.war')
                }

                war.project.configurations.runtimeClasspath.each { file ->
                    if (file.name.endsWith('.war')) {
                        def fileList = war.project.zipTree(file)

                        if (warOverlay.includeWarJars) {
                            war.from fileList
                        } else {
                            war.from fileList.matching {
                                exclude '**/*.jar'
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Extension to configure WAR overlay specific parameters.
 */
class WarOverlayPluginExtension {

    boolean includeWarJars = false

    void warOverlay(Closure closure) {
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

    def methodMissing(String name, args) {
        if (args && args.length > 0) {
            this."${name}" = args[0]
        }
    }
}