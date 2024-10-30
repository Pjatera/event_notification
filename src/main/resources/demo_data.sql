INSERT INTO changes_string (id, old_field, new_field)
VALUES (1, 'концерт Руки Вверх', 'концерт Три дня дождя'),
       (2, 'концерт Metallica', 'спектакль Синий поезд');
SELECT setval('changes_string_id_seq', (SELECT MAX(id) FROM changes_string));


INSERT INTO notifications(id, event_id, owner_id, name_id, maxplaces_id, date_id, cost_id, duration_id, locationid_id,
                          status, create_date, is_read)
VALUES (1, 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, current_timestamp, false),
       (2, 2, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL, '2024-09-13 22:07:00', false);

SELECT setval('notifications_id_seq', (SELECT MAX(id) FROM notifications));


INSERT INTO user_notifications (id, user_id, notification_id)
VALUES (1, 2, 1),
       (2, 2, 2);

SELECT setval('user_notifications_id_seq', (SELECT MAX(id) FROM user_notifications));
