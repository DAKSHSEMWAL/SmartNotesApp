import com.android.build.gradle.LibraryExtension
import com.dakshsemwal.smartnotes.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                // Use 'android-library' for library modules
                apply(libs.findPlugin("android-library").get().get().pluginId)
                apply(libs.findPlugin("kotlin-android").get().get().pluginId)
                apply(libs.findPlugin("ksp").get().get().pluginId)
                apply(libs.findPlugin("hilt").get().get().pluginId)
            }

            // Configure the LibraryExtension for library modules
            extensions.configure<LibraryExtension> {
                flavorDimensions.add("default")
                configureKotlinAndroid(this)

            }
        }
    }
}