ALTER TABLE `bd_responsivo`.`tb_pericia` 
ADD COLUMN `tipoDoc` VARCHAR(50) NULL AFTER `saida_user_id`,
ADD COLUMN `contatoCel` VARCHAR(45) NULL AFTER `saida_user_id`,
ADD COLUMN `contatoCel1` VARCHAR(45) NULL AFTER `contatoCel`,
ADD COLUMN `obs_entrega` VARCHAR(1000) NULL AFTER `contatoCel1`, 
ADD COLUMN `obs_entrada` VARCHAR(1000) NULL AFTER `tipoDoc`;
