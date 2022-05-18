package com.example.model

import kotlinx.serialization.*


@Serializable
data class Poi(
    @Contextual
    val _id: String,
    val name: String,
    val lat: Float,
    val lng: Float,
    val city: City,
    val info: String = "",
    val pictures: List<String> = emptyList(),
    val category: Category = Category.CULTURE,
    val visited: Boolean = false
)

/*
{
    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
 */

