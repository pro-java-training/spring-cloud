DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` BIGINT ( 20 ) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_name` VARCHAR ( 64 ) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `user_password` VARCHAR ( 256 ) NOT NULL DEFAULT '' COMMENT '用户密码',
  `user_type` TINYINT NOT NULL DEFAULT 1 COMMENT '用户类型',
  `user_birthday` BIGINT ( 20 ) UNSIGNED NOT NULL DEFAULT '0' COMMENT '用户生日',
  PRIMARY KEY `pk_user_id` ( `user_id` )
) AUTO_INCREMENT = 1;