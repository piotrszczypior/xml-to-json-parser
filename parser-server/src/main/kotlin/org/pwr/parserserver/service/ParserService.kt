package org.pwr.parserserver.service

import org.pwr.XmlToJsonConverter
import org.pwr.parserservice.ParseRequest
import org.pwr.parserservice.ParseResponse
import org.pwr.parserservice.XmlToJsonParserServiceGrpcKt


class ParserService(private var converter: XmlToJsonConverter) :
    XmlToJsonParserServiceGrpcKt.XmlToJsonParserServiceCoroutineImplBase() {

    override suspend fun parse(request: ParseRequest): ParseResponse {
        val json = converter.convert(request.xml)
        return ParseResponse.newBuilder()
            .setJson(json)
            .build()
    }
}