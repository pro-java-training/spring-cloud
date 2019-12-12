DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL DEFAULT '',
  `user_password` varchar(256) NOT NULL DEFAULT '',
  `user_type` tinyint NOT NULL DEFAULT 1,
  `user_birthday` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) AUTO_INCREMENT=1;