create table t_user
(
    id           int auto_increment
        primary key,
    name         varchar(20)  null comment '用户姓名',
    user_name    varchar(30)  null comment '用户名',
    password     varchar(30)  null comment '登录密码',
    age          int          null comment '年龄',
    gender       int          null comment '性别：0为女；1为男',
    introduction varchar(255) null comment '介绍',
    type         int          null comment '权限：0为普通用户，1为管理员',
    email        varchar(30)  null comment '电子邮箱',
    mobile       varchar(30)  null comment '手机',
    province     varchar(30)  null comment '省',
    city         varchar(30)  null comment '城市',
    register     date         null comment '注册时间',
    QQ           varchar(30)  null,
    weixin       varchar(30)  null comment '微信',
    avatar_fname varchar(30)  null comment '头像地址',
    status       int          null comment '在线状态：1为在线；0为离线'
);