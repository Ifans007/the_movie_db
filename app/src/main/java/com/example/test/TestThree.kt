package com.example.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestThree {

    init {
        runBlocking {

            launch(Dispatchers.IO) {


                async {

                    val counter = 1000000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("0") }


                async {

                    val counter = 10000210
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("1") }


                async {

                    val counter = 115400000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("2") }


                async {

                    val counter = 789000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("3") }


                async {

                    val counter = 760000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("4") }


                async {

                    val counter = 6450000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("5") }


                async {

                    val counter = 600
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("6") }


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