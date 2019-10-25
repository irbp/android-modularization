package com.example.domain.usecase.mock

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single

object AndroidJobsRepositoryMock {

    private var getAndroidJobsMock = listOf(
        AndroidJob(
            title = "Android Develper",
            experienceTimeRequired = "2 years",
            native = true,
            country = "Brazil"
        )
    )

    fun create(): AndroidJobsRepository = mock {
        on { getJobs(any()) } doReturn Single.just(getAndroidJobsMock)
    }
}