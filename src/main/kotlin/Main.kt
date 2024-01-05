package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.example.etl.ETL
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if(args.size != 2){
        println("Usage: compose2terraform input_path output_path");
        exitProcess(1);
    }

    val input: FileInputStream
    val output: FileOutputStream

    try {
        input = FileInputStream(args[0]);
    } catch (ex: FileNotFoundException) {
        println("Couldn't find input file: ${args[0]}");
        exitProcess(1);
    }

    try {
        output = FileOutputStream(args[1]);
    } catch (ex: FileNotFoundException) {
        println("Couldn't find output file: ${args[1]}");
        exitProcess(1);
    }

    ETL.convert(input, output, ObjectMapper(YAMLFactory()))
}