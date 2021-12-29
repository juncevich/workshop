rootProject.name = "microservices_1"

include(":limits-service")
include(":cloud-config-server")
include(":currency-exchange-service")
include(":currency-conversion-service")
include(":naming-server")
include(":api-gateway")

pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/release") }
        mavenCentral()
        gradlePluginPortal()
    }
}
