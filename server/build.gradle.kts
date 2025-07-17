plugins{
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
}

application{
    mainClass = "io.ktor.server.cio.EngineMain"
}

kotlin{
    jvmToolchain(17)
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies{
    implementation(libs.ktor.server.negotiation)
    implementation(libs.ktor.json)
    implementation(libs.ktor.logging)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.yaml)
    implementation("io.ktor:ktor-serialization-gson:3.2.2")
    testImplementation(libs.ktor.server.test)
}