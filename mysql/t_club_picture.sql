create table t_club_picture
(
    id      int         not null
        primary key,
    name    varchar(30) null comment '图片名',
    fname   varchar(30) null comment '图片地址',
    club_id int         null comment '俱乐部id',
    time    date        null comment '添加时间',
    constraint t_club_picture_ibfk_1
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_club_picture (club_id);

