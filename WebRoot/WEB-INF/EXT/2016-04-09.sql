ALTER TABLE `es_pay_shop` 
	ADD COLUMN `bili` int(11) NULL DEFAULT 100 AFTER `fpage`;
ALTER TABLE `es_pay_jf_shop` 
	ADD COLUMN `bili` int(11) NULL DEFAULT 100 AFTER `fpage`;
	
DROP TABLE IF EXISTS `es_pay_order` ;
CREATE TABLE `es_pay_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hyuid` bigint(20) DEFAULT NULL,
  `wxuid` bigint(20) DEFAULT NULL,
  `ownerid` bigint(20) DEFAULT NULL,
  `createtime` datetime NOT NULL COMMENT '�µ�ʱ��',
  `addressid` bigint(20) DEFAULT NULL COMMENT '������ַ',
  `sendtime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `totalprice` int(11) DEFAULT '0' COMMENT '��Ʒ�ܼ�',
  `discountprice` int(11) DEFAULT '0' COMMENT '�����۸�',
  `realprice` int(11) DEFAULT '0' COMMENT '�ɽ��۸�',
  `expressid` varchar(255) DEFAULT NULL COMMENT '��ݵ���',
  `del_tag` char(1) DEFAULT 'N' COMMENT '�Ƿ�ȡ������',
  `status` char(3) DEFAULT 'DFK' COMMENT 'DFK-������;DQR:��ȷ��DFH-������;DSH-���ջ�;CMP-�����;YQX:ȡ������',
  `ip` varchar(255) DEFAULT NULL,
  `subtype` int(11) DEFAULT '0' COMMENT '0-���ֳ��� 1-΢�̳�',
  `fatherorderid` bigint(20) DEFAULT NULL,
  `usejf` int(11) DEFAULT NULL,
  `goodscount` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `hyuid` (`hyuid`),
  KEY `addressid` (`addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;


CREATE TABLE `es_pay_order_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` bigint(20) NOT NULL COMMENT '����id',
  `productid` bigint(20) DEFAULT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `productsubtype` char(1) DEFAULT NULL COMMENT 'S-ʵ�K-��ȯ',
  `productsimg` varchar(255) DEFAULT NULL,
  `pcodeid` bigint(20) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `price` int(11) NOT NULL DEFAULT '0' COMMENT 'ʱ����Ʒ����',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0-���֣�1-�����',
  `uuid` char(10) DEFAULT NULL,
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '��Ʒ����',
  `paid` bigint(20) DEFAULT NULL COMMENT 'pay_apt id ���Ի�',
  `used` char(1) DEFAULT 'N' COMMENT '�Ƿ�ʹ�ù�',
  `shoppingcartid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `es_pay_order_goods_ibfk_2` (`orderid`),
  KEY `productid` (`productid`),
  CONSTRAINT `es_pay_order_goods_ibfk_2` FOREIGN KEY (`orderid`) REFERENCES `es_pay_order` (`id`),
  CONSTRAINT `es_pay_order_goods_ibfk_3` FOREIGN KEY (`productid`) REFERENCES `es_content_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

DELIMITER ;;
CREATE TRIGGER `goodscount` AFTER INSERT ON `es_pay_order_goods` FOR EACH ROW update es_pay_order set goodscount=goodscount+1 where id = new.orderid;;
DELIMITER ;



ALTER TABLE `es_pay_record` 
	ADD COLUMN `hyuid` bigint NULL AFTER `price`,
	ADD COLUMN `orderid` bigint NULL AFTER `hyuid`;
	
	

ALTER TABLE `es_pay_address` 
	ADD COLUMN `isdefault` char(1) NULL DEFAULT 'N' COMMENT '�Ƿ���Ĭ�ϵ�ַ Y�� N��' AFTER `telphone`;
