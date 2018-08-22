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

drop table plan;

insert into plan values(1, 1, 10, 10, '이미지', '스위스여행', '20170801');
insert into plan values(1, 1, 60, 100, '이미지', '베트남여행', '20180802');
insert into plan values(1, 1, 70, 200, '이미지', '터키여행', '20150901');
insert into plan values(1, 1, 40, 300, '이미지', '이탈리아여행', '20181201');
insert into plan values(1, 1, 250, 400, '이미지', '영국여행', '20201220');
insert into plan values(1, 1, 160, 500, '이미지', '미국여행', '20181225');
insert into plan values(1, 1, 10, 10, '이미지', '스위스프링', '20170801');
insert into plan values(1, 1, 10, 10, '이미지', '스가', '20170801');
insert into plan values(1, 1, 10, 10, '이미지', '스나', '20170801');

commit;

CREATE TABLE imgTable (
    img blob
);

ENGINE=Innoblob DEFAULT CHARSET=utf8;

select * from plan;

select * from imgTable;

drop table imgTable;



select * from imgTable;

SELECT img FROM imgTable;

delete plan;

commit;

rollback;

select * from board;

select * from users;

select * from sboard;

select * from plan;

desc plan;

insert into plan(planNo, teamNo, goodCount, readCount, image, planName, planDate) values (1,2,3,4,'테스트','테스트',sysdate);


commit;
select * from plan order by planName;
select * from plan where planName like '%스위스여행%';


likeCheck
select * from usertetst;

select * from liketo;


drop table liketo;

create table likeTo(
    planNo number,
    userId varchar2(100)
    );
    
insert into likeTo(userId) values ('admin');

commit;

select * from liketo;

delete from liketo;



 create table userVO (
        id varchar2(100)
        );
        
    insert into userVO values('admin');
    
    create table likeTo(
    planNo number,
    userId varchar2(100),
    goodCount number
    );
    
    select * from likeTO;
    
   insert into likeTo values(1, 'admin');
   
   drop table liketo;
   
   commit;
   
   
   
   
   -----0819-----
   select * from liketo;

insert into liketo values(1, 'admin', 'Y');
insert into liketo values(1, 'abc', 'Y');
insert into liketo values(1, 'jack', 'Y');
insert into liketo values(1, 'tom', 'Y');
insert into liketo values(1, 'hshs', 'Y');

drop table liketo;

create table likeTo (
    planNo number,
    userId varchar2(100),
    likeYn varchar2(2)
    );
    
    commit;
    
 --좋아요
 
    SELECT case likeYn when 'Y' then 'Y' else 'N' end FROM LIKETO where userId = 'hansang';
    
    select NULLIF(likeYn,'N') from liketo where userid = 'hshs';
    
    SELECT  planNo,userId,likeYn FROM LIKETO where userId='admin';
    
    delete from liketo;
    
   
    
    select * from liketo;
    
    select * from plan;
    
    
    SELECT  planNo,userId,likeYn FROM LIKETO where userId = 'admin';
    
    update plan set GOODCOUNT=(SELECT  count(*) FROM LIKETO where planNo=1 and likeYn ='Y')  where planNo=1;
    
    
    
    
     SELECT  count(*) FROM LIKETO where planNo=1 and likeYn ='Y';
    
    
    UPDATE LIKETO SET likeYn = 'N'  where userId = 'admin'  AND planNo =1;
    
    
    
    UPDATE LIKETO SET likeYn = 'Y'  where userId = 'admin'  AND planNo = 1;


delete from plan;

insert into plan values(1, 1, 10, null, '이미지', '스위스여행', '20170801');
insert into plan values(2, 1, 60, 100, '이미지', '베트남여행', '20180802');
insert into plan values(3, 1, 70, 200, '이미지', '터키여행', '20150901');
insert into plan values(4, 1, 40, 300, '이미지', '이탈리아여행', '20181201');
insert into plan values(5, 1, 250, 400, '이미지', '영국여행', '20201220');
insert into plan values(6, 1, 160, 500, '이미지', '미국여행', '20181225');
insert into plan values(7, 1, 10, 10, '이미지', '스위스프링', '20170801');
insert into plan values(8, 1, 10, 10, '이미지', '스가', '20170801');
insert into plan values(9, 1, 10, 10, '이미지', '스나', '20170801');



 select * from uservo;
 
 insert into uservo values ('jack');
 
-- 조회수
 
 select * from plan where planNo = 1;
 
 delete from plan where planNo = 1;
 
 select readCount from plan where planNo = 10;
 
 update plan set readCount = nvl((select readCount from plan where planNo = 1) ,0)+1 where planNo = 1;
 
 rollback;
 
 commit;


------------0820-------------------
create table planTable (
    calendar varchar2(2000),
        map varchar2(2000)
    );
    
    insert into planTable values ('8월25일', '서울');
    insert into planTable values ('8월26일', '부산');
    insert into planTable values ('8월27일', '여수');
    
    select * from planTable;
    
    commit;


  -----------0821-------------------
   create table planTable (
--    planNo number,
    calendar varchar2(2000),
    map varchar2(2000),
    memo varchar2(2000)
    );
    
    create table planCalendar (
    calendar varchar2(100)
    );
    
    create table planMap (
    map varchar2(100)
    );
    
    insert into planCalendar values ('8월22일');
    select * from planCalendar;
    commit;
    
    insert into planTable values ( '8월25일', '서울', '서울여행');
    insert into planTable values ( '8월26일', '부산', '부산여행');
    insert into planTable values ( '8월27일', '여수', '여수밤바다');
    
    insert into planTable(calendar, map) values('8월21일', '엔코아');
    
    
    update planTable set memo = 'test';
    
    select * from planTable;
    
    delete from planTable;
    
    update planTable set memo = '여행';
    rollback;
    
    drop table planTable;
    
    commit;
    
 -----------0822---------------------
   create table planTable (
    --planNo number,
    calendar varchar2(2000),
    map varchar2(2000),
    memo varchar2(2000)
    );
    
    create table planCalendar (
    --planNo number,
    calendar varchar2(100)
    );
    
    create table planMap (
    --planNo number,
    map varchar2(100)
    );
    
    
    drop table planTable;
    drop table planCalendar;
    drop table planMap;
    
    insert into planCalendar values ('8월3일');
    insert into planMap values ('부산');
    
    delete from planCalendar;
    delete from planMap;
    delete from planTable;
    
    select * from planCalendar;
    select * from planMap;
    select * from planTable;
    
    insert into planTable(calendar)(select calendar from planCalendar);
    update planTable set map = (select map from planMap);
    commit;
    
    insert into planTable values ( '8월25일', '서울', '서울여행');
    insert into planTable values ( '8월26일', '부산', '부산여행');
    insert into planTable values ( '8월27일', '여수', '여수밤바다');
    
    insert into planTable(calendar, map) values('8월21일', '엔코아');
    
    
    update planTable set memo = 'test';
    
    select * from planTable;
    
    delete from planTable;
    
    update planTable set memo = '여행';
    rollback;
    
    drop table planTable;
    
    commit;
    
    
    
     
  
     insert into planTable(calendar) (select calendar from planCalendar);
     update planTable set map = (select map from planMap);
     
     select * from planTable;
     delete from planTable;
     
     commit;
     
     insert into planTable(calendar)(select calendar from planCalendar);
     update planTable set map = (select map from planMap);
     
     
        --좋아요 체크
     select * from liketo;
     
     SELECT  count (*) likeCheck FROM LIKETO where userId = 'admin' AND planNo = 1;
    