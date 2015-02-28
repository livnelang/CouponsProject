-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2015 at 06:27 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

CREATE DATABASE IF NOT EXISTS livnedatabase;
USE livnedatabase;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `livnedatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `coupons`
--

CREATE TABLE IF NOT EXISTS `coupons` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `category` text NOT NULL,
  `longitude` int(11) NOT NULL,
  `latitude` int(11) NOT NULL,
  `expiration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=223577 ;

--
-- Dumping data for table `coupons`
--

INSERT INTO `coupons` (`id`, `name`, `description`, `category`, `longitude`, `latitude`, `expiration`) VALUES
(13, 'ABRA', 'CADABRA', 'Music', 3, 3, '2015-03-02 15:44:00'),
(16, 'Louie', 'Gresko', 'TV', 4, 4, '2015-03-02 15:42:00'),
(43, 'Lindy', 'Hop', 'Music', 23, 23, '2020-03-03 15:42:00'),
(3454, 'Mya', 'Fero', 'MUSIC', 55, 45, '2020-01-24 10:31:00'),
(8374, 'Mo', 'Joes', 'TV', 45, 6, '2015-03-02 16:21:00'),
(9182, 'a', 'sas', 'Music', 34, 43, '1995-01-24 10:31:00'),
(34324, 'asasa', 'ad', 'TV', 324, 2, '1995-01-24 10:30:00'),
(223576, 'Bob', 'Builder', 'CARS', 5, 5, '2015-03-01 15:50:00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('admin', 'bcf8e2ca178ec3e5e5d7d84f3a0b2ae9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coupons`
--
ALTER TABLE `coupons`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coupons`
--
ALTER TABLE `coupons`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=223577;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
