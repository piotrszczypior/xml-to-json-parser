package org.pwr.parserserver

import org.pwr.XmlToJsonConverterImpl
import org.pwr.parserserver.configuration.GrpcServerConfiguration
import org.pwr.parserserver.service.ParserService
import org.pwr.processor.JsonProcessorImpl


fun main() {
    // TODO: Add some DI
    val processor = JsonProcessorImpl()
    val converter = XmlToJsonConverterImpl(processor)
    val parserService = ParserService(converter)
    val grpcServerConfiguration = GrpcServerConfiguration(parserService)

    Runtime.getRuntime().addShutdownHook(Thread {
        grpcServerConfiguration.onShutdown()
    })

    grpcServerConfiguration.grpcServer()
}