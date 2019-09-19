package com.example.themoviedb.convert

object CountyCodeToFlag {


//    I don't use extension function

    fun toFlagEmoji(string: String): String {

        var stringFlags = ""

        val listCodeCountry = string.split(" ")

        for (code in listCodeCountry) {

            val firstLetter = Character.codePointAt(code, 0) - 0x41 + 0x1F1E6
            val secondLetter = Character.codePointAt(code, 1) - 0x41 + 0x1F1E6

            stringFlags += "${String(Character.toChars(firstLetter))}${String(Character.toChars(secondLetter))} "

        }
        return stringFlags
    }

}