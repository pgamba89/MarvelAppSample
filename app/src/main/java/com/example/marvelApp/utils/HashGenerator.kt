package com.example.marvelApp.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object HashGenerator {

    fun generate(timestamp: Long, privateKey: String, publicKey: String): String? {
        return try {
            val concatResult = timestamp.toString() + privateKey + publicKey
            md5(concatResult)
        } catch (e: Exception) {
            null
        }
    }

    @Throws(NoSuchAlgorithmException::class)
    fun md5(s: String): String? {
        val digest: MessageDigest = MessageDigest
            .getInstance("MD5")
        digest.update(s.toByteArray())
        val messageDigest: ByteArray = digest.digest()
        val bigInt = BigInteger(1, messageDigest)
        var hashText: String = bigInt.toString(16)

        while (hashText.length < 32) {
            hashText = "0$hashText"
        }
        return hashText
    }
}