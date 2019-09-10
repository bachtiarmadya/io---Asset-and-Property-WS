-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 30, 2019 at 05:37 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.2.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `iot_config_service`
--

-- --------------------------------------------------------

--
-- Table structure for table `config`
--

CREATE TABLE `config` (
  `app_name` varchar(50) NOT NULL,
  `key` varchar(50) NOT NULL,
  `value` varchar(255) NOT NULL,
  `create_dt` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_dt` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `config`
--

INSERT INTO `config` (`app_name`, `key`, `value`, `create_dt`, `modified_dt`) VALUES
('app1', 'dataSource.uss.className', 'org.mariadb.jdbc.MariaDbDataSource', '2019-07-23 12:11:57', '2019-07-23 12:11:57'),
('app1', 'dataSource.uss.default', 'true', '2019-07-28 18:50:54', '2019-07-28 18:50:54'),
('app1', 'dataSource.uss.host', 'localhost', '2019-07-23 12:11:57', '2019-07-23 12:11:57'),
('app1', 'dataSource.uss.password', 'iot_user', '2019-07-23 12:13:21', '2019-07-30 15:34:33'),
('app1', 'dataSource.uss.port', '3306', '2019-07-23 12:13:21', '2019-07-23 12:13:21'),
('app1', 'dataSource.uss.schema', 'asset', '2019-07-23 12:13:21', '2019-07-30 15:35:06'),
('app1', 'dataSource.uss.username', 'iot_user', '2019-07-23 12:13:21', '2019-07-30 15:34:39');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`app_name`,`key`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
