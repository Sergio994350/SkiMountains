# SkiMountains

Firebase, Retrofit2, Room, Koin, Coroutines, Clean Architecture project.
Glide for load pics, intuit for scalable sizes for different devices, Lottie for splash.
(Проект использует Firebase, Retrofit2, Room, Koin, Coroutines, Clean Architecture. Glide для загрузки изображений, intuit для подгонки размеров всех элементов у разных устройств, Lottie для splash-экрана)

Divided by 3 features - ski_places, weather, authorization. 
(Проект разделен на 3 фичи: курорты, погода, авторизация)

Get data (all of 128 Russians SkiPlaces) from Firebase Firestore,
fill data into created Room database,
show list of ski places in RecyclerView in Home Screen.
(Получаем данные обо всех 128 горнолыжных курортах России с Firebase, заливаем данные в базу данных Room, 
показываем список курортов в Recycler View на главном экране)

Search SkiPlace you want to find in search field by name, region name, macro-region name.
Search can be by part of the word, no matter lower or upper case. 
(Поиск курорта по имени, названию региона, названию макро-региона. Поиск можно вести по части слова, в любом регистре)

Check if No-internet, get toasts about.
(Проверка на отсутствие интернета)

Get weather from open-weather api to show weather type and temperature 
in Detail Fragment.
(Получаем данные о погоде с API open-weather, показываем температуру и тип погоды в фрагменте Detail)

Click on any SkiPlace to enter Details Fragment.
Get 'right-now' weather from open-weather api to show weather type (28 types) 
and temperature in Detail Fragment.

Get HowToGet Screen, WebSite, WebCameras, YouTube video by click on Button.
If no url, show toast about. 
(При клике в Detail фрагменте по соответствующей кнопке показываем экраны "Как добраться", "Веб-сайт", "Веб-камера", "YouTube-ролик".
Если ресурс отсутствует, показываем тост.)

Save to favorites, swipe delete, get snackbars for save and delete.
(Сохранение курорта в избранном, удаление свайпом, снэкбары при добавлении и удалении. Реализована возможность отменить удаление)

![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-001.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-002.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-003.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-004.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-005.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-006.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-007.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-008.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-009.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-010.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-011.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-012.png)
![alt tag](https://github.com/Sergio994350/SkiMountains/blob/master/app/src/main/res/screenshots/screenshot-013.png)
