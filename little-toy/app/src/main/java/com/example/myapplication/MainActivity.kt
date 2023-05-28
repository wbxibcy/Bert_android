package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


interface MyApiService {
    @POST("sentence")
    suspend fun postData(@Body data: String): String
}

object RetrofitClient {
    private const val BASE_URL = "http://192.168.1.139:5000"

    val apiService: MyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MyApiService::class.java)
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PostButton()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostButton() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val responseText = remember { mutableStateOf("") }
    val inputText = remember { mutableStateOf("") }
    val res = processBackendResponse(responseText.value)

    Column {
        TextField(
            value = inputText.value,
            onValueChange = { inputText.value = it },
            label = { Text(text = "在此输入文本") },
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = {
                lifecycleOwner.lifecycleScope.launch {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitClient.apiService.postData(inputText.value)
                    }

                    responseText.value = response
                }
            }
        ) {
            Text(text = "Send")
        }

        Text(text = res)
    }
}

fun processBackendResponse(value: String) : String {
    return when (value) {
        "1" -> {
            // 执行对应于值为1的操作
            "消极"
        }

        "2" -> {
            // 执行对应于值为2的操作
            "稍微消极"
        }

        "3" -> {
            // 执行对应于值为3的操作
            "平平无奇"
        }

        "4" -> {
            // 执行对应于值为4的操作
            "有点积极"
        }

        "5" -> {
            // 执行对应于值为5的操作
            "积极的很"
        }

        else -> {
            // 执行对应于其他值的操作
            "你发的啥玩意，重发"
        }
    }
}
