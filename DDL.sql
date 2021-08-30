create database centralerros;

use centralerros;

CREATE USER root WITH PASSWORD 'root';

grante select, insert, delete, update on centralerros.* to root@'localhost';

create table autorizacao (
  id    serial primary key,
  nome  varchar(100)
);

create table log (
  id            serial primary key,
  origem        varchar(100),
  titulo        varchar(100),
  detalhe       text,
  created_at    timestamp,
  categoria     varchar(100),
  "level"       varchar(100)
);

create table usuario (
  id        serial primary key,
  nome      varchar(100),
  email     varchar(100),
  senha     varchar(100),
  token     varchar(255)
);

create table usuario_autorizacao (
  user_id           serial not null,
  autorizacao_id    serial not null,
  primary key (user_id, autorizacao_id),
  foreign key (user_id) references usuario(id),
  foreign key (autorizacao_id) references autorizacao(id)
);

create table usuario_log (
  user_id         serial not null,
  log_id          serial not null,
  primary key (user_id, log_id),
  foreign key (user_id) references usuario(id),
  foreign key (log_id) references log(id)
);