package com.android.carousell

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.android.carousell.models.NewsDo
import com.android.carousell.network.CarousellServices
import com.android.carousell.viewmodels.NewsViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    var mViewModel = NewsViewModel()

    @Mock
    lateinit var carousellServices: CarousellServices

    private var testSingle: Single<List<NewsDo>>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getNewsSuccessPath() {
        testSingle = Single.just(getDummyData())
        `when`(carousellServices.getCarousellNews()).thenReturn(testSingle)
        mViewModel.getNewsFromServer()
        Assert.assertEquals(4, mViewModel.handleNewsData.value?.size)
    }

    private fun getDummyData(): MutableList<NewsDo> {
        return mutableListOf(
            NewsDo(
                id = "121",
                title = "Paytm is launching its own digital wallet to improve payments for its users",
                description = "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash.",
                banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg"
            ),
            NewsDo(
                id = "122",
                title = "Paytm is launching its own digital wallet to improve payments for its users",
                description = "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash.",
                banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg"
            ),
            NewsDo(
                id = "123",
                title = "Paytm is launching its own digital wallet to improve payments for its users",
                description = "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash.",
                banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg"
            ),
            NewsDo(
                id = "124",
                title = "Paytm is launching its own digital wallet to improve payments for its users",
                description = "Due to launch next month in Singapore, CarouPay will allow buyers and sellers to complete transactions without leaving the Carousell app, rather than having to rely on third-party platforms or doing meet-ups to hand over cash.",
                banner_url = "https://storage.googleapis.com/carousell-interview-assets/android/images/carousell-siu-rui-ceo-tia-sg-2018.jpg"
            )
        )
    }

    @Before
    fun setUpRxSchedulers() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }
        })
    }
}