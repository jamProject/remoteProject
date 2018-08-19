create table planDate(
    planNo number not null,
    selectDate varchar2(20),
    id varchar2(36),
    dateCount number,
    confirmIndicator number
);

create table TEAMINFO(
    teamNo number not null,
    teamName varchar2(30) not null,
    id VARCHAR2(20) not null, 
    role number,
    planno number,
    planName varchar2(30) not null,
    joinDate date not null
);

create table userteam(
    id varchar2(32),
    teamno number,
    role number,
    planno number,
    teamname varchar2(32)
);
select * from plan;

select * from teaminfo;
--delete teaminfo;
commit;
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
select * from plandate where planNo =1 and SELECTDATE ='18/08/01'; 
commit;
select * from plandate order by selectdate asc;
select * from plandate where planno =1 and SELECTDATE = '18/08/03';
select * from plandate where id = 'admin' and SELECTDATE = '18/08/07';
select * from plandate where  SELECTDATE = '18/08/10';
commit;
--drop table plandate;
delete from plandate where id = 'admin' and selectdate = '18/08/02' and dateCount = 1;
insert into plandate values(1, '18/08/01',  'admin', 1, 0);
insert into plandate values(1, '18/08/02',  'admin', 1, 0);
insert into plandate values(1, '18/08/04',  'admin', 1, 0);
insert into plandate values(1, '18/08/05',  'admin', 1, 0);
insert into plandate values(1, '18/08/22',  'admin', 1, 0);
insert into plandate values(1, '18/08/23',  'admin', 1, 0);
insert into plandate values(1, '18/08/24',  'admin', 1, 0);
insert into plandate values(1, '18/08/25',  'admin', 1, 0);

insert into plandate values(1, '18/09/03',  'admin', 1, 0);
insert into plandate values(1, '18/09/12',  'admin', 1, 0);
insert into plandate values(1, '18/10/03',  'admin', 1, 0);
insert into plandate values(1, '18/11/07',  'admin', 1, 0);
insert into plandate values(1, '18/12/28',  'admin', 1, 0);
insert into plandate values(1, '19/01/11',  'admin', 1, 0);
insert into plandate values(1, '19/01/26',  'admin', 1, 0);
insert into plandate values(1, '19/01/25',  'admin', 1, 0);



commit;
--drop table planDate;
select * from planDate;


select planNo from userteam where id = 'admin';

--drop table userteam;
select * from userteam where id = 'admin';
select*from userteam;
insert into userteam values('admin',1,0,1,'team1');
insert into userteam values('user1',1,1,1,'team1');
insert into userteam values('user2',1,2,1,'team1');
insert into userteam values('user3',1,3,1,'team1');
insert into userteam values('user4',1,4,1,'team1');
insert into userteam values('user1',1,2,3,'team1');
insert into userteam values('user1',2,1,4,'team1');
insert into userteam values('admin',2,1,5,'team1');
insert into userteam values('user2',3,1,6,'team1');
insert into userteam values('user2',3,1,7,'team1');
commit;

select *from userinfo;
select * from userInfo where id = 'user1' and pass = '1234';
COMMIT;
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('admin','wookim456@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('wookim','wookim456@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user1','user1@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user2','user2@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user3','user3@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user4','user4@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user5','user5@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user6','user6@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');

--drop table plan;
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
insert into plan (planno, teamno, planname, plandate, isopen) values (13,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (14,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (15,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (16,4,'team4',sysdate,1);

select * form plan where isopen =0;



create table planDate(
    planNo number not null,
    selectDate varchar2(20),
    id varchar2(36),
    dateCount number,
    confirmIndicator number
);

commit;
select * from plandate ;
select * from plandate where id = 'admin' and SELECTDATE = '18/08/07';
select * from plandate where  SELECTDATE = '18/08/10';
commit;
drop table plandate;
delete from plandate where DATECOUNT = 2;
insert into plandate values(1, '18/08/01',  'admin', 1, 0);
insert into plandate values(1, '18/08/02',  'admin', 1, 0);
insert into plandate values(1, '18/08/04',  'admin', 1, 0);
insert into plandate values(1, '18/08/05',  'admin', 1, 0);
insert into plandate values(1, '18/08/22',  'admin', 1, 0);
insert into plandate values(1, '18/08/23',  'admin', 1, 0);
insert into plandate values(1, '18/08/24',  'admin', 1, 0);
insert into plandate values(1, '18/08/25',  'admin', 1, 0);

insert into plandate values(1, '18/09/03',  'admin', 1, 0);
insert into plandate values(1, '18/09/12',  'admin', 1, 0);
insert into plandate values(1, '18/10/03',  'admin', 1, 0);
insert into plandate values(1, '18/11/07',  'admin', 1, 0);
insert into plandate values(1, '18/12/28',  'admin', 1, 0);
insert into plandate values(1, '19/01/11',  'admin', 1, 0);
insert into plandate values(1, '19/01/26',  'admin', 1, 0);
insert into plandate values(1, '19/01/25',  'admin', 1, 0);



commit;
drop table planDate;
select * from planDate;


select planNo from userteam where id = 'admin';
create table userteam(
    id varchar2(32),
    teamno number,
    role number,
    planno number,
    teamname varchar2(32)
);
drop table userteam;
select * from userteam where id = 'admin';
select*from userteam;
insert into userteam values('admin',1,0,1,'team1');
insert into userteam values('user1',1,1,1,'team1');
insert into userteam values('user2',1,2,1,'team1');
insert into userteam values('user3',1,3,1,'team1');
insert into userteam values('user4',1,4,1,'team1');
insert into userteam values('user1',1,2,3,'team1');
insert into userteam values('user1',2,1,4,'team1');
insert into userteam values('admin',2,1,5,'team1');
insert into userteam values('user2',3,1,6,'team1');
insert into userteam values('user2',3,1,7,'team1');
commit;
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
select *from userinfo;
select * from userInfo where id = 'user1' and pass = '1234';
COMMIT;
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('admin','wookim456@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('wookim','wookim456@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user1','user1@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user2','user2@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user3','user3@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user4','user4@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user5','user5@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');
insert into userinfo (id, email, pass, isadmin, signDate, nation, gender, age, travelType, hobby) values('user6','user6@gmail.com','1234',1,sysdate,'한국','남',26,'혼자','기타연주');

create table userTeam(
    id varchar2 ref
)

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
--drop table plan;
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
insert into plan (planno, teamno, planname, plandate, isopen) values (13,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (14,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (15,4,'team4',sysdate,1);
insert into plan (planno, teamno, planname, plandate, isopen) values (16,4,'team4',sysdate,1);

select * form plan where isopen =0;