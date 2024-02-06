package com.example.seminarmanagementsystem.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.seminarmanagementsystem.data.apiService.ApiService
import com.example.seminarmanagementsystem.data.apiService.AuthApiService
import com.example.seminarmanagementsystem.data.apiService.AuthInterceptor
import com.example.seminarmanagementsystem.data.repository.authRepo.AuthRepoImpl
import com.example.seminarmanagementsystem.data.repository.bookRepo.BookRepoImpl
import com.example.seminarmanagementsystem.data.tokenPreference.TokenPreferenceImplementation
import com.example.seminarmanagementsystem.domain.repository.authRepo.AuthRepo
import com.example.seminarmanagementsystem.domain.repository.bookRepo.BookRepo
import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.AppEntryUseCases
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.ReadAppEntry
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.SaveAppEntry
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.AuthUseCases
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.LoginUseCase
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.forgetPasswordUseCase.*
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.signupUseCases.*
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.AddBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.DeleteBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.GetBookListUseCase
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.IssueBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.IssueTheBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.ReturnTheBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.SearchBook
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.UpdateBook
import com.example.seminarmanagementsystem.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    //  ------------------------------------- Retrofit -------------------------------------
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder.build().create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAuthApiService(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): AuthApiService {
        return retrofitBuilder.client(okHttpClient).build().create(AuthApiService::class.java)
    }

    //  ---------------------------------------Repository---------------------------------------

    @Singleton
    @Provides
    fun provideAuthRepo(
        apiService: ApiService,
        preference: TokenPreference
    ): AuthRepo {
        return AuthRepoImpl(apiService, preference)
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        apiService: ApiService,
        authApiService: AuthApiService
    ): BookRepo {
        return BookRepoImpl(apiService, authApiService)
    }

    //  --------------------------------- Preference DataStore ---------------------------------
    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("token") }
        )
    }

    @Singleton
    @Provides
    fun provideUserPref(dataStore: DataStore<Preferences>): TokenPreference =
        TokenPreferenceImplementation(dataStore)

    //  ---------------------------------------Use cases----------------------------------------

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        tokenPreference: TokenPreference
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(tokenPreference),
        saveAppEntry = SaveAppEntry(tokenPreference)
    )

    @Provides
    @Singleton
    fun provideAuthUseCases(
        authRepo: AuthRepo
    ) = AuthUseCases(
        loginUseCase = LoginUseCase(authRepo),
        generateOtpUseCase = GenerateOtpUseCase(authRepo),
        verifyOtpUseCase = VerifyOtpUseCase(authRepo),
        registerUserUseCase = RegisterUserUseCase(authRepo),
        forgetPasswordUseCase = ForgetPasswordUseCase(authRepo),
        changePasswordUseCase = ChangePasswordUseCase(authRepo),
        verifyOtpForExistingEmailUseCase = VerifyOtpForExistingEmailUseCase(authRepo)
    )

    @Provides
    @Singleton
    fun provideBookUseCases(
        bookRepo: BookRepo
    ) = BookUseCase(
        getBookListUseCase = GetBookListUseCase(bookRepo),
        searchBook = SearchBook(bookRepo),
        issueBook = IssueBook(bookRepo),
        addBook = AddBook(bookRepo),
        deleteBook = DeleteBook(bookRepo),
        updateBook = UpdateBook(bookRepo),
        issueTheBook = IssueTheBook(bookRepo),
        returnTheBook = ReturnTheBook(bookRepo)
    )
}