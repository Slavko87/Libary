-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 23, 2019 at 12:43 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `biblioteka2`
--

-- --------------------------------------------------------

--
-- Table structure for table `autor`
--

CREATE TABLE `autor` (
  `id` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autor`
--

INSERT INTO `autor` (`id`, `ime`, `prezime`) VALUES
(1, 'Ivo', 'Andric'),
(2, 'Mesa', 'Selimovic'),
(3, 'Ben', 'Mezrik'),
(4, 'Milos', 'Crnjanski'),
(5, 'Vuk', 'Karadzic'),
(6, 'Stiv', 'Dzobs');

-- --------------------------------------------------------

--
-- Table structure for table `clan`
--

CREATE TABLE `clan` (
  `id` int(11) NOT NULL,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `sifra` varchar(40) NOT NULL,
  `brojZaduzenihKnjiga` int(11) NOT NULL,
  `privilegija` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clan`
--

INSERT INTO `clan` (`id`, `ime`, `prezime`, `email`, `sifra`, `brojZaduzenihKnjiga`, `privilegija`) VALUES
(1, 'Slavko', 'Stanisavljevic', 'stanisavljevicslavko@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 5, 2),
(2, 'Mirko', 'Stanisavljevic', 'mirko@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2),
(3, 'Miki', 'Mikic', 'miki@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2),
(4, 'Marko', 'Markovic', 'marko@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 1, 2),
(5, 'ADMIN', 'SYS', 'admin@gmail.com', 'd033e22ae348aeb5660fc2140aec35850c4da997', 0, 1),
(6, 'Pera', 'Peric', 'pera@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2),
(7, 'Marko', 'Stanisavljevic', 'markos@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2),
(16, 'Ana', 'Matic', 'ana@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2),
(17, 'Mitar', 'Miric', 'micko@gmail.com', 'e3bce3369657d538921783b9a51b44772ee5ed6a', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `evidencijaiznajmljenihknjiga`
--

CREATE TABLE `evidencijaiznajmljenihknjiga` (
  `id` int(11) NOT NULL,
  `idClana` int(11) NOT NULL,
  `idKnjige` int(11) NOT NULL,
  `datumIznajmljivanja` timestamp NULL DEFAULT NULL,
  `datumVracanja` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evidencijaiznajmljenihknjiga`
--

INSERT INTO `evidencijaiznajmljenihknjiga` (`id`, `idClana`, `idKnjige`, `datumIznajmljivanja`, `datumVracanja`) VALUES
(5, 1, 1, '2019-09-03 17:37:42', NULL),
(6, 1, 3, '2019-09-17 17:38:27', NULL),
(7, 1, 5, '2019-09-04 17:38:29', NULL),
(8, 1, 7, '2019-09-01 17:38:31', NULL),
(9, 1, 10, '2019-08-13 17:38:33', '2019-09-21 19:24:36'),
(10, 4, 5, '2019-09-02 17:41:07', NULL),
(11, 7, 1, '2019-09-01 17:52:11', '2019-09-17 17:56:36'),
(12, 1, 1, '2019-09-21 20:34:34', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `knjiga`
--

CREATE TABLE `knjiga` (
  `id` int(11) NOT NULL,
  `naziv` varchar(50) NOT NULL,
  `ukupnoKnjiga` int(11) NOT NULL,
  `trenutniBrojKnjiga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `knjiga`
--

INSERT INTO `knjiga` (`id`, `naziv`, `ukupnoKnjiga`, `trenutniBrojKnjiga`) VALUES
(1, 'Java', 10, 8),
(2, 'Java 2', 5, 5),
(3, 'OOP', 5, 4),
(4, 'C#', 7, 7),
(5, 'MySQL', 8, 6),
(6, 'HTML i CSS', 9, 9),
(7, 'C++', 9, 8),
(9, 'C', 3, 3),
(10, 'JS', 4, 4),
(15, 'Baze podataka', 5, 5),
(16, 'Na drini cuprija', 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `knjigaautor`
--

CREATE TABLE `knjigaautor` (
  `id` int(11) NOT NULL,
  `idAutora` int(11) NOT NULL,
  `idKnjige` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `knjigaautor`
--

INSERT INTO `knjigaautor` (`id`, `idAutora`, `idKnjige`) VALUES
(1, 3, 4),
(3, 2, 1),
(4, 4, 2),
(5, 3, 5),
(7, 3, 7),
(9, 4, 9),
(10, 2, 10),
(15, 2, 6),
(16, 1, 6),
(17, 3, 6),
(18, 1, 3),
(23, 3, 15),
(24, 1, 16);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clan`
--
ALTER TABLE `clan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `evidencijaiznajmljenihknjiga`
--
ALTER TABLE `evidencijaiznajmljenihknjiga`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idClanaFK` (`idClana`),
  ADD KEY `idKnjigaFK` (`idKnjige`);

--
-- Indexes for table `knjiga`
--
ALTER TABLE `knjiga`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `knjigaautor`
--
ALTER TABLE `knjigaautor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idAutoraFK` (`idAutora`),
  ADD KEY `idKnjigeFK` (`idKnjige`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `autor`
--
ALTER TABLE `autor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `clan`
--
ALTER TABLE `clan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `evidencijaiznajmljenihknjiga`
--
ALTER TABLE `evidencijaiznajmljenihknjiga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `knjiga`
--
ALTER TABLE `knjiga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `knjigaautor`
--
ALTER TABLE `knjigaautor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evidencijaiznajmljenihknjiga`
--
ALTER TABLE `evidencijaiznajmljenihknjiga`
  ADD CONSTRAINT `idClanaFK` FOREIGN KEY (`idClana`) REFERENCES `clan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idKnjigaFK` FOREIGN KEY (`idKnjige`) REFERENCES `knjiga` (`id`);

--
-- Constraints for table `knjigaautor`
--
ALTER TABLE `knjigaautor`
  ADD CONSTRAINT `idAutoraFK` FOREIGN KEY (`idAutora`) REFERENCES `autor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idKnjigeFK` FOREIGN KEY (`idKnjige`) REFERENCES `knjiga` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
