package com.hucet.cache.source

import com.hucet.cache.rx.TestSchedulerProvider
import com.hucet.cache.signature.Key
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.mock
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek
import java.util.concurrent.TimeUnit

/**
 * Created by taesu on 2017-11-23.
 */

class LRUCacheDataSourceTest : SubjectSpek<LRUCacheDataSource<String>>({
    val testScheduler = TestScheduler()
    val testSchedulerProvider = TestSchedulerProvider(testScheduler)

    val lruCache by memoized { LRUCache<Key, String>(3) }

    val testData = listOf(
            "abc",
            "def",
            "eea",
            "qqw"
    )
    given("LRUCacheDataSource")
    {
        subject {
            LRUCacheDataSource(lruCache)
        }
        on("send [abc]")
        {
            val testSubscriber = subject
                    .getOnFlowable(testData)
                    .subscribeOn(testSchedulerProvider.io())
                    .test()


            testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

            it("receiver [abc] 이어야 함")
            {
                testSubscriber?.run {
                    assertComplete()
                    assertNoErrors()
                    assertValue {
                        testData == it
                    }
                }
            }
        }
    }
})