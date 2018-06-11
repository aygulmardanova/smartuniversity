INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Программирование на Java'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Проектор'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Программирование на Java'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Математический анализ'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Линейная алгебра'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Аналитическая геометрия'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Робототехника'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Роботы'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Робототехника'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Робототехника'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Проектор'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Робототехника'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Интерактивная доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Методология научных исследований'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Проектор'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Методология научных исследований'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Трибуна'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Информационная безопасность'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Операционные системы ОС'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Основы информационного поиска'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Интерактивная доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Теория систем и системный анализ'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Управление проектами'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Проектор'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Управление проектами'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Круглый стол'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Управление проектами'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Интерактивная доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Разработка API для мобильных приложений'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Проектор'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Разработка API для мобильных приложений'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = '3D-моделирование'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Интерактивная доска'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = '3D-моделирование'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Компьютер'));
INSERT INTO uni.wish (from_id, stud_id, teach_id, subj_id, aud_id, week_day,
                      pair_st_num, pair_end_num, wish_info_id, wish_status_id, equip_id)
VALUES (NULL, NULL, NULL, (SELECT id
                           FROM uni.subject
                           WHERE name = 'Физика'), NULL, NULL,
              NULL, NULL, (SELECT id
                           FROM uni.wish_info
                           WHERE type = 'SUBJ_EQUIP'), (SELECT id
                                                        FROM uni.wish_status
                                                        WHERE name = 'SYSTEM'), (SELECT id
                                                                                 FROM uni.equipment
                                                                                 WHERE name = 'Доска'));