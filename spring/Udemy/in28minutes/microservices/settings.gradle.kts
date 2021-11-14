rootProject.name = "microservices_1"

include(":limits-service")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/release") }
        mavenCentral()
        gradlePluginPortal()
    }
}
