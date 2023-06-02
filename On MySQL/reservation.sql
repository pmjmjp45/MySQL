drop table if exists reservation;	
create table reservation(	
	name varchar(10),	
    reserve_date date,	
    room int,	
    addr varchar(20),	
    tel varchar(20),	
    ipgum_name varchar(10),	
    memo varchar(50),	
    input_date date,	
    primary key(reserve_date,room));	
desc reservation;	

delete from reservation;
insert into reservation values ("Sana","2023-05-24",1,"Seoul","010-0101-0101","Sana","Thank you",NOW());


select * from reservation;

drop procedure if exists reserve_calc;
delimiter $$
create procedure reserve_calc()
begin
	declare _date date;
    
    set _date = now();
    
    drop table if exists reserve_stat;
    create table reserve_stat(
		reserve_date date not null,
        room1 varchar(20),
        room2 varchar(20),
        room3 varchar(20));
    
    _loop : loop
        
        insert into reserve_stat 
			select distinct _date,
				ifnull((select name from reservation where room = 1 and reserve_date = _date), "Available") as room1,
                ifnull((select name from reservation where room = 2 and reserve_date = _date), "Available") as room2,
                ifnull((select name from reservation where room = 3 and reserve_date = _date), "Available") as room3
			from reservation;
            
			set _date = date_add(_date, interval 1 day);
            
            if _date > date_add(now(), interval 30 day) then
				leave _loop;
			end if;
		end loop _loop;

end $$
delimiter ;

call reserve_calc();

select * from reserve_stat;