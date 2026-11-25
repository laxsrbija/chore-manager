CREATE TABLE household
(
    id   varchar(255) NOT NULL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE user
(
    chanify_token    varchar(255),
    email            varchar(255),
    encoded_password varchar(255),
    id               varchar(255) NOT NULL PRIMARY KEY,
    image            varchar(255),
    name             varchar(255)
);

CREATE TABLE category
(
    household_id varchar(255),
    id           varchar(255) NOT NULL PRIMARY KEY,
    name         varchar(255)
);

CREATE TABLE item
(
    category_id varchar(255),
    id          varchar(255) NOT NULL PRIMARY KEY,
    name        varchar(255)
);

CREATE TABLE completion_history_item
(
    date_completed date,
    id             integer PRIMARY KEY AUTOINCREMENT,
    user_id        varchar(255) NOT NULL
);

CREATE TABLE task
(
    date_created           date,
    defer_date             date,
    dynamic_frequency      integer,
    enabled                boolean      NOT NULL,
    fixed_recurrence_day   integer,
    fixed_recurrence_month integer,
    reminder_frequency     integer,
    deferred_by_user_id    varchar(255),
    description            varchar(255),
    dynamic_date_unit      varchar(255),
    id                     varchar(255) NOT NULL PRIMARY KEY,
    item_id                varchar(255),
    name                   varchar(255),
    recurrence_type        varchar(255),
    reminder_date_unit     varchar(255)
);

CREATE TABLE task_completion_history
(
    history_item_id bigint       NOT NULL UNIQUE,
    task_id         varchar(255) NOT NULL,
    PRIMARY KEY (task_id, history_item_id)
);

CREATE TABLE task_reminder_users
(
    task_id varchar(255) NOT NULL,
    user_id varchar(255) NOT NULL,
    PRIMARY KEY (task_id, user_id)
);

CREATE TABLE user_household
(
    household_id varchar(255) NOT NULL,
    user_id      varchar(255) NOT NULL,
    PRIMARY KEY (user_id, household_id)
);

CREATE TABLE user_notification_channel
(
    notification_channel varchar(255),
    user_id              varchar(255) NOT NULL,
    PRIMARY KEY (user_id, notification_channel)
);

CREATE TABLE user_permission
(
    permission varchar(255),
    user_id    varchar(255) NOT NULL,
    PRIMARY KEY (user_id, permission)
);