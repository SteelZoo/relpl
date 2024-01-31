package com.gdd.relpl.module

import com.gdd.data.repository.project.ProjectRepositoryImpl
import com.gdd.data.repository.user.UserRepositoryImpl
import com.gdd.domain.repository.ProjectRepository
import com.gdd.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindProjectRepository(projectRepositoryImpl: ProjectRepositoryImpl): ProjectRepository

}