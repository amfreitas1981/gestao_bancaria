create table usuarios(
    id bigint not null auto_increment,
    login varchar(100) not null,
	nome varchar(100) not null,
    password varchar(255) not null,

    primary key(id)
);

create table perfis(
    id bigint not null auto_increment,
    nome varchar(10) not null unique,

    primary key(id)
);

create table usuarios_perfis(
    usuario_id bigint not null,
    perfil_id bigint not null,

    primary key(usuario_id, perfil_id),
    constraint usuarios_perfis_fk_usuario foreign key(usuario_id) references usuarios(id),
    constraint usuarios_perfis_fk_perfil foreign key(perfil_id) references perfis(id)
);

-- Criação de usuários de teste (Administrador / Teste)
INSERT INTO usuarios VALUES(1, 'admin@email.com.br', 'Administrador', '$2a$12$Yr5sZQQ8oImnBLGGwFpBvuHO7GLz6V4uhXJvQBL0gsNmCxUFTxdpy'); --123456
INSERT INTO usuarios VALUES(2, 'user.test@transact.conta', 'Teste', '$2a$12$WWHPcw43LWNpuhyANNtt1ezvlBPKaHL6WPNK/2.apPMzGSALlTV2a'); --12345

insert into perfis values(1, 'ROLE_ADMIN');
insert into perfis values(2, 'ROLE_USER');

insert into usuarios_perfis values(1, 1);


create table contas(
    id bigint not null auto_increment,
    numero_conta varchar not null,
    saldo decimal(8,2) not null,
    ativo boolean default true,

    primary key(id)
);


create table transacoes(
    id bigint not null auto_increment,
    forma_pagamento enum('C', 'D', 'P') not null, --Coloque no enum() as constantes do seu enum PaymentForm
    valor decimal(8,2) not null,
    conta_id bigint not null,

    primary key(id),
    constraint fk_transacoes_conta_id foreign key(conta_id) references contas(id)
);


select * from usuarios;
select * from perfis;
select * from usuarios_perfis;

select * from contas;
select * from transacoes;
