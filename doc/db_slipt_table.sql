#初始化数据表，方便测试
CREATE TABLE member(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(20),
sex TINYINT NOT NULL DEFAULT '0'
)ENGINE=MYISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

#初始化数据
INSERT INTO member(id,NAME,sex) VALUES (1,'jacson','0');
#重复执行，生成多条数据
INSERT INTO member(NAME,sex) SELECT NAME,sex FROM member;

###############################
#对表进行拆分
DROP TABLE IF EXISTS tb_member1;
CREATE TABLE tb_member1(
    id BIGINT PRIMARY KEY AUTO_INCREMENT ,
    NAME VARCHAR(20),
    sex TINYINT NOT NULL DEFAULT '0'
)ENGINE=MYISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 
DROP TABLE IF EXISTS tb_member2;
CREATE TABLE tb_member2(
    id BIGINT PRIMARY KEY AUTO_INCREMENT ,
    NAME VARCHAR(20),
    sex TINYINT NOT NULL DEFAULT '0'
)ENGINE=MYISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ; 

#######################################
DROP TABLE IF EXISTS tb_member;
CREATE TABLE tb_member(
id BIGINT PRIMARY KEY AUTO_INCREMENT ,
NAME VARCHAR(20),
sex TINYINT NOT NULL DEFAULT '0'
)ENGINE=MERGE UNION=(tb_member1,tb_member2) INSERT_METHOD=LAST CHARSET=utf8 AUTO_INCREMENT=1 ;


SELECT * FROM tb_member  ORDER BY id LIMIT 0,15
SELECT * FROM tb_member1;
SELECT * FROM tb_member2;
#不指定子表进行数据插入
INSERT INTO tb_member(NAME,sex) VALUES ('jacson2','2');