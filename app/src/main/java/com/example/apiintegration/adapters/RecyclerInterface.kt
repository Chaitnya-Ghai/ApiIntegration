package com.example.apiintegration.adapters

import com.example.apiintegration.ResponseModel

interface RecyclerInterface {
    fun delete(position: Int)
    fun update(position: Int,oldModel: ResponseModel.Data)
    fun itemClick(position: Int , model: ResponseModel.Data)
}
