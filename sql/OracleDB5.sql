create table userInfo(
    id varchar2(32) primary key,
    email varchar2(80) not null,
    pass varchar2(32) not null,
    isAdmin number not null,
    signDate date not null,
    nation varchar2(30),
    gender varchar2(10),
    age number,
    snsSite varchar2(1000),
    travelType varchar2(2000),
    hobby varchar2 (500)
);

select * from userInfo;

insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('wookim','wookim456@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user1','user1@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user2','user2@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user3','user3@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user4','user4@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user5','user5@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user6','user6@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');


create table plan(
    planno number primary key,
    teamno number not null,
    planname varchar2(30) not null,
    plandate date,
    goodcount number,
    hastag varchar2(30),
    readcount number,
    isopen number not null
);
drop table plan;
select * from plan;     
select * from plan where isopen = 0 and teamno =1;
COMMIT;

insert into plan (planno, teamno, planname, plandate, isopen) values (2,1,'team1',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (3,1,'team1',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (4,1,'team1',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (5,2,'team2',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (6,2,'team2',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (7,2,'team2',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (8,3,'team3',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (9,3,'team3',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (10,3,'team3',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (11,4,'team4',sysdate,0);
insert into plan (planno, teamno, planname, plandate, isopen) values (12,4,'team4',sysdate,1);

select * form plan where isopen =0;