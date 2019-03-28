-- update e delete redundancia Atividade de Ginastica --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='135' WHERE `atividadeeconomica`='134';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='135';
-- fim ---

-- update e delete redundancia Atividade de Moteis --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='5' WHERE `atividadeeconomica`='6';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='6';
-- fim --

-- update e delete redundancia Atividade de COM. VAERG. DE ART.DE VESTUÁRIO E ACESSÓRIOS --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='13' WHERE `atividadeeconomica`='12';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='12';
UPDATE `dvs_stm`.`tb_atividade_economica` SET `nmatividade`='COM. VAERG. DE ART.DE VESTUÁRIO E ACESSÓRIOS' WHERE `cdatividadeeconomica`='13';
-- FIM --

-- update e delete redundancia Atividade de PADARIA, CONFEITARIA E CONVENIÊNCIA --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='51' WHERE `atividadeeconomica`='52';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='52';
-- FIM --

-- update e delete redundancia Atividade de RESTAURANTES --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='61' WHERE `atividadeeconomica`='60';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='60';
-- FIM --

-- update e delete redundancia Atividade de LABORATÓRIOS CLÍNICOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='63';
-- FIM --

-- update e delete redundancia Atividade de LABORATÓRIOS CLÍNICOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='67';
-- FIM --

-- update e delete redundancia Atividade de COM. VAREG. DE ARTS. DE CAÇA, PESCA E CAMPING--
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='70';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='71';
-- FIM --

-- update e delete redundancia Atividade de DISTRIBUIDORA DE COSMÉTICOS --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='74' WHERE `atividadeeconomica`='75';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='75';
-- FIM --

-- update e delete redundancia Atividade de ATIVIDADE DE PSICOLOGIA E PSICANALISE --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='77' WHERE `atividadeeconomica`='76';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='76';
-- FIM --

-- update e delete redundancia Atividade de FABRICAÇÃO DE PRODUTOS DE PADARIA E CONFEITARIA --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='80' WHERE `atividadeeconomica`='81';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='81';
-- FIM --

-- update e delete redundancia Atividade de ENSINO PROFISSIONALIZANTE --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='83' WHERE `atividadeeconomica`='84';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='84';
-- FIM --

-- update e delete redundancia Atividade de ÓTICAS --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='89' WHERE `atividadeeconomica`='90';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='90';
-- FIM --

-- update e delete redundancia Atividade de MINI BOX --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='102' WHERE `atividadeeconomica`='103';
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='102' WHERE `atividadeeconomica`='104';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='103';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='104';
-- FIM --

-- update e delete redundancia Atividade de MANICURE --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='106';
-- FIM --
-- update e delete redundancia Atividade de COM. VAREG. DE PRODUTOS AGROPECUÁRIOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='110';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO VAREGISTA DE BEBIDAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='114';
-- FIM --

-- update e delete redundancia Atividade de DISTRIBUIDORA DE CIGARROS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='121';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO ATACADISTA DE PRODUTOS ALIMENTÍCIOS EM GERAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='124' WHERE `atividadeeconomica`='125';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='125';
-- FIM --

-- update e delete redundancia Atividade de CONSULTÓRIO DE FISIOTERAPIA --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='138';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE `cdatividadeeconomica`='139';
-- FIM --

-- update e delete redundancia Atividade de ATIVIDADE MÉDICA AMBULATORIAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='150' WHERE `atividadeeconomica`='159';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='149';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='159';
-- FIM --

-- update e delete redundancia Atividade de ATENDIMENTO HOSPITALAR, EXC. PRONTO SOCORRO E UNIDADE PARA ATEND. A URGÊNCIA --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='154';
-- FIM --

-- update e delete redundancia Atividade de MERCEARIA E MINI BOX --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='164';
-- FIM --

-- update e delete redundancia Atividade de SERVIÇOS DE AGRONOMIA E CONSULTORIA ÀS ATIV. AGRICOLAS E PECUÁRIAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='178';
-- FIM --

-- update e delete redundancia Atividade de ESCRITÓRIO ADMINISTRATIVO --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='202' WHERE `atividadeeconomica`='203';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='203';
-- FIM --

-- update e delete redundancia Atividade de CHURRASQUINHO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='207';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO E REPRESENTAÇÃO DE PRODUTOS AGROPECUÁRIOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='225';
-- FIM --

