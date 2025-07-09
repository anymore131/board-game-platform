create table t_activity_comments
(
    id            int auto_increment
        primary key,
    user_id       int          null comment '用户id',
    activity_id   int          null comment '活动id',
    comments      varchar(255) null comment '评论',
    comments_time date         null comment '评论时间',
    constraint t_activity_comments_ibfk_1
        foreign key (user_id) references t_user (id),
    constraint t_activity_comments_t_activity_id_fk
        foreign key (activity_id) references t_activity (id)
);

create index user_id
    on t_activity_comments (user_id);