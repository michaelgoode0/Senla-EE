#SimpleTask
select * from cd.facilities;
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
---------------------------------------------------------------------------------------------------
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

select distinct mems.firstname || ' ' || mems.surname as member, fac.name as facility from cd.members mems
join cd.bookings bks on mems.memid=bks.memid
join cd.facilities fac on bks.facid=fac.facid 
where fac.name like '%Tennis Court%' 
order by member,facility;

select mems.firstname || ' ' || mems.surname as member,
fac.name as facility,
case
when mems.memid=0 
then bks.slots*fac.guestcost
else bks.slots*fac.membercost
end as cost
from cd.members mems
join cd.bookings bks on mems.memid=bks.memid
join cd.facilities fac on bks.facid=fac.facid
where
bks.starttime>='2012-09-14' and 
bks.starttime<='2012-09-15' and 
((mems.memid =0 and bks.slots*fac.guestcost>30) or
(mems.memid !=0 and bks.slots*fac.membercost>30))
order by cost desc;

select distinct mems.firstname || ' ' || mems.surname as  member,
(select rec.firstname || ' ' || rec.surname as  recommender from 
cd.members rec where mems.recommendedby= rec.memid) from cd.members mems
order by member;

select member, facility, cost from (
select 	mems.firstname || ' ' || mems.surname as member,facs.name as facility,
case	
when mems.memid = 0 then
bks.slots*facs.guestcost
else
bks.slots*facs.membercost
end as cost
from cd.members mems
join cd.bookings bks
on mems.memid = bks.memid
join cd.facilities facs
on bks.facid = facs.facid
where
bks.starttime >= '2012-09-14' and
bks.starttime < '2012-09-15'
) as bookings
where cost > 30
order by cost desc; 
-----------------------------------------------------------------------------------------------
#Aggregates
select count(facid) from cd.facilities;
select count(facid) from cd.facilities where guestcost>10;

select recommendedby ,count(*) from cd.members where recommendedby!=0 
group by recommendedby 
order by recommendedby;

select facid,sum(slots) as "Total Slots" from cd.bookings 
group by facid order by facid;

select facid,sum(slots) as "Total Slots" from cd.bookings
where starttime>='2012-09-01'
and starttime<'2012-10-01'
group by facid order by sum(slots);

select facid,extract(month from starttime) as month,sum(slots) as "Total Slots"
from cd.bookings
where starttime>='2012-01-01' and
starttime<'2013-01-01'
group by facid,month
order by facid,month

select count(distinct memid) from cd.bookings;

select facid,sum(slots) as"Total Slots" from cd.bookings
group by facid
having sum(slots)>1000 
order by facid;

select fac.name, sum(slots * case
when memid=0 then fac.guestcost
else fac.membercost
end )as revenue from cd.bookings bks
join cd.facilities fac on bks.facid=fac.facid 
group by fac.name
order by revenue;

select fac.name, sum(slots * case
when memid=0 then fac.guestcost
else fac.membercost
end )as revenue 
from cd.bookings bks
join cd.facilities fac on bks.facid=fac.facid
group by fac.name
having sum(case
when memid=0 then slots*fac.guestcost
else slots*fac.membercost
end )<1000
order by revenue;

select fac.facid,sum(bks.slots) as "Total Slots" from cd.facilities fac
join cd.bookings bks on fac.facid=bks.facid group by fac.facid
order by sum(bks.slots) desc limit 1;

select bks.facid,extract(month from starttime) as month,sum(slots) as "Total Slots"
from cd.bookings bks 
where starttime>='2012-01-01' and
starttime<'2013-01-01'
group by rollup(bks.facid,month)
order by bks.facid,month;


select fac.facid, fac.name,
trim(to_char(sum(bks.slots)/2.0, '9999999999999999D99')) as "Total Hours"
from cd.bookings bks
join cd.facilities fac
on fac.facid = bks.facid
group by fac.facid, fac.name
order by fac.facid;

select mem.surname,mem.firstname,mem.memid,min(bks.starttime)as starttime from cd.members mem 
join cd.bookings bks on bks.memid=mem.memid
where bks.starttime>='2012-09-01'
group by mem.firstname,mem.surname,mem.memid
order by mem.memid;

select (select count(*) from cd.members), firstname, surname 
from cd.members order by joindate;

select row_number() over(order by joindate) , firstname, surname 
from cd.members order by joindate

select facid, total from
(select facid, sum(slots) total, rank() over (order by sum(slots) desc) rank
from cd.bookings
group by facid) as ranked
where rank = 1;

select firstname, surname,
((sum(bks.slots)+10)/20)*10 as hours,
rank() over (order by ((sum(bks.slots)+10)/20)*10 desc) as rank
from cd.bookings bks
join cd.members mems
on bks.memid = mems.memid
group by mems.memid
order by rank, surname, firstname;  

select name, rank from (
select facs.name as name, rank() over (order by sum(case
when memid = 0 then slots * facs.guestcost
else slots * membercost end) desc) as rank
from cd.bookings bks
join cd.facilities facs
on bks.facid = facs.facid
group by facs.name) as subq
where rank <= 3;

select name, case when class=1 then 'high'
when class=2 then 'average'
else 'low'
end revenue
from (select facs.name as name, ntile(3) over (order by sum(case
when memid = 0 then slots * facs.guestcost
else slots * membercost
end) desc) as class
from cd.bookings bks
join cd.facilities facs
on bks.facid = facs.facid
group by facs.name) as subq
order by class, name; 

select 	facs.name as name,
facs.initialoutlay/((sum(case
when memid = 0 then slots * facs.guestcost
else slots * membercost
end)/3) - facs.monthlymaintenance) as months
from cd.bookings bks
inner join cd.facilities facs
on bks.facid = facs.facid
group by facs.facid
order by name; 

select 	dategen.date,
	()
		select sum(case
			when memid = 0 then slots * facs.guestcost
			else slots * membercost
		end) as rev

		from cd.bookings bks
		inner join cd.facilities facs
			on bks.facid = facs.facid
		where bks.starttime > dategen.date - interval '14 days'
			and bks.starttime < dategen.date + interval '1 day'
	)/15 as revenue
	from
	(
		select 	cast(generate_series(timestamp '2012-08-01',
			'2012-08-31','1 day') as date) as date
	)  as dategen
order by dategen.date;
--------------------------------------------------------------------------------------------------------------------------------
#Recursive 

with recursive recommenders(recommender) as(
select 
recommendedby as recommender from cd.members mem where mem.memid=27
union
select mem.recommendedby from recommenders rec
join cd.members mem
on mem.memid = rec.recommender)
select rec.recommender, mem.firstname, mem.surname
from recommenders rec
join cd.members mem
on rec.recommender = mem.memid
order by memid desc;

with recursive recommendeds(memid) as(
select 
memid from cd.members mem where recommendedby=1
union
select mem.memid from recommendeds rec
join cd.members mem
on mem.recommendedby = rec.memid)
select rec.memid, mem.firstname, mem.surname
from recommendeds rec
join cd.members mem
on rec.memid = mem.memid
order by memid;

with recursive recommenders(recommender, member) as (
	select recommendedby, memid
		from cd.members
	union all
	select mems.recommendedby, recs.member
		from recommenders recs
		join cd.members mems
			on mems.memid = recs.recommender
)
select recs.member member, recs.recommender, mems.firstname, mems.surname
	from recommenders recs
	join cd.members mems		
		on recs.recommender = mems.memid
	where recs.member = 22 or recs.member = 12
order by recs.member asc, recs.recommender desc 




