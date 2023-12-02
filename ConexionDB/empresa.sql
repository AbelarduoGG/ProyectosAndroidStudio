-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-12-2023 a las 07:32:30
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `idclientes` int(11) NOT NULL,
  `nombre` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `domicilio` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `email` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `rfc` varchar(13) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idclientes`, `nombre`, `apellido`, `domicilio`, `email`, `fecha_nac`, `rfc`) VALUES
(1, 'Hector', 'Guerrero', 'Depa 765', 'hguerrero@correo.com', '2012-10-15', 'HGT151022erwt');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `idempleados` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `puesto` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `sueldo` decimal(8,2) NOT NULL,
  `domicilio` varchar(200) COLLATE utf8_spanish2_ci NOT NULL,
  `foto` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idempleados`, `nombre`, `apellido`, `puesto`, `sueldo`, `domicilio`, `foto`) VALUES
(1, 'Juan', 'Perez', 'Administrador', '8000.00', '', ''),
(2, 'Ana', 'Flores', 'Cajero', '700.00', 'Casa 45', NULL),
(3, 'Angel', 'Lopez', 'Vendedor', '7000.00', 'Casa23', NULL),
(4, 'Jose Luis', 'Rodriguez', 'Gerente', '80000.00', '', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `usuario` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` varchar(8) COLLATE utf8_spanish2_ci NOT NULL,
  `contraseña` varchar(15) COLLATE utf8_spanish2_ci NOT NULL,
  `codigoref` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `usuario`, `tipo`, `contraseña`, `codigoref`) VALUES
(1, 'jperez', 'Empleado', '12345', 1),
(2, 'aflores', 'Empleado', 'aflores', 2),
(3, 'alopez', 'Empleado', 'alopez', 3),
(4, 'hguerrero', 'Cliente', 'hguerrero', 1),
(5, 'jrodriguez', 'Empleado', 'luis123', 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`idclientes`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`idempleados`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `idclientes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `idempleados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
