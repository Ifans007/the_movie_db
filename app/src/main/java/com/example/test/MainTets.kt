package com.example.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {

    TestOne()

//    testOne()
//    testTwo()
//    testThree()
//    testFour()


}

fun testOne() {
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

        /* val job0 = launch {

             val counter = 1000000000
             var i = 0

             while (i < counter) {
                 i++
             }

             println("0") }

         val job1 = launch {

             val counter = 100000
             var i = 0

             while (i < counter) {
                 i++
             }

             println("1") }

         val job2 = launch {

             val counter = 100210000
             var i = 0

             while (i < counter) {
                 i++
             }

             println("2") }

         val job3 = launch {

             val counter = 98012000
             var i = 0

             while (i < counter) {
                 i++
             }

             println("3") }

         val job4 = launch { println("4") }
         val job5 = launch { println("5") }
         val job6 = launch { println("6") }
         val job7 = launch { println("7") }
         val job8 = launch { println("8") }
         val job9 = launch { println("9") }

         job0.join()
         job1.join()
         job2.join()
         job3.join()
         job4.join()
         job5.join()
         job6.join()
         job7.join()
         job8.join()
         job9.join()*/

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

fun testTwo() {
    runBlocking {
        launch(Dispatchers.IO) {

            run11()
            run12()
            run13()
            run14()
            run15()
            run16()

        }

    }
}

suspend fun run11() {

        val counter = 43214
        var i = 0

        while (i < counter) {
            i++

        println("1") }
}

suspend  fun run12() {

    val counter = 41613
    var i = 0

    while (i < counter) {
        i++

        println("2") }
}

suspend  fun run13() {

    val counter = 35734
    var i = 0

    while (i < counter) {
        i++

        println("3") }
}

suspend  fun run14() {

    val counter = 57547
    var i = 0

    while (i < counter) {
        i++

        println("4") }
}


suspend  fun run15() {

    val counter = 4567
    var i = 0

    while (i < counter) {
        i++

        println("5") }
}

suspend  fun run16() {

    val counter = 56754
    var i = 0

    while (i < counter) {
        i++

        println("6") }
}




fun testThree() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}

fun testFour() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
