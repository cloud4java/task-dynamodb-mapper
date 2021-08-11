package com.itau.adapters.persistence.repositories.com.itau.adapters.persistence.repository

import com.itau.*
import com.itau.adapters.grpc.customers.CreateTaskRequest
import com.itau.adapters.grpc.customers.TaskBody
import com.itau.adapters.grpc.customers.TaskIdRequest

interface TaskRepository {
  fun saveTask(request: CreateTaskRequest): TaskBody?

  fun update(request: TaskBody): TaskBody?

  fun findById(idRequest: TaskIdRequest): TaskBody?

//  fun findAll(page: TaskPageRequest): FindAllResponse?

  fun delete(request: TaskIdRequest): TaskBody
}