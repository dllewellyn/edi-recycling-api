package edi.recycling.points.api.aws

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.PutItemRequest

open class DyanmoDbUploader {

    private val dynamodbClient : AmazonDynamoDB by lazy {
        AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_WEST_1)
                .build()
    }

    fun upload(tableName : String, item : Map<String, AttributeValue>) {
        PutItemRequest()
                .withTableName(tableName)
                .withItem(item)
                .let {
                    dynamodbClient.putItem(it)
                }

    }
}