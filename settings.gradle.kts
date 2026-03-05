rootProject.name = "portfolio_ai_app"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers")
    }
}

includeBuild("../type_safe_shared") {
    dependencySubstitution {
        substitute(module("com.quadrigasoftware.shared:server_core")).using(project(":server_core"))
    }
}

include(":server")
include(":web")
