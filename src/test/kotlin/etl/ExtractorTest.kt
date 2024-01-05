package etl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.example.etl.Extractor
import org.example.models.compose.Compose
import org.example.models.compose.Network
import org.example.models.compose.NetworkDriverEnum
import org.example.models.compose.Service
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import kotlin.test.assertEquals


class ExtractorTest {
    private val dockerCompose = """
        version: '3'
        services:
          web:
            image: nginx
            networks:
              - frontend
            volumes:
              - ./app:/app
            environment:
              - NGINX_PORT=80
        
          api:
            image: myapi:latest
            networks:
              - frontend
              - backend
            volumes:
              - ./api:/api
            environment:
              - API_PORT=3000
        
        networks:
          frontend:
            driver: bridge
        
          backend:
            driver: bridge
    """

    private val expectedOutput = Compose(
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

    @Test
    fun `Should extract`(){
        val inputStream: InputStream = ByteArrayInputStream(dockerCompose.toByteArray(StandardCharsets.UTF_8))
        val objectMapper = ObjectMapper(YAMLFactory())

        val got: Compose = Extractor.extract(inputStream, objectMapper)
        assertEquals(expectedOutput, got)
    }
}