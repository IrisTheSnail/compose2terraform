package org.example.etl

import org.example.models.compose.Compose
import org.example.models.compose.NetworkDriverEnum
import org.example.models.terraform.*

object Transformer {
    fun transform(source: Compose): Terraform {
        return Terraform(
            providers = listOf(
                DockerProvider(
                    version = "~> 2.13"
                )
            ),
            resources = source.services.map {
                DockerContainerResource(
                    id = it.key,
                    image = it.value.image,
                    networks = it.value.networks,
                    volumes = it.value.volumes,
                    environment = it.value.environment
                )
            } + source.networks?.map {
                DockerNetworkResource(
                    id = it.key,
                    name = it.key,
                    driver = it.value.driver ?: NetworkDriverEnum.BRIDGE
                )
            } as List<Resource>
        )
    }
}