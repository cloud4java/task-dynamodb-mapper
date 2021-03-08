package com.silva.dynamo

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.silva.dynamo")
		.start()
}

