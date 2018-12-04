package com.laylasm.mvpfootballclubs.model

import com.google.gson.annotations.SerializedName

data class FootballMatchPlayer(
        @SerializedName("player")
        var player: List<PlayerMatch>)

