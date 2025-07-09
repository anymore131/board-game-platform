create table t_user_attend
(
    id          int auto_increment
        primary key,
    user_id     int null,
    activity_id int null,
    constraint t_user_attend_t_activity_id_fk
        foreign key (activity_id) references t_activity (id),
    constraint t_user_attend_t_user_id_fk
        foreign key (user_id) references t_user (id)
)
    comment '用户参加活动记录';