package com.hucet.cache.source

import com.hucet.cache.signature.Key
import com.hucet.cache.signature.ObjectKey
import io.reactivex.Flowable
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.rx2.rxFlowable

/**
 * Created by taesu on 2017-11-23.
 */
class LRUCacheDataSource<R>(
        private val lruCache: LRUCache<Key, R>
) {

    fun getOnFlowable(o: R): Flowable<R?> {
        return rxFlowable(CommonPool) {
            send(cache(o)) // this is a suspending function
        }
    }

    suspend fun cache(value: R): R? {
        return lruCache.getOrPut(ObjectKey(value as Any), { value })
    }
}