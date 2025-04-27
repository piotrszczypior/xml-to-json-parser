package org.pwr.parserserver.service

import org.pwr.parserservice.ParseRequest
import org.pwr.parserservice.ParseResponse
import org.pwr.parserservice.XmlToJsonParserServiceGrpcKt


class ParserService : XmlToJsonParserServiceGrpcKt.XmlToJsonParserServiceCoroutineImplBase() {

    override suspend fun parse(request: ParseRequest): ParseResponse {
        return ParseResponse.newBuilder()
            .setJson("JSON")
            .build()
    }
}