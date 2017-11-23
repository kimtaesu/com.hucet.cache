package com.hucet.cache.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * Created by taesu on 2017-11-23.
 */
class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {


    override fun computation() = testScheduler

    override fun io(): Scheduler = testScheduler
}
