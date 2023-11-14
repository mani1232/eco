@file:JvmName("PotionUtilsExtensions")

package com.willfp.eco.util

import org.bukkit.potion.PotionType

/** @see PotionType.duration */
val PotionType.duration: Int
    get() = PotionUtils.getDuration(this)
