package com.laylasm.mvpfootballclubs.model

import com.google.gson.annotations.SerializedName

data class FootballMatchResponse (
        @SerializedName("events") var events: List<Data>)