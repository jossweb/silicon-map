-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : sam. 13 déc. 2025 à 23:07
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
  `status` enum('actually_use','ready','maintenance','issue') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `components`
--

INSERT INTO `components` (`id`, `brand`, `model`, `machine_id`, `spec_value_primary`, `spec_value_secondary`, `type`, `status`) VALUES
(1, 'AMD', 'EPYC 7313', 2, 16, 512, 'CPU', 'actually_use'),
(2, 'Kingston', 'DDR4 ECC', 2, 128, 4, 'RAM', 'actually_use'),
(3, 'Seagate', 'Exos X18', 2, 18000, NULL, 'DISK', 'actually_use'),
(4, 'Seasonic', 'Prime TX-1000', 2, 1000, NULL, 'Power_supply', 'actually_use'),
(5, 'Supermicro', '4U Storage Chassis', 2, 4, NULL, 'Chassis', 'actually_use'),
(6, 'AMD', 'EPYC 7282', 3, 16, 512, 'CPU', 'maintenance'),
(7, 'Kingston', 'DDR4 ECC', 3, 96, 4, 'RAM', 'actually_use'),
(8, 'Seagate', 'Exos X16', 3, 16000, NULL, 'DISK', 'actually_use'),
(9, 'Corsair', 'RM850x', 3, 850, NULL, 'Power_supply', 'actually_use'),
(10, 'Supermicro', '4U Storage Chassis', 3, 4, NULL, 'Chassis', 'actually_use'),
(11, 'Intel', 'Xeon Silver 4314', 4, 16, 256, 'CPU', 'actually_use'),
(12, 'Samsung', 'DDR4 ECC', 4, 64, 4, 'RAM', 'actually_use'),
(13, 'Samsung', 'PM9A3 NVMe', 4, 2000, NULL, 'DISK', 'actually_use'),
(14, 'Seasonic', 'Focus GX-750', 4, 750, NULL, 'Power_supply', 'actually_use'),
(15, 'Dell', '2U Server Chassis', 4, 2, NULL, 'Chassis', 'actually_use'),
(16, 'Intel', 'Xeon Silver 4210', 5, 10, 192, 'CPU', 'actually_use'),
(17, 'Samsung', 'DDR4 ECC', 5, 48, 4, 'RAM', 'actually_use'),
(18, 'Crucial', 'MX500 SSD', 5, 1000, NULL, 'DISK', 'actually_use'),
(19, 'Corsair', 'RM750', 5, 750, NULL, 'Power_supply', 'actually_use'),
(20, 'Dell', '2U Server Chassis', 5, 2, NULL, 'Chassis', 'actually_use'),
(21, 'AMD', 'EPYC 7302', 6, 16, 256, 'CPU', 'issue'),
(22, 'Kingston', 'DDR4 ECC', 6, 64, 4, 'RAM', 'actually_use'),
(23, 'Intel', 'DC S4510', 6, 2000, NULL, 'DISK', 'actually_use'),
(24, 'Seasonic', 'Focus GX-650', 6, 650, NULL, 'Power_supply', 'actually_use'),
(25, 'Supermicro', '1U Server Chassis', 6, 1, NULL, 'Chassis', 'actually_use'),
(26, 'AMD', 'EPYC 7402', 7, 24, 512, 'CPU', 'actually_use'),
(27, 'NVIDIA', 'A100', 7, 80, 6912, 'GPU', 'actually_use'),
(28, 'Samsung', 'DDR4 ECC', 7, 256, 4, 'RAM', 'actually_use'),
(29, 'Samsung', 'PM9A3 NVMe', 7, 4000, NULL, 'DISK', 'actually_use'),
(30, 'Seasonic', 'Prime PX-1600', 7, 1600, NULL, 'Power_supply', 'actually_use'),
(31, 'Supermicro', '4U GPU Chassis', 7, 4, NULL, 'Chassis', 'actually_use'),
(32, 'AMD', 'EPYC 7352', 8, 24, 512, 'CPU', 'maintenance'),
(33, 'NVIDIA', 'RTX 6000 Ada', 8, 48, 18176, 'GPU', 'ready'),
(34, 'Samsung', 'DDR4 ECC', 8, 128, 4, 'RAM', 'actually_use'),
(35, 'Samsung', 'PM9A3 NVMe', 8, 2000, NULL, 'DISK', 'actually_use'),
(36, 'Corsair', 'AX1600i', 8, 1600, NULL, 'Power_supply', 'actually_use'),
(37, 'Supermicro', '4U GPU Chassis', 8, 4, NULL, 'Chassis', 'actually_use'),
(38, 'AMD', 'EPYC 3251', 10, 8, 64, 'CPU', 'actually_use'),
(39, 'Kingston', 'DDR4 ECC', 10, 32, 4, 'RAM', 'actually_use'),
(40, 'Intel', 'SSD DC S3500', 10, 480, NULL, 'DISK', 'actually_use'),
(41, 'Seasonic', 'Focus GX-550', 10, 550, NULL, 'Power_supply', 'actually_use'),
(42, 'Netgate', '1U Network Chassis', 10, 1, NULL, 'Chassis', 'actually_use'),
(43, 'Intel', 'Xeon D-2146NT', 11, 8, 128, 'CPU', 'actually_use'),
(44, 'Samsung', 'DDR4 ECC', 11, 32, 4, 'RAM', 'actually_use'),
(45, 'Intel', 'SSD DC S3510', 11, 480, NULL, 'DISK', 'actually_use'),
(46, 'Seasonic', 'Focus GX-650', 11, 650, NULL, 'Power_supply', 'actually_use'),
(47, 'Netgate', '1U Security Chassis', 11, 1, NULL, 'Chassis', 'actually_use'),
(48, 'AMD', 'EPYC 7313', 2, 16, 512, 'CPU', 'actually_use');

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
(48, 45, '2025-12-13 22:37:10', 48);

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
(13, 'FIGUEIRAS', 'Jossua', 'contact@jossua.dev', 'admin', 0, '$2a$12$i3YnMztVE//cal/DeACsmOffv5Z04hWTt1aTagW6m2FYSN0L5NeiC'),
(15, 'FIGUEIRAS', 'Jossua', 'contact1@jossua.dev', 'technician', 0, '$2a$12$7SyDAVjT4su6H0iENKhknu.i9twaFSMl141Ipak01AqlizL3yO5h6');

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
-- Index pour les tables déchargées
--

--
-- Index pour la table `components`
--
ALTER TABLE `components`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_components_machine_id` (`machine_id`);

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT pour la table `component_load`
--
ALTER TABLE `component_load`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `temperature`
--
ALTER TABLE `temperature`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `components`
--
ALTER TABLE `components`
  ADD CONSTRAINT `fk_components_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`);

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