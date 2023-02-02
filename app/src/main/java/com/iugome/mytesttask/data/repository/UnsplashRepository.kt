package com.iugome.mytesttask.data.repository

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.iugome.mytesttask.domain.models.UnsplashModel
import com.iugome.mytesttask.domain.rrepository.UnsplashRepositoryInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import ru.gildor.coroutines.okhttp.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class UnsplashRepository @Inject constructor(): UnsplashRepositoryInterface {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override suspend fun getUnsplash(): ArrayList<UnsplashModel> {
        val list = ArrayList<UnsplashModel>()
        try {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
            val request: Request = Request.Builder()
                .url("https://api.unsplash.com/photos/random?count=30&client_id=L8PQoBoRXC-eVXEj0LItDk73ykDvFes-V2vugTxV_1w")
                .build()
            val response: Response = okHttpClient.newCall(request).await()
            if (response.isSuccessful) {
                val jsonArray = response.body?.string()?.let { JSONArray(it) }
                if (jsonArray != null) for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.optJSONObject(i)
                    val id = jsonObject.getString("id")
                    val createdAt = jsonObject.getString("created_at")
                    val likes = jsonObject.getString("likes")
                    val jsonObjectUrls = jsonObject.getJSONObject("urls")
                    val jsonObjectName = jsonObject.getJSONObject("user")
                    val linkPhoto = jsonObjectUrls.getString("small")
                    val name = jsonObjectName.getString("name")
                    val inputFormatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                    val outputFormatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.getDefault())
                    val date: LocalDate = LocalDate.parse(createdAt, inputFormatter)
                    val formattedDate: String = outputFormatter.format(date)
                    val unsplashModel = UnsplashModel(
                        id = id,
                        createdAt = formattedDate,
                        likes = likes,
                        linkPhoto = linkPhoto,
                        name = name
                    )
                    list.add(unsplashModel)
                }

            } else {
                list.add(
                    UnsplashModel(
                        id = "",
                        createdAt = "",
                        likes = "",
                        linkPhoto = "",
                        name = ""
                    )
                )
            }
        } catch (e: Exception) {
            list.add(UnsplashModel(id = "", createdAt = "", likes = "", linkPhoto = "", name = ""))
        }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getSearch(link: String): ArrayList<UnsplashModel> {
        val list = ArrayList<UnsplashModel>()
        try {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build()
            val request: Request = Request.Builder()
                .url("https://api.unsplash.com/search/photos?query=$link?&client_id=L8PQoBoRXC-eVXEj0LItDk73ykDvFes-V2vugTxV_1w")
                .build()
            val response: Response = okHttpClient.newCall(request).await()
            if (response.isSuccessful) {
                val jsonObject = JSONObject(response.body!!.string())
                val jsonArray = JSONArray(jsonObject.getString("results"))
                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val id = jsonObject1.getString("id")
                    val createdAt = jsonObject1.getString("created_at")
                    val likes = jsonObject1.getString("likes")
                    val jsonObjectUrls = jsonObject1.getJSONObject("urls")
                    val jsonObjectName = jsonObject1.getJSONObject("user")
                    val linkPhoto = jsonObjectUrls.getString("small")
                    val name = jsonObjectName.getString("name")
                    val inputFormatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                    val outputFormatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.getDefault())
                    val date: LocalDate = LocalDate.parse(createdAt, inputFormatter)
                    val formattedDate: String = outputFormatter.format(date)
                    val unsplashModel = UnsplashModel(
                        id = id,
                        createdAt = formattedDate,
                        likes = likes,
                        linkPhoto = linkPhoto,
                        name = name
                    )
                    list.add(unsplashModel)

                }
            }else{
                list.add(
                    UnsplashModel(
                        id = "",
                        createdAt = "",
                        likes = "",
                        linkPhoto = "",
                        name = ""
                    )
                )
            }
        } catch (e: Exception) {
            list.add(
                UnsplashModel(
                    id = "",
                    createdAt = "",
                    likes = "",
                    linkPhoto = "",
                    name = ""
                )
            )
        }
        return list
    }
}