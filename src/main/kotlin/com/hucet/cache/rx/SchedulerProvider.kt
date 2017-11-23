package com.hucet.cache.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by taesu on 2017-11-23.
 */
interface SchedulerProvider {
    fun computation() = Schedulers.computation()
    fun io() = Schedulers.io()
}