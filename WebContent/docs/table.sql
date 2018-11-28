create table imember(
 id varchar2(12) not null,
 name varchar2(30) not null,
 passwd varchar2(12) not null,
 phone varchar2(15) not null,
 email varchar2(30) not null,
 zipcode varchar2(6) not null,
 address1 varchar2(40) not null,
 address2 varchar2(60),
 reg_date date not null,
 constraint member_pk primary key (id)
);

create table iboard(
	num number not null,
	title varchar2(150) not null,
	content clob not null,
	hit number(5) default 0 not null,
	regdate date not null,
	ip varchar2(40) not null,
	id varchar2(12) not null,
	filename varchar2(50),
	constraint board_pk primary key (num),
	constraint board_fk1 foreign key (id) references imember (id)
);

create sequence board_seq;

create table iboard_reply(
	re_num number not null,
	re_content varchar2(900) not null,
	re_date date not null,
	re_ip varchar2(40) not null,
	num number not null,
	id varchar2(12) not null,
	constraint reply_pk primary key (re_num),
	constraint reply_board_fk1 foreign key (num) references iboard (num),
	constraint reply_member_fk2 foreign key (id) references imember (id)
)

create sequence reply_seq;

create table p_member(
 mem_id varchar2(12) not null,
 mem_name varchar2(30) not null,
 mem_passwd varchar2(15) not null,
 mem_phone varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(12),
 mem_addr1 varchar2(40),
 mem_addr2 varchar2(60),
 mem_register date not null,
 mem_level varchar2(3)
 constraint member_pk1 primary key (mem_id)
);