drop table if exists articles cascade;

create table articles
(
  id          bigint                                  not null,
  content     varchar(255)                            not null,
  title       varchar(255)                            not null,
  author_id   bigint                                  not null,
  board_id    bigint                                  not null,
  visibility  enum('PRIVATE', 'PUBLIC', 'RESTRICTED') not null,
  created_at  timestamp(6) with time zone             not null,
  modified_at timestamp(6) with time zone,
  deleted_at  timestamp(6) with time zone,
  is_deleted  boolean                                 not null,
  primary key (id)
);
