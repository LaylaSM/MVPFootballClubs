package com.laylasm.mvpfootballclubs.model

import com.google.gson.annotations.SerializedName

data class SearchMatchClub(
        @SerializedName("event") var events: List<Data>
)