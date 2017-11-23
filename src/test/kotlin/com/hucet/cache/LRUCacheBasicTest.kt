package com.hucet.cache

import org.amshove.kluent.`should be in`
import org.amshove.kluent.`should be`
import org.amshove.kluent.`should not be in`
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * Created by taesu on 2017-11-23.
 */
class LRUCacheBasicTest : SubjectSpek<LRUCache<String, Int>>({
    given("a lruCache")
    {
        subject {
            LRUCache<String, Int>(2)
        }

        on("LRU 알고리즘 검증")
        {
            subject.put("a", 1)
            subject.put("b", 1)
            subject.put("c", 1)
            subject.put("b", 2)
            it("Key [a] 는 삭제되어야 함")
            {
                false `should be` subject.containsKey("a")
            }
            it("Key [b] 는 맨 뒤에 위치해야 함.")
            {
                subject.keys.last() `should be` "b"
            }
        }
    }
})