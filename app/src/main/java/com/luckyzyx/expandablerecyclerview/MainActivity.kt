package com.luckyzyx.expandablerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.luckyzyx.expandablerecyclerview.databinding.ActivityMainBinding

private lateinit var viewModel: ScopeViewModel
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initScope()
    }

    //加载页面
    private fun initScope() {
        //短列表
        val shortList = false

        val modelFactory = SavedStateViewModelFactory(application, this)
        viewModel = ViewModelProvider(this, modelFactory)[ScopeViewModel::class.java]
        viewModel.scopeList.observe(this) {
            //判断限制List Size
            val list = if (shortList) it.subList(0, 1) else it
            binding.recyclerView.adapter = setAdapter(list)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    //设置适配器
    private fun setAdapter(list: List<Scope>): ScopeAdapter {
        return ScopeAdapter(list) { groupID: Int, childID: Int ->
            Toast.makeText(this, "Group:$groupID Child:$childID", Toast.LENGTH_SHORT).show()
        }
    }


    //创建数据
    private fun createData(): ScopeWrapper {
        return ScopeWrapper(
            listOf(
                Scope(
                    "系统框架", "", listOf(
                        Func("系统框架", "不生效可尝试重启", "Android", null),
                    )
                ),
                Scope(
                    "系统界面", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "系统桌面", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "时钟", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "相机", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "安全中心", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "应用包安装程序", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "游戏助手", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "云服务", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "好多动漫", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
                Scope(
                    "图凌", "", listOf(
                        Func("移除状态栏上层警告", "", "", "switch"),
                        Func("移除状态栏上层警告", "", "", "switch"),
                    )
                ),
            ),
        )
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.scopeList.value.isNullOrEmpty()) {
            viewModel.loadData(createData())
        }
    }
}