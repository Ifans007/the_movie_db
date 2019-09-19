package com.example.test

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestForSuspendFun {

    init {

        runBlocking {


            launch { funOne() }

        }

    }

    suspend fun funOne() {
        println("1")
        funTwo()
        println("11")

    }

    suspend fun funTwo() {
        println("2")
        funThree()
        println("22")
    }

    suspend fun funThree() {
        println("3")
        funFour()
        println("33")
    }

    suspend fun funFour() {

        println("4")
    }

}