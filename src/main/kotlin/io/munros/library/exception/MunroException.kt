package io.munros.library.exception

import java.lang.Exception

class MunroException constructor(override val message: String?) : Exception()