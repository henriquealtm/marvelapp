package com.example.commons.extension

/**
 * Handle empty String
 * @return itself if not null or an empty String
 */
fun String?.handleOpt() = this ?: ""

/**
 * Handle empty Int
 * @return itself if not null or zero
 */
fun Int?.handleOpt() = this ?: 0

/**
 * Handle empty Boolean
 * @return itself if not null or false
 */
fun Boolean?.handleOpt() = this ?: false

/**
 * Handle empty list
 * @return itself if not null or an empty list
 */
fun <T> List<T>?.handleOpt() = this ?: listOf()