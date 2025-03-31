drop table if exists articles cascade;

create table articles
(
  is_delete       boolean                                 not null,
  author_id       bigint                                  not null,
  board_id        bigint                                  not null,
  created_at      timestamp(6) with time zone             not null,
  id              bigint                                  not null,
  modified_at     timestamp(6) with time zone,
  content         varchar(255)                            not null,
  title           varchar(255)                            not null,
  visibility_type enum('PRIVATE', 'PUBLIC', 'RESTRICTED') not null,
  primary key (id)
);
