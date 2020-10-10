CREATE USER hrcu IDENTIFIED BY 'go_range_1_2_3_4_5_6';

GRANT all ON hrc.* TO 'hrcu'@'%';

GRANT all ON hrc_flowable.* TO 'hrcu'@'%';

GRANT all ON hrc_rest.* TO 'hrcu'@'%';

-- update user set authentication_string=password('go_range_1_2_3_4_5_6') where user='hrcu';

flush privileges;