create table t_club_picture
(
    id      int auto_increment
        primary key,
    name    varchar(255) null comment '图片名',
    fname   varchar(255) null comment '图片地址',
    club_id int          null comment '俱乐部id',
    constraint t_club_picture_ibfk_1
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_club_picture (club_id);