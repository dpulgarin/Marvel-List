package com.dpulgarin.marvellist.core.extensions

fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }