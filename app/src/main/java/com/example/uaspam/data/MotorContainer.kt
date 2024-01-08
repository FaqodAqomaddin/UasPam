package com.example.uaspam.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val motorRepository: MotorRepository
}

class MotorContainer : AppContainer{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val motorRepository: MotorRepository by lazy {
        MotorRepositoryImpl(firestore)
    }
}