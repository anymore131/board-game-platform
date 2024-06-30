create table t_activity_picture
(
    id          int auto_increment
        primary key,
    name        varchar(255) null comment '照片名',
    fname       varchar(255) null comment '照片位置',
    activity_id int          null,
    constraint t_activity_picture_t_activity_id_fk
        foreign key (activity_id) references t_activity (id)
);

