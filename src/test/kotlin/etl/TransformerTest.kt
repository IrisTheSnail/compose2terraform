package etl

import org.example.etl.Transformer
import org.example.models.compose.Compose
import org.example.models.compose.Network
import org.example.models.compose.NetworkDriverEnum
import org.example.models.compose.Service
import org.example.models.terraform.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TransformerTest {
    private val input = Compose(
        version = "3",
        services = mapOf(
            "web" to Service(
                image = "nginx",
                networks = listOf("frontend"),
                volumes = listOf("./app:/app"),
                environment = listOf("NGINX_PORT=80"),
            ),
            "api" to Service(
                image = "myapi:latest",
                networks = listOf("frontend", "backend"),
                volumes = listOf("./api:/api"),
                environment = listOf("API_PORT=3000"),
            ),
        ),
        networks = mapOf(
            "frontend" to Network(
                driver = NetworkDriverEnum.BRIDGE
            ),
            "backend" to Network(
                driver = NetworkDriverEnum.BRIDGE
            ),
        )
    )
    private val expectedOutput = Terraform(
        providers = listOf(
            DockerProvider(
                version = "~> 2.13"
            )
        ),
        resources = listOf(
            DockerContainerResource(
                id = "web",
                image = "nginx",
                networks = listOf("frontend"),
                volumes = listOf("./app:/app"),
                environment = listOf("NGINX_PORT=80"),
            ),
            DockerContainerResource(
                id = "api",
                image = "myapi:latest",
                networks = listOf("frontend", "backend"),
                volumes = listOf("./api:/api"),
                environment = listOf("API_PORT=3000"),
            ),
            DockerNetworkResource(
                id = "frontend",
                name = "frontend",
                driver = NetworkDriverEnum.BRIDGE
            ),
            DockerNetworkResource(
                id = "backend",
                name = "backend",
                driver = NetworkDriverEnum.BRIDGE
            ),
        )
    )

    @Test
    fun `should transform`(){
        val got = Transformer.transform(input)
        assertEquals(expectedOutput, got)
    }

}