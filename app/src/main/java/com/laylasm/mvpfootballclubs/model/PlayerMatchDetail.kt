package com.laylasm.mvpfootballclubs.model

import com.google.gson.annotations.SerializedName

data class PlayerMatchDetail(
        @SerializedName("players")
        var player: List<PlayerMatch>)