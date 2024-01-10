package com.example.uaspam.data

import android.content.ContentValues
import android.util.Log
import com.example.uaspam.model.Pemilik
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface PemilikRepository {
    fun getAll(): Flow<List<Pemilik>>
    suspend fun save(pemilik: Pemilik): String
    suspend fun update(pemilik: Pemilik)
    suspend fun delete(pemilikId: String)
    fun getKontakById(pemilikId: String): Flow<Pemilik>
}

class PemilikRepositoryImpl(private val firestore: FirebaseFirestore) : PemilikRepository {
    override fun getAll(): Flow<List<Pemilik>> = flow {
        val snapshot = firestore.collection("Pemilik")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val pemilik = snapshot.toObjects(Pemilik::class.java)
        emit(pemilik)
    }.flowOn(Dispatchers.IO)


    override suspend fun save(pemilik: Pemilik): String {
        return try {
            val documentReference = firestore.collection("Pemilik").add(pemilik).await()
            // Update the Kontak with the Firestore-generated DocumentReference
            firestore.collection("Pemilik").document(documentReference.id)
                .set(pemilik.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }

    override suspend fun update(pemilik: Pemilik) {
        firestore.collection("Pemilik").document(pemilik.id).set(pemilik).await()
    }

    override suspend fun delete(pemilikId: String) {
        firestore.collection("Pemilik").document(pemilikId).delete().await()
    }

    override fun getKontakById(pemilikId: String): Flow<Pemilik> {
        return flow {
            val snapshot = firestore.collection("Pemilik").document(pemilikId).get().await()
            val pemilik = snapshot.toObject(Pemilik::class.java)
            emit(pemilik!!)
        }.flowOn(Dispatchers.IO)
    }

}
