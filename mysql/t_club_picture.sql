create table t_club_picture
(
    id      int auto_increment
        primary key,
    name    varchar(30) null comment '图片名',
    fname   varchar(30) null comment '图片地址',
    club_id int         null comment '俱乐部id',
    constraint t_club_picture_ibfk_1
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_club_picture (club_id);

INSERT INTO board_game_platform.t_club_picture (id, name, fname, club_id) VALUES (1, '1', '11120240622210207994StarRail_Image_1718888620.png', 1);
INSERT INTO board_game_platform.t_club_picture (id, name, fname, club_id) VALUES (2, '11120240622211544102StarRail_Image_1703676295', '小明2024063016360319811120240622211544102StarRail_Image_1703676295.png', 1);
INSERT INTO board_game_platform.t_club_picture (id, name, fname, club_id) VALUES (3, '崩坏：星穹铁道 2024_5_8 15_37_03', '小明2024063016365235崩坏：星穹铁道 2024_5_8 15_37_03.png', 1);
