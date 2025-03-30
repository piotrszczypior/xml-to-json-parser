package org.pwr.parserserver.configuration

import io.grpc.Server
import io.grpc.ServerBuilder
import org.pwr.parserserver.service.ParserService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.PreDestroy

@Configuration
open class GrpcServerConfiguration(private val parserService: ParserService) {

    private val log = LoggerFactory.getLogger(GrpcServerConfiguration::class.java)

    private val server by lazy {
        log.debug("Initializing GrpcServer on port 8090")
        ServerBuilder.forPort(8090)
            .addService(parserService)
            .build()
    }

    @Bean(initMethod = "start")
    open fun grpcServer(): Server = server

    @PreDestroy
    open fun onShutdown() {
        log.info("Shutting down GRPC server on port 8090")
        server.shutdown()
    }
}