INSERT INTO Hotel (id, name_hotel, country, stars, town) VALUES 
 (1, 'Апельсин', 'Россия', 3, 'Астрахань'),
 (2, 'Космос', 'Россия', 5, 'Москва'), (3, 'Крошка Енот', 'Россия', 4, 'Красногорск');
INSERT INTO Room (id, type_of_room, cost, num, full_room, bar, id_hotel) VALUES 
 (1, 2, 1000, 10, 6, 'Y', 3),
 (2, 3, 800, 5, 4, 'N', 1), 
 (3, 3, 2000, 5, 4, 'Y', 1), 
 (4, 1, 700, 5, 4, 'Y', 1), 
 (5, 3, 500, 5, 4, 'Y', 3), 
 (6, 3, 3000, 20, 14, 'Y', 2);

INSERT INTO Provider (id, name_provider) VALUES 
(1, 'ОАО Тур-Поставщики'),
(2, 'Toyr-na-toyr');

INSERT INTO Hotel_Provider (id, id_hotel, id_provider, limit_of_time, dogovor) VALUES 
 (1, 1, 1, '2013-04-01', 'Договор 1'),
 (2, 2, 1, '2015-09-01', 'Договор 2'), 
 (3, 3, 2, '2012-11-01', 'Договор 3');
INSERT INTO Season (id, name_season, percent_sum) VALUES 
(1, 'Январь', -12.3),
 (2, 'Январь', 5.9);

INSERT INTO Transport (id, sum_transport, type_of_transport) VALUES 
(1, 13000, 'Самолет'),
(3, 1300, 'Автобус'),
 (2, 1650, 'Поезд');

INSERT INTO Buyer (id, name_buyer, surname, middle_name, passport_data, zagran_passport, login, password) VALUES 
(1, 'Виталий', 'Пономарев', 'Алекснеевич', '4508 987711', '4567899', 'vitalik', 'pon_vitalik1985'),
 
(2, 'Анна', 'Иванова', 'Владимировна', '1008 123710', '48765432', 'konfetka', '123first321'),
(3, 'Александр', 'Морозов', 'Юрьевич', '1008 123710', '48765432', 'alex', 'Nitron')
;

INSERT INTO Permit (id, id_buyer, id_transport, id_season, id_provider, id_room, start_date, period, visa, final_sum) VALUES 
(1, 1, 1, 2, 2, 3, '2013-01-10', 8, 1000, 40242), 
(2, 2, 1, 2, 2, 3, '2013-01-10', 8, 1000, 40242);
