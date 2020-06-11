- ### 命令行连接MySQL
```
msyql -u root -proot;
```
- ### 创建数据库
```
DATABASE `school`;
```
- ### 查看数据库列表
```
SHOW DATABASES;
```
- ### 选择数据库
```
USE `school`;
```
- ### 删除数据库
```
DROP DATABASE `school`;
```
- ### 创建表 学生表 （字段：学生编号 学生名）
```
CREATE TABLE IF NOT EXISTS student( studentId INT(4) PRIMARY KEY, studentName VARCHAR(20));
```
- ### 创建学生表 字段的约束及属性 主键 注释 字符集
```
CREATE TABLE IF NOT EXISTS student ( studentId INT(4) PRIMARY KEY, studentName VARCHAR(20)) COMMENT="学生表" DEFAULT CHARSET=utf8;
```
- ### 查看表
```
SHOW TABLES;
SELECT * FROM 表名;
```
- ### 删除表
```
DROP TABLE student;
```
- ### 修改表表名
```
ALTER TABLE new_student RENAME student;
```
- ### 修改表字段
```
ALTER TABLE student MODIFY studentname VARCHAR(10) NOT NULL;
ALTER TABLE 表名 CHANGE 旧字段名 新字段名[属性或约束];
```
- ### 添加表字段
```
ALTER TABLE student ADD newC INT(4);
```
- ### 删除字段
```
ALTER TABLE student DROP COLUMN newc;
```
- ### 添加主键
```
ALTER TABLE student ADD PRIMARY KEY `id`;
```
- ### 添加外键
```
ALTER TABLE student ADD CONSTRAINT g_s FOREIGN KEY (gradeId) REFERENCES `grade`(`gradeId`);
```
- ### 编写SQL语句实现从学生表提取姓名、手机号两列数据存储到通讯录表中
```
CREATE TABLE tongxunlu (SELECT studentname,phone, FROM student);
```
- ### 把成绩都降低10%后加5分，再查询及格成绩，并按照成绩从高到低排序
```
SELECT studentresult*0.9+5 FROM result WHERE studentresult*0.9+5>=60 ORDER BY studentresult*0.9+5 DESC;
```
- ### 查询所有年级编号为1的学员信息，按学号升序排序，显示前4条记录，每页4条，显示第2页，即从第5条记录开始显示4条数据
```
SELECT * FROM student WHERE gradeId = 1 ORDER BY studentNo LIMIT 4,4;
- ### 将学生表中学号为20000的学生的邮箱修改为stu20000@163.com，密码改为000
UPDATE student SET email='stu20000@163.com' WHERE studentNO = 20000;
```
- ### 将科目表中课时数大于200且年级编号为1的科目的课时减少10
```
UPDATE `subject` SET classhour=classhour-10 WHERE classHour>200 AND gradeId = 1;
```
- ### 将所有年级编号为1的学员姓名、性别、出生日期、手机号码信息保存到新表student_grade1中
```
CREATE TABLE student_grade1(SELECT studentName,sex,bornDate,phone FROM student);
```
- ### 查询2016年2月17日考试前5名的学员的学号和分数
```
SELECT studentNO,studentresult FROM result WHERE examdate = '2016-02-17' ORDER BY studentresult DESC LIMIT 5;
```
- ### 将所有女学生按年龄从大到小排序，从第2条记录开始显示6名女学生的姓名、年龄、出生日期、手机号信息
```
SELECT studentname,borndate,phone FROM student WHERE sex = '女' ORDER BY bornDate LIMIT 1,6;
```
- ### 查询参加2016年2月17日考试的所有学员的最高分、最低分、平均分
```
SELECT MAX(studentresult),MIN(studentresult),AVG(studentresult) FROM result WHERE examdate='2016-02-17';
```
- ### 编写SQL语句，查看年龄比“李斯文”小的学生，要求显示这些学生的信息
```
SELECT * FROM student WHERE bornDate < (SELECT borndate FROM student WHERE studentName = '李斯文');
```
- ### 查询参加最近一次Logic Java考试成绩的学生的最高分和最低分
```
SELECT MAX(studentResult),MIN(studentResult) FROM result WHERE examDate = (SELECT MAX(examdate) FROM result WHERE subjectno = (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava'));
```
- ### 查询“Logic Java”课程考试成绩为60分的学生名单
```
SELECT studentName FROM result,student WHERE result.`studentNo`=student.`studentNo` AND studentResult=60;
```
- ### 检查“Logic Java”课程最近一次考试成绩,#如果有 80分以上的成绩，显示分数排在前5名的学员学号和分数
```
SELECT studentResult FROM result WHERE EXISTS (SELECT studentNo FROM result WHERE studentResult>=80 AND examDate = (SELECT MAX(examDate) FROM result WHERE subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava')) AND
subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava') AND examDate = (SELECT MAX(examDate) FROM result WHERE subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava')) AND
subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava') ORDER BY studentResult DESC LIMIT 5;
```
- ### 检查“Logic Java”课程最近一次考试成绩,#如果全部未通过考试（60分及格），认为本次考试偏难，计算的该次考试平均分加5分
```
SELECT AVG(studentResult)+5 FROM result WHERE NOT EXISTS (SELECT studentNo FROM result WHERE studentResult<60 AND examDate = (SELECT MAX(examDate) FROM result WHERE subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava')) AND
subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava') AND examDate = (SELECT MAX(examDate) FROM result WHERE subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava')) AND subjectNo IN (SELECT subjectno FROM `subject` WHERE subjectName = 'logicjava')
```
- ### 根据课程编号分组，求每个小组平均分
```
SELECT AVG(studentResult) FROM result GROUP BY subjectNo;
```
- ### 根据课程编号分组，求每个小组平均分,由小到大排序
```
SELECT AVG(studentResult) FROM result GROUP BY subjectNo ORDER BY AVG(studentResult);
```
- ### 以年级，性别分组，求人数
```
SELECT COUNT(0) '人数' FROM student GROUP BY gradeId,sex;
```
- ### 求每个平均分数大于60的年级编号
```
SELECT gradeId FROM result,`subject` WHERE result.`subjectNo`=`subject`.`subjectNo` GROUP BY gradeId HAVING AVG(studentResult)>60;
```
- ### 查询学生成绩信息及个人信息显示
```
SELECT * FROM student JOIN result USING (studentNo);
```
- ### 查询u1所在的年级课程
```
SELECT sb.* FROM `subject` sb JOIN grade g USING (gradeid)
WHERE gradeName = 'u1';
```



文章采用 [BY-NC-SA](https://creativecommons.org/licenses/by-nc-nd/4.0/) 许可协议。转载请注明出处！

