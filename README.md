java_mvc
========
Описание:
Заказ туристической путевки (java, mvc, hibernate, postgres, play, html)

1) Необходима Linux'о подобная система с установленным postgres (от 8.4 до 9.1.8 все подойдут)

2) В консоли создаем БД: 
# sudo su
# su - postgres
# psql
postgres=# CREATE USER vitaliya WITH password 'casualties';
postgres=# CREATE DATABASE tourfirm WITH OWNER vitaliya;
postgres=# \q

3) Загружаем в БД все данные, для этого с помощью команды exit выходим в свой домашний каталог, переходим в папку с моими скриптами и файлами и в ней выполняем:
# psql tourfirm < scheme_mvc.sql
# psql tourfirm < insert_mvc.sql 
# psql tourfirm < procedure_mvc.sql
будем надеяться что все прошло успешно и все данные загрузились в БД

4) Заходим через консоль в дирикторию с java проектом: cd /..../h_mvc

5) Запускаем проект 
# play/play run

6) В браузере переходим по localhost:9000 (если номер порта по какой-либо причине на вашем компьютере изменится, его можно посмотреть в консоли: 
Listening for HTTP on port 9000 (Waiting a first request to start) ...
)

7) Далее пробуем оформить путевку (это еще не конечный вариант, поэтому БД достаточно мала и оформление крайне скудно)

7.1) Страна: Россия
    Город: Астрахань, Москва, Красногорск
7.2) Выбираете подходящую комнату
7.3) Вводите данные в поле "Регистрация"
7.4) Выбираете дату, период - целое число, эквивалентное кол-ву дней
7.5) Проверяете данные и подтверждаете заказ
