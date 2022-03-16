CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '122313213', '15656455662', 18);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( '我来自主数据库zjq', '3213214', '15656455662', 18);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq', '41414', '15656455662', 18);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '4141414', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '421414214', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '42141421', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '414142142', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '2142142141', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', '2142142142', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', 'dsfgwew53', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', 'dsfwtetg43643g', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ('zjq666888', 'fdsgew54w44gews', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', 'dsfwtgtdfdsgw3', '15666666666', 16);
INSERT INTO `user`( `username`, `password`, `phone`, `age`) VALUES ( 'zjq666888', 'gewew43wtgsvsd', '15666666666', 16);
