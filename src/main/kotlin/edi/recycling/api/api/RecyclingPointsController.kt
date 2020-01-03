package edi.recycling.points.api

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import edi.recycling.points.api.interfaces.Uploader
import io.micronaut.context.annotation.Value
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import javax.inject.Inject

data class RecyclingPointApi(val name: String, val description: String, val type: String, val latitude: Double, val longitude: Double)

@Controller("/recyclingPoints")
class RecyclingPointsController {

    @Value("\${vars.bucket_name}")
    lateinit var bucketName: String

    @Value("\${vars.json_name}")
    lateinit var jsonName: String

    @Inject
    lateinit var uploader: Uploader<RecyclingPointApi>

    private val s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1).build()

    @Post("/new")
    fun postData(@Body recyclingPoints: RecyclingPointApi) {
        uploader.uploadSingleItem(recyclingPoints)
    }

    @ExperimentalStdlibApi
    @Get("/")
    fun index() = s3Client.getObject(bucketName, jsonName)
            .objectContent
            .readBytes()
            .decodeToString()
}