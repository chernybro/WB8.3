package com.chernybro.wb53.di


import android.content.Context
import androidx.room.Room
import com.chernybro.wb53.data.remote.favourites.FavouritesApi
import com.chernybro.wb53.data.remote.favourites.FavouritesApiImpl
import com.chernybro.wb53.data.remote.search.SearchService
import com.chernybro.wb53.data.remote.search.SearchServiceImpl
import com.chernybro.wb53.data.repository.FavouritesRepository
import com.chernybro.wb53.data.repository.FavouritesRepositoryImpl
import com.chernybro.wb53.data.storage.dao.FavouriteCatDAO
import com.chernybro.wb53.data.storage.database.CatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton
import kotlinx.serialization.json.Json as KotlinJson

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(JsonFeature) {
                serializer = KotlinxSerializer(KotlinJson {
                    ignoreUnknownKeys = true
                })
            }
        }
    }


    @Provides
    @Singleton
    fun provideSearchService(
        httpClient: HttpClient,
        @ApplicationContext context: Context
    ): SearchService {
        return SearchServiceImpl(httpClient, context)
    }

    @Provides
    @Singleton
    fun provideFavouritesService(httpClient: HttpClient): FavouritesApi {
        return FavouritesApiImpl(httpClient)
    }


    @Provides
    @Singleton
    fun provideFavouritesRepository(
        favouritesApi: FavouritesApi,
        favouriteCatDAO: FavouriteCatDAO,
        @ApplicationContext context: Context
    ): FavouritesRepository {
        return FavouritesRepositoryImpl(favouritesApi, favouriteCatDAO, context)
    }

    @Provides
    @Singleton
    fun provideCatsDatabase(@ApplicationContext context: Context): CatsDatabase {
        return Room.databaseBuilder(
            context,
            CatsDatabase::class.java, "cats_database"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideFavouritesDao(catsDatabase: CatsDatabase): FavouriteCatDAO {
        return catsDatabase.favouritesCatsDAO
    }
}