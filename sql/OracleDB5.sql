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