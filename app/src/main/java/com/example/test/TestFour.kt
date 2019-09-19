package com.example.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestFour {

    init {
        runBlocking {

            launch(Dispatchers.IO) {


                async {

                    val counter = 1000000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("0") }.await()


                async {

                    val counter = 10000210
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("1") }.await()


                async {

                    val counter = 115400000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("2") }.await()


                async {

                    val counter = 789000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("3") }.await()


                async {

                    val counter = 760000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("4") }.await()


                async {

                    val counter = 6450000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("5") }.await()


                async {

                    val counter = 600
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("6") }.await()


            }.join()

            launch {

                val counter = 10000000
                var i = 0

                while (i < counter) {
                    i++
                }

                println("11111111111111")

            }

        }
    }
}