package com.kamran.newsapp.di

import android.content.Context
import androidx.room.Room
import com.kamran.newsapp.data.local.NewsDao
import com.kamran.newsapp.data.local.NewsDatabase
import com.kamran.newsapp.data.local.NewsTypeConverter
import com.kamran.newsapp.data.manager.LocalUserManagerImpl
import com.kamran.newsapp.data.remote.NewsApi
import com.kamran.newsapp.data.repository.NewsRepositoryImpl
import com.kamran.newsapp.domain.manager.LocalUserManager
import com.kamran.newsapp.domain.repository.NewsRepository
import com.kamran.newsapp.domain.usecases.appEntry.AppEntryUseCases
import com.kamran.newsapp.domain.usecases.appEntry.ReadAppEntry
import com.kamran.newsapp.domain.usecases.appEntry.SaveAppEntry
import com.kamran.newsapp.domain.usecases.news.DeleteArticle
import com.kamran.newsapp.domain.usecases.news.GetArticle
import com.kamran.newsapp.domain.usecases.news.GetArticles
import com.kamran.newsapp.domain.usecases.news.GetNews
import com.kamran.newsapp.domain.usecases.news.NewsUseCases
import com.kamran.newsapp.domain.usecases.news.SearchNews
import com.kamran.newsapp.domain.usecases.news.UpsertArticle
import com.kamran.newsapp.utils.Constants
import com.kamran.newsapp.utils.Constants.NEWS_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager {
        return LocalUserManagerImpl(context)
    }


    @Singleton
    @Provides
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Singleton
    @Provides
    fun provideGetNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getArticle = GetArticle(newsRepository),
            getArticles = GetArticles(newsRepository)
        )
    }

    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = NEWS_DB
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration(false).build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
        return newsDatabase.newsDao
    }


}