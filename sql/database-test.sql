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
(null , '市集', 0, now(), now());

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

INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品2","test02","测试内容1","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860518.jpg",NOW(),NOW(),6,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品3","test03","测试内2容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860519.jpg",NOW(),NOW(),6,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品4","test33","测试12内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860510.jpg",NOW(),NOW(),6,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品5","test021","测试12内3容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860512.jpg",NOW(),NOW(),7,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品6","test202","测试内5容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860511.jpg",NOW(),NOW(),7,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品7","t2est02","测试32内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860513.jpg",NOW(),NOW(),7,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品8","tes2t02","测试1内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860514.jpg",NOW(),NOW(),8,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品9","test2102","测试内43容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860515.jpg",NOW(),NOW(),8,2);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品10","te21st02","测112试qwq内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860520.jpg",NOW(),NOW(),6,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品11","tesd02","测112试d内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860521.jpg",NOW(),NOW(),7,2);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品12","testvs02","测112试123内容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860522.jpg",NOW(),NOW(),8,1);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品13","tesast02","测112试内2容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860523.jpg",NOW(),NOW(),6,2);
INSERT INTO works (works_id,works_name,works_en_name,works_content,cover_path,create_time,update_time,cate_id,desi_id)
VALUES
(NULL,"测试作品14","testvda02","测112试内wq容","https://img.alicdn.com/imgextra/i1/113860517/TB2AbarcFLM8KJjSZFBXXXJHVXa_!!113860524.jpg",NOW(),NOW(),8,3);

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