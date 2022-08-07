package com.example.testdemodubai.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdemodubai.model.api.ApiHelper
import com.example.testdemodubai.model.data.BaseClass
import com.example.testdemodubai.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper): ViewModel() {
    private var mainLiveData=MutableLiveData<Resource<BaseClass>>()

    init {
        makeNetworkCall()
    }

     fun makeNetworkCall(){
        viewModelScope.launch {
            mainLiveData.postValue(Resource.loading(null))
            try {
                val mostPopularNews = apiHelper.getMostPopular("all-sections","7","qlo1RwF90WFUxizL5l4gkaIwmwoHN2sq")
                mainLiveData.postValue(Resource.success(mostPopularNews))
            }
            catch (e:Exception){
                mainLiveData.postValue(Resource.error(e.toString(), null))
            }
        }
    }


    fun getPapularNews(): LiveData<Resource<BaseClass>> {
        return mainLiveData
    }


}