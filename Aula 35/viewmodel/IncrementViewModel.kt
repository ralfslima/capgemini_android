package com.example.exemplolivedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exemplolivedata.model.IncrementModel

class IncrementViewModel: ViewModel() {

    // Projetos recentes, utilizar StateFlow (State, Mutable, StateShare, etc.)
    // Projetos antigos, utilizar LiveData (LiveData, MutableLiveData, etc.)

    // Referência do modelo (model)
    private val _obj = MutableLiveData(IncrementModel())

    // Referência da visão (view)
    val obj: LiveData<IncrementModel> = _obj

    // Função para incrementar o counter
    fun incrementCounter() {
        // Criar uma cópia do estado atual do objeto
        val currentObj = _obj.value ?: IncrementModel()

        // Criar uma cópia e sobrescrever o _obj
        _obj.value =  currentObj.copy(counter = currentObj.counter + 1)
    }
}





