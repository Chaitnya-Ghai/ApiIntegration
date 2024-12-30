package com.example.apiintegration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

val TAG="VModel"
class VModel: ViewModel() {
    var getRes:MutableLiveData<SealedClass<ResponseModel>?> = MutableLiveData()
    var uRepo=Repo()
    fun apiHit(){
        getRes.value=null
        getRes.value=SealedClass.Loading()
        // viewModelScope launch only when and only when data is coming from server
        viewModelScope.launch {
            try {
                Log.e(TAG, "getData: kro",)
                val response = uRepo.getData()
                Log.e(TAG, "getData: hua",)
                Log.e(TAG, ":${uRepo.getData()} ",)
                if (response.code()==200){
                    if (response.body()!=null){
                        getRes.setValue(SealedClass.Success(response.body()))
                        Log.e(TAG, "getData: success${response.body()}",)
                    }}else{
                    Log.e("response", "getData: Failure ${response.body()}",)
                    getRes.value=SealedClass.Error(response.message())
                }}
            catch (e : Exception){
                Log.e(TAG, "exception :${e.message} ", )
//printStackTrace() =
//gives snippet ki itne part mai error hai,kaha pr kya error hai,gives description of each-every line
                e.printStackTrace()
            }
        }
    }
//    repo() means ki uske pass pura ka pura response pdaa
}
//    mutableLiveData = liveServer gives live data = usko auto-hit krna,
//    iske through hee live data ayega,
//    live data live hee handle hoynge