-- update e delete redundancia Atividade de MÉDICA  AMBULATORIAL COM RECURSOS PARA REALIZAÇÃO DE PROCEDIMENTOS CIRÚRGICOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='230';
-- FIM --

-- update e delete redundancia Atividade de ESTÉTICA E OUTROS SERVIÇOS DE CUIDADOS COM A BELEZA --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='233';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO VAREJISTA DE PROD. ALIMENTÍCIOS EM GERAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='239' WHERE `atividadeeconomica`='242';
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='239' WHERE `atividadeeconomica`='244';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='240';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='241';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='242';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='243';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='244';
-- FIM --

-- update e delete redundancia Atividade de ARMAZEM --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='250';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='251';
-- FIM --

-- update e delete redundancia Atividade de REPRESENTAÇÃO COMERCIAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='252' WHERE `atividadeeconomica`='253';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='253';
-- FIM --

-- update e delete redundancia Atividade de VENDA DE FARINHA --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='254' WHERE `atividadeeconomica`='256';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='255';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='256';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO ATACADISTA DE PRODUTOS ALIMENTÍCIOS --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='258' WHERE `atividadeeconomica`='260';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='260';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='259';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO ATACADISTA DE MEDICAMENTOS E DROGAS DE USO HUMANO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='262';
-- FIM --

-- update e delete redundancia Atividade de COM. ATACAD. DE DEFENSIVOS AGRIC. ADUBOS, FERT. E CORRETIVOS DO SOLO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='266';
-- FIM --

-- update e delete redundancia Atividade de COMERCIO VAREJISTA DE VIDROS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='269';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='270';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='271';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='272';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='273';
-- FIM --

-- update e delete redundancia Atividade de PSICOLOGIA E PSICANÁLISE --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='276' WHERE `atividadeeconomica`='277';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='277';
-- FIM --

-- update e delete redundancia Atividade de FABRICAÇÃO DE SALGADOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='281';
-- FIM --

-- update e delete redundancia Atividade de EDUCAÇÃO PROFISSIONAL DE NÍVEL TÉCNICO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='283';
-- FIM --

-- update e delete redundancia Atividade de ALUGUEL DE EQUIPAMENTOS MUSICAIS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='288';
-- FIM --

-- update e delete redundancia Atividade de COMERCIO VSAREJISTA DE MERCADORIAS EM GERAIS(SUPERMERCADOS) --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='292';
-- FIM --

-- update e delete redundancia Atividade de AGÊNCIA DE VIAGENS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='299';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='301';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='302';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO VAREJISTA DE ANIMAIS VIVOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='303';
-- FIM --

-- update e delete redundancia Atividade de CENTRO DE SAÚDE --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='307';
-- FIM --

-- update e delete redundancia Atividade de CENTRO/SAUDE UNIDAD BASICA --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='309' WHERE `atividadeeconomica`='311';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='310';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='311';
-- FIM --

-- update e delete redundancia Atividade de DISTRIBUIDORA DE SEMENTES, PLANTAS ,FLORES E GRAMAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='318';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='319';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='320';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='321';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='322';
-- FIM --

-- update e delete redundancia Atividade de COMERCIO ATACADISTA DE SEMENTES,FLORES,PLANTAS E GRAMAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='324';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='325';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='326';
-- FIM --

-- update e delete redundancia Atividade de ALUGUEL DE MAQUINAS E EQUIPAMENTOS PARA ESCRITORIOS E ENSINO DE IDIOMAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='333';
-- FIM --

-- update e delete redundancia Atividade de  FABRICAÇÃO DE BOMBONS REGIONAIS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='337';
-- FIM --

-- update e delete redundancia Atividade de COMERCIO VAREJISTA DE MERCADORIAS EM GERAL. COM PREDOMINÂNCIA DE PRODUTOS ALIMENTÍCIOS --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='341' WHERE `atividadeeconomica`='339';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='339';
-- FIM --

-- update e delete redundancia Atividade de MASSAS CASEIRAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='350';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='351';
-- FIM --

-- update e delete redundancia Atividade de ATIVDADES DE LIMPEZA NÃO ESPECIFICADO ANTEEIORMENTE --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='356';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='357';
-- FIM --

