create table t_activity
(
    id           int          not null
        primary key,
    club_id      int          null,
    name         varchar(30)  null,
    introduction varchar(255) null comment '简介',
    tags         varchar(30)  null,
    address      varchar(255) null comment '地址',
    start_time   datetime     null comment '开始时间',
    end_time     datetime     null comment '结束时间',
    create_time  date         null comment '发起时间',
    constraint t_activity_ibfk_1
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_activity (club_id);

