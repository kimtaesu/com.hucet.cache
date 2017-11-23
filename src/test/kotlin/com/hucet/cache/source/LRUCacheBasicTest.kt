package com.hucet.cache.source

import com.hucet.cache.signature.Key
import com.hucet.cache.signature.ObjectKey
import org.amshove.kluent.`should be`
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * Created by taesu on 2017-11-23.
 */
class LRUCacheBasicTest : SubjectSpek<LRUCache<Key, Int>>({
    val keys = listOf(
            ObjectKey("a"),
            ObjectKey("b"),
            ObjectKey("c"),
            ObjectKey("b")
    )
    given("LRUCache")
    {
        subject {
            LRUCache<Key, Int>(2)
        }

        on("LRU 알고리즘 검증")
        {
            subject.put(keys[0], 1)
            subject.put(keys[1], 1)
            subject.put(keys[2], 1)
            subject.put(keys[3], 2)
            it("Key [a] 는 삭제되어야 함")
            {
                false `should be` subject.containsKey(keys[0])
            }
            it("Key [b] 는 맨 뒤에 위치해야 함.")
            {
                subject.keys.last() `should be` keys[3]
            }
        }
    }
})