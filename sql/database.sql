/*============================================ 创建数据库 ============================================*/

drop database if exists profilo;
create database profilo charset utf8;
use profilo;

/*============================================ 作品分类表 ============================================*/

create table category(
    cate_id int primary key auto_increment, -- 分类id
    cate_name varchar(30) not null unique,  -- 分类名字
    parent_cate_id int not null,            -- 上级分类ID，没有则为0
    create_time datetime not null,          -- 创建时间
    update_time datetime not null           -- 更新时间
);

insert into category
(cate_id, cate_name, parent_cate_id, create_time ,update_time)
values
(null , '首页', 0, now(), now());
insert into category
(cate_id, cate_name, parent_cate_id, create_time ,update_time)
values
(null , '作品', 0, now(), now());
insert into category
(cate_id, cate_name, parent_cate_id, create_time ,update_time)
values
(null , '视频', 0, now(), now());
insert into category
(cate_id, cate_name, parent_cate_id, create_time ,update_time)
values
(null , '文章', 0, now(), now());
insert into category
(cate_id, cate_name, parent_cate_id, create_time ,update_time)
values
(null , '工具', 0, now(), now());

INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'平面设计',2,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'手绘作品',2,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'3d设计',2,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'摄影作品',2,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'电商设计',2,NOW(),NOW());


INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'V-LOG',3,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'小视频',3,NOW(),NOW());

INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'设计文章',4,NOW(),NOW());
INSERT INTO category (cate_id,cate_name,parent_cate_id,create_time,update_time)
VALUES
(null,'技术文章',4,NOW(),NOW());

/*============================================ 作品设计形式表 ============================================*/

create table desi(
    desi_id int primary key auto_increment,   -- 设计形式id
    desi_name varchar(30) not null unique,    -- 设计形式名字
    create_time datetime not null,            -- 创建时间
    update_time datetime not null             -- 更新时间
);

insert into desi
(desi_id, desi_name, create_time, update_time)
values
(null , '原创', now(), now());
insert into desi
(desi_id, desi_name, create_time, update_time)
values
(null , '临摹', now(), now());
insert into desi
(desi_id, desi_name, create_time, update_time)
values
(null , '转载', now(), now());

/*============================================ 作品表 ============================================*/

create table works(
    works_id int primary key auto_increment,    -- 作品id
    works_name varchar(60) not null unique,     -- 作品名字
    works_en_name varchar(60) not null,         -- 作品英文名字
    works_content mediumtext not null,          -- 作品内容
    cover_path varchar(200) not null,           -- 封面地址
    create_time datetime not null,              -- 创建时间
    update_time datetime not null,              -- 更新时间
    cate_id int not null,                       -- 分类id
    desi_id int not null                        -- 设计形式id
);



/*============================================ 用户表 ============================================*/

create table user(
    user_id int primary key auto_increment,  -- 用户id
    user_tel varchar(33) not null unique,    -- 用户手机
    user_status tinyint(1) not null,         -- 用户状态，0为禁用，1为启用
    user_name varchar(60) not null unique,   -- 用户名
    user_header_path varchar(200) not null,  -- 用户头像
    user_gender tinyint(1) not null,         -- 用户性别,0为女，1为男
    user_salt varchar(64) not null,          -- 用户密码盐
    user_password varchar(200) not null,     -- 用户密码
    role_id int not null,                    -- 用户角色id
    create_time datetime not null,           -- 创建时间
    update_time datetime not null,           -- 更新时间
    index(user_tel(5)),
    index(user_name(5))
);

/*============================================ 角色表 ============================================*/

create table role(
    role_id int primary key auto_increment,  -- 角色id
    role_name varchar(60) not null unique,   -- 角色名
    create_time datetime not null,           -- 创建时间
    update_time datetime not null            -- 更新时间
);

INSERT INTO role (role_id, role_name, create_time, update_time)
VALUES
(NULL,"超级管理员",NOW(),NOW());

INSERT INTO role (role_id, role_name, create_time, update_time)
VALUES
(NULL,"管理员",NOW(),NOW());

INSERT INTO role (role_id, role_name, create_time, update_time)
VALUES
(NULL,"普通用户",NOW(),NOW());