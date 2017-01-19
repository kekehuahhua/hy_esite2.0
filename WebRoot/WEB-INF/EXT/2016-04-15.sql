ALTER TABLE `es_yu_yue_servicer` 
	ADD COLUMN `oidx` int(11) NOT NULL DEFAULT 0 AFTER `type`,
	ADD COLUMN `otop` int(11) NULL AFTER `oidx`,
	ADD COLUMN `owner` bigint NULL AFTER `otop`,
	ADD COLUMN `leveltotal` int(11) NOT NULL DEFAULT 0 COMMENT '���۵ĵȼ�����' AFTER `owner`,
	ADD COLUMN `pjtotal` int(11) NOT NULL DEFAULT 0 COMMENT '���۵Ĵ���' AFTER `leveltotal`;
CREATE TABLE `es_yu_yue_servicer_pj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `srid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `dzcontent` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `es_off_chenck_dz_way` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `csid` bigint(20) DEFAULT NULL COMMENT 'check_source',
  `ceatetime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0-δʹ�ã�1-�Ѿ�ʹ��',
  `cwxuid` bigint(20) DEFAULT NULL COMMENT '��ά�봴����',
  `swxuid` bigint(20) DEFAULT NULL COMMENT '��ɨ��',
  `stime` datetime DEFAULT NULL COMMENT 'ɨ��ʱ��',
  `rmb` int(11) NOT NULL DEFAULT '0' COMMENT '���ѵ�rmb����λ��',
  `jf` int(11) NOT NULL COMMENT '��õĻ���',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `es_off_check_source` 
	ADD COLUMN `type` int(11) NOT NULL DEFAULT 0 COMMENT '0-��ͨǩ����1-�������ѻ���' AFTER `gzeid`,
	ADD COLUMN `dzpageid` bigint NULL COMMENT '��ӪԱҳ��' AFTER `type`,
	ADD COLUMN `dtpageid` bigint NULL COMMENT '���ɶ�̬��ά���ҳ��' AFTER `dzpageid`,
	ADD COLUMN `jfpageid` bigint NULL COMMENT '���ѻ�û��ֳɹ���ҳ��' AFTER `dtpageid`,
	ADD COLUMN `rmbjf` int(11) NOT NULL DEFAULT 0 COMMENT '���Ķ���Ԫ���һ������' AFTER `jfpageid`;
ALTER TABLE `es_yu_yue_servicer` 
	ADD COLUMN `oidx` int(11) NOT NULL DEFAULT 0 AFTER `type`,
	ADD COLUMN `otop` int(11) NULL AFTER `oidx`,
	ADD COLUMN `owner` bigint NULL AFTER `otop`,
	ADD COLUMN `leveltotal` int(11) NOT NULL DEFAULT 0 COMMENT '���۵ĵȼ�����' AFTER `owner`,
	ADD COLUMN `pjtotal` int(11) NOT NULL DEFAULT 0 COMMENT '���۵Ĵ���' AFTER `leveltotal`;
CREATE TABLE `es_yu_yue_servicer_pj` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `srid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `dzcontent` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `es_off_chenck_dz_way` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `csid` bigint(20) DEFAULT NULL COMMENT 'check_source',
  `ceatetime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0-δʹ�ã�1-�Ѿ�ʹ��',
  `cwxuid` bigint(20) DEFAULT NULL COMMENT '��ά�봴����',
  `swxuid` bigint(20) DEFAULT NULL COMMENT '��ɨ��',
  `stime` datetime DEFAULT NULL COMMENT 'ɨ��ʱ��',
  `rmb` int(11) NOT NULL DEFAULT '0' COMMENT '���ѵ�rmb����λ��',
  `jf` int(11) NOT NULL COMMENT '��õĻ���',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `es_off_check_source` 
	ADD COLUMN `type` int(11) NOT NULL DEFAULT 0 COMMENT '0-��ͨǩ����1-�������ѻ���' AFTER `gzeid`,
	ADD COLUMN `dzpageid` bigint NULL COMMENT '��ӪԱҳ��' AFTER `type`,
	ADD COLUMN `dtpageid` bigint NULL COMMENT '���ɶ�̬��ά���ҳ��' AFTER `dzpageid`,
	ADD COLUMN `jfpageid` bigint NULL COMMENT '���ѻ�û��ֳɹ���ҳ��' AFTER `dtpageid`,
	ADD COLUMN `rmbjf` int(11) NOT NULL DEFAULT 0 COMMENT '���Ķ���Ԫ���һ������' AFTER `jfpageid`;

