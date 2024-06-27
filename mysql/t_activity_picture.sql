create table t_activity_picture
(
    id          int          not null
        primary key,
    name        varchar(255) null comment '照片名',
    fname       varchar(255) null comment '照片位置',
    activity_id int          null,
    constraint t_activity_picture_ibfk_1
        foreign key (activity_id) references t_activity (id)
);

create index activity_id
    on t_activity_picture (activity_id);

