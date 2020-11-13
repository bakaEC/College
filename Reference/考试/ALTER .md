ALTER TABLE BM ADD CONSTRAINT 外键名 FOREIGN KEY 外键字段

REFRENCES

insert into 表名 values 对应值

SELECT <字段名列表>
FROM <表名或视图>
[WHERE <查询条件>]
[GROUP BY <分组的字段名>]
[HAVING <条件>]
[ORDER BY <排序的字段名> [ASC或DESC]]

SELECT `studentNo`,`studentName`FROM 'student`
WHERE studentNo IN (
SEL ECT 'studentNo' FROM result
WHERE 'subjectNo' IN (
SELECT 'subjectNo' FROM 'subject
WHERE subjectName'=' Logic Java'
) AND 'examDate = (
SELECT MAX( examDate') FROM 'result
WHERE subjectNo' = (
SELECT 'subjectNo' FROM 'subject'
WHERE subjectName' ='Logic Java '
)
);