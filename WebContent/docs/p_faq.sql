create table p_faq(
	faq_num number not null,
	mem_id varchar2(30) not null,
	faq_subject varchar2(40) not null,
	faq_content clob not null,
	faq_reg_date date not null,
	faq_readcount number(3) default 0 not null,	
	faq_passwd varchar2(10) not null,
	constraint p_faq_pk primary key (faq_num),
	constraint p_faq_fk1 foreign key (mem_id) references p_member (mem_id)
);

create sequence p_faq_seq;