
#call insert_examtable(10000);
select * from examtable;

select *, 
		(kor + eng + mat) as total, 
        (kor + eng + mat) / 3 as avg, 
        (select count(*) from examtable where total < (kor + eng + mat)) + 1 as ranking 
from examtable
order by total desc; 


drop function if exists f_get_ranking;
DELIMITER $$
create function f_get_ranking(_id integer) returns integer
begin
	declare _ranking integer;
    select count(*) + 1 into _ranking from examtable 
		where ((select (kor + eng + mat) from examtable where studentid = _id) < (kor + eng + mat));
    
    return _ranking;
    end $$
DELIMITER ;

select *, f_get_ranking(studentid) from examtable;
select *, 
		(kor + eng + mat) as total, 
        (kor + eng + mat) / 3 as avg, 
        f_get_ranking(studentid) as ranking 
	from examtable
    order by total desc;