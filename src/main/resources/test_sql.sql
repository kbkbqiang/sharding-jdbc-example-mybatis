CREATE TABLE IF NOT EXISTS `t_order_0` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(50), PRIMARY KEY (`order_id`));
CREATE TABLE IF NOT EXISTS `t_order_1` (`order_id` INT NOT NULL, `user_id` INT NOT NULL, `status` VARCHAR(50), PRIMARY KEY (`order_id`));


INSERT INTO `t_order_0` VALUES (1000, 10, 'INIT');
INSERT INTO `t_order_0` VALUES (1002, 10, 'INIT');
INSERT INTO `t_order_0` VALUES (1004, 10, 'INIT');
INSERT INTO `t_order_0` VALUES (1006, 10, 'INIT');
INSERT INTO `t_order_0` VALUES (1008, 10, 'INIT');
INSERT INTO `t_order_1` VALUES (1001, 10, 'INIT');
INSERT INTO `t_order_1` VALUES (1003, 10, 'INIT');
INSERT INTO `t_order_1` VALUES (1005, 10, 'INIT');
INSERT INTO `t_order_1` VALUES (1007, 10, 'INIT');
INSERT INTO `t_order_1` VALUES (1009, 10, 'INIT');


SELECT * FROM t_order_0;
SELECT * FROM t_order_1;