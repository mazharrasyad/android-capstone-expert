@file:Suppress("SpellCheckingInspection", "SpellCheckingInspection", "SpellCheckingInspection",
    "SpellCheckingInspection", "SpellCheckingInspection"
)

package com.muhazharrasyad.capstoneexpertii.core.di

import androidx.room.Room
import com.muhazharrasyad.capstoneexpertii.core.data.SportRepository
import com.muhazharrasyad.capstoneexpertii.core.data.source.local.LocalDataSource
import com.muhazharrasyad.capstoneexpertii.core.data.source.local.room.SportDatabase
import com.muhazharrasyad.capstoneexpertii.core.data.source.remote.RemoteDataSource
import com.muhazharrasyad.capstoneexpertii.core.data.source.remote.network.ApiService
import com.muhazharrasyad.capstoneexpertii.core.domain.repository.ISportRepository
import com.muhazharrasyad.capstoneexpertii.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SportDatabase>().sportDao() }
    single {
        // Encryption
        val passphrase: ByteArray = SQLiteDatabase.getBytes("muhazharrasyad".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SportDatabase::class.java, "sport.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        // Certificate Pinning
        val hostname = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/ctt1haazs8U6LJbBhG1dMDCxflw6Q5LRFqlJP+iCf3E=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/all_sports.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ISportRepository> {
        SportRepository(
            get(),
            get(),
            get()
        )
    }
}