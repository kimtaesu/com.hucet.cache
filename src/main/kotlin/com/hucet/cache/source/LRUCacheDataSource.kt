package com.hucet.cache.source

import com.hucet.cache.signature.Key
import com.hucet.cache.signature.ObjectKey
import io.reactivex.Flowable
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.rx2.rxFlowable
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by taesu on 2017-11-23.
 */
class LRUCacheDataSource<R>(
        private val lruCache: LRUCache<Key, R>
) {

    private suspend fun cache(value: R): R {
        return lruCache.getOrPut(ObjectKey(value as Any), { value })
    }

    fun getOnFlowable(list: List<R>): Flowable<List<R>> {
        return rxFlowable(Unconfined) {
            val result = ArrayList<R>()
            for (x in list) {
                result.add(cache(x))
            }
            send(result as List<R>)
        }
    }
}