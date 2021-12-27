-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.62 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 atm 的数据库结构
CREATE
DATABASE IF NOT EXISTS `atm` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE
`atm`;

-- 导出  表 atm.admin 结构
CREATE TABLE IF NOT EXISTS `admin`
(
    `id` int
(
    11
) unsigned NOT NULL AUTO_INCREMENT,
    `account` varchar
(
    20
) NOT NULL,
    `password` varchar
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='记录能登录后台的管理员账号和密码信息';

-- 正在导出表  atm.admin 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `account`, `password`)
VALUES (1, '18051085', '18051085');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- 导出  表 atm.card 结构
CREATE TABLE IF NOT EXISTS `card`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `account` varchar
(
    20
) NOT NULL,
    `password` varchar
(
    20
) NOT NULL,
    `name` varchar
(
    20
) NOT NULL,
    `sex` varchar
(
    20
) DEFAULT NULL,
    `age` varchar
(
    10
) DEFAULT NULL,
    `idCard` varchar
(
    20
) NOT NULL,
    `balance` varchar
(
    20
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='记录用户的银行卡信息';

-- 正在导出表  atm.card 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` (`id`, `account`, `password`, `name`, `sex`, `age`, `idCard`, `balance`)
VALUES (1, '123', '1234', '马云', '男', '45', '666666', '66400.0'),
       (2, '1234', '1234', '马化腾', '男', '40', '777777', '1600.0'),
       (3, '12345', '12345', '马冬梅', '女', '30', '888888', '500.0'),
       (8, '1', '1', '马云', '男', '45', '222', '0');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;

-- 导出  表 atm.user 结构
CREATE TABLE IF NOT EXISTS `user`
(
    `id` int
(
    10
) unsigned NOT NULL AUTO_INCREMENT,
    `account` varchar
(
    20
) NOT NULL,
    `password` varchar
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='记录能登录前台的用户账号和密码信息，每条记录包括：编号；账号；密码';

-- 正在导出表  atm.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `account`, `password`)
VALUES (1, '123', '123'),
       (2, '1234', '1234');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
