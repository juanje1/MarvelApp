package com.marvelapp.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class UtilitiesTest {

    @Test
    fun `"getMd5" returns valid value`() {
        val timestamp = 1619247656119
        val md5 = getMd5("${timestamp}${PRIVATE_KEY}${PUBLIC_KEY}")

        assertNotNull(md5)
        assertEquals("339480231f9e489ed68e320983c00b1c", md5)
    }

    @Test
    fun `"getCurrentDate" returns not null value`()=
        assertNotNull(getCurrentDate())
}