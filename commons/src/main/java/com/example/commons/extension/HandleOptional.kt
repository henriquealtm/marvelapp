package com.example.commons

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
