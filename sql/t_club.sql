create table t_club
(
    id           int auto_increment
        primary key,
    club_name    varchar(20)  null comment '俱乐部名',
    user_id      int          null comment '拥有者',
    create_time  date         null comment '俱乐部创建时间',
    province     varchar(30)  null comment '省',
    city         varchar(30)  null comment '城市',
    tags         varchar(30)  null comment '标签：以;分割',
    introduction varchar(255) null comment '简介',
    status       int          null comment '是否创建成功：成功为1；没通过为-1，待通过为0',
    constraint t_club_ibfk_1
        foreign key (user_id) references t_user (id)
);

create index user_id
    on t_club (user_id);