package com.example.domain.usecase

import com.example.domain.repository.AndroidJobsRepository
import com.example.domain.usecase.mock.AndroidJobsRepositoryMock
import com.example.domain.usecases.GetJobsUseCasesImpl
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.schedulers.Schedulers
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

@RunWith(JUnitPlatform::class)
class GetJobsUseCasesSpek : Spek({

    class Env {
        val androidJobsRepository: AndroidJobsRepository = AndroidJobsRepositoryMock.create()
    }

    val env: Env by memoized { Env() }

    val set by memoized {
        with(env) {
            GetJobsUseCasesImpl(
                androidJobsRepository,
                Schedulers.computation()
            )
        }
    }

    describe("Get jobs") {
        context("should return list of jobs with forcing update") {
            beforeEachTest {
                set.execute(forceUpdate = true).test()
            }
            it("should return list of jobs") {
                verify(env.androidJobsRepository).getJobs(true)
            }
        }

        context("should return list of jobs without update") {
            beforeEachTest {
                set.execute(forceUpdate = false).test()
            }
            it("should return list of jobs") {
                verify(env.androidJobsRepository).getJobs(false)
            }
        }
    }
})