package org.pwr.parserserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ParserServerApplication

fun main(args: Array<String>) {
    runApplication<ParserServerApplication>(*args)
}