-- update e delete redundancia Atividade de  COMÉRCIO VAREJISTA DE COSMÉTICOS, PRODUTOS DE PERFUMARIA E HIGIENE PESSOAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='360' WHERE atividadeeconomica='361';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='361';
-- FIM --

-- update e delete redundancia Atividade de  COMÉRCIO VAREJISTA --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='369';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO VAREJISTA DE MERCADORIAS EM GERAL COM PRED. DE GÊNEROS ALIMENTÍCIOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='371';
-- FIM --

-- update e delete redundancia Atividade de COM. ATAC. DE BEBIDAS NÃO ESPECIFICADAS ANTERIORMENTE --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='381';
-- FIM --

-- update e delete redundancia Atividade de COM. ATAC. DE DEFENSIVOS AGRICOLAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='392';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO VAREJIISTA DE COMBUSTÍVEIS AUTO MOTORES --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='401';
-- FIM --

-- update e delete redundancia Atividade de CONT. DE PRAGAS URBANAS, HIG. E DESINF. DE RESERV. DE ÁGUA E POÇOS ARTESIANOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='406';
-- FIM -- 

-- update e delete redundancia Atividade de EDUCAÇÃO SUPERIOR  - GRADUAÇÃO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='408';
-- FIM --

-- update e delete redundancia Atividade de EDUCAÇÃO PROFISSIONAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET `atividadeeconomica`='412' WHERE atividadeeconomica='411';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='411';

-- update e delete redundancia Atividade de COMÉRCIO VAREJISTA DE COMBUSTÍVEIS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='419' WHERE atividadeeconomica='447';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='419' WHERE atividadeeconomica='425';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='418';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='425';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='447';
-- FIM --

-- update e delete redundancia Atividade de ATIVIDADE MÉDICA AMBULATORIAL RESTRITA A CONSULTAS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='434' WHERE atividadeeconomica='435';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='435';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='434' WHERE atividadeeconomica='435';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='435';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO ATACADISTA DE SEMENTES, FLORES, PLANTAS E GRAMAS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='453';
-- FIM --

-- update e delete redundancia Atividade de COMÉRCIO ATACADISTA DE SEMENTES, FLORES, PLANTAS E GRAMAS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='461' WHERE atividadeeconomica='479';
UPDATE `dvs_stm`.`tb_atividade_economica` SET `nmatividade`='COM. ATAC. DE INST. E MATE. P/ USO MÉDIC. CIRÚRG. HOSP. E DE LABORATÓRIO' WHERE `cdatividadeeconomica`='461';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='459';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='460';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='471';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='479';
-- FIM --

-- update e delete redundancia Atividade de ATIV. MÉD. AMBUL. COM RECURSOS PARA REALIZAÇÃO DE PROCED. CIRÚRGICOS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='605';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='643';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='604';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='605';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='643';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='604';


UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='569';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='574';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='575';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='569';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='383';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='209' WHERE atividadeeconomica='130';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='383';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='130';

-- FIM --

-- update e delete redundancia Atividade de ATIV. MÉDICA AMB. RESTRITA  A CONSULTAS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='559';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='559';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='557';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='558';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='540';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='541';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='542';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='543';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='160';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='434';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='445';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='446';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='555';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='374';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='196' WHERE atividadeeconomica='359';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='160';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='434';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='445';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='446';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='555';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='374';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='359';
-- FIM --

-- update e delete redundancia Atividade de ATIVIDADE MÉDICA AMBULATORIAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='150' WHERE atividadeeconomica='567';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='762';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='806';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='567';
-- FIM --


UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='860' WHERE atividadeeconomica='861';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='860' WHERE atividadeeconomica='862';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='860' WHERE atividadeeconomica='863';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='860' WHERE atividadeeconomica='864';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='861';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='862';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='863';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='864';


DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='892';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='893';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='894';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='895';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='896';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='889';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='890';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='891';


-- update e delete redundancia Atividade ATIVIDADES DE ESTÉTICAS E OUTROS SERVIÇOS DE CUIDADOS COM A BELEZA --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='375' WHERE atividadeeconomica='449';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='375' WHERE atividadeeconomica='346';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='449';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='346';
--  FIM --

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='222';

-- update e delete redundancia Atividade CABELEIREIROS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='87' WHERE atividadeeconomica='432';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='146';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='432';
-- FIM --

