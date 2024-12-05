package com.example.tusroomseeker.component.map

import com.google.android.gms.maps.model.LatLng

data class MapDetails(
    val type: String,
    val image: Int,
    val latitude: Double?,
    val longitude: Double?
) {
    fun position() : LatLng {
        return LatLng(latitude ?: 0.0, longitude ?: 0.0)
    }
}