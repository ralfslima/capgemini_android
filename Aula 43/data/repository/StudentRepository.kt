package com.example.api2.data.repository

import com.example.api2.data.model.Student
import com.example.api2.data.remote.RetrofitClient

class StudentRepository {

    suspend fun getStudents(): List<Student>{
        return RetrofitClient.api.getStudents()
    }

    suspend fun addStudent(student: Student): Student{
        return RetrofitClient.api.addStudent(student)
    }

    suspend fun removeStudent(id: Int){
        RetrofitClient.api.removeStudent(id)
    }

}