create table t_game_type
(
    id           int auto_increment
        primary key,
    name         varchar(20)  null comment '游戏名',
    referrals    varchar(20)  null comment '推荐人数(xx人-xx人)',
    introduction varchar(255) null comment '介绍',
    insert_time  date         null comment '创建时间',
    user_id      int          null comment '创建人',
    type         int          null comment '状态（1为通过，0为未通过）',
    constraint t_game_type_t_user_id_fk
        foreign key (user_id) references t_user (id)
);