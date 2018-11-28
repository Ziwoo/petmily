--테이블생성
--br_pic blob -> varchar2 데이터 타입변경
create table p_breed(
	br_num number not null,
	mem_id varchar2(30) not null,
	br_subject varchar2(40) not null,
	br_content clob not null,
	br_passwd varchar2(10) not null,
	br_regdate date not null,
	br_pic varchar2(45) not null,
	br_readcount number,
	constraint breed_pk primary key(br_num),
	constraint breed_fk1 foreign key (mem_id) references p_member (mem_id)
);

--시퀀스 생성
create sequence breed_seq;

--테이블 전체 삭제
drop table p_breed cascade constraints;