-- update e delete redundancia Atividade de CABELEIREIROS, MANICURE E PEDICURE --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='676' WHERE atividadeeconomica='682';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='682';
-- FIM --

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='654' WHERE atividadeeconomica='653';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='653';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='706';

-- update e delete redundancia Atividade de --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='403' WHERE atividadeeconomica='609';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='609';
-- FIM --


UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='608' WHERE atividadeeconomica='647';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='607';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='646';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='647';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='615';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='415';


UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='193' WHERE atividadeeconomica='461';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='461';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='194';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='550';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='549';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='639';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='811';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='832';


UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='329' WHERE atividadeeconomica='576';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='329' WHERE atividadeeconomica='508';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='576';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='508';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='668' WHERE atividadeeconomica='669';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='669';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='630' WHERE atividadeeconomica='677';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='667';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='39' WHERE atividadeeconomica='561';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='561';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='536';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='621';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='601';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='602';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='465' WHERE atividadeeconomica='494';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='494';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='491';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='821';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='823';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='163' WHERE atividadeeconomica='172';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='172';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='844';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='845';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='880';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='881';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='354' WHERE atividadeeconomica='470';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='470';
UPDATE `dvs_stm`.`tb_atividade_economica` SET `nmatividade`='COMERCIO ATACADISTA DE FARINHAS, AMIDOS E FÉCULAS' WHERE `cdatividadeeconomica`='354';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='437';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='298' WHERE atividadeeconomica='879';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='298' WHERE atividadeeconomica='878';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='879';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='878';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='258' WHERE atividadeeconomica='285';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='258' WHERE atividadeeconomica='539';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='285';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='539';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='124' WHERE atividadeeconomica='367';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='124' WHERE atividadeeconomica='421';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='124' WHERE atividadeeconomica='431';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='367';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='421';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='431';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='300' WHERE atividadeeconomica='535';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='535';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='239' WHERE atividadeeconomica='783';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='239' WHERE atividadeeconomica='487';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='783';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='487';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='444';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='857';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='858';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='769';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='770';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='771';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='772';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='773';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='514' WHERE atividadeeconomica='768';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='768';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='774';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='775';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='787';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='849';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='610' WHERE atividadeeconomica='611';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='611';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='27' WHERE atividadeeconomica='72';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='72';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='439';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='711' WHERE atividadeeconomica='712';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='712';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='368';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='369';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='131' WHERE atividadeeconomica='400';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='400';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='29' WHERE atividadeeconomica='115';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='29' WHERE atividadeeconomica='443';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='29' WHERE atividadeeconomica='450';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='29' WHERE atividadeeconomica='748';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='115';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='443';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='450';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='748';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='413';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='142' WHERE atividadeeconomica='395';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='142' WHERE atividadeeconomica='414';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='395';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='414';


-- UPDATE DELETE COMÉRCIO VAREJISTA DE COMBUTÍVEIS PARA VEÍCULOS AUTOMOTORES --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='419' WHERE atividadeeconomica='391';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='419' WHERE atividadeeconomica='394';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='391';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='394';
-- FIM --

-- UPDATE DELETE COMERCIO VAREJISTA DE HORTFRUTISGRANGEIROS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='316' WHERE atividadeeconomica='349';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='349';
-- FIM --

-- UPDATE DELETE COMÉRCIO VAREJISTA DE LATICINEOS E FRIOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='689';
-- FIM --

-- UPDATE DELETE COMÉRCIO VAREJISTA DE MEDICAMENTOS VETERINÁRIOS --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='873';
-- FIM --

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='801' WHERE atividadeeconomica='493';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='493';

-- UPDATE DELETE COMERCIO ATACADISTA DE SEMENTES,FLORES,PLANTAS E GRAMAS --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='323' WHERE atividadeeconomica='454';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='454';
-- FIM --

-- COMERCIO VAREJ. DE MERCADORIAS EM GERAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='25' WHERE atividadeeconomica='394';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='394';
-- FIM --

-- COMÉRCIO VAREJISTA DE MERCADORIAS EM GERAL --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='801' WHERE atividadeeconomica='397';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='397';
-- FIM --

-- 
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='316' WHERE atividadeeconomica='198';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='198';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='741';

-- UPDATE DELTE DISTRIBUIDORA PRODUTOS LIMPEZA --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='612' WHERE atividadeeconomica='613';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='613';
-- FIM --

