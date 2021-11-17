rootProject.name = "microservices_1"

include(":limits-service")
include(":cloud-config-server")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/release") }
        mavenCentral()
        gradlePluginPortal()
    }
}
