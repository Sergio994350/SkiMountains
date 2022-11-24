package com.epicteam1.skimountains.feature_ski_places.core

object Constants {
    const val FIREBASE_COLLECTION_NAME = "ski_places_dtb"
    const val LOCAL_DATABASE_NAME = "ski_place_local_db.db"
    const val ENTITY = "entity"
    const val PRE_SAVED = "1"
    const val NOT_SAVED = "0"
    const val NO_INTERNET = "Нет подключения к интернету"
    const val SORT_LIST = "Сортировка списка"
    const val LOAD_FROM_LOCAL_DATABASE = "Загрузка из локальной базы"
    const val DETAILS = "details"
    const val HOW_TO_GET_ARGS = "how_to_get_args"
    const val SKI_PLACE_DELETED = "Курорт удален"
    const val SKI_PLACE_SAVED = "Курорт сохранен"
    const val UNDO = "отменить"
    const val HAS_NIGHTRIDE = "true"
    const val HAS_RAILWAY = "true"
    const val LOG_OUT = "Выйти"
    const val EMAIL_NOT_FOUND = "email не задан"
    const val SIGN_UP = "Зарегистрироваться"
    const val LOG_OUT_SUCCESS = "Успешный выход"
    const val LOG_IN_SUCCESS = "Успешный вход"
    const val EMAIL_HAS_SEND = "Email отправлен"
    const val CANNOT_SEND_PASSWORD_RESET = "сброс пароля не отправлен"
    const val SIGN_UP_SUCCESS = "Пользователь зарегистрирован"
    const val EMPTY_EMAIL_MESSAGE = "email должен быть заполнен"
    const val LOGOUT_FAILURE_MESSAGE = "ошибка выхода"
    const val NO_YOUTUBE_LINK = "Видео на YouTube недоступно"
    const val NO_WEB_CITE = "Web-сайт недоступен"
    const val NO_WEB_CAMERAS = "Web-камеры недоступны"
    const val SKI_PLACES_RELOAD = "Список трасс обновлен"

    const val SKIPLACE_ID = "skiPlaceId"
    const val SKIPLACE_BLACKTRAILS = "blackTrails"
    const val SKIPLACE_BLUETRAILS = "blueTrails"
    const val SKIPLACE_CABINLIFTS = "cabinLifts"
    const val SKIPLACE_CHAIRLIFTS = "chairLifts"
    const val SKIPLACE_CITIESNEARBY = "citiesNearby"
    const val SKIPLACE_GREENTRAILS = "greenTrails"
    const val SKIPLACE_HEIGHTDIFF = "heightDiff"
    const val SKIPLACE_HOWTOGETPIC = "howToGetPic"
    const val SKIPLACE_HOWTOGETTEXT = "howToGetText"
    const val SKIPLACE_LATITUDE = "latitude"
    const val SKIPLACE_LONGITUDE = "longitude"
    const val SKIPLACE_MAINPIC = "mainPic"
    const val SKIPLACE_MAXHEIGHT = "maxHeight"
    const val SKIPLACE_MAXLENGTHTRAIL = "maxLengthTrail"
    const val SKIPLACE_NAMEENG = "nameEng"
    const val SKIPLACE_NAMERUS = "nameRus"
    const val SKIPLACE_NIGHTRIDE = "nightRide"
    const val SKIPLACE_RAILWAYAVAIL = "railwayAvail"
    const val SKIPLACE_RATING = "rating"
    const val SKIPLACE_REDTRAILS = "redTrails"
    const val SKIPLACE_REGIONBIG = "regionBig"
    const val SKIPLACE_REGIONENG = "regionEng"
    const val SKIPLACE_REGIONRUS = "regionRus"
    const val SKIPLACE_SEASONDATEBEGIN = "seasonDateBegin"
    const val SKIPLACE_SEASONDATEEND = "seasonDateEnd"
    const val SKIPLACE_SUMTRAILSLENGTH = "sumTrailsLength"
    const val SKIPLACE_TOWLIFTS = "towLifts"
    const val SKIPLACE_WEBCAMERA = "webCamera"
    const val SKIPLACE_WEBCITE = "webCite"
    const val SKIPLACE_YOUTUBELINK = "youTubeLink"
    const val SKIPLACE_ISSAVED = "isSaved"

    val String.Companion.EMPTY: String get() = ""

    const val EMAIL_EMPTY = 1
    const val PASSWORD_EMPTY = 2
    const val PASSWORD_NOT_MATCH = 3

    const val SPLASH_DELAY = 2000L
}