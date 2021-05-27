package com.example.myapplication.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class DomainModule {

    /* @Binds
     abstract fun bindSearchUser(searchUserImpl: SearchUserImpl): SearchUser*/
}
