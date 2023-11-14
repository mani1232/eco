group = "com.willfp"
version = rootProject.version

dependencies {
    // Libraries
    implementation("org.reflections:reflections:0.10.2")
    implementation("org.objenesis:objenesis:3.3")

    compileOnly("io.papermc.paper:paper-api:1.20-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.5")
    compileOnly("net.kyori:adventure-text-minimessage:4.14.0")
    compileOnly("net.kyori:adventure-platform-bukkit:4.3.1")
    compileOnly("org.yaml:snakeyaml:2.2")
    compileOnly("com.moandjiezana.toml:toml4j:0.7.2")
}
