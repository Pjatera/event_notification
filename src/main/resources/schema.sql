CREATE TABLE IF NOT EXISTS changes_date
(
    id        BIGSERIAL PRIMARY KEY,
    old_field TIMESTAMP,
    new_field TIMESTAMP

);
CREATE TABLE IF NOT EXISTS changes_decimal
(
    id        BIGSERIAL PRIMARY KEY,
    old_field NUMERIC,
    new_field NUMERIC
);
CREATE TABLE IF NOT EXISTS changes_long
(
    id        BIGSERIAL PRIMARY KEY,
    old_field BIGINT,
    new_field BIGINT
);

CREATE TABLE IF NOT EXISTS changes_integer
(
    id        BIGSERIAL PRIMARY KEY,
    old_field INTEGER,
    new_field INTEGER
);

CREATE TABLE IF NOT EXISTS changes_string
(
    id        BIGSERIAL PRIMARY KEY,
    old_field VARCHAR(255),
    new_field VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS notifications
(
    id            BIGSERIAL PRIMARY KEY,
    event_id      BIGINT NOT NULL,
    owner_id      BIGINT NOT NULL,
    name_id       BIGINT,
    maxPlaces_id  BIGINT,
    date_id       BIGINT,
    cost_id       BIGINT,
    duration_id   BIGINT,
    locationId_id BIGINT,
    status        BIGINT,
    create_date   TIMESTAMP,
    is_read       BOOLEAN,
    FOREIGN KEY (name_id) REFERENCES changes_string (id),
    FOREIGN KEY (maxPlaces_id) REFERENCES changes_integer (id),
    FOREIGN KEY (date_id) REFERENCES changes_date (id),
    FOREIGN KEY (cost_id) REFERENCES changes_decimal (id),
    FOREIGN KEY (locationId_id) REFERENCES changes_long (id),
    FOREIGN KEY (duration_id) REFERENCES changes_integer (id),
    FOREIGN KEY (status) REFERENCES changes_string (id)
);

CREATE TABLE IF NOT EXISTS user_notifications
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL ,
    notification_id BIGINT NOT NULL ,
    FOREIGN KEY (notification_id) REFERENCES notifications (id),
    CONSTRAINT unique_event_user UNIQUE ( user_id,notification_id)
);


TRUNCATE TABLE notifications,changes_date,changes_decimal,changes_integer,changes_string,changes_long,user_notifications;