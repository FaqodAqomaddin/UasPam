package com.example.uaspam.data


import android.content.ContentValues
import android.util.Log
import com.example.uaspam.model.Motor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface MotorRepository {
    fun getAll(): Flow<List<Motor>>
    suspend fun save(motor: Motor): String
    suspend fun update(motor: Motor)
    suspend fun delete(motorno: String)
    fun getMotorById(motorno: String): Flow<Motor>
}
class MotorRepositoryImpl(private val firestore: FirebaseFirestore) : MotorRepository {
    override fun getAll(): Flow<List<Motor>> = flow {
        val snapshot = firestore.collection("Motor")
            .orderBy("merek", Query.Direction.ASCENDING)
            .get()
            .await()
        val motor = snapshot.toObjects(Motor::class.java)
        emit(motor)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(motor: Motor): String {
        return try {
            val documentReference = firestore.collection("Motor").add(motor).await()
            // Update the Motor with the Firestore-generated DocumentReference
            firestore.collection("Motor").document(documentReference.id)
                .set(motor.copy(no = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(motor: Motor) {
        firestore.collection("Motor").document(motor.no).set(motor).await()
    }

    override suspend fun delete(motorno: String) {
        firestore.collection("Motor").document(motorno).delete().await()
    }

    override fun getMotorById(motorno: String): Flow<Motor> {
        return flow {
            val snapshot = firestore.collection("Motor").document(motorno).get().await()
            val motor = snapshot.toObject(Motor::class.java)
            emit(motor!!)
        }.flowOn(Dispatchers.IO)
    }

}