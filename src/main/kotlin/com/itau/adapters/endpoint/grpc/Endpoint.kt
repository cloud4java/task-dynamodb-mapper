package com.itau.adapters.endpoint.grpc

import com.itau.adapters.grpc.customers.CreateTaskRequest
import com.itau.adapters.grpc.customers.TaskBody
import com.itau.adapters.grpc.customers.TaskIdRequest
import com.itau.adapters.grpc.customers.TaskServiceGrpc
import com.itau.adapters.persistence.repositories.TaskRepository
import io.grpc.Status
import io.grpc.stub.StreamObserver
import javax.inject.Singleton

@Singleton
class TaskEndpoint(private val repository: TaskRepository) : TaskServiceGrpc.TaskServiceImplBase() {
    override fun create(request: CreateTaskRequest?, responseObserver: StreamObserver<TaskBody>?) {
        val task = null;
        try {
            val task = repository.saveTask(request!!)
            responseObserver?.onNext(task)
        } catch (e: Exception) {
            e.printStackTrace()
            responseObserver?.onError(Status.INTERNAL.asException())
        }
        responseObserver?.onCompleted()
    }
//
//  override fun update(request: TaskBody?, responseObserver: StreamObserver<TaskBody>?) {
//    try {
//      val task = repository.update(request!!)
//      responseObserver?.onNext(task)
//    } catch (e: Exception) {
//      responseObserver?.onError(Status.NOT_FOUND.asException())
//    }
//    responseObserver?.onCompleted()
//  }

    override fun findById(request: TaskIdRequest?, responseObserver: StreamObserver<TaskBody>?) {
        try {
            val task = repository.findById(request!!)
            responseObserver?.onNext(task)
        } catch (e: Exception) {
            responseObserver?.onError(Status.NOT_FOUND.asException())
        }
        responseObserver?.onCompleted()
    }

//  override fun findByIdWithUser(request: TaskIdWithUserRequest?, responseObserver: StreamObserver<TaskBodyWithUser>?) {
//    super.findByIdWithUser(request, responseObserver)
//  }
//
//  override fun findAll(request: TaskPageRequest?, responseObserver: StreamObserver<FindAllResponse>?) {
//    super.findAll(request, responseObserver)
//  }
//
//  override fun deleteTask(request: TaskIdRequest?, responseObserver: StreamObserver<TaskBody>?) {
//    try {
//      val task = repository.delete(request!!)
//      responseObserver?.onNext(task)
//    } catch (e: Exception) {
//      responseObserver?.onError(Status.NOT_FOUND.asException())
//    }
//    responseObserver?.onCompleted()
//  }
}
