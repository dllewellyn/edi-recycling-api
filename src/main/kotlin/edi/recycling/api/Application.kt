package edi.recycling.api

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("edi.recycling.api")
                .mainClass(Application.javaClass)
                .start()
    }
}