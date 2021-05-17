package com.yesfranchise.excel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude= [DataSourceAutoConfiguration::class])
class ExcelApplication

fun main(args: Array<String>) {
	runApplication<ExcelApplication>(*args)
}
