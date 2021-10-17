#SimpleTask
]select * from cd.facilities;
select name,membercost from cd.facilities;
select * from cd.facilities where membercost>0;
select facid,name,membercost,monthlymaintenance from cd.facilities where membercost>0 and membercost<monthlymaintenance/50;
select * from cd.facilities where name like '%Tennis%';
select * from cd.facilities where facid in (1,5);
select name,case 
when monthlymaintenance>100 then 'expensive'
else 'cheap'
end as cost
from cd.facilities;
select memid,surname,firstname,joindate from cd.members where joindate>='2012-09-01';
select distinct surname from cd.members order by surname limit 10;
select surname from cd.members union select name from cd.facilities;
select max(joindate) as latest from cd.members ;
select firstname,surname,joindate from cd.members where joindate=(select max(joindate) from cd.members);

#JoinsandSubqueries
select starttime from cd.bookings bks
join cd.members mem on mem.memid=bks.memid where firstname='David' and surname='Farrell';
select bks.starttime,fac.name from cd.bookings bks 
join cd.facilities fac on fac.facid=bks.facid where bks.starttime >='2012-09-21' 
and bks.starttime <'2012-09-22'
and fac.name like '%Tennis Court%' order by bks.starttime;
select distinct mem.firstname, mem.surname from cd.members mem 
join cd.members mems on mems.recommendedby=mem.memid order by surname,firstname;
select mem.firstname as memframe, mem.surname as memsname,rec.firstname as recfname,rec.surname as recsname from cd.members mem
left outer join cd.members rec on rec.memid =mem.recommendedby
order by memsname,memframe;