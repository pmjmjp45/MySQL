create table examtable2 (id int, name varchar(20), kor int, eng int, mat int);
drop table examtable2;
drop procedure if exists insert_examtable2;
delimiter $$
create procedure insert_examtable2(_last integer)
begin 
declare _name varchar(20);
declare _id integer;
declare _cnt integer;
set _cnt = 0;
delete from examtable2 where id > 0;
	_loop: loop
		set _cnt = _cnt + 1;
        set _name = concat("홍길", cast(_cnt as char(4)));
        set _id = _cnt;
        
        insert into examtable2 value (_id, _name, rand() * 100, rand() * 100, rand() * 100);
        
        if _cnt = _last then
			leave _loop;
		end if ;
	end loop _loop;
end $$
delimiter ;

call insert_examtable2(1000);
select * from examtable2;

drop procedure if exists print_examtable2;
delimiter $$
create procedure print_examtable2(_totalNum integer, _page integer, _numPerPage integer)
begin 
declare _start integer;

if _page > _totalNum / cast(_numPerPage as float) then
	if _totalNum % _numPerPage = 0 then
		set _page = _totalNum / _numPerPage;
	else
		set _page = _totalNum / _numPerPage + 1;
	end if ;
end if ;

set _start = (_page - 1) * _numPerPage;

select *, (kor + eng + mat) as total, (kor + eng + mat) / 3 as avg,
		(select count(*) from examtable2 where total < (kor + eng + mat)) + 1 as ranking 
	from examtable2 limit _start, _numPerPage;
end $$
delimiter ;

drop procedure if exists print_examtable2_curr;
delimiter $$
create procedure print_examtable2_curr(_totalNum integer, _page integer, _numPerPage integer)
begin 
declare _start integer;

if _page > _totalNum / cast(_numPerPage as float) then
	if _totalNum % _numPerPage = 0 then
		set _page = _totalNum / _numPerPage;
	else
		set _page = _totalNum / _numPerPage + 1;
	end if ;
end if ;
if _page <= 0 then
	set _page = 1;
end if ;

set _start = (_page - 1) * _numPerPage;

select sum(a.kor) as korTotal,
		avg(a.kor) as korAvg,
		sum(a.eng) as engTotal,
        avg(a.eng) as engAvg,
        sum(a.mat) as matTotal,
        avg(a.mat) as matAvg,
        sum(a.kor + a.eng + a.mat) as totalTotal,
        avg(a.kor + a.eng + a.mat) as totalAvg,
        sum((a.kor + a.eng + a.mat) / 3) as avgTotal,
        avg((a.kor + a.eng + a.mat) / 3) as avgAvg
	from (select * from examtable2 limit _start, _numPerPage) as a;
end $$
delimiter ;

drop procedure if exists print_examtable2_acml;
delimiter $$
create procedure print_examtable2_acml(_totalNum integer, _page integer, _numPerPage integer)
begin 
declare _end integer;

if _page > _totalNum / cast(_numPerPage as float) then
	if _totalNum % _numPerPage = 0 then
		set _page = _totalNum / _numPerPage;
	else
		set _page = _totalNum / _numPerPage + 1;
	end if ;
end if ;
if _page <= 0 then
	set _page = 1;
end if ;

set _end = _page * _numPerPage;

select sum(a.kor) as korTotal,
		avg(a.kor) as korAvg,
		sum(a.eng) as engTotal,
        avg(a.eng) as engAvg,
        sum(a.mat) as matTotal,
        avg(a.mat) as matAvg,
        sum(a.kor + a.eng + a.mat) as totalTotal,
        avg(a.kor + a.eng + a.mat) as totalAvg,
        sum((a.kor + a.eng + a.mat) / 3) as avgTotal,
        avg((a.kor + a.eng + a.mat) / 3) as avgAvg
	from (select * from examtable2 limit 0, _end) as a;
end $$
delimiter ;

call print_examtable2(1000, 41, 25);
call print_examtable2_curr(1000, 41, 25);
call print_examtable2_acml(1000, 41, 25);