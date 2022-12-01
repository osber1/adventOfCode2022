package util

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import java.nio.file.Files.lines
import java.nio.file.Path.of
import java.util.stream.Stream

private val httpClient = HttpClient(Apache)

fun readInput(day: Int): Stream<String> = lines(of("src/main/resources/day$day.txt"))

fun loadInput(day: Int): List<String> =
    runBlocking {
        httpClient.get("https://adventofcode.com/2022/day/$day/input") {
            header("Cookie", "Cookie")
        }.body<String>()
    }.split('\n')