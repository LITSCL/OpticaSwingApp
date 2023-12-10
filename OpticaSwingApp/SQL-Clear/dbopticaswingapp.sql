-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-12-2023 a las 19:11:38
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbopticaswingapp`
--
CREATE DATABASE IF NOT EXISTS `dbopticaswingapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dbopticaswingapp`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE `boleta` (
  `folio` varchar(45) NOT NULL,
  `precio_neto` double NOT NULL,
  `precio_con_iva` double NOT NULL,
  `costo_iva` double NOT NULL,
  `fecha_de_venta` date NOT NULL,
  `hora_de_venta` time NOT NULL,
  `venta_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id` int(11) NOT NULL,
  `region` varchar(45) NOT NULL,
  `comuna` varchar(45) NOT NULL,
  `calle` varchar(45) NOT NULL,
  `numero` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `fecha_nacimiento` varchar(45) NOT NULL,
  `sucursal_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lente`
--

CREATE TABLE `lente` (
  `codigo` varchar(10) NOT NULL,
  `precio` double NOT NULL,
  `color_cristal` varchar(45) NOT NULL,
  `color_marco` varchar(45) NOT NULL,
  `material_marco` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `imagen` varchar(45) NOT NULL,
  `marca_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lente_sucursal`
--

CREATE TABLE `lente_sucursal` (
  `id` int(11) NOT NULL,
  `lente_codigo` varchar(10) NOT NULL,
  `sucursal_codigo` varchar(45) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca`
--

CREATE TABLE `marca` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pais_origen` varchar(45) NOT NULL,
  `logo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metrica`
--

CREATE TABLE `metrica` (
  `id` int(11) NOT NULL,
  `unidad_medida` varchar(45) NOT NULL,
  `valor` double NOT NULL,
  `fecha` date NOT NULL,
  `sucursal_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefono`
--

CREATE TABLE `telefono` (
  `id` int(11) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `empleado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `precio` double NOT NULL,
  `sucursal_codigo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_lente`
--

CREATE TABLE `venta_lente` (
  `id` int(11) NOT NULL,
  `lente_codigo` varchar(10) NOT NULL,
  `venta_id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD PRIMARY KEY (`folio`,`venta_id`),
  ADD KEY `fk_boleta_venta1_idx` (`venta_id`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`,`sucursal_codigo`),
  ADD KEY `fk_empleado_sucursal1_idx` (`sucursal_codigo`);

--
-- Indices de la tabla `lente`
--
ALTER TABLE `lente`
  ADD PRIMARY KEY (`codigo`,`marca_id`),
  ADD KEY `fk_lente_marca1_idx` (`marca_id`);

--
-- Indices de la tabla `lente_sucursal`
--
ALTER TABLE `lente_sucursal`
  ADD PRIMARY KEY (`id`,`lente_codigo`,`sucursal_codigo`),
  ADD KEY `fk_lente_has_sucursal_sucursal1_idx` (`sucursal_codigo`),
  ADD KEY `fk_lente_has_sucursal_lente1_idx` (`lente_codigo`);

--
-- Indices de la tabla `marca`
--
ALTER TABLE `marca`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `metrica`
--
ALTER TABLE `metrica`
  ADD PRIMARY KEY (`id`,`sucursal_codigo`),
  ADD KEY `fk_metrica_sucursal1_idx` (`sucursal_codigo`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`codigo`,`direccion_id`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  ADD KEY `fk_sucursal_direccion1_idx` (`direccion_id`);

--
-- Indices de la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD PRIMARY KEY (`id`,`empleado_id`),
  ADD KEY `fk_telefono_empleado1_idx` (`empleado_id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`,`sucursal_codigo`),
  ADD KEY `fk_venta_sucursal1_idx` (`sucursal_codigo`);

--
-- Indices de la tabla `venta_lente`
--
ALTER TABLE `venta_lente`
  ADD PRIMARY KEY (`id`,`venta_id`,`lente_codigo`),
  ADD KEY `fk_lente_has_venta_venta1_idx` (`venta_id`),
  ADD KEY `fk_lente_has_venta_lente1_idx` (`lente_codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `lente_sucursal`
--
ALTER TABLE `lente_sucursal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marca`
--
ALTER TABLE `marca`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metrica`
--
ALTER TABLE `metrica`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `telefono`
--
ALTER TABLE `telefono`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta_lente`
--
ALTER TABLE `venta_lente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `boleta`
--
ALTER TABLE `boleta`
  ADD CONSTRAINT `fk_boleta_venta1` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleado_sucursal1` FOREIGN KEY (`sucursal_codigo`) REFERENCES `sucursal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `lente`
--
ALTER TABLE `lente`
  ADD CONSTRAINT `fk_lente_marca1` FOREIGN KEY (`marca_id`) REFERENCES `marca` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `lente_sucursal`
--
ALTER TABLE `lente_sucursal`
  ADD CONSTRAINT `fk_lente_has_sucursal_lente1` FOREIGN KEY (`lente_codigo`) REFERENCES `lente` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_lente_has_sucursal_sucursal1` FOREIGN KEY (`sucursal_codigo`) REFERENCES `sucursal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `metrica`
--
ALTER TABLE `metrica`
  ADD CONSTRAINT `fk_metrica_sucursal1` FOREIGN KEY (`sucursal_codigo`) REFERENCES `sucursal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD CONSTRAINT `fk_sucursal_direccion1` FOREIGN KEY (`direccion_id`) REFERENCES `direccion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `telefono`
--
ALTER TABLE `telefono`
  ADD CONSTRAINT `fk_telefono_empleado1` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `fk_venta_sucursal1` FOREIGN KEY (`sucursal_codigo`) REFERENCES `sucursal` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `venta_lente`
--
ALTER TABLE `venta_lente`
  ADD CONSTRAINT `fk_lente_has_venta_lente1` FOREIGN KEY (`lente_codigo`) REFERENCES `lente` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_lente_has_venta_venta1` FOREIGN KEY (`venta_id`) REFERENCES `venta` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
