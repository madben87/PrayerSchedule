# PrayerSchedule
## Техническое Задание для тестового приложения на Android
### **Цель:**
* ### *Построить приложение, показать навыки разработки, знания принципов ООП и платформы Android*
### **Описание:**
* ### *Разработать приложения для отображения ежедневного расписания молитв в мусульманском вероисповедании на текущий месяц Григорианского календаря. Приложение имеет две локализации для: Григорианского и Арабского календарей. Все необходимые данные, включая локализации, приходят в ответах API методов (описания и документация по ссылке https://aladhan.com/prayer-times-api)*

### **Экраны:**
### *Приложение состоит из 3-х экранов*
* ### **Splash screen**
  #### **Первый экран который видит пользователь при открытии приложения. На данном экране необходимо узнать местоположение пользователя (не обязательно точное, которое требует разрешение пользователя), для последующего запроса данных по API. При необходимости, происходит загрузка и кэширование данных, для отображения на последующих экранах. Если данные на текущий месяц уже были кэшированы ранее, то загрузки не происходит и пользователю сразу показывается Home screen. Выглядит экран так: Однотонный фон, любого цвета, и центрированная картинка (любая на выбор, можно использовать иконку андроида ic_launcher)**
* ### **Home screen**
  #### **Главный экран приложения. Экран представляет собой список дней текущего месяца. Элемент списка выглядит следующим образом:**
	#### **Число месяца  Название месяца  Год**
  * #### **Григорианский календарь: данные берутся из gregorian объекта в ответе методов API (Например, 15 Май 2019)**
  * #### **Арабский календарь: данные берутся из объекта hijri  в ответе методов API (Например, 1 Ramaḍān 1439)**
	#### **По умолчанию выбран Григорианский календарь. В тулбаре этого экрана, в правом конце есть кнопка вызова всплывающего меню. В меню находится два пункта  Gregorian и Hijri. Если выбрать один из пунктов то список дней перезагрузиться в соответствующий календарь. При заходе на экран текущий день в списке выделен (элемент списка окружен красной рамкой) и список проскролен к этому дню, так что пользователю не нужно скролить до него самостоятельно. При клике на элемент Списка происходит переход на Timings screen. При нажатии на кнопку “Назад” выходим из приложения.**
* ### **Timings screen**
  #### **Экран для отображения объекта timings в ответе методов API, выбранного на предыдущем экране дня. В тулбаре экрана находится кнопка “Назад” и заголовок - название дня недели. Название берется из календаря (Григорианского или Арабского) в соответствии с выбором пользователя на Home screen. Контент экрана представляет собой список, элемент которого выглядит так :**
  #### **Название молитвы : время**
  #### **(Например, Fajr : 03:57)**
  #### **Нажатие на кнопку “Назад” возвращает на Home screen**
  
### **API методы:**
#### **Документация находится по ссылке https://aladhan.com/prayer-times-api.**
#### **Необходимые API методы (любой на выбор):**
* #### *Prayer Times Calendar - http://api.aladhan.com/v1/calendar*
* #### *Prayer Times Calendar by address - http://api.aladhan.com/v1/calendarByAddress*
* #### *Prayer Times Calendar by city - http://api.aladhan.com/v1/calendarByCity*
#### **Разница в перечисленных API методах заключается в передаче местоположения 1) по координатам, 2) по адресу 3) по городу. На параметры методов не нужные для выполнения задание не обращать внимания. В документации API после каждого описания метода есть пример запроса и ответа. Определить местоположение (необязательно точное) нужно на Splash screen. Какое именно местоположение запрашивать у пользователя решать разработчику (что быстрее можно сделать тот метод и выбирайте).**

### **Обратить внимание!**
#### **Если пользователь существенно поменяет свое местоположение, например другой город или страна то кэшированные данные на текущий Григорианский месяц необходимо обновить. В то же время при незначительном изменении местоположения (территория в пределах размера города) обновлять кэш не нужно.**

### **Будет плюсом**
#### **Как дополнительное задание, можно в всплывающем меню на Home screen добавить еще два пункта для светлой и темной темы приложения. При выборе темы приложение меняет цветовое оформление своих элементов в соответствии с определенными стилями в приложении.**
