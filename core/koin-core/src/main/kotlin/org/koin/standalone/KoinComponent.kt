package org.koin.standalone

import org.koin.core.KoinContext
import org.koin.core.parameter.ParameterDefinition
import org.koin.core.parameter.emptyParameterDefinition

/**
 * Koin component
 */
interface KoinComponent

/**
 * inject lazily given dependency for KoinComponent
 * @param name - bean name
 * @param parameters - injection parameters
 */
inline fun <reified T> KoinComponent.inject(
    name: String = "",
    module: String? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
) = lazy { (StandAloneContext.koinContext as KoinContext).get<T>(name, module, parameters) }

/**
 * Retrieve given dependency for KoinComponent
 * @param name - bean name
 * @param parameters - injection parameters
 */
inline fun <reified T> KoinComponent.get(
    name: String = "",
    module: String? = null,
    noinline parameters: ParameterDefinition = emptyParameterDefinition()
): T =
    (StandAloneContext.koinContext as KoinContext).get(name, module, parameters)

/**
 * inject lazily given property for KoinComponent
 * @param key - key property
 * throw MissingPropertyException if property is not found
 */
inline fun <reified T> KoinComponent.property(key: String): Lazy<T> =
    kotlin.lazy { (StandAloneContext.koinContext as KoinContext).getProperty<T>(key) }

/**
 * inject lazily given property for KoinComponent
 * give a default value if property is missing
 *
 * @param key - key property
 * @param defaultValue - default value if property is missing
 *
 */
inline fun <reified T> KoinComponent.property(key: String, defaultValue: T): Lazy<T> =
    kotlin.lazy { (StandAloneContext.koinContext as KoinContext).getProperty(key, defaultValue) }

/**
 * Retrieve given property for KoinComponent
 * @param key - key property
 * throw MissingPropertyException if property is not found
 */
inline fun <reified T> KoinComponent.getProperty(key: String): T =
    (StandAloneContext.koinContext as KoinContext).getProperty(key)

/**
 * Retrieve given property for KoinComponent
 * give a default value if property is missing
 *
 * @param key - key property
 * @param defaultValue - default value if property is missing
 *
 */
inline fun <reified T> KoinComponent.getProperty(key: String, defaultValue: T): T =
    (StandAloneContext.koinContext as KoinContext).getProperty(key, defaultValue)

/**
 * set a property
 * @param key
 * @param value
 */
fun KoinComponent.setProperty(key: String, value: Any) = getKoinContext().setProperty(key, value)

/**
 * Release instances at given module scope
 * @param path
 */
fun KoinComponent.release(path: String) = getKoinContext().release(path)


/**
 * Help to Access context
 */
private fun getKoinContext() = (StandAloneContext.koinContext as KoinContext)


