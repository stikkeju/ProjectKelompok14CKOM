-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 20, 2025 at 04:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_datapelanggan`
--

CREATE TABLE `tb_datapelanggan` (
  `kodepelanggan` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telp` varchar(50) NOT NULL,
  `paket` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `biaya` int(24) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_datapelanggan`
--

INSERT INTO `tb_datapelanggan` (`kodepelanggan`, `nama`, `alamat`, `telp`, `paket`, `tanggal`, `biaya`, `status`) VALUES
('PL01', 'Fajar', 'ID', '085691883936', '100 Mbps', '2025-06-20', 800000, 1),
('PL02', 'Romdhoni', 'ID', '083878737814', '20 Mbps', '2025-06-28', 200000, 1),
('PL03', 'Bobby', 'KR', '08765432123', '100 Mbps', '2025-06-20', 800000, 1),
('PL04', 'King', 'KR', '08765432122', '50 Mbps', '2025-10-05', 500000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_datatransaksi`
--

CREATE TABLE `tb_datatransaksi` (
  `kodetransaksi` varchar(20) NOT NULL,
  `kodepelanggan` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `paket` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `biaya` int(12) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_datatransaksi`
--

INSERT INTO `tb_datatransaksi` (`kodetransaksi`, `kodepelanggan`, `nama`, `paket`, `tanggal`, `biaya`, `status`) VALUES
('WFPL01200625-01', 'PL01', 'Fajar', '100 Mbps', '2025-06-20', 800000, 1),
('WFPL03200625-01', 'PL03', 'Bobby', '100 Mbps', '2025-06-20', 800000, 1),
('WFPL04050625-02', 'PL04', 'King', '50 Mbps', '2025-06-05', 500000, 1),
('WFPL04051025-01', 'PL04', 'King', '50 Mbps', '2025-10-05', 500000, 1),
('WFPL04201025-01', 'PL04', 'King', '20 Mbps', '2025-10-20', 200000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `nama` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`nama`, `email`, `password`) VALUES
('admin', 'admin@billingwifi.com', '123456'),
('Staf2', 'staf2@billingwifi.com', '1234'),
('staf', 'staf@billingwifi.com', '123456');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_datapelanggan`
--
ALTER TABLE `tb_datapelanggan`
  ADD PRIMARY KEY (`kodepelanggan`);

--
-- Indexes for table `tb_datatransaksi`
--
ALTER TABLE `tb_datatransaksi`
  ADD PRIMARY KEY (`kodetransaksi`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
