package com.sir.entity.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import com.sir.entity.ui.theme.AppTheme

@Composable
fun <T> PaginationList(
    itemList: List<T>,
    loadNewPage: () -> Unit,
    createItem: @Composable (T) -> Unit,
) {
    val startPaginationItemCount = 3
    val lazyColumnListState = rememberLazyListState()

    val shouldStartPaginate = remember {
        derivedStateOf {
            with(lazyColumnListState.layoutInfo) {
                if (visibleItemsInfo.isNotEmpty()) {
                    visibleItemsInfo.last().index >= totalItemsCount - startPaginationItemCount
                } else {
                    false
                }
            }
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        loadNewPage()
    }

    LazyColumn(
        state = lazyColumnListState,
        contentPadding = PaddingValues(AppTheme.dimensions.defaultPadding)
    ) {
        itemsIndexed(itemList) { id, item ->
            key(id) {
                createItem(item)
            }
        }
    }
}