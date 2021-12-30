package com.mine.opensea.networking


import com.google.gson.JsonParser
import com.mine.opensea.database.models.Username
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserJsonConvertorTest {

    private lateinit var userJsonConvertor: UserJsonConvertor

    @Before
    fun setUp() {
        userJsonConvertor = UserJsonConvertor()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test if user is type of a jsonObject`() {
        val res = "{\n" +
                "            \"user\": {\n" +
                "              \"username\": \"iiliketurtles\"\n" +
                "            },\n" +
                "            \"profile_img_url\": \"https://storage.googleapis.com/opensea-static/opensea-profile/30.png\",\n" +
                "            \"address\": \"0x1b6db55c7aa95f74f38b27d50eaee75a6a27d631\",\n" +
                "            \"config\": \"\"\n" +
                "          }"


        val jsonObject = JsonParser.parseString(res)
        assertTrue(userJsonConvertor.createUserFromJson(jsonObject).user is Username)
        assertFalse((userJsonConvertor.createUserFromJson(jsonObject).user as Username).username.isNullOrBlank())
    }
}