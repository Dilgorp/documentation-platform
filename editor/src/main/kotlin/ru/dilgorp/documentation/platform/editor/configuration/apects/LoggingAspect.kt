package ru.dilgorp.documentation.platform.editor.configuration.apects

import mu.KLogging
import mu.NamedKLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.CodeSignature
import org.aspectj.lang.reflect.MethodSignature

@Suppress("unused")
@Aspect
class LoggingAspect {
    companion object : KLogging() {
        private const val ERROR_VALUE = "ERROR_PRINT_VALUE"
        private const val ERROR_PARSING_VALUE = "ERROR_PARSING_ARGS"
        private const val TRUNCATE_LENGTH = 2000
    }

    @Pointcut("execution(* ru.dilgorp.documentation.platform.editor.domain.services..*(..))")
    fun serviceMark() {
    }

    @Pointcut("execution(* ru.dilgorp.documentation.platform.editor.rest.controllers..*(..))")
    fun controllersMark() {
    }

    @Pointcut("execution(* ru.dilgorp.documentation.platform.editor.persistence.repositories..*(..))")
    fun repositoriesMark() {
    }

    @Around("serviceMark() || controllersMark() || repositoriesMark()")
    fun log(joinPoint: ProceedingJoinPoint): Any? {
        val objectLogger = NamedKLogging(joinPoint.signature.declaringTypeName).logger
        val args = joinPoint.args
        val argTypes = (joinPoint.signature as CodeSignature).parameterTypes
        val argNames = (joinPoint.signature as CodeSignature).parameterNames
        val returnType = (joinPoint.signature as MethodSignature).returnType
        val methodName = joinPoint.signature.name

        val params = try {
            args.mapIndexed { index, value ->
                try {
                    val argValue = valueToString(argTypes[index], value)
                    "${argNames[index]}=$argValue"
                } catch (e: Exception) {
                    logger.error(e) { "Unexpected exception occurred: cannot parsing params name=${argNames[index]}, type=${argTypes[index]}" }
                }
            }.toTypedArray().joinToString()
        } catch (e: java.lang.Exception) {
            logger.error(e) { "Unexpected exception occurred: cannot parsing params for method=$methodName" }
            ERROR_PARSING_VALUE
        }

        val methodSignatureString = "$methodName(${truncate(params)})"
        objectLogger.info { "Start $methodSignatureString" }

        val result: Any? = joinPoint.proceed()
        val resultString = try {
            valueToString(returnType, result)
        } catch (e: Exception) {
            logger.error(e) { "Unexpected exception occurred: cannot parsing result" }
            ERROR_VALUE
        }

        objectLogger.info { "Finish $methodSignatureString return ${truncate(resultString)}" }

        return result
    }

    private fun valueToString(type: Class<Any>, value: Any?): String = try {
        "${type.cast(value)}"
    } catch (e: Exception) {
        try {
            "$value"
        } catch (e: Exception) {
            ERROR_VALUE
        }
    }

    private fun truncate(str: String): String =
        if (str.length > TRUNCATE_LENGTH) {
            str.substring(0, TRUNCATE_LENGTH)
        } else {
            str
        }
}