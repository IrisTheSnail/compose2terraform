package org.example.models.terraform

import lombok.*

@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
data class Terraform(
    val providers: List<Provider>,
    val resources: List<Resource>
)

sealed class Provider {
}

sealed class Resource{
    abstract val id: String
};

data class DockerProvider (
    val version: String
): Provider();

@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
data class DockerContainerResource(
    override val id: String,
    val image: String,
    val networks: List<String>?,
    val volumes: List<String>?,
    val environment: List<String>?,
): Resource()

enum class NetworkDriverEnum(val value: String) {
    BRIDGE("bridge"),
    HOST("host"),
    OVERLAY("overlay"),
    IPVLAN("ipvlan"),
    MACVLAN("macvlan"),
    NONE("none")
}

@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
data class DockerNetworkResource(
    override val id: String,
    val name: String,
    val driver: org.example.models.compose.NetworkDriverEnum
): Resource()
