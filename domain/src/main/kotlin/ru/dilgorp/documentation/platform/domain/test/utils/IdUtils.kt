package ru.dilgorp.documentation.platform.domain.test.utils

import java.util.concurrent.ThreadLocalRandom

fun randomId() = ThreadLocalRandom.current().nextLong()

fun Long.diffFromRandom(): Long = if (this > 0) this - 1 else this + 1