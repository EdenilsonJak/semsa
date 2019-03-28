ALTER TABLE `bd_responsivo`.`tb_itens_receituario` 
CHANGE COLUMN `dataMarcacao` `dataMarcacao` DATETIME NULL DEFAULT NULL;

ALTER TABLE `bd_responsivo`.`tb_itens_receituario` 
CHANGE COLUMN `dataCancelado` `dataCancelado` DATETIME NULL DEFAULT NULL;

ALTER TABLE `bd_responsivo`.`tb_itens_receituario` 
CHANGE COLUMN `dataRequisicao` `dataRequisicao` DATETIME NULL DEFAULT NULL;
