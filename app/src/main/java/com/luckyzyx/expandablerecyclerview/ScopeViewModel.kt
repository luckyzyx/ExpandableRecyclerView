package com.luckyzyx.expandablerecyclerview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.*

@JsonClass(generateAdapter = true)
data class ScopeWrapper(val scopes: List<Scope>)

@JsonClass(generateAdapter = true)
data class Scope(val title:Any,val summer:Any?,val funcs:List<Func>)

@JsonClass(generateAdapter = true)
data class Func(val title:Any,val summer:Any?,val key:String,val type:String?)

class ScopeViewModel(application: Application) : AndroidViewModel(application) {

    val scopeList = MutableLiveData<List<Scope>>()

    //加载数据
    @OptIn(DelicateCoroutinesApi::class)
    fun loadData(data:ScopeWrapper){
        GlobalScope.launch(Dispatchers.Main){
            val scopes = withContext(Dispatchers.IO){

                val scopeMap = HashMap<String, Scope>()
                //作用域
                //存储键值 作用域id -> 作用域组
                for (scope in data.scopes) {
                    scopeMap[scope.title.toString()] = scope
                }
                data.scopes
            }
            scopeList.value = scopes
        }
    }
}