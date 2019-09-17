package com.example.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestOne {

    init {

        runBlocking(Dispatchers.IO) {

            launch {


                launch {

                    val counter = 1000000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("0") }


                launch {

                    val counter = 10000210
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("1") }


                launch {

                    val counter = 115400000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("2") }


                launch {

                    val counter = 789000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("3") }


                launch {

                    val counter = 760000000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("4") }


                launch {

                    val counter = 6450000
                    var i = 0

                    while (i < counter) {
                        i++
                    }

                    println("5") }


                launch {

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

        println("1-1-1-1-1-1")

    }



}