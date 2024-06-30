create table t_activity
(
    id            int auto_increment
        primary key,
    club_id       int          null,
    activity_name varchar(30)  null,
    introduction  varchar(255) null comment '简介',
    tags          varchar(30)  null,
    address       varchar(255) null comment '地址',
    start_time    datetime     null comment '开始时间',
    end_time      datetime     null comment '结束时间',
    create_time   date         null comment '发起时间',
    constraint t_activity_ibfk_1
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_activity (club_id);

INSERT INTO board_game_platform.t_activity (id, club_id, activity_name, introduction, tags, address, start_time, end_time, create_time) VALUES (1, 1, '1', null, ';1;', '1', '2024-07-07 22:46:10', '2024-07-13 22:46:16', '2024-06-14');
