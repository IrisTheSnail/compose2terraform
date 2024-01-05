package org.example.etl

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream
import java.io.OutputStream

object ETL {
    fun convert(input: InputStream, output: OutputStream, objectMapper: ObjectMapper){
        val compose = Extractor.extract(input, objectMapper)
        val terraform = Transformer.transform(compose)
        Loader.load(terraform, output)
    }
}