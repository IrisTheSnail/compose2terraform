package org.example.models.compose

data class Compose(
    val version: String,
    val services: Service,
    val network: Network
)

data class Service(
    val name: String,
    val image: String,
    val networks: List<String>,
    val volumes: List<String>,
    val environment: List<String>
)

enum class NetworkDriverEnum(val value: String) {
    BRIDGE("bridge"),
    HOST("host"),
    OVERLAY("overlay"),
    IPVLAN("ipvlan"),
    MACVLAN("macvlan"),
    NONE("none")
}

data class Network(
    val name: String,
    val driver: NetworkDriverEnum
)