package etl

import org.example.etl.Loader
import org.example.models.compose.NetworkDriverEnum
import org.example.models.terraform.DockerContainerResource
import org.example.models.terraform.DockerNetworkResource
import org.example.models.terraform.DockerProvider
import org.example.models.terraform.Terraform
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.StringWriter
import java.nio.charset.StandardCharsets
import kotlin.test.assertEquals


class LoaderTest {
    private val input = Terraform(
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
    fun `should load`(){
        val out = ByteArrayOutputStream()
        Loader.load(input, out)
        val got = out.toString(StandardCharsets.UTF_8)

        assertEquals(got, """
            provider "docker" {
                version = "~> 2.13"
            }
            resource "docker_container" "web" {
                image      = "nginx"
                networks    = ["frontend"]
                volumes     = ["./app:/app"]
                environment = ["NGINX_PORT=80"]
            }
            resource "docker_container" "api" {
                image      = "myapi:latest"
                networks    = ["frontend", "backend"]
                volumes     = ["./api:/api"]
                environment = ["API_PORT=3000"]
            }
            resource "docker_network" "frontend" {
                name   = "frontend"
                driver = "bridge"
            }
            resource "docker_network" "backend" {
                name   = "backend"
                driver = "bridge"
            }
            
            """.trimIndent())
    }
}