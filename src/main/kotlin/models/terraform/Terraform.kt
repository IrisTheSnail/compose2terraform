package org.example.models.terraform

data class Terraform(
    val provider: Provider,
    val resource: Resource
)

sealed class Provider {
    abstract val id: String
}

sealed class Resource{
    abstract val id: String
};

data class DockerContainerResource(
    override val id: String,
    val image: String,
    val networks: List<String>,
    val volumes: List<String>,
    val environment: List<String>,
): Provider()

enum class NetworkDriverEnum(val value: String) {
    BRIDGE("bridge"),
    HOST("host"),
    OVERLAY("overlay"),
    IPVLAN("ipvlan"),
    MACVLAN("macvlan"),
    NONE("none")
}

data class DockerNetworkResource(
    override val id: String,
    val name: String,
    val driver: org.example.models.compose.NetworkDriverEnum
): Resource()
