package org.pwr.parserserver.configuration

import io.grpc.ServerBuilder
import org.pwr.parserserver.service.ParserService
import org.slf4j.LoggerFactory

class GrpcServerConfiguration(private val parserService: ParserService) {

    private val log = LoggerFactory.getLogger(GrpcServerConfiguration::class.java)

    private val server by lazy {
        log.debug("Initializing GrpcServer on port 8090")
        ServerBuilder.forPort(8080)
            .addService(parserService)
            .build()
    }

    fun grpcServer(): Unit = start()

    fun onShutdown() {
        log.debug("Shutting down GRPC server on port 8090")
        server.shutdown()
    }

    private fun start() {
        server.start()
        server.awaitTermination()
    }
}