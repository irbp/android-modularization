package com.example.domain.usecases

import com.example.domain.entities.AndroidJob
import com.example.domain.repository.AndroidJobsRepository
import com.sun.org.apache.xpath.internal.operations.Bool
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * I just update this Use case to has a interface, with this I can apply tests in the right way
 */
interface GetJobsUseCases {
    fun execute(forceUpdate: Boolean): Single<List<AndroidJob>>
}

class GetJobsUseCasesImpl (
    private val repository: AndroidJobsRepository,
    private val scheduler: Scheduler
) : GetJobsUseCases {

    override fun execute(forceUpdate: Boolean): Single<List<AndroidJob>> {
        return repository.getJobs(forceUpdate)
            .subscribeOn(scheduler)
    }
}