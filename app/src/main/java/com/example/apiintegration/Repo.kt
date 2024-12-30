package com.example.apiintegration

import retrofit2.Response

class Repo {
    suspend fun getData():Response<ResponseModel>{
        return RetrofitClass.api.getData()
    }
}
//    suspend function used
//    it does not block any response if another response occur at same time
//   block nhi krta =
//    manloo ek response anaa hai but ek alg response agya  tooh voh pehle wale ko block nhi krega thread mai bejdega