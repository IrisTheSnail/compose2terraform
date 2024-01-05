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

interface Serializable {
    fun serialize(): String;
}

sealed class Provider: Serializable {
}

sealed class Resource: Serializable{
    abstract val id: String
}

data class DockerProvider (
    val version: String
): Provider() {
    override fun serialize(): String {
        return """
            provider "docker" {
                version = "$version"
            }
        """.trimIndent()
    }
}

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
): Resource()  {
    override fun serialize(): String {
        val nets = networks?.joinToString(", ") { "\"$it\"" } ?: ""
        val vols = volumes?.joinToString(", ") { "\"$it\"" } ?: ""
        val envs = environment?.joinToString(", ") { "\"$it\"" } ?: ""

        return """
            resource "docker_container" "$id" {
                image      = "$image"
                ${
                    if(networks != null) {
                        "networks    = [$nets]"
                    } else ""
                }
                ${
                    if(volumes != null) {
                        "volumes     = [$vols]"
                    } else ""
                }
                ${
                    if(volumes != null) {
                        "environment = [$envs]"
                    } else ""
                }
            }
        """.trimIndent()
    }
}

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
): Resource() {
    override fun serialize(): String {
        return """
            resource "docker_network" "$id" {
                name   = "$name"
                driver = "${driver.value}"
            }
        """.trimIndent()
    }
}