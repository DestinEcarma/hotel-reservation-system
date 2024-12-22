-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 12, 2024 at 02:37 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

CREATE DATABASE `hotel_reservation`;
USE `hotel_reservation`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_reservation`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `contact`) VALUES
(6, 'Destin', 'destin@gmail.com'),
(7, 'Ecarma', 'ecarma@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `inquiries`
--

CREATE TABLE `inquiries` (
  `id` int(11) NOT NULL,
  `client_name` varchar(50) NOT NULL,
  `client_contact` varchar(50) NOT NULL,
  `room_type` enum('Single Bed','Double Bed','Triple Bed','') NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` enum('Pending','Accepted','Declined') NOT NULL DEFAULT 'Pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `inquiries`
--

INSERT INTO `inquiries` (`id`, `client_name`, `client_contact`, `room_type`, `start_date`, `end_date`, `status`) VALUES
(1, 'Destin', 'destin@gmail.com', 'Single Bed', '2024-12-13', '2024-12-14', 'Accepted'),
(2, 'Ecarma', 'ecarma@gmail.com', 'Single Bed', '2024-12-14', '2024-12-15', 'Accepted'),
(3, 'John Smith', 'john.smith@example.com', 'Single Bed', '2024-12-16', '2024-12-18', 'Pending'),
(4, 'Alice Lee', 'alice.lee@company.com', 'Triple Bed', '2024-12-16', '2024-12-20', 'Pending'),
(5, 'Michael Brown', 'michael_brown@work.org', 'Double Bed', '2024-12-18', '2024-12-20', 'Pending'),
(6, 'John Smith', 'john.smith@example.com', 'Single Bed', '2024-12-16', '2024-12-18', 'Pending'),
(7, 'Alice Lee', 'alice.lee@company.com', 'Triple Bed', '2024-12-16', '2024-12-20', 'Pending'),
(8, 'Michael Brown', 'michael_brown@work.org', 'Double Bed', '2024-12-18', '2024-12-20', 'Pending'),
(9, 'David Williams', 'david.williams123@hotmail.com', 'Double Bed', '2024-12-16', '2024-12-22', 'Pending'),
(10, 'Emily Garcia', 'emily.garcia@email.net', 'Single Bed', '2024-12-17', '2024-12-19', 'Pending'),
(11, 'Charles Johnson', 'charles.johnson@yahoo.com', 'Triple Bed', '2024-12-16', '2024-12-18', 'Pending'),
(12, 'Amanda Hernandez', 'amanda_h@outlook.com', 'Double Bed', '2024-12-15', '2024-12-17', 'Pending'),
(13, 'Robert Moore', 'robert.moore@business.com', 'Triple Bed', '2024-12-16', '2024-12-18', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `handled_by` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `payment_made` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`id`, `customer_id`, `room_id`, `handled_by`, `start_date`, `end_date`, `payment_made`) VALUES
(1, 6, 1, 1, '2024-12-13', '2024-12-14', 0),
(2, 7, 3, 1, '2024-12-14', '2024-12-15', 0);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `room_number` varchar(25) NOT NULL,
  `room_type` enum('Single Bed','Double Bed','Triple Bed','') NOT NULL,
  `is_available` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `room_number`, `room_type`, `is_available`) VALUES
(1, 'A101', 'Single Bed', 1),
(3, 'A102', 'Single Bed', 1),
(5, 'B101', 'Double Bed', 1),
(6, 'B102', 'Double Bed', 1),
(7, 'C101', 'Triple Bed', 1),
(8, 'C102', 'Triple Bed', 1);

-- --------------------------------------------------------

--
-- Table structure for table `staffs`
--

CREATE TABLE `staffs` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `password_length` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staffs`
--

INSERT INTO `staffs` (`id`, `name`, `contact`, `username`, `password_hash`, `password_length`) VALUES
(1, 'Destin Ecarma', 'destin_ecarma@gmail.com', 'destin', '$2a$10$UXbQlwFCMxbAwmnT/NQyRejjfwHu3BhNlIkcb/wmdFFzM./sUKjnS', 6),
(2, 'John Doe', 'john_doe@gmail.com', 'john_doe', '$2a$10$EUWTcLpMLrpHLVi54JoG1uRK5RXM74HqBcSLKY01ZPCXqOqCBPHVm', 4),
(3, 'asdf', 'asdf', 'asdf', '$2a$10$3r3.6Ftj8jvBpZuzLZtZ/uiOZgvlbGmrlqUGwqbtei01LfHd6kK0y', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `inquiries`
--
ALTER TABLE `inquiries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `handled_by` (`handled_by`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_room_number` (`room_number`);

--
-- Indexes for table `staffs`
--
ALTER TABLE `staffs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `inquiries`
--
ALTER TABLE `inquiries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `staffs`
--
ALTER TABLE `staffs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`handled_by`) REFERENCES `staffs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
