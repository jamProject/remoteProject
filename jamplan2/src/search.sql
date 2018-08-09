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
    planDate varchar2(2000)
    );
    
insert into plan values (1, 1, 20, 200, '/jamplan2/resources/search/image/swiss2.jpg',
'Ω∫¿ßΩ∫∞Ë»π', '20180805');

commit;

