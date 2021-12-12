-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 11, 2021 at 05:26 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medical_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `mm_company`
--

CREATE TABLE `mm_company` (
  `Name` varchar(20) NOT NULL,
  `Address` varchar(40) NOT NULL,
  `Phone No.` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mm_company`
--

INSERT INTO `mm_company` (`Name`, `Address`, `Phone No.`) VALUES
('chetan', 'c', '3456'),
('6', '6', '6'),
('prashant ', 'I am from LATUR', '72829299'),
('atharva pvt ltd', 'pune', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `mm_drugs`
--

CREATE TABLE `mm_drugs` (
  `SN` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Price` int(100) NOT NULL,
  `Expiry day's` int(100) NOT NULL,
  `Company` varchar(100) NOT NULL,
  `Shelf No.` int(100) NOT NULL,
  `Quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mm_drugs`
--

INSERT INTO `mm_drugs` (`SN`, `Name`, `Type`, `Price`, `Expiry day's`, `Company`, `Shelf No.`, `Quantity`) VALUES
(1, '1', 'Syrup', 1, 1, '1', 1, 1),
(2, '2', 'Syrup', 2, 2, '2', 2, 2),
(3, '3', 'Syrup', 3, 3, '3', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `mm_sales`
--

CREATE TABLE `mm_sales` (
  `SN` int(100) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Price` int(200) NOT NULL,
  `Quantity` int(200) NOT NULL,
  `Total Price` varchar(100) NOT NULL,
  `Date` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mm_sales`
--

INSERT INTO `mm_sales` (`SN`, `Name`, `Type`, `Price`, `Quantity`, `Total Price`, `Date`) VALUES
(1, 'prashant', 'Medicine', 10, 3, '30', '10/12/2021'),
(2, 'q', 'Medicine', 9, 7, '63', '11/12/2021'),
(3, 'q', 'Medicine', 9, 2, '18', '11/12/2021'),
(4, 'q', 'Medicine', 9, 0, '0', '11/12/2021'),
(5, '1', 'Syrup', 1, 1, '1', '11/12/2021'),
(6, '3', 'Syrup', 3, 3, '9', '11/12/2021'),
(7, '4', 'Syrup', 4, 4, '16', '11/12/2021'),
(8, '1', 'Medicine', 1, 1, '1', '11/12/2021'),
(9, '1', 'Medicine', 1, 11, '11', '11/12/2021'),
(10, '1', 'Medicine', 1, 1, '1', '11/12/2021'),
(11, '1', 'Medicine', 1, 1, '1', '11/12/2021'),
(12, '1', 'Medicine', 1, 1, '1', '11/12/2021'),
(13, '1', 'Syrup', 1, 11, '11', '11/12/2021'),
(14, '1', 'Medicine', 1, 1, '1', '11/12/2021'),
(15, '6', 'Medicine', 6, 6, '36', '11/12/2021');

-- --------------------------------------------------------

--
-- Table structure for table `mm_warning`
--

CREATE TABLE `mm_warning` (
  `Name` varchar(20) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Expiry day's` int(200) NOT NULL,
  `Quantity` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mm_warning`
--

INSERT INTO `mm_warning` (`Name`, `Type`, `Expiry day's`, `Quantity`) VALUES
('1', 'Syrup', 1, 1),
('3', 'Syrup', 3, 3),
('6', 'Medicine', 6, 6),
('xyz', 'Medicine', 45, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mm_drugs`
--
ALTER TABLE `mm_drugs`
  ADD PRIMARY KEY (`SN`);

--
-- Indexes for table `mm_sales`
--
ALTER TABLE `mm_sales`
  ADD PRIMARY KEY (`SN`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `mm_drugs`
--
ALTER TABLE `mm_drugs`
  MODIFY `SN` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mm_sales`
--
ALTER TABLE `mm_sales`
  MODIFY `SN` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
