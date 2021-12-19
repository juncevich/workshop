rootProject.name = "microservices_1"

include(":limits-service")
include(":cloud-config-server")
include(":currency-exchange-service")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/release") }
        mavenCentral()
        gradlePluginPortal()
    }
}
