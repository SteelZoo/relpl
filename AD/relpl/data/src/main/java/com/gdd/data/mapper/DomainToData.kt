package com.gdd.data.mapper

import com.gdd.data.model.PointResponse
import com.gdd.data.model.tracking.RelayPathEntity
import com.gdd.domain.model.Point
import com.gdd.domain.model.tracking.RelayPathData

fun Point.toPointResponse(): PointResponse{
    return PointResponse(this.x, this.y)
}

fun RelayPathData.toRelayPathEntity(): RelayPathEntity{
    return RelayPathEntity(latitude, longitude, visit)
}