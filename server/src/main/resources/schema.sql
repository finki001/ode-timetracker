create table TASK
(
    ID   INTEGER      not null
        primary key,
    NAME VARCHAR(255) not null
);

create table USER
(
    ID            INTEGER      not null
        primary key,
    FIRSTNAME     VARCHAR(255) not null,
    LASTNAME      VARCHAR(255) not null,
    LOGIN         VARCHAR(255) not null,
    PASSWORD_HASH VARCHAR(255) not null,
    ROLE          VARCHAR(255) not null
);

create table RECORD
(
    ID         INTEGER not null
        primary key,
    END_TIME   TIMESTAMP,
    NOTES      VARCHAR(255),
    START_TIME TIMESTAMP,
    TASK_ID    INTEGER,
    USER_ID    INTEGER,
    constraint FK36C93VIFNM8K9B6AQ8XRR4XCS
        foreign key (TASK_ID) references TASK (ID),
    constraint FKENY3549XAR8RNRCMDW3HL0LA1
        foreign key (USER_ID) references USER (ID)
);
