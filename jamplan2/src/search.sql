    CREATE TABLE PEOPLE (
        ID VARCHAR2(20) PRIMARY KEY,
        NAME VARCHAR2(10),
        JOB VARCHAR2(20),
        ADDRESS VARCHAR2(30),
        BLOODTYPE VARCHAR2(3)
    );
    
    select * from plan;
    select * from people;
    
    drop table plan;
    
    create table plan (
        planNo number,
        teamNo number,
        goodCount number,
        readCount number,
        image varchar2(2000),
        planName varchar2(2000),
        planDate varchar2(16)
        );
    
    insert into plan values (1, 1, 10, 58, '이미지', '스위스여행', '20161105');
    insert into plan values (1, 1, 50, 100, '이미지', '스페인여행', '20161205');
    insert into plan values (1, 1, 185, 6000, '이미지', '스웨덴', '20180805');
    insert into plan values (1, 1, 110, 1000, '이미지', '한국여행', '20200805');
    insert into plan values (1, 1, 2180, 10200, '이미지', '이탈리아여행', '20150805');
    insert into plan values (1, 1, 580, 3720, '이미지', '프랑스여행', '20110805');
    insert into plan values (1, 1, 580, 3720, '이미지', '터키여행', sysdate);
    insert into plan values (1, 1, 580, 3720, '이미지', '프랑여행', sysdate);
    insert into plan values (1, 1, 580, 3720, '이미지', '프스여행', sysdate);
    
    delete from plan;
    
    commit;
    
    
    