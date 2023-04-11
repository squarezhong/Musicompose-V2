package com.anafthdev.musicompose2.data

sealed class Destination(open val route: String) {

	object Main: Destination("main")
	
	object Search: Destination("search")
	
	object Setting: Destination("setting")
	
	object Language: Destination("language")
	
	object Theme: Destination("theme")
	
	object ScanOptions: Destination("scan-options")
	
	object Album: Destination("album/{albumID}") {
		fun createRoute(albumID: String): String {
			return "album/$albumID"
		}
	}
	
	object Artist: Destination("artist/{artistID}") {
		fun createRoute(artistID: String): String {
			return "artist/$artistID"
		}
	}
	
	object Playlist: Destination("playlist/{playlistID}") {
		fun createRoute(playlistID: Int): String {
			return "playlist/$playlistID"
		}
	}
	
	object SongSelector: Destination("song-selector/{type}/{playlistID}") {
		fun createRoute(type: SongSelectorType, playlistID: Int): String {
			return "song-selector/${type.ordinal}/$playlistID"
		}
	}
	
	class BottomSheet {
		object MusicPlayer: Destination("music-player")
		
		object Sort: Destination("bottom-sheet/sort/{type}") {
			fun createRoute(type: SortType): String {
				return "bottom-sheet/sort/${type.ordinal}"
			}
		}
		
		object Playlist: Destination("bottom-sheet/playlist/{option}/{playlistID}") {
			fun createRoute(
				option: PlaylistOption,
				playlistID: Int = com.anafthdev.musicompose2.data.model.Playlist.default.id
			): String {
				return "bottom-sheet/playlist/${option.ordinal}/$playlistID"
			}
		}
		
		object DeletePlaylist: Destination("delete-playlist/{playlistID}") {
			fun createRoute(playlistID: Int): String {
				return "delete-playlist/$playlistID"
			}
		}
	}

}
