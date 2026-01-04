-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : sam. 03 jan. 2026 à 23:21
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
(49, 'NVIDIA', 'H100', NULL, 96, 16896, 'Chassis', 'actually_use', 17);

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
DELIMITER $$
CREATE TRIGGER `machines_before_insert` BEFORE INSERT ON `components` FOR EACH ROW BEGIN
    IF NEW.machine_id IS NOT NULL THEN
        SET NEW.status = 'actually_use';
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
  `type` enum('Storage','Compute','GPU_Compute','switch','router','firewall') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `machines`
--

INSERT INTO `machines` (`id`, `hostname`, `ip_address`, `mac_address`, `os`, `status`, `type`) VALUES
(2, 'stor-01', '10.0.0.10', '00:1A:2B:3C:4D:10', 'TrueNAS SCALE', 'Online', 'Storage'),
(3, 'stor-02', '10.0.0.11', '00:1A:2B:3C:4D:11', 'TrueNAS CORE', 'Online', 'Storage'),
(4, 'compute-01', '10.0.0.20', '00:1A:2B:3C:4D:20', 'Ubuntu Server 22.04', 'Online', 'Compute'),
(5, 'compute-02', '10.0.0.21', '00:1A:2B:3C:4D:21', 'Debian 12', 'Online', 'Compute'),
(6, 'compute-03', '10.0.0.22', '00:1A:2B:3C:4D:22', 'Rocky Linux 9', 'Online', 'Compute'),
(7, 'gpu-01', '10.0.0.30', '00:1A:2B:3C:4D:30', 'Ubuntu Server 22.04', 'Online', 'GPU_Compute'),
(8, 'gpu-02', '10.0.0.31', '00:1A:2B:3C:4D:31', 'Ubuntu Server 22.04', 'Online', 'GPU_Compute'),
(9, 'sw-core-01', '10.0.0.2', '00:1A:2B:3C:4D:02', 'Cisco IOS', 'Online', 'switch'),
(10, 'rt-edge-01', '10.0.0.1', '00:1A:2B:3C:4D:01', 'VyOS', 'Online', 'router'),
(11, 'fw-01', '10.0.0.254', '00:1A:2B:3C:4D:FE', 'OPNsense', 'Online', 'firewall');

-- --------------------------------------------------------

--
-- Structure de la table `Message`
--

CREATE TABLE `Message` (
  `id` int NOT NULL,
  `message` text NOT NULL,
  `tickets` int NOT NULL,
  `author` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(15, 'FIGUEIRAS', 'Jossua', 'contact1@jossua.dev', 'technician', 0, '$2a$12$7SyDAVjT4su6H0iENKhknu.i9twaFSMl141Ipak01AqlizL3yO5h6'),

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
-- Déclencheurs `tickets`
--
DELIMITER $$
CREATE TRIGGER `ticketStatusGestion` BEFORE UPDATE ON `tickets` FOR EACH ROW BEGIN
    IF NEW.closed_at IS NOT NULL THEN
        SET NEW.status = 'Closed';
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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Message`
--
ALTER TABLE `Message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ticket` (`tickets`),
  ADD KEY `fk_author` (`author`);

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
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `component_load`
--
ALTER TABLE `component_load`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `machines`
--
ALTER TABLE `machines`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `Message`
--
ALTER TABLE `Message`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `temperature`
--
ALTER TABLE `temperature`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `fk_components_machine_id` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`),
  ADD CONSTRAINT `fk_components_ticket` FOREIGN KEY (`ticket`) REFERENCES `tickets` (`id`);

--
-- Contraintes pour la table `component_load`
--
ALTER TABLE `component_load`
  ADD CONSTRAINT `fk_load_component_id` FOREIGN KEY (`component_id`) REFERENCES `components` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `fk_author` FOREIGN KEY (`author`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `fk_ticket` FOREIGN KEY (`tickets`) REFERENCES `tickets` (`id`);

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
