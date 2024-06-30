create table t_club_comments
(
    id            int auto_increment
        primary key,
    user_id       int          null comment '用户id',
    club_id       int          null comment '俱乐部id',
    comments      varchar(255) null comment '评论',
    comments_time date         null comment '评论时间',
    constraint t_club_comments_ibfk_1
        foreign key (user_id) references t_user (id),
    constraint t_club_comments_ibfk_2
        foreign key (club_id) references t_club (id)
);

create index club_id
    on t_club_comments (club_id);

create index user_id
    on t_club_comments (user_id);

INSERT INTO board_game_platform.t_club_comments (id, user_id, club_id, comments, comments_time) VALUES (1, 2, 1, '好', '2024-05-25');
INSERT INTO board_game_platform.t_club_comments (id, user_id, club_id, comments, comments_time) VALUES (4, 1, 1, '123', '2024-06-30');
