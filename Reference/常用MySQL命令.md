一 、常用操作数据库的命令

1.show databases; 查看所有的数据库
2.create database test; 创建一个叫test的数据库
3.drop database test;删除一个叫test的数据库
4.use test;选中库 ,在建表之前必须要选择数据库
5.show tables; 在选中的数据库之中查看所有的表
6.create table 表名 (字段1 类型, 字段2 类型);
7.desc 表名;查看所在的表的字段
8.drop table 表名; 删除表
9.show create database 库名;查看创建库的详细信息
10.show create table 表名; 查看创建表的详细信息
二、修改表的命令

1.修改字段类型 alter table 表名 modify 字段 字段类型;
2.添加新的字段 alter table 表名 add 字段 字段类型
3.添加字段并指定位置  alter table 表名 add 字段 字段类型   after 字段;
4.删除表字段  alter table 表名 drop 字段名;
5.修改指定的字段  alter table 表名 change 原字段名字  新的字段名字 字段类型
三、对数据的操作

1.增加数据(insert)3种方式
    1.1 insert into 表名 values(值1，值2，...)(很少用)
    1.2 insert into 表名(字段1，字段2...) values(值1，值2，....);（较常用）
    1.3 insert into 表名(字段1，字段2...) values(值1，值2，....)，(值1，值2，....)，(值1，值2，....);
2.删除数据(delete) delete from 表名 where 条件 注意：where 条件必须加，否则数据会被全部删除
3.更新数据(update)  update 表名 set字段1 = 值1, 字段2 = 值2 where 条件
4.查询数据(select)
    4.1 查询表中的所有数据   select * from 表名
    4.2 指定数据查询    select 字段 from 表名 
    根据条件查询出来的数据  select 字段 from 表名 where 条件 (最常用的)
    where 条件后面跟的条件
     关系：>,<,>=,<=,!=  
     逻辑：or, and 
     区间：id between 4 and 6 ;闭区间，包含边界
5.排序
select 字段 from 表 order by 字段  排序关键词(desc | asc)
排序关键词 desc 降序 asc 升序(默认)
    5.1 通过字段来排序
    例如 ：select * from star orser by money desc, age asc;   
    5.2 多字段排序
    select 字段 from 表 order by 字段1  desc |asc,...字段n desc| asc;
6.常用的统计函数 sum，avg，count，max,min
    只分组:select * from 表 group by 字段
    例子: select count(sex) as re,sex from star group by sex having re > 3;
    分组统计: select count(sex) from star group by sex;
7.分组 select * from 表名  limit 偏移量,数量
    说明:
        8.1.不写偏移量的话就是默认的为0
        8.2.实现分页的时候必须写偏移量
        偏移量怎么计算？:
        limit (n-1)*数量 ,数量 
四、多表联合查询

1.内连接
隐式内连接 select username,name from user,goods where user,gid=gods,gid;
显示内连接
select username,from user inner join goods on user.gid=goods.gid;
select * from user left join goods on user.gid=goods.gid;
2.外链接
左连接 包含所有的左边表中的记录以及右边表中没有和他匹配的记录
右连接 
select * from user where gid in(select gid from goods);
select * from user right jOin goods on user.gid=goods.gid;
子嵌套查询
数据联合查询
select * from user left join goods on user.gid=goods.gid union select * from user right join goods on user.gid=goods.gid;
两个表同时更新
update user u, goods g set u.gid=12,g.price=1 where u.id=2 and u.gid=g.gid;
五、DCL 数据控制语言

1.创建用户:create user'xiaoming'@'localhost' identified by '666666';
2.授权用户:grant all on test.*to'xiaoming'@'localhost';
3.刷新权限:flush privileges;
4.取消授权:revoke all on test.* from 'xiaoming'@'localhost';
5.删除用户: drop user'xiaoming'@'localhost';
六、DTL 数据事务语言

开启事务：set autocommit=0;
操作回滚：rollback;
提交事务：commit;

————————————————
版权声明：本文为CSDN博主「Python全栈之巅」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/hzw6991/java/article/details/87757426