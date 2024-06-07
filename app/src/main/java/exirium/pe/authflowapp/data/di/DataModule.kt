package exirium.pe.authflowapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import exirium.pe.authflowapp.data.repository.AuthenticationRepositoryImpl
import exirium.pe.authflowapp.domain.repository.AuthenticationRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsReqresRepository(
        reqresRepository: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}