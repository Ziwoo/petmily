create table p_noticeboard(
   nb_num number not null,
   mem_id varchar2(30) not null,
   nb_subject varchar2(40) not null,
   nb_content clob not null,
   nb_passwd varchar2(10) not null,
   filename varchar2(50),
   nb_reg_date date not null,
   nb_readcount number(3) default 0 not null,
   constraint p_noticeboard_pk primary key (nb_num),
   constraint p_noticeboard_fk1 foreign key (mem_id) references p_member (mem_id)
);

create sequence p_noticeboard_seq;