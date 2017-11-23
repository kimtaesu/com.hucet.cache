package com.hucet.cache.signature

/**
 * Created by taesu on 2017-11-23.
 */
interface Key {
    override fun equals(o: Any?): Boolean
    override fun hashCode(): Int
}