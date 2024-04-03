package com.example.scheduler.info

import java.io.Serializable


data class TimerInfo(
    var totalFireCount : Int ? =0,
    var remainingFireCount : Int ?=0,
    var isRunForever : Boolean ?=true,
    var repeatIntervalMs : Long ? =0,
    var initialOffsetMs : Long ?=0,
    var callBackData : String?=null
) : Serializable
