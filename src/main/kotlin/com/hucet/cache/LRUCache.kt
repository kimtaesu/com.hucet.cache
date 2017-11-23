package com.hucet.cache

import java.util.LinkedHashMap

/**
 * Created by taesu on 2017-11-23.
 */
class LRUCache<K, V>(initialCapacity: Int,
                     loadFactor: Float,
                     private val maxEntries: Int) : LinkedHashMap<K, V>(initialCapacity, loadFactor, true) {

    constructor(initialCapacity: Int,
                maxEntries: Int) : this(initialCapacity, DEFAULT_LOAD_FACTOR, maxEntries) {
    }

    constructor(maxEntries: Int) : this(DEFAULT_INITIAL_CAPACITY, maxEntries) {}

    // not very useful constructor
    constructor(m: Map<out K, V>,
                maxEntries: Int) : this(m.size, maxEntries) {
        putAll(m)
    }

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        return size > maxEntries
    }

    companion object {
        private val DEFAULT_INITIAL_CAPACITY = 16
        private val DEFAULT_LOAD_FACTOR = 0.75f
    }
}
