create table t_user_join
(
    id        int  not null
        primary key,
    user_id   int  null comment '用户id',
    club_id   int  null comment '俱乐部id',
    join_time date null comment '加入时间',
    club_type int  null comment '俱乐部权限：1为管理，0为普通',
    constraint t_user_join_ibfk_1
        foreign key (user_id) references t_user (id),
    constraint t_user_join_ibfk_2
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_user_join (club_id);

create index user_id
    on t_user_join (user_id);

