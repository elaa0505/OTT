package com.isuncloud.ott.di.module

import android.app.Application
import android.arch.persistence.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.isuncloud.isuntvmall.app.EventPublishSubject
import com.isuncloud.isuntvmall.app.Pref
import com.isuncloud.isuntvmall.database.AppDatabase
import com.isuncloud.isuntvmall.event.BaseRxEvent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    fun providePref(gson: Gson, application: Application): Pref {
        return Pref(gson, application.applicationContext, "OTT_Prefs")
    }

    @Provides
    @Singleton
    fun provideEventPublishSubject(): EventPublishSubject<BaseRxEvent> {
        return EventPublishSubject()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        val builder = Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "OTT")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
        return builder.build()
    }

}