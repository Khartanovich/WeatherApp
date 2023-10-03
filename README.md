# WeatherApp
## Необходимо было реализовать приложение со многими источниками данных.
### 1.Экран поиска погоды. На этом экране мы вводим название города, по нажатию на кнопку поиска ищем данные о погоде с помощью API. 
Если данные были успешно загружены, открываем новое окно с данными о погоде. Загруженные данные надо сохранить в базу данных — вдруг дальше у нас не будет сети, а погоду нужно будет знать?
Если API недоступно, но данный город ранее уже был загружен, у пользователя есть возможность посмотреть ранее загруженную информацию.
Если погода ни разу не была получена, об этом на экран выводится соответствующее уведомление.
### 2.Экран деталей о погоде. Экран отображает данные, получаемые по API или из базы данных, в зависимости от того, что на текущий момент доступно
### 3.Экран сохранённых данных. Здесь есть возможность посмотреть весь список предзагруженных городов, перейти внутрь и посмотреть погоду.
## Важные функции приложения:
### 1.Экран поиска сохраняет данные последнего поиска в поле ввода (используйте для этого SharedPrefs) и отображает их, пока пользователь не ввёл ни одного символа. При нажатии на предыдущий поиск подставляет значение в поле ввода и начинает искать актуальную погоду.
### 2.Данные о погоде сохранены в базе данных (в качестве primary key используйте связку: название города + дата данных о погоде).
### 3.При загрузке данных из API новые данные перезаписывают старые. 
### 4.Поиск данных о погоде регистронезависимый и может искать по неполному имени (например, находить Москву по запросу «мос»).
### 5.Каждый из трёх экранов реализован с помощью MVVM-архитектуры.
### 6.Каждая ViewModel получает в конструкторе соответствующий Repository.
### 7.Репозиторий получен с помощью Dagger.
### 8.Каждый из источников данных (API, база данных, SharedPrefs) существует в единственном виде (синглтон) и также получен с помощью Dagger.
### 9.ViewModels не работают с данными уровня базы данных или API. Конвертируйте данные в сущности MVVM в Repository.
### 10.ViewModels не знают о конкретных реализациях и принципах хранения данных. Описания репозиториев просто предоставляют возможности для загрузки данных.