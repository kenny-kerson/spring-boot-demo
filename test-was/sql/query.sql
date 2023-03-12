select * from user_m;
select * from user_d;

select * from team_m;

insert into user_m (email, name) values( 'kenny.k@kakaobank.com', '고케니2');
truncate user_m;

commit;
rollback;