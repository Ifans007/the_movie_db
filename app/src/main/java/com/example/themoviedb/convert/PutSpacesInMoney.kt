package com.example.themoviedb.convert

object PutSpacesInMoney {

//    I don't use extension function

    fun putSpaceInMoney(string: String): String {

        val countDivideThree = string.length / 3

        val stringRevers = string.reversed()

        var stringWithSpace = ""

        var i = 0

        while (i < countDivideThree) {

            stringWithSpace += stringRevers.subSequence(i * 3, (i + 1) * 3)

            stringWithSpace += " "

            i++
        }

        stringWithSpace += stringRevers.subSequence(i * 3, stringRevers.length)

        return stringWithSpace.reversed()
    }
}