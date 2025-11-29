-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : sam. 29 nov. 2025 à 20:47
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
  `type` enum('actually_use','ready','maintenance','issue') NOT NULL DEFAULT 'ready',
  `spec_value_primary` int DEFAULT NULL,
  `spec_value_secondary` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `type` enum('Storage','Compute','GPU_Compute','switch','router','firewall') NOT NULL,
  `rack` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `racks`
--

CREATE TABLE `racks` (
  `id` int NOT NULL,
  `size` int NOT NULL DEFAULT '42',
  `location` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE `room` (
  `id` int NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` text
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
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `component_load`
--
ALTER TABLE `component_load`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `machines`
--
ALTER TABLE `machines`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `racks`
--
ALTER TABLE `racks`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `room`
--
ALTER TABLE `room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

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