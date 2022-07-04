package com.anafthdev.musicompose2.feature.main

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.musicompose2.R
import com.anafthdev.musicompose2.data.MusicomposeDestination
import com.anafthdev.musicompose2.data.SortType
import com.anafthdev.musicompose2.feature.album_list.AlbumScreen
import com.anafthdev.musicompose2.feature.artist_list.ArtistListScreen
import com.anafthdev.musicompose2.feature.home.HomeScreen
import com.anafthdev.musicompose2.foundation.uicomponent.MoreOptionPopup
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class,
	ExperimentalAnimationApi::class
)
@Composable
fun MainScreen(
	navController: NavController
) {
	
	val scope = rememberCoroutineScope()
	val pagerState = rememberPagerState()
	
	var isMoreOptionPopupShowed by remember { mutableStateOf(false) }
	
	BackHandler {
		when {
			pagerState.currentPage != 0 -> scope.launch {
				pagerState.animateScrollToPage(0)
			}
			else -> navController.popBackStack()
		}
	}
	
	Column(
		modifier = Modifier
			.statusBarsPadding()
			.fillMaxSize()
	) {
		SmallTopAppBar(
			colors = TopAppBarDefaults.smallTopAppBarColors(
				containerColor = Color.Transparent
			),
			title = {},
			navigationIcon = {
				IconButton(
					onClick = {
						navController.navigate(MusicomposeDestination.Setting.route)
					}
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_setting),
						contentDescription = null
					)
				}
			},
			actions = {
				IconButton(
					onClick = {
						navController.navigate(MusicomposeDestination.Search.route)
					}
				) {
					Icon(
						imageVector = Icons.Rounded.Search,
						contentDescription = null
					)
				}
				
				IconButton(
					onClick = {
						isMoreOptionPopupShowed = !isMoreOptionPopupShowed
					}
				) {
					Icon(
						imageVector = Icons.Rounded.MoreVert,
						contentDescription = null
					)
				}
				
				if (isMoreOptionPopupShowed) {
					MoreOptionPopup(
						options = listOf(
							stringResource(id = R.string.sort_by)
						),
						onDismissRequest = {
							isMoreOptionPopupShowed = false
						},
						onClick = { i ->
							when (i) {
								0 -> {
									navController.navigate(
										MusicomposeDestination.BottomSheet.Sort.createRoute(
											type = when (pagerState.currentPage) {
												0 -> SortType.SONG
												1 -> SortType.ALBUM
												2 -> SortType.ARTIST
												else -> SortType.SONG
											}
										)
									)
								}
							}
						},
						modifier = Modifier
							.padding(8.dp)
					)
				}
			}
		)
		
		HorizontalPager(
			count = 4,
			state = pagerState
		) { page ->
			when (page) {
				0 -> HomeScreen()
				1 -> AlbumScreen()
				2 -> ArtistListScreen()
				3 -> {
					// TODO: PlaylistScreen
				}
			}
		}
	}
}
