package org.example.models.compose

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.Setter

@AllArgsConstructor
@Getter
@Setter
@Data
data class Compose(
    @JsonProperty("version")
    val version: String,
    @JsonProperty("services")
    val services: Map<String, Service>,
    @JsonProperty("networks")
    val networks: Map<String, Network>? = null
) {

}

@AllArgsConstructor
@Getter
@Setter
@Data
data class Service(
    @JsonProperty("image")
    val image: String,
    @JsonProperty("networks")
    val networks: List<String>? = null,
    @JsonProperty("volumes")
    val volumes: List<String>? = null,
    @JsonProperty("environment")
    val environment: List<String>? = null
)

@AllArgsConstructor
@Getter
@Setter
@Data
enum class NetworkDriverEnum(@JsonValue val value: String) {
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
data class Network(
    @JsonProperty("driver")
    val driver: NetworkDriverEnum? = NetworkDriverEnum.BRIDGE
)
