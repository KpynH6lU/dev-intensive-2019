package ru.skillbranch.devintensive.utils

object Utils {
    val map: HashMap<String, String> = hashMapOf(
        "а" to "a", "A" to "A",
        "б" to "b", "Б" to "B",
        "в" to "v", "В" to "V",
        "г" to "g", "Г" to "G",
        "д" to "d", "Д" to "D",
        "е" to "e", "Е" to "E",
        "ё" to "e", "Ё" to "E",
        "ж" to "zh", "Ж" to "Zh",
        "з" to "z", "З" to "Z",
        "и" to "i", "И" to "I",
        "й" to "i", "Й" to "I",
        "к" to "k", "К" to "K",
        "л" to "l", "Л" to "L",
        "м" to "m", "М" to "M",
        "н" to "n", "Н" to "N",
        "о" to "o", "О" to "O",
        "п" to "p", "П" to "P",
        "р" to "r", "Р" to "R",
        "с" to "s", "С" to "S",
        "т" to "t", "Т" to "T",
        "у" to "u", "У" to "U",
        "ф" to "f", "Ф" to "F",
        "х" to "h", "Х" to "H",
        "ц" to "c", "Ц" to "C",
        "ч" to "ch", "Ч" to "Ch",
        "ш" to "sh", "Ш" to "Sh",
        "щ" to "sh'", "Щ" to "Sh'",
        "ъ" to "",  "Ъ" to "",
        "ы" to "i", "Ы" to "I",
        "ь" to "", "Ь" to "",
        "э" to "e", "Э" to "E",
        "ю" to "yu", "Ю" to "Yu",
        "я" to "ya", "Я" to "Ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        val firstName = when (parts?.getOrNull(0)) {
            null -> null
            "" -> null
            " " -> null
            else -> parts[0]
        }
        val lastName = when (parts?.getOrNull(1)) {
            null -> null
            "" -> null
            " " -> null
            else -> parts[1]
        }

        return firstName to lastName
    }

    fun transliteration(payloyd: String, divider: String = " "): String {
        var nickname: String = ""
        payloyd.forEach {
            nickname += when {
                it == ' ' -> {
                    divider
                }
                map.containsKey(it.toString()) -> {
                    map[it.toString()]
                }
                else -> it
            }
        }
        return nickname
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        val firstLatter = if (firstName.isNullOrEmpty()) {
            ""
        } else {
            firstName.take(1).toUpperCase()
        }
        val lastLatter = if (lastName.isNullOrEmpty()) {
            ""
        } else {
            lastName.take(1).toUpperCase()
        }
        return firstLatter.plus(lastLatter)
    }

}