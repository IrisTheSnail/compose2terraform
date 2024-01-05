package org.example.etl

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.models.compose.Compose
import java.io.InputStream

object Extractor {
    fun extract(inputStream: InputStream, objectMapper: ObjectMapper): Compose {
        return objectMapper.readValue(inputStream, Compose::class.java)
    }
}