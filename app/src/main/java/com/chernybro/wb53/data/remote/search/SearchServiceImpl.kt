package com.chernybro.wb53.data.remote.search

import android.content.Context
import com.chernybro.wb53.R
import com.chernybro.wb53.domain.data.Cat
import com.chernybro.wb53.utils.Constants
import com.chernybro.wb53.utils.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.nio.channels.UnresolvedAddressException

class SearchServiceImpl(
    private val httpClient: HttpClient,
    private val context: Context
) : SearchService {

    override suspend fun getCat(): Resource<Cat> {
        return try {
            httpClient.submitForm<HttpResponse>(
                url = Constants.ENDPOINT_SEARCH ,
                formParameters = Parameters.build {
                    append("api_key", Constants.TOKEN)
                    append("sub_id", "user-id1")
                },
                encodeInQuery = true
            )
            Resource.Success(data = httpClient.get<List<Cat>>(Constants.ENDPOINT_SEARCH)[0])
        } catch (e: UnresolvedAddressException) {
            Resource.Error(message = context.resources.getString(R.string.error_internet_not_found))
        }
        catch (e: Exception) {
            Resource.Error(message = e.toString())
        }
    }
}