//package com.itau.adapters.endpoint.grpc
//
//import com.itau.*
//import com.itau.adapters.grpc.customers.CreateTaskRequest
//import com.itau.adapters.grpc.customers.TaskBody
//import com.itau.adapters.persistence.repositories.com.itau.adapters.persistence.repository.TaskRepository
//import io.grpc.Status
//import io.grpc.stub.StreamObserver
//import javax.inject.Singleton
//
//@Singleton
//class TaskEndpointCorroutine(private val repository: TaskRepository): TaskServiceGrpcKt.TaskServiceCoroutineImplBase() {
//  override suspend fun create(request: CreateTaskRequest): TaskBody {
//    try {
//      return repository.saveTask(request!!)!!
//    } catch (e: Exception) {
//      throw Status.INTERNAL.asException()
//    }
//  }
//
//  override suspend fun update(request: TaskBody): TaskBody {
//    try {
//      return repository.update(request!!)!!
//    } catch (e: Exception) {
//      throw Status.INTERNAL.asException()
//    }
//  }
//
//  override suspend fun findById(request: TaskIdRequest): TaskBody {
//    try {
//      return repository.findById(request!!)!!
//    } catch (e: Exception) {
//      e.printStackTrace()
//      throw Status.INTERNAL.asException()
//    }
//  }
//
//  override suspend fun findByIdWithUser(request: TaskIdWithUserRequest): TaskBodyWithUser {
//    return super.findByIdWithUser(request)
//  }
//
//  override suspend fun findAll(request: TaskPageRequest): FindAllResponse {
//    return super.findAll(request)
//  }
//
//  override suspend fun deleteTask(request: TaskIdRequest): TaskBody {
//    try {
//      return repository.delete(request!!)!!
//    } catch (e: Exception) {
//      throw Status.INTERNAL.asException()
//    }
//  }
//}