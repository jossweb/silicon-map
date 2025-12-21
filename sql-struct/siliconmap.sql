-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : dim. 21 déc. 2025 à 17:31
-- Version du serveur : 8.0.40
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `siliconmap`
--

-- --------------------------------------------------------

--
-- Structure de la table `components`
--

CREATE TABLE `components` (
  `id` int NOT NULL,
  `brand` varchar(40) NOT NULL,
  `model` varchar(80) NOT NULL,
  `machine_id` int DEFAULT NULL,
  `spec_value_primary` int DEFAULT NULL,
  `spec_value_secondary` int DEFAULT NULL,
  `type` enum('CPU','GPU','RAM','DISK','Power_supply','Chassis') DEFAULT NULL,
  `status` enum('actually_use','ready','maintenance','issue') DEFAULT NULL,
  `ticket` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `components`
--

INSERT INTO `components` (`id`, `brand`, `model`, `machine_id`, `spec_value_primary`, `spec_value_secondary`, `type`, `status`, `ticket`) VALUES
(1, 'AMD', 'EPYC 7313', 2, 16, 512, 'CPU', 'actually_use', NULL),
(2, 'Kingston', 'DDR4 ECC', 2, 128, 4, 'RAM', 'actually_use', NULL),
(3, 'Seagate', 'Exos X18', 2, 18000, NULL, 'DISK', 'actually_use', NULL),
(4, 'Seasonic', 'Prime TX-1000', 2, 1000, NULL, 'Power_supply', 'actually_use', NULL),
(5, 'Supermicro', '4U Storage Chassis', 2, 4, NULL, 'Chassis', 'actually_use', NULL),
(6, 'AMD', 'EPYC 7282', 3, 16, 512, 'CPU', 'maintenance', NULL),
(7, 'Kingston', 'DDR4 ECC', 3, 96, 4, 'RAM', 'actually_use', NULL),
(8, 'Seagate', 'Exos X16', 3, 16000, NULL, 'DISK', 'actually_use', NULL),
(9, 'Corsair', 'RM850x', 3, 850, NULL, 'Power_supply', 'actually_use', NULL),
(10, 'Supermicro', '4U Storage Chassis', 3, 4, NULL, 'Chassis', 'actually_use', NULL),
(11, 'Intel', 'Xeon Silver 4314', 4, 16, 256, 'CPU', 'actually_use', NULL),
(12, 'Samsung', 'DDR4 ECC', 4, 64, 4, 'RAM', 'actually_use', NULL),
(13, 'Samsung', 'PM9A3 NVMe', 4, 2000, NULL, 'DISK', 'actually_use', NULL),
(14, 'Seasonic', 'Focus GX-750', 4, 750, NULL, 'Power_supply', 'actually_use', NULL),
(15, 'Dell', '2U Server Chassis', 4, 2, NULL, 'Chassis', 'actually_use', NULL),
(16, 'Intel', 'Xeon Silver 4210', 5, 10, 192, 'CPU', 'actually_use', NULL),
(17, 'Samsung', 'DDR4 ECC', 5, 48, 4, 'RAM', 'actually_use', NULL),
(18, 'Crucial', 'MX500 SSD', 5, 1000, NULL, 'DISK', 'actually_use', NULL),
(19, 'Corsair', 'RM750', 5, 750, NULL, 'Power_supply', 'actually_use', NULL),
(20, 'Dell', '2U Server Chassis', 5, 2, NULL, 'Chassis', 'actually_use', NULL),
(21, 'AMD', 'EPYC 7302', 6, 16, 256, 'CPU', 'issue', NULL),
(22, 'Kingston', 'DDR4 ECC', 6, 64, 4, 'RAM', 'actually_use', NULL),
(23, 'Intel', 'DC S4510', 6, 2000, NULL, 'DISK', 'actually_use', NULL),
(24, 'Seasonic', 'Focus GX-650', 6, 650, NULL, 'Power_supply', 'actually_use', NULL),
(25, 'Supermicro', '1U Server Chassis', 6, 1, NULL, 'Chassis', 'actually_use', NULL),
(26, 'AMD', 'EPYC 7402', 7, 24, 512, 'CPU', 'actually_use', NULL),
(27, 'NVIDIA', 'A100', 7, 80, 6912, 'GPU', 'actually_use', NULL),
(28, 'Samsung', 'DDR4 ECC', 7, 256, 4, 'RAM', 'actually_use', NULL),
(29, 'Samsung', 'PM9A3 NVMe', 7, 4000, NULL, 'DISK', 'actually_use', NULL),
(30, 'Seasonic', 'Prime PX-1600', 7, 1600, NULL, 'Power_supply', 'actually_use', NULL),
(31, 'Supermicro', '4U GPU Chassis', 7, 4, NULL, 'Chassis', 'actually_use', NULL),
(32, 'AMD', 'EPYC 7352', 8, 24, 512, 'CPU', 'maintenance', NULL),
(33, 'NVIDIA', 'RTX 6000 Ada', 8, 48, 18176, 'GPU', 'ready', NULL),
(34, 'Samsung', 'DDR4 ECC', 8, 128, 4, 'RAM', 'actually_use', NULL),
(35, 'Samsung', 'PM9A3 NVMe', 8, 2000, NULL, 'DISK', 'actually_use', NULL),
(36, 'Corsair', 'AX1600i', 8, 1600, NULL, 'Power_supply', 'actually_use', NULL),
(37, 'Supermicro', '4U GPU Chassis', 8, 4, NULL, 'Chassis', 'actually_use', NULL),
(38, 'AMD', 'EPYC 3251', 10, 8, 64, 'CPU', 'actually_use', NULL),
(39, 'Kingston', 'DDR4 ECC', 10, 32, 4, 'RAM', 'actually_use', NULL),
(40, 'Intel', 'SSD DC S3500', 10, 480, NULL, 'DISK', 'actually_use', NULL),
(41, 'Seasonic', 'Focus GX-550', 10, 550, NULL, 'Power_supply', 'actually_use', NULL),
(42, 'Netgate', '1U Network Chassis', 10, 1, NULL, 'Chassis', 'actually_use', NULL),
(43, 'Intel', 'Xeon D-2146NT', 11, 8, 128, 'CPU', 'actually_use', NULL),
(44, 'Samsung', 'DDR4 ECC', 11, 32, 4, 'RAM', 'actually_use', NULL),
(45, 'Intel', 'SSD DC S3510', 11, 480, NULL, 'DISK', 'actually_use', NULL),
(46, 'Seasonic', 'Focus GX-650', 11, 650, NULL, 'Power_supply', 'actually_use', NULL),
(47, 'Netgate', '1U Security Chassis', 11, 1, NULL, 'Chassis', 'actually_use', NULL),
(48, 'AMD', 'EPYC 7313', 2, 16, 512, 'CPU', 'actually_use', NULL),
(49, 'NVIDIA', 'H100', NULL, 96, 16896, 'GPU', 'ready', NULL);

--
-- Déclencheurs `components`
--
DELIMITER $$
CREATE TRIGGER `componentCheckInsert` BEFORE INSERT ON `components` FOR EACH ROW BEGIN
    IF NEW.ticket IS NOT NULL AND NEW.machine_id IS NOT NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'A component can’t be assigned to both a ticket and a machine at the same time!';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `componentCheckUpdate` BEFORE UPDATE ON `components` FOR EACH ROW BEGIN
    IF NEW.ticket IS NOT NULL AND NEW.machine_id IS NOT NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'A component can’t be assigned to both a ticket and a machine at the same time.';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `componentStatusGestion` BEFORE UPDATE ON `components` FOR EACH ROW BEGIN
    IF NEW.ticket IS NOT NULL OR NEW.machine_id IS NOT NULL THEN
        SET NEW.status = 'actually_use';
	ELSE
		SET NEW.status = 'ready';
    END IF ;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `componentTicketReassignCheck` BEFORE UPDATE ON `components` FOR EACH ROW BEGIN
    IF OLD.ticket IS NOT NULL
       AND NEW.ticket IS NOT NULL
       AND NEW.ticket <> OLD.ticket THEN
       
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You can’t change the ticket on a machine that is already used in another ticket';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `component_load`
--

CREATE TABLE `component_load` (
  `id` int NOT NULL,
  `components_load` int NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `component_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `component_load`
--

INSERT INTO `component_load` (`id`, `components_load`, `date_time`, `component_id`) VALUES
(1, 72, '2025-12-13 22:37:10', 1),
(2, 81, '2025-12-13 22:37:10', 2),
(3, 68, '2025-12-13 22:37:10', 3),
(4, 74, '2025-12-13 22:37:10', 4),
(5, 55, '2025-12-13 22:37:10', 5),
(6, 65, '2025-12-13 22:37:10', 6),
(7, 77, '2025-12-13 22:37:10', 7),
(8, 61, '2025-12-13 22:37:10', 8),
(9, 69, '2025-12-13 22:37:10', 9),
(10, 52, '2025-12-13 22:37:10', 10),
(11, 70, '2025-12-13 22:37:10', 11),
(12, 78, '2025-12-13 22:37:10', 12),
(13, 66, '2025-12-13 22:37:10', 13),
(14, 71, '2025-12-13 22:37:10', 14),
(15, 50, '2025-12-13 22:37:10', 15),
(16, 64, '2025-12-13 22:37:10', 16),
(17, 73, '2025-12-13 22:37:10', 17),
(18, 59, '2025-12-13 22:37:10', 18),
(19, 67, '2025-12-13 22:37:10', 19),
(20, 48, '2025-12-13 22:37:10', 20),
(21, 82, '2025-12-13 22:37:10', 21),
(22, 80, '2025-12-13 22:37:10', 22),
(23, 62, '2025-12-13 22:37:10', 23),
(24, 76, '2025-12-13 22:37:10', 24),
(25, 53, '2025-12-13 22:37:10', 25),
(26, 91, '2025-12-13 22:37:10', 26),
(27, 98, '2025-12-13 22:37:10', 27),
(28, 88, '2025-12-13 22:37:10', 28),
(29, 74, '2025-12-13 22:37:10', 29),
(30, 85, '2025-12-13 22:37:10', 30),
(31, 68, '2025-12-13 22:37:10', 31),
(32, 76, '2025-12-13 22:37:10', 32),
(33, 82, '2025-12-13 22:37:10', 33),
(34, 71, '2025-12-13 22:37:10', 34),
(35, 66, '2025-12-13 22:37:10', 35),
(36, 73, '2025-12-13 22:37:10', 36),
(37, 60, '2025-12-13 22:37:10', 37),
(38, 69, '2025-12-13 22:37:10', 38),
(39, 74, '2025-12-13 22:37:10', 39),
(40, 58, '2025-12-13 22:37:10', 40),
(41, 67, '2025-12-13 22:37:10', 41),
(42, 49, '2025-12-13 22:37:10', 42),
(43, 71, '2025-12-13 22:37:10', 43),
(44, 76, '2025-12-13 22:37:10', 44),
(45, 61, '2025-12-13 22:37:10', 45),
(46, 70, '2025-12-13 22:37:10', 46),
(47, 52, '2025-12-13 22:37:10', 47),
(48, 45, '2025-12-13 22:37:10', 48),
(49, 84, '2025-12-14 20:51:51', 1),
(50, 90, '2025-12-14 20:51:51', 2),
(51, 78, '2025-12-14 20:51:51', 3),
(52, 86, '2025-12-14 20:51:51', 4),
(53, 68, '2025-12-14 20:51:51', 5),
(54, 80, '2025-12-14 20:51:51', 6),
(55, 88, '2025-12-14 20:51:51', 7),
(56, 75, '2025-12-14 20:51:51', 8),
(57, 83, '2025-12-14 20:51:51', 9),
(58, 65, '2025-12-14 20:51:51', 10),
(59, 82, '2025-12-14 20:51:51', 11),
(60, 89, '2025-12-14 20:51:51', 12),
(61, 77, '2025-12-14 20:51:51', 13),
(62, 85, '2025-12-14 20:51:51', 14),
(63, 66, '2025-12-14 20:51:51', 15),
(64, 79, '2025-12-14 20:51:51', 16),
(65, 86, '2025-12-14 20:51:51', 17),
(66, 73, '2025-12-14 20:51:51', 18),
(67, 81, '2025-12-14 20:51:51', 19),
(68, 64, '2025-12-14 20:51:51', 20),
(69, 91, '2025-12-14 20:51:51', 21),
(70, 88, '2025-12-14 20:51:51', 22),
(71, 76, '2025-12-14 20:51:51', 23),
(72, 89, '2025-12-14 20:51:51', 24),
(73, 70, '2025-12-14 20:51:51', 25),
(74, 95, '2025-12-14 20:51:51', 26),
(75, 99, '2025-12-14 20:51:51', 27),
(76, 94, '2025-12-14 20:51:51', 28),
(77, 87, '2025-12-14 20:51:51', 29),
(78, 92, '2025-12-14 20:51:51', 30),
(79, 78, '2025-12-14 20:51:51', 31),
(80, 88, '2025-12-14 20:51:51', 32),
(81, 93, '2025-12-14 20:51:51', 33),
(82, 90, '2025-12-14 20:51:51', 34),
(83, 82, '2025-12-14 20:51:51', 35),
(84, 89, '2025-12-14 20:51:51', 36),
(85, 74, '2025-12-14 20:51:51', 37),
(86, 83, '2025-12-14 20:51:51', 38),
(87, 88, '2025-12-14 20:51:51', 39),
(88, 72, '2025-12-14 20:51:51', 40),
(89, 84, '2025-12-14 20:51:51', 41),
(90, 67, '2025-12-14 20:51:51', 42),
(91, 85, '2025-12-14 20:51:51', 43),
(92, 90, '2025-12-14 20:51:51', 44),
(93, 74, '2025-12-14 20:51:51', 45),
(94, 87, '2025-12-14 20:51:51', 46),
(95, 69, '2025-12-14 20:51:51', 47),
(96, 63, '2025-12-14 20:51:51', 48);

-- --------------------------------------------------------

--
-- Structure de la table `machines`
--

CREATE TABLE `machines` (
  `id` int NOT NULL,
  `hostname` varchar(255) NOT NULL,
  `ip_address` varchar(15) NOT NULL,
  `mac_address` varchar(17) NOT NULL,
  `os` varchar(255) NOT NULL,
  `status` enum('Online','Offline','Maintenance') NOT NULL DEFAULT 'Offline',
  `type` enum('Storage','Compute','GPU_Compute','switch','router','firewall') NOT NULL,
  `rack` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `machines`
--

INSERT INTO `machines` (`id`, `hostname`, `ip_address`, `mac_address`, `os`, `status`, `type`, `rack`) VALUES
(2, 'stor-01', '10.0.0.10', '00:1A:2B:3C:4D:10', 'TrueNAS SCALE', 'Online', 'Storage', 1),
(3, 'stor-02', '10.0.0.11', '00:1A:2B:3C:4D:11', 'TrueNAS CORE', 'Maintenance', 'Storage', 1),
(4, 'compute-01', '10.0.0.20', '00:1A:2B:3C:4D:20', 'Ubuntu Server 22.04', 'Online', 'Compute', 1),
(5, 'compute-02', '10.0.0.21', '00:1A:2B:3C:4D:21', 'Debian 12', 'Online', 'Compute', 1),
(6, 'compute-03', '10.0.0.22', '00:1A:2B:3C:4D:22', 'Rocky Linux 9', 'Offline', 'Compute', 1),
(7, 'gpu-01', '10.0.0.30', '00:1A:2B:3C:4D:30', 'Ubuntu Server 22.04', 'Online', 'GPU_Compute', 1),
(8, 'gpu-02', '10.0.0.31', '00:1A:2B:3C:4D:31', 'Ubuntu Server 22.04', 'Maintenance', 'GPU_Compute', 1),
(9, 'sw-core-01', '10.0.0.2', '00:1A:2B:3C:4D:02', 'Cisco IOS', 'Online', 'switch', 1),
(10, 'rt-edge-01', '10.0.0.1', '00:1A:2B:3C:4D:01', 'VyOS', 'Online', 'router', 1),
(11, 'fw-01', '10.0.0.254', '00:1A:2B:3C:4D:FE', 'OPNsense', 'Online', 'firewall', 1);

-- --------------------------------------------------------

--
-- Structure de la table `racks`
--

CREATE TABLE `racks` (
  `id` int NOT NULL,
  `size` int NOT NULL DEFAULT '42',
  `location` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `racks`
--

INSERT INTO `racks` (`id`, `size`, `location`) VALUES
(1, 42, 1);

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `room`
--

INSERT INTO `room` (`id`, `name`, `description`) VALUES
(1, 'test', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `staff`
--

CREATE TABLE `staff` (
  `id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `role` enum('admin','technician') NOT NULL,
  `available` tinyint NOT NULL DEFAULT '0',
  `hashpass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `staff`
--

INSERT INTO `staff` (`id`, `name`, `first_name`, `user_name`, `role`, `available`, `hashpass`) VALUES
(13, 'FIGUEIRAS', 'Jossua', 'contact@jossua.dev', 'admin', 1, '$2a$12$i3YnMztVE//cal/DeACsmOffv5Z04hWTt1aTagW6m2FYSN0L5NeiC'),
(15, 'FIGUEIRAS', 'Jossua', 'contact1@jossua.dev', 'technician', 0, '$2a$12$7SyDAVjT4su6H0iENKhknu.i9twaFSMl141Ipak01AqlizL3yO5h6'),
(16, 'Jossu', '', '/+5ER9Xejq§?TxNX9zQ49ZzVG+n,R ;V', 'technician', 0, '$2a$12$2j5gG3hUQreKS2c080hYv.I71.kjWDDnIE48ONKBIlE6SzXColMi6'),
(17, 'test', 'new admin', '>>UWSdWV1\'tbX+$/Ovf -elgln.jL;>)', 'admin', 0, '$2a$12$BLNYbuTtsIQTI8xr6TXHSu8RSMrQBoS24lH1TBtj7Qguf/h3zB26y'),
(18, 'Jossua', 'new admin', '? mV)pWVCT$8(RP6FT<\"UGs(9B.4we4v', 'admin', 0, '$2a$12$E42IclZFwLGKmIV1nRNQhObB44rXyJR70W1Oni6IP8Y6U5.k5Xk1.'),
(19, 'thomas', 'toto', 'w;G,GQeXqhk9U6MskmiQQL/)4&7:e5qN', 'technician', 0, '$2a$12$A25Gwlj5h1nGo/iWIdmzeeS5FY2APnbgJ19tKTTeSBwW2jCrrJ66m'),
(20, 'testsystem', '', ';ISmNewR4uN\"rjw.$>+iê&2\"6xI5ê1\"f', 'admin', 0, '$2a$12$Y9NdxdFvmDXA/xIKDo7Rnu8ZaSOwNjs1XYKFT3lk2lg9..nq88fzS'),
(21, 'test', '', '?9)9szcx-mMID$jex3827/SQ55Ns/m-I', 'technician', 0, '$2a$12$sEJ2A.MwAvPBhDB6irSNTuLQ1B0iy20vTab6wjeg4qZHjdnwcQAsa'),
(22, 'test', 'test', 'j?w 3G:$Gd≠S+5n70tRj?≠HdE=\'fmfrT', 'technician', 0, '$2a$12$vvdtBWlWB.7hS4r9142QGe/bitKSI/63Tgac04LHwLHxiItOq8bme'),
(23, 'test', 'test', '1xa r)z6êXH!b\'Efé3*3x≠,1g\'L\'rj≠<', 'technician', 0, '$2a$12$axUIRv1EDGUIlkocLppDGOSkLEcf7lynrrYuvMeFljb0lEyDMLHE6'),
(24, 'zer', 'zer', 'cejf:0L?KHZZ6A0k<9êXVTu1lméRePiv', 'technician', 0, '$2a$12$aXPEbx9o4Cm0kknkHIC35OG/TduBzkTraFp7iphs5rLoLotwpnzTq'),
(25, 'zaezaedsqd', 'sqd', 'wWMy,<Ah.!-t0Bo3;ê\'\"7=4zNéNtDygq', 'technician', 0, '$2a$12$roGp7g0VmH7kOprDArICruU948H9u1E0kMzCimMnIbB3gfTUXQrIy'),
(26, 'test', 'test', 'é\'\'S,pR=Q≠S@C3kVê2NR<MrKR ch2a 6', 'technician', 0, '$2a$12$DZLUrBeEMl3On3Lf5wmrf.RkCmr4qPFlUbvuWooCkio6Z4pNDYPPe'),
(27, 'rsgfdfd', 'dsfdsf', '$N0Qjcu<GHin≠KuW!ST≠9YxdvC2&S?NG', 'technician', 0, '$2a$12$xkp72C9GaNQmn1oe00UkaeLd0/20ZRo49wVuG6bSysWMoRGU./JUK'),
(28, 'azer', 'azer', '2W2;m&dv u2yoSC1@Pc≠V$.vym\"§.*uP', 'technician', 0, '$2a$12$riuSDCj1CWUFnpes4psNtO0a5SxYnDrNsq1HUvNkKO7ICO7Qg/.pS'),
(29, 'azsaz', 'sqdsqd', 'Y>>G@9mY<i92J!x1.tt:SB\"hCO*U3Jc5', 'technician', 0, '$2a$12$Aih1LV0dx5DJEbqkEuSmK.jDOgu4CyBiOcL/5ocL9ptrN0kJm0UKu'),
(31, 'fdsf', 'xwcwxc', 'bIh8LfN6M9Dê>S;Mi\'Rcif.CT,BH!e≠≠', 'technician', 0, '$2a$12$ePMzW8g9w12/jqC2le9uBu2Eny0c5EPgucD0UYynSZt0mQqP2Kcsi'),
(32, 'test', 'test', '&1R+6l*ofIggkOKAD-Kod2GY\"-oo*r9≠', 'technician', 0, '$2a$12$mAziAU8rH9hv6lXpRtjohutec4PWg6MocvZOlsMVSSIEe0nkOx8Fm'),
(33, 'test', 'test', '8Q,QxOzqnl*gTM<q=,PR.X-§g<:B43Qg', 'technician', 0, '$2a$12$bqVuOcMiLFSb/uFQGsDh2OKTvP710ElILwLFBhMPCRFRNt/U/C7a2'),
(34, 'Patrice', 'Maubert', '-!\"\'tu)VE\'xp.R<ZVd5f2Qt\'=E-Bg8zs', 'admin', 0, '$2a$12$vqwN4bCOs7ClRJVxhfdLOO3ZyjNh7nJbcIH8.YntOZxh7jWF8arA6'),
(35, 'test', 'test', '\"N*(lg???jkb+XT;<?8L?QejdIa!d-KV', 'technician', 0, '$2a$12$TCbcKJh0LoPfyZaZAWDpv.HYedvzoM9A6AWuv0wHSdlmISlhrDbIW'),
(36, 'admin', 'test', 'B?oqjG!n6j!42ê$8tAh≠sx(9X@?;-&7k', 'admin', 0, '$2a$12$OAC7xW/J3QKIA0Yh1S4jlOFcyrYlcw43IJ9w1wimyYoWFAR6KI2lK'),
(37, 'test', 'test', '§/fhL3O:1f;x<3\'WfIi+E(Y≠dZY.e9uI', 'technician', 0, '$2a$12$1zbF.VEpt441muf3In2YEevVlRMyVzkFOK2LQIEb5l6FFM3u/J4V6'),
(38, 'test', 'test', '-Vzêyx?I=+8WGt5Owv\'9ca!3,Ldét=-1', 'technician', 0, '$2a$12$8nbSgZ5Bkf/HCdn6HFRzGe3KLm0N7xe8RShqoE7w.dAIzsI0iQhT2'),
(39, 'le chien', 'Hella', 'YQdy&e6ég&>N7l+QDEX-T=7\'G?\"M.C:\'', 'admin', 0, '$2a$12$9L81.k05gqsIfR2tQ9nB7.gipiyY2f3q/SlYQWMEClfrzqk8pfdLW');

-- --------------------------------------------------------

--
-- Structure de la table `temperature`
--

CREATE TABLE `temperature` (
  `id` int NOT NULL,
  `temperature` int NOT NULL,
  `date_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `machine_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `temperature`
--

INSERT INTO `temperature` (`id`, `temperature`, `date_time`, `machine_id`) VALUES
(1, 38, '2025-12-13 22:19:44', 2),
(2, 34, '2025-12-13 22:19:44', 3),
(3, 46, '2025-12-13 22:19:44', 4),
(4, 44, '2025-12-13 22:19:44', 5),
(5, 41, '2025-12-13 22:19:44', 6),
(6, 92, '2025-12-13 22:19:44', 7),
(7, 58, '2025-12-13 22:19:44', 8),
(8, 33, '2025-12-13 22:19:44', 9),
(9, 47, '2025-12-13 22:19:44', 10),
(10, 49, '2025-12-13 22:19:44', 11);

-- --------------------------------------------------------

--
-- Structure de la table `tickets`
--

CREATE TABLE `tickets` (
  `id` int NOT NULL,
  `machine_id` int NOT NULL,
  `created_by` int NOT NULL,
  `assigned_to` int DEFAULT NULL,
  `title` varchar(40) NOT NULL,
  `description` text,
  `status` enum('open','in_progress','closed') DEFAULT 'open',
  `open_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `closed_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `tickets`
--

INSERT INTO `tickets` (`id`, `machine_id`, `created_by`, `assigned_to`, `title`, `description`, `status`, `open_at`, `closed_at`) VALUES
(1, 5, 13, 15, 'Test', 'Test Ticket system', 'open', '2025-12-18 12:25:21', NULL),
(2, 2, 13, 15, 'Test interface', 'ticket de test de l\'interface', 'open', '2025-12-21 15:38:48', '2025-12-21 15:38:48'),
(3, 2, 13, 15, 'new test', '', 'open', '2025-12-21 17:30:19', '2025-12-21 17:30:19');

--
-- Déclencheurs `tickets`
--
DELIMITER $$
CREATE TRIGGER `ticketStatusGestion` BEFORE UPDATE ON `tickets` FOR EACH ROW BEGIN
    IF NEW.closed_at IS NOT NULL THEN
        SET NEW.status = 'Close';
    END IF;
END
$$
DELIMITER ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `components`
--
ALTER TABLE `components`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_components_machine_id` (`machine_id`),
  ADD KEY `fk_components_ticket` (`ticket`);

--
-- Index pour la table `component_load`
--
ALTER TABLE `component_load`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_load_component_id` (`component_id`);

--
-- Index pour la table `machines`
--
ALTER TABLE `machines`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_machines_rack` (`rack`);

--
-- Index pour la table `racks`
--
ALTER TABLE `racks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_racks_location` (`location`);

--
-- Index pour la table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_staff_user_name` (`user_name`);

--
-- Index pour la table `temperature`
--
ALTER TABLE `temperature`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_temperature_machine_id` (`machine_id`);

--
-- Index pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tickets_machine_id` (`machine_id`),
  ADD KEY `fk_tickets_created_by` (`created_by`),
  ADD KEY `fk_tickets_assigned_to` (`assigned_to`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `components`
--
ALTER TABLE `components`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT pour la table `component_load`
--
ALTER TABLE `component_load`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT pour la table `machines`
--
ALTER TABLE `machines`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `racks`
--
ALTER TABLE `racks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `temperature`
--
ALTER TABLE `temperature`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `components`
--
ALTER TABLE `components`
  ADD CONSTRAINT `fk_components_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`),
  ADD CONSTRAINT `fk_components_ticket` FOREIGN KEY (`ticket`) REFERENCES `tickets` (`id`);

--
-- Contraintes pour la table `component_load`
--
ALTER TABLE `component_load`
  ADD CONSTRAINT `fk_load_component_id` FOREIGN KEY (`component_id`) REFERENCES `components` (`id`);

--
-- Contraintes pour la table `machines`
--
ALTER TABLE `machines`
  ADD CONSTRAINT `fk_machines_rack` FOREIGN KEY (`rack`) REFERENCES `racks` (`id`);

--
-- Contraintes pour la table `racks`
--
ALTER TABLE `racks`
  ADD CONSTRAINT `fk_racks_location` FOREIGN KEY (`location`) REFERENCES `room` (`id`);

--
-- Contraintes pour la table `temperature`
--
ALTER TABLE `temperature`
  ADD CONSTRAINT `fk_temperature_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`);

--
-- Contraintes pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `fk_tickets_assigned_to` FOREIGN KEY (`assigned_to`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `fk_tickets_created_by` FOREIGN KEY (`created_by`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `fk_tickets_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;