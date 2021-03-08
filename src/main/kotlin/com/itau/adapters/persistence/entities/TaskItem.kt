package com.itau.adapters.persistence.entities

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "Task2")
data class TaskItem(
    @DynamoDBHashKey
    var id: String,
    @DynamoDBAttribute
    var title: String?,
    @DynamoDBAttribute
    var status: String?,
    @DynamoDBAttribute
    var userId: String?

) {
    constructor(id: String) : this(id, null, null,null){}
}