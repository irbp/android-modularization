package com.example.domain.usecase

import com.example.domain.di.domainModule
import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.usecase.mock.AndroidJobsRepositoryMock
import com.example.domain.usecases.GetJobsUseCases
import com.example.utils.TrampolineSchedulerRule
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.mockito.MockitoAnnotations

class GetJobUseCasesKoin: AutoCloseKoinTest() {

    private val getJobsUseCases: GetJobsUseCases = mock()
    private val androidJobsRepository: AndroidJobsRepository = AndroidJobsRepositoryMock.create()

    @Rule
    @JvmField
    val schedulers = TrampolineSchedulerRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(domainModule)
        }
    }

    @Test
    fun `test if a list of jobs is came`() {
        getJobsUseCases.execute(true)
        androidJobsRepository.getJobs(true)

        inOrder(getJobsUseCases, androidJobsRepository) {
            verify(getJobsUseCases).execute(true)
            verify(androidJobsRepository).getJobs(true)
        }
    }
}