package edi.recycling.points.api.aws

import com.amazonaws.services.dynamodbv2.model.AttributeValue
import edi.recycling.points.api.RecyclingPointApi
import edi.recycling.points.api.interfaces.Uploader
import javax.inject.Singleton

@Singleton
class WasteUploaderDynamoDb : DyanmoDbUploader(), Uploader<RecyclingPointApi> {
    override fun uploadSingleItem(item: RecyclingPointApi) {
        upload("new-items", item.toMap())
    }
}

fun RecyclingPointApi.toMap(): Map<String, AttributeValue> {
    val returnValue = mutableMapOf<String, AttributeValue>()
    returnValue["name"] = AttributeValue(name)
    returnValue["description"] = AttributeValue(description)
    returnValue["type"] = AttributeValue(type)
    returnValue["latitude"] = AttributeValue(latitude.toString())
    returnValue["longitude"] = AttributeValue(longitude.toString())
    returnValue["id"] = AttributeValue("$name-$type-$latitude-$longitude")
    return returnValue
}