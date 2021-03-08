package com.itau.adapters.persistence.repositories

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.itau.adapters.grpc.customers.CreateTaskRequest
import com.itau.adapters.grpc.customers.TaskBody
import com.itau.adapters.grpc.customers.TaskIdRequest
import com.itau.adapters.persistence.entities.TaskItem
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton


@Singleton
class TaskDynamoRepository(/*private val mongoClient: DynamoDbClient*/) : TaskRepository {
    private companion object {
        const val TABLE_NAME: String = "Task2"
        const val TASK_ID_COL: String = "id"
        const val TASK_TITLE_COL: String = "title"
        const val TASK_STATUS_COL: String = "status"
        const val TASK_USER_ID_COL: String = "userId"
        val logger = LoggerFactory.getLogger(this.javaClass.name)
        const val collectionName = "tasks"
    }

    private val ddbClient: AmazonDynamoDB by lazy {
        AmazonDynamoDBClient.builder()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .build()
    }

    override fun saveTask(request: CreateTaskRequest): TaskBody? {
        var id = UUID.randomUUID().toString()

        val taskItem: TaskItem = TaskItem(id, request.title, request.status.toString(), request.userId.toString())
        logger.info(" Inserindo task $taskItem")

        val ddbMapper = DynamoDBMapper(ddbClient)
        ddbMapper.save(taskItem)

        return TaskBody.newBuilder()
                .setId(taskItem.id)
                .setTitle(request.title)
                .setStatus(request.status)
                .setUserId(request.userId)
                .build()
    }

    override fun findById(idRequest: TaskIdRequest): TaskBody? {
        logger.info("Iniciando a busca por ID: ${idRequest.id}")

        val taskItem = TaskItem(idRequest.id.toString())
        val ddbMapper = DynamoDBMapper(ddbClient)
        val item = ddbMapper.load(taskItem)
        logger.info("Busca retornou o item: ${item.id} ${item.title} ${item.status} ${item.userId} ")

        return TaskBody.newBuilder()
                .setId(idRequest.id)
                .setId(item.id)
                .setTitle(item.title)
                .setStatus(item.status!!.toInt())
                .setUserId(item.userId!!.toLong())
                .build()
    }

    override fun update(request: TaskBody): TaskBody? {

        val taskItem: TaskItem = TaskItem(request.id, request.title, request.status.toString(), request.userId.toString())
        logger.info(" Alterando task $taskItem")

        val ddbMapper = DynamoDBMapper(ddbClient)
        ddbMapper.save(taskItem)

        return TaskBody.newBuilder()
                .setId(taskItem.id)
                .setTitle(request.title)
                .setStatus(request.status)
                .setUserId(request.userId)
                .build()
    }

//  override fun findAll(page: TaskPageRequest): FindAllResponse? {
//    TODO("Not yet implemented")
//  }

    override fun delete(request: TaskIdRequest): TaskBody {

        val taskItem = TaskItem(request.id.toString())
        val ddbMapper = DynamoDBMapper(ddbClient)
        ddbMapper.delete(taskItem)

        return TaskBody
                .newBuilder()
                .setId(request.id.toString())
                .build()
    }
}