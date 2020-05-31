package com.omkar.core.di

import android.content.Context
import com.omkar.core.BuildConfig
import com.omkar.core.data.api.CircleCIService
import com.omkar.core.data.prefs.PreferenceStorage
import com.omkar.core.data.prefs.SharedPreferenceStorage
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class CoreDataModule {

    @Singleton
    @Provides
    fun providesPreferenceStorage(context: Context): PreferenceStorage {
        return SharedPreferenceStorage(context)
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideCircleCIService(
        okHttpClient: Lazy<OkHttpClient>,
        moshiConverterFactory: MoshiConverterFactory
    ): CircleCIService {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(CircleCIService.ENDPOINT)
            .callFactory(okHttpClient.get())
            .build()
            .create(CircleCIService::class.java)
    }
}