-- UPDATE DELETE EDUCAÇÃO INFANTIL/PRÉ-ESCOLA --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='192' WHERE atividadeeconomica='452';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='192' WHERE atividadeeconomica='513';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='704';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='452';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='513';
-- FIM --

-- UPDATE DELTE EDUCAÇÃO PROFISSIONAL DE NÍVEL TÉCNICO --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='282' WHERE atividadeeconomica='416';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='416';
-- FIM --

-- DELETE EDUCAÇÃO SUPERIOR  - GRADUAÇÃO --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='409';
-- FIM ---

-- UPDATE DELETE ENSINO DE ARTE E CULTURA --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='552' WHERE atividadeeconomica='553';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='553';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='776';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='777';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='778';
-- FIM --

-- DELTE ENSINO DE ESPORTES --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='658';
-- FIM --

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='483' WHERE atividadeeconomica='757';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='757';
UPDATE `dvs_stm`.`tb_atividade_economica` SET `nmatividade`='ESCRITÓRIO DE VENDA DE PASSAGENS' WHERE `cdatividadeeconomica`='483';
-- FIM --

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='718';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='723';

-- DELTE UPDATE GELO COMUM -- 
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='86' WHERE atividadeeconomica='388';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='86' WHERE atividadeeconomica='484';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='388';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='484';
-- FIM --

-- DELTE FABRICAO DE PRODUTO ARROZ --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='715';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='714';
-- FIM --


-- DELTE UPDATE FABRICAÇÃO DE PRODUTOS DE PADARIA E CONFEITARIA C PRED. DE PRODUÇÃO PRÓPRIA --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='372' WHERE atividadeeconomica='458';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='372' WHERE atividadeeconomica='365';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='458';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='365';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='364';
-- FIM --

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='816';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='817';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='594';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='865';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='379' WHERE atividadeeconomica='488';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='488';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='247' WHERE atividadeeconomica='511';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='511';

-- DELTE IGREJA --
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='814';
-- FIM --

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='297' WHERE atividadeeconomica='366';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='366';

-- 
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='49' WHERE atividadeeconomica='497';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='496';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='497';


DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='633';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='64' WHERE atividadeeconomica='634';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='634';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='877';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='100' WHERE atividadeeconomica='31';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='31';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='582';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='583';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='584';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='373';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='73' WHERE atividadeeconomica='102';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='102';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='227' WHERE atividadeeconomica='257';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='257';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='883';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='884';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='885';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='529' WHERE atividadeeconomica='674';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='529' WHERE atividadeeconomica='695';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='529' WHERE atividadeeconomica='455';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='674';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='455';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='695';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='528';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='599';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='780';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='781';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='782';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='842';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='8' WHERE atividadeeconomica='50';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='50';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='795';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='530';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='619';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='179' WHERE atividadeeconomica='213';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='213';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='185' WHERE atividadeeconomica='248';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='248';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='887';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='888';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='744' WHERE atividadeeconomica='745';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='745';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='852';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='546';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='263' WHERE atividadeeconomica='505';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='505';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='734';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='735';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='736';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='94' WHERE atividadeeconomica='215';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='215';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='709' WHERE atividadeeconomica='710';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='710';

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='702';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='810';

-- UPDATE E DELETE EM BAR --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='96' WHERE atividadeeconomica='1';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='1';
-- FIM --

-- UPDATE E DELETE EM BAR E LANCHONETE
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='127' WHERE atividadeeconomica='489';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='489';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='127';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='218';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='236';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='524';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='538';
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='175' WHERE atividadeeconomica='96';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='127';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='96';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='218';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='236';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='524';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='538';
-- FIM ---

-- DELETE UPDATE AÇOUGUE --
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='142' WHERE atividadeeconomica='55';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='55';
-- FIM --

-- DELETE UPDATE COMERCIO VAREJISTA DE MERCADORIAS EM GERAL
UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='837' WHERE atividadeeconomica='340';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='340';
-- FIM --

DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='578';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='822';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='152' WHERE atividadeeconomica='161';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='161';

UPDATE `dvs_stm`.`tb_fornecedor` SET atividadeeconomica='152' WHERE atividadeeconomica='134';
DELETE FROM `dvs_stm`.`tb_atividade_economica` WHERE cdatividadeeconomica='134';