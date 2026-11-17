package edu.iesam.aad_1evaluacion.features.data

import edu.iesam.aad_1evaluacion.features.data.local.xml.VehicleXmlLocalDataSource
import edu.iesam.aad_1evaluacion.features.domain.Vehicle
import edu.iesam.aad_1evaluacion.features.domain.VehicleRepository

class VehicleDataRepository (private val xmlLocalDataSource: VehicleXmlLocalDataSource): VehicleRepository {

    override fun save(vehicle: List<Vehicle>) {
        return xmlLocalDataSource.save(vehicle)
    }

    override fun deleteById(id: String) {
        return xmlLocalDataSource.deleteById(id)
    }

    override fun findById(id: String): Vehicle? {
        return xmlLocalDataSource.findById(id)
    }

    override fun findAll(): List<Vehicle> {
        return xmlLocalDataSource.findAll()
    }

    override fun update(vehicle: Vehicle) {
        return xmlLocalDataSource.update(vehicle)
    }
}