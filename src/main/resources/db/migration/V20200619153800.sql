create table cliente (
    id bigint auto_increment not null primary key,
    nome varchar(60) not null,
    email varchar(255) not null unique,
    telefone varchar(20) not null
)