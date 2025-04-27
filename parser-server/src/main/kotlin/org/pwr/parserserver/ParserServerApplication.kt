package org.pwr.parserserver

import org.pwr.parserserver.configuration.GrpcServerConfiguration
import org.pwr.parserserver.service.ParserService


fun main() {
    // Add some DI
    val parserService = ParserService()
    val grpcServerConfiguration = GrpcServerConfiguration(parserService)

    Runtime.getRuntime().addShutdownHook(Thread {
        grpcServerConfiguration.onShutdown()
    })

    grpcServerConfiguration.grpcServer()
}