create table p_member(
 mem_id varchar2(12) not null,
 mem_name varchar2(30) not null,
 mem_passwd varchar2(15) not null,
 mem_cell varchar2(15) not null,
 mem_email varchar2(50) not null,
 mem_zipcode varchar2(12),
 mem_addr1 varchar2(40),
 mem_addr2 varchar2(60),
 mem_register date not null,
 mem_level varchar2(3),
 constraint member_pk1 primary key (mem_id)
);
