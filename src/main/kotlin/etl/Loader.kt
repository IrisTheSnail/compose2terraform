package org.example.etl

import org.example.models.terraform.Terraform
import java.io.OutputStream

object Loader {
    fun load(terraform: Terraform, outputStream: OutputStream){
        val writer = outputStream.bufferedWriter()
        terraform.providers.forEach {
            writer.write(it.serialize());
            writer.newLine()
        }
        terraform.resources.forEach {
            writer.write(it.serialize());
            writer.newLine()
        }
        writer.flush()
    }
}