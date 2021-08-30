create database centralerros;

user centralerros;

create user 'user'@'localhost' identified by '123456';

grante select, insert, delete, update on centralerros.* to user@'localhost';

create table autorizacao (
  id    bigserial primary key,
  nome  varchar(100)
);

create table log (
  id            bigserial primary key,
  origem        varchar(100),
  titulo        varchar(100)
  datalhe       text,
  created_at    timestamptz,
  categoria     varchar(100),
  'level'       varchar(100)
);

create table usuario (
  id        bigserial primary key,
  nome      varchar(100),
  email     varchar(100)
  senha     varchar(100),
  token     vachar(255),
);

create table usuario_autorizacao (
  user_id           bigserial unsigned not null,
  autorizacao_id    bigserial unsigned not null
  primary key (user_id, autorizacao_id),
  foreign key aut_usuario_fk (user_id) references usuario(id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (autorizacao_id) references autorizacao(id) on delete restrict on update
);

create table usuario_log (
  user_id         bigserial unsigned not null,
  log_id          bigserial unsigned not null
  primary key (user_id, log_id),
  foreign key aut_usuario_fk (user_id) references usuario(id) on delete restrict on update cascade,
  foreign key aut_log_fk (log_id) references log(id) on delete restrict on update
);