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


    
    
 
    