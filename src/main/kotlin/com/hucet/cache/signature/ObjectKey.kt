package com.hucet.cache.signature

/**
 * Created by taesu on 2017-11-23.
 */
class ObjectKey(val obj : Any) : Key {

    override fun equals(o: Any?): Boolean = obj == o

    override fun hashCode(): Int = obj.hashCode()

    override fun toString(): String {
        return ("ObjectKey{"
                + "object=" + obj
                + '}')
    }
}