package org.pwr.parserserver.service

import io.grpc.stub.StreamObserver
import org.pwr.parserservice.ParseRequest
import org.pwr.parserservice.ParseResponse
import org.pwr.parserservice.XmlToJsonParserServiceGrpc
import org.pwr.parserservice.XmlToJsonParserServiceGrpcKt
import org.springframework.stereotype.Service


@Service
class ParserService : XmlToJsonParserServiceGrpcKt.XmlToJsonParserServiceCoroutineImplBase() {

    override suspend fun parse(request: ParseRequest): ParseResponse {
        return ParseResponse.newBuilder()
            .setJson("JSON")
            .build()
    }
}