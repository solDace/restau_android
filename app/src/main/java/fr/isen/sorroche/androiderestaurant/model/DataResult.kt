package fr.isen.sorroche.androidrestaurant.model

import com.google.gson.annotations.SerializedName


data class DataResult (

  @SerializedName("data" ) var data : ArrayList<Data> = arrayListOf()

)