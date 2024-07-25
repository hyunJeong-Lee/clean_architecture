package com.leehj.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leehj.cleanarchitecture.ui.item.WordInfoItemCardView
import com.leehj.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.leehj.cleanarchitecture.viewmodel.WordInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewmodel: WordInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CleanArchitectureTheme {
                val snackbarHostState = remember{ SnackbarHostState() }

                //viewModel 의 eventFlow 를 수집해 UI 이벤트 처리.
                //ShowSnackbar 이벤트 발생 시 스낵바 표시
                LaunchedEffect(key1 = true) {
                    viewmodel.eventFlow.collectLatest { event ->
                        when(event){
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                snackbarHostState.showSnackbar(
                                    message = event.message,
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }

                DictionaryView(snackbarHostState)
            }
        }
    }

    @Composable
    fun DictionaryView(snackbarHostState: SnackbarHostState){
        val state = viewmodel.state.value
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .background(MaterialTheme.colorScheme.background),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)){
                Column(
                    modifier = Modifier,
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        value = viewmodel.searchQuery.value,
                        onValueChange = {query ->
                            viewmodel.onSearch(query)
                        },
                        placeholder = {
                            Text(text = "Search...")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(state.wordInfoItems.size){ index ->
                            val wordInfo = state.wordInfoItems[index]
                            WordInfoItemCardView(wordInfo = wordInfo)
                        }
                    }
                }
                if(state.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

