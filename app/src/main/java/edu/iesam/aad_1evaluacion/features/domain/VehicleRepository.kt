package edu.iesam.aad_1evaluacion.features.domain

interface VehicleRepository {

    fun save(vehicle: List<Vehicle>)
    fun deleteById(id: String)
    fun findById(id: String): Vehicle?
    fun findAll(): List<Vehicle>
    fun update(vehicle: Vehicle)
}