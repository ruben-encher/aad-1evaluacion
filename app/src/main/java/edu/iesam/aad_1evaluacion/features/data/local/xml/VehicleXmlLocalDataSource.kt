package edu.iesam.aad_1evaluacion.features.data.local.xml

import android.content.Context
import edu.iesam.aad_1evaluacion.R
import edu.iesam.aad_1evaluacion.core.cache.AppCacheTTL
import edu.iesam.aad_1evaluacion.features.domain.Vehicle

class VehicleXmlLocalDataSource (private val context: Context,
private val appCacheTTL: AppCacheTTL,
    private val serializer: edu.iesam.marvelapp.core.serialization.JsonSerializer<List<Vehicle>>){

    val sharedPref = context.getSharedPreferences(context.getString(R.string.local_source_vehicle),
        Context.MODE_PRIVATE)

    fun save(vehicle: List<Vehicle>) {
        sharedPref.edit().apply {
            putString(keyVehicle, serializer.toJson(vehicle))
            apply()
        }
    }

    fun findAll(): List<Vehicle> {
        if(!appCacheTTL.isExpired(keyVehicle, ttl)) {
            val jsonList = sharedPref.getString(keyVehicle, null)
            jsonList?.let {
                return serializer.fromJson(it)
            }
        }
        return emptyList()
    }

    fun deleteById(id: String) {
        return save(findAll().filterNot { it.id == id })
    }

    fun findById(id: String): Vehicle? {
        return findAll().find { it.id == id }
    }

    fun update(vehicle: Vehicle) {
        val vehicles = findAll().filterNot { it.id == vehicle.id }.toMutableList()
        vehicles.add(vehicle)
        save(vehicles)

    }

    companion object {
        private val keyVehicle: String = "vehicle"

        private val ttl: Long = 2 * 1000

    }
}