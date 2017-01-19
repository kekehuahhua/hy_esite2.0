ALTER TABLE `es_content_product` 
	ADD COLUMN `f1` text(32) NULL AFTER `vip`,
	ADD COLUMN `f2` text NULL AFTER `f1`,
	ADD COLUMN `f3` text NULL AFTER `f2`,
	ADD COLUMN `f4` text NULL AFTER `f3`,
	ADD COLUMN `f5` text NULL AFTER `f4`;
	
ALTER TABLE `es_interact_lottery_prize` 
	MODIFY COLUMN `wishing` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'Congratulations' COMMENT 'ף����' AFTER `hprice`,
	MODIFY COLUMN `act_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '�����' COMMENT '�����' AFTER `wishing`,
	MODIFY COLUMN `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '��ע' COMMENT '��ע' AFTER `act_name`,
	ADD COLUMN `positionid` int(11) NULL DEFAULT 0 AFTER `link`,
	ADD COLUMN `style` text NULL AFTER `positionid`;