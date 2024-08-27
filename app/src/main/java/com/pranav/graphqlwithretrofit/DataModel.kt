package com.pranav.graphqlwithretrofit


import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("data")
    val data: Data?
) {
    data class Data(
        @SerializedName("countries")
        val countries: List<Country?>?
    ) {
        data class Country(
            @SerializedName("name")
            val name: String?
        )
    }
}
