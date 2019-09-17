package com.example.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestTwo {

    init {
        runBlocking {

            launch(Dispatchers.IO) {




                val counter1 = 1000000000
                var i1 = 0

                while (i1 < counter1) {
                    i1++
                }

                println("0")




                val counter2 = 10000210
                var i2 = 0

                while (i2 < counter2) {
                    i2++
                }

                println("1")




                val counter3 = 115400000
                var i3 = 0

                while (i3 < counter3) {
                    i3++
                }

                println("2")




                val counter4 = 789000
                var i4 = 0

                while (i4 < counter4) {
                    i4++
                }

                println("3")




                val counter5 = 760000000
                var i5 = 0

                while (i5 < counter5) {
                    i5++
                }

                println("4")




                val counter6 = 6450000
                var i6 = 0

                while (i6 < counter6) {
                    i6++
                }

                println("5")




                val counter = 600
                var i = 0

                while (i < counter) {
                    i++
                }

                println("6")


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