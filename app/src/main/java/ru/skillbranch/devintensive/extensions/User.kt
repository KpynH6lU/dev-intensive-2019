package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

fun User.toUserView() : UserView{
    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,lastName)
    val status = if(lastVisit == null)"Еще ни разу не был" else if(isOnline)"online" else "Последний раз был ${lastVisit.humanizeDiff()}"


    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        initials = initials,
        status = status
    )
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var difference = date.time - this.time

    fun dif(
        type: Long,
        forOne: String = "",
        forTwoToFour: String = "",
        other: String = ""
    ): String {

        if (difference < 0) {
            difference = -difference
        }

        return when {
            (difference / type) % 10 == 1L -> forOne
            (difference / type) % 10 in 2..4 -> forTwoToFour
            else -> other
        }
    }

    if (difference < 0) {
        return when (-difference) {
            in 0..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "через несколько секунд"
            in 45 * SECOND..75 * SECOND -> "через минуту"
            in 75 * SECOND..45 * MINUTE -> "через ${-difference / MINUTE}" +
                    " ${dif(MINUTE, "минуту", "минуты", "минут")}"
            in 45 * MINUTE..75 * MINUTE -> "через час"
            in 75 * MINUTE..22 * HOUR -> "через ${-difference / HOUR}" +
                    " ${dif(HOUR, "час", "часа", "часов")}"
            in 22 * HOUR..26 * HOUR -> "через день"
            in 26 * HOUR..360 * DAY -> "через ${-difference / DAY}" +
                    " ${dif(DAY, "день", "дня", "дней")}"
            else -> "более чем через год"
        }
    } else {
        return when (difference) {
            in 0..1 * SECOND -> "только что"
            in 1 * SECOND..45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND..75 * SECOND -> "минуту назад"
            in 75 * SECOND..45 * MINUTE -> "${difference / MINUTE}" +
                    " ${dif(MINUTE, "минуту", "минуты", "минут")} назад"
            in 45 * MINUTE..75 * MINUTE -> "час назад"
            in 75 * MINUTE..22 * HOUR -> "${difference / HOUR}" +
                    " ${dif(HOUR, "час", "часа", "часов")} назад"
            in 22 * HOUR..26 * HOUR -> "день назад"
            in 26 * HOUR..360 * DAY -> "${difference / DAY}" +
                    " ${dif(DAY, "день", "дня", "дней")} назад"
            else -> "более года назад"
        }
    }
}