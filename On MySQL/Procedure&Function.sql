drop procedure if exists get_sum;
delimiter $$
create procedure get_sum(
	in _id integer,
    out _name varchar(20),
    out _sum integer
    )
begin
	declare _kor integer;
    declare _eng integer;
    declare _mat integer;
    set _kor = 0;
    set _eng = 0;
    set _mat = 0;
    
    select name, kor, eng, mat
		into _name, _kor, _eng, _mat from examtable where studentid = _id;
	set _sum = _kor + _eng + _mat;
end $$
delimiter ;

call get_sum(209901, @name, @sum);
select @name, @sum;

drop function if exists f_get_sum;
DELIMITER $$
create function f_get_sum(_id integer) returns integer
begin
	declare _sum integer;
    select kor + eng + mat into _sum from examtable where studentid = _id;
    return _sum;
    end $$
DELIMITER ;
    
    select *, f_get_sum(studentid) from examtable;
    
    show global variables like 'log_bin_trust_function_creators';
    set global log_bin_trust_function_creators = 'ON';