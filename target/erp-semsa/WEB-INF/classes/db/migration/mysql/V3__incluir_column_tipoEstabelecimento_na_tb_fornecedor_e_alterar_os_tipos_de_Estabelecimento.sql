create table tb_pericia (
        id_pericia bigint not null auto_increment,
        co_procedimento varchar(10) not null,
        dt_entrada datetime not null,
        dt_entrega datetime,
        no_procedimento varchar(250) not null,
        nomeRecebor varchar(200),
        rgCpf varchar(255),
        status varchar(50) not null,
        cid_id bigint not null,
        estabelecimento_origem_id bigint not null,
        estabelecimento_destino_id bigint not null,
        medico_solicitante_id bigint,
        paciente_id bigint not null,
        entrada_user_id bigint not null,
        saida_user_id bigint,
        primary key (id_pericia));

alter table tb_pericia 
     add constraint FK7bs1mkpc226m6exqop4hqkqjs 
     foreign key (cid_id) 
     references tb_cid (id);
    
alter table tb_pericia 
     add constraint FKnh32ff39hpablxaghse1p0vg9 
     foreign key (estabelecimento_origem_id) 
     references tb_fornecedor (id);
    
alter table tb_pericia 
     add constraint FKjrcrqcisejg5mexubkwbe6s42 
     foreign key (estabelecimento_destino_id) 
     references tb_fornecedor (id);
    
alter table tb_pericia 
     add constraint FKd3kgg9ujrpsgwdd7047gwavea 
     foreign key (medico_solicitante_id) 
     references tb_medico (id);
   
alter table tb_pericia 
     add constraint FKahyamp5431w6xbsi6dbe2oacl 
     foreign key (paciente_id) 
     references tb_paciente (id_cliente);

alter table tb_pericia 
     add constraint FKc7yusacxabvn0lr9hyd81g25t 
     foreign key (entrada_user_id) 
     references usuario (id); 
    
alter table tb_pericia 
     add constraint FK69kqdojdmer5g030sk57x7ve 
     foreign key (saida_user_id) 
     references usuario (id);

/* adicionar column tipoEstabelecimento 

HABILITAR ESSE ALTER ASSIM QUE FOR GERAR O DEPLOY

*/
     
ALTER TABLE `bd_responsivo`.`tb_fornecedor` 
ADD COLUMN `tipoEstabelecimento` VARCHAR(100) NOT NULL AFTER `tipoLogradouro`;


/* adicionar coluna de chave estrangeira
 * add index estrangeirismo
 * add constraint
 */
/*
HABILITAR ASSIM QUE FOR GERAR O DEPLOY 

ALTER TABLE `bd_responsivo`.`tb_pericia` 
ADD COLUMN `estabelecimento_destino_id` BIGINT(20) NOT NULL AFTER `estabelecimento_origem_id`,
ADD INDEX `FK_Estabelecimento__idx` (`estabelecimento_destino_id` ASC);
ALTER TABLE `bd_responsivo`.`tb_pericia` 
ADD CONSTRAINT `FK_Estabelecimento_`
  FOREIGN KEY (`estabelecimento_destino_id`)
  REFERENCES `bd_responsivo`.`tb_fornecedor` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
*/
/* update para tb_fornecedor para setar status todos nulos e tipo estabelecimento para seu respectivo estabelecimento */

ALTER TABLE `bd_responsivo`.`tb_fornecedor` auto_increment = 9;
     
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='2';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='3';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='CENTROESPECIALIZADO' WHERE `id`='4';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='5';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='6';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='7';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `status`=NULL, `tipoEstabelecimento`='HOSPITAL' WHERE `id`='8';

/* update para nome fantasia dos estabelecimento*/

UPDATE `bd_responsivo`.`tb_fornecedor` SET `nm_fantasia`='HMS - HOSPITAL SANTAREM' WHERE `id`='5';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `nm_fantasia`='HMSF - SAGRADA FAMILIA' WHERE `id`='7';
UPDATE `bd_responsivo`.`tb_fornecedor` SET `nm_fantasia`='HRBA - HOSPITAL REGIONAL' WHERE `id`='8';

INSERT INTO `tb_fornecedor` VALUES (NULL,'SANTA CLARA',NULL,'68005110','SANTARÉM','05182233000680','2018-05-26','PA','CENTRO DE REFERENCIA DE SAUDE DA MULHER',NULL,'PREFEITURA MUNICIPAL DE SANTAREM',NULL,'BARAO DO RIO BRANCO',NULL,NULL,NULL,NULL,'AVENIDA','CENTROESPECIALIZADO'),(NULL,'FATIMA',NULL,'68040790','SANTARÉM','26993737000146','2018-05-26','PA','RTI TELECOM',NULL,'RTI SERVICOS DE TELECOMUNICACAO EIRELI ',NULL,'JOAQUIM MARTINS',182,NULL,NULL,NULL,'RUA','FORNECEDOR');
