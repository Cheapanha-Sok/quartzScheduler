package com.example.scheduler.info

import java.io.Serializable


data class TimerInfo(
    var totalFireCount : Int,
    var isRunForever : Boolean,
    var repeatIntervalMs : Long,
    var initialOffsetMs : Long,
    var callBackData : String
) : Serializable
