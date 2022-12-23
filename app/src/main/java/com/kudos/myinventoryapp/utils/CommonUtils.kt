package com.kudos.myinventoryapp.utils

import com.kudos.myinventoryapp.db.entity.Item
import java.text.NumberFormat

object CommonUtils {

    fun Item.getFormattedPrice(): String =
        NumberFormat.getCurrencyInstance().format(itemPrice)

}