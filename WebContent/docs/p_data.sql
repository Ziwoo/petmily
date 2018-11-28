--ÆêÁ¤º¸
create table p_data(
	pd_num number not null,
	mem_id varchar2(30) not null,
	pd_passwd varchar2(15) not null,
	pd_content clob not null,
	pd_title varchar2(100) not null,
	pd_regdate date not null,
	pd_pic varchar2(1000) not null,
	constraint pd_data_pk primary key (pd_num),
	constraint pd_data_fk1 foreign key (mem_id) references p_member (mem_id)
);
--½ÃÄö½º
create sequence pd_data_seq;