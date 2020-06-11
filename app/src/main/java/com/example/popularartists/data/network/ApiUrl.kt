package com.example.popularartists.data.network

const val METHOD = "?method="
const val API_KEY = "api_key=a5421142bbfcd83e71078d2717653282"
const val JSON_FORMAT = "format=json"

const val GET_TOP_ARTIST_BY_COUNTRY = "${METHOD}geo.gettopartists&$API_KEY&$JSON_FORMAT&"

const val GET_TOP_ALBUMS_BY_ARTIST = "${METHOD}artist.gettopalbums&$API_KEY&$JSON_FORMAT&"

const val GET_ALBUM = "${METHOD}album.getinfo&$API_KEY&$JSON_FORMAT&"