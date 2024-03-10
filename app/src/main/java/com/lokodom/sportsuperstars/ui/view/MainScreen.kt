package com.lokodom.sportsuperstars.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Dehaze
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsBaseball
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lokodom.sportsuperstars.ui.navigation.TabItem
import com.lokodom.sportsuperstars.ui.view.cards.PlayerCard
import com.lokodom.sportsuperstars.ui.viewmodel.MainViewmodel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    viewModel: MainViewmodel = hiltViewModel()
){
    val context = LocalContext.current

    val tabItems= listOf<TabItem>(
        TabItem("sports",Icons.Outlined.SportsBaseball, Icons.Filled.SportsBaseball),
        TabItem("home",Icons.Outlined.Home, Icons.Filled.Home),
        TabItem("favs",Icons.Outlined.StarOutline, Icons.Filled.Star),

    )
    
    var selectedTabIndex by remember {
        mutableIntStateOf(1)
    }

    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex){
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress){
        if (!pagerState.isScrollInProgress){
            selectedTabIndex = pagerState.currentPage
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, item ->
                    Tab(selected = index == selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = {Text(text = item.title)},
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex){
                                    item.selectedIcon
                                }else item.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }
            HorizontalPager(state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {
                if (selectedTabIndex == 1){
                    SearchScreen(viewModel = viewModel, context)
                } else if (selectedTabIndex == 0){
                    SportsScreen(viewmodel = viewModel, context)
                }else if (selectedTabIndex == 2){
                    SavedDataScreen(viewmodel = viewModel, context)
                }
            }
        }
    }
}