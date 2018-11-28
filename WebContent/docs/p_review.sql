--분양후기
create table p_review(
	rv_num number(3) not null,
	rv_subject varchar2(40) not null,
	rv_content clob not null,
	rv_pet_picture varchar2(50),
	rv_now_date date not null,
	rv_readcount number(10) default 0 not null,
	ip varchar2(40) not null,
	mem_id varchar2(30) not null,
	constraint review_pk primary key (rv_num),
	constraint review_fk1 foreign key (mem_id) references p_member (mem_id)
);
--시퀀스
create sequence review_seq;