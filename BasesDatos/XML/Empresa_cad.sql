-- -------------------------------------------------------------
-- TablePlus 4.0.2(374)
--
-- https://tableplus.com/
--
-- Database: Empresa_cad
-- Generation Time: 2022-02-21 02:18:48.0400
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `departamentos` (
  `id_departamento` int NOT NULL,
  `nombre_dep` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `empleados` (
  `id_emp` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(250) DEFAULT NULL,
  `direccion` varchar(250) DEFAULT NULL,
  `codigo_postal` varchar(6) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `salario` decimal(8,2) DEFAULT NULL,
  `id_dept` int DEFAULT NULL,
  PRIMARY KEY (`id_emp`),
  KEY `id_dept` (`id_dept`),
  CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`id_dept`) REFERENCES `departamentos` (`id_departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;







INSERT INTO `departamentos` (`id_departamento`, `nombre_dep`) VALUES
(1, 'Mercadotecnia'),
(2, 'Recursos Humanos'),
(3, 'Contabilidad'),
(4, 'Sistemas'),
(5, 'Producción'),
(6, 'Ventas'),
(7, 'Almacen');

INSERT INTO `empleados` (`id_emp`, `nombre`, `apellido`, `direccion`, `codigo_postal`, `cargo`, `salario`, `id_dept`) VALUES
(1, 'Giannina', 'Santacruz\n', 'Celso Emilio Ferreiro, 27\n', '50600', 'Tax collector\n', 12000.00, 2),
(2, 'Roque', 'Vanegas', 'Herrería, 84\n18160 Güejar Sierra\n', '18160', 'Internal auditor', 15000.00, 1),
(3, 'Maia', 'Hernandes', 'Eusebio Dávila, 36\n', '41429', 'processing equipment repairer', 35000.00, 3),
(4, 'Violette', 'Marín', 'Crta. Cádiz-Málaga, 54\n', '12224', 'Precision printing worker\n', 45000.00, 4),
(5, 'Miriam Sarabia', 'Ramón\n', 'Paraguay, 25\n', '03600', 'Boiler operator\n', 32000.00, 5),
(6, 'Naimid', 'Rosales', 'Extramuros, 6\n', '28410', 'Cardiac sonographer\n', 50000.00, 1),
(7, 'Abad', 'Santacruz', 'Rúa de San Pedro, 75\n', '37794', 'job analysis specialist', 22000.00, 2),
(8, 'Siagria', 'Chapa Rincón\n', 'Eusebio Dávila, 63\n', '41540', 'Employee training specialist\n', 25000.00, 3),
(9, 'Caín', 'Vanegas Callas\n', 'Eusebio Dávila, 73\n', '02934', 'Abstractor', 13000.00, 4),
(10, 'Keith', 'Márquez Colunga\n', 'Avenida Cervantes, 3\n', '48360', 'Coatroom attendant', 34999.00, 5),
(11, 'Yain', 'Arredondo Camacho\n', 'Estrela, 97\n', '40150', 'Fence erector\n', 43000.00, 1),
(12, 'Clímene', 'Escobedo', 'La Fontanilla, 17\n', '14300', 'Clinical manager\n', 38000.00, 2),
(13, 'Serafín', 'Barrios', 'Pl. Virgen Blanca, 73\n', '08710', 'Garment presser\n', 45000.00, 3),
(14, 'Luminosa', 'Soliz', 'Rio Segura, 63\n', '31500', 'Life Guard', 23000.00, 4),
(15, 'Egeo', 'Carranza', 'Cercas Bajas, 15\n', '08100', 'Management assistant\n', 28399.00, 5),
(16, 'Bertrán', 'Alfaro', 'La Fontanilla, 91\n', '14412', 'Information systems manager', 49888.00, 6),
(17, 'Domikene', 'Galván', 'Camiño Ancho, 23\n', '37765', 'Songwriter', 38000.00, 7),
(18, 'Rhode', 'Cervántez', 'Quevedo, 57\n', '15270', 'Electronic technician\n', 17000.00, 6),
(19, 'Fidelia', 'Acosta', 'Plaza de España, 60\n', '15700', 'Hotel detective\n', 29000.00, 7),
(20, 'Jasón', 'Soliz', 'C/ Eras, 48\n', '28738', 'General practitioner\n', 13000.00, 6),
(21, 'Romelio', 'Pantoja', 'Bellavista, 67\n', '50220', 'Training specialist\n', 34000.00, 7),
(22, 'Juanito', 'Perez', 'Calle 123', '09843', 'Maestro', 25000.00, 3),
(23, 'Alejandra', 'Chávez', 'Sin número', '099383', 'Abogada', 25000.00, 4);

CREATE VIEW `vista_empl_salario_may_20000` AS select `empleados`.`id_emp` AS `id_emp`,`empleados`.`nombre` AS `nombre`,`empleados`.`apellido` AS `apellido`,`empleados`.`direccion` AS `direccion`,`empleados`.`codigo_postal` AS `codigo_postal`,`empleados`.`cargo` AS `cargo`,`empleados`.`salario` AS `salario`,`empleados`.`id_dept` AS `id_dept` from `empleados` where (`empleados`.`salario` > 20000);
CREATE VIEW `vista_empl_ventas_produccion` AS select `empleados`.`nombre` AS `nombre`,`empleados`.`apellido` AS `apellido`,`departamentos`.`nombre_dep` AS `nombre_dep` from (`empleados` join `departamentos` on((((`empleados`.`id_dept` = 5) and (`departamentos`.`id_departamento` = 5)) or ((`empleados`.`id_dept` = 6) and (`departamentos`.`id_departamento` = 6)))));
CREATE VIEW `vista_informacion_empl` AS select `empleados`.`id_emp` AS `id_emp`,`empleados`.`nombre` AS `nombre`,`empleados`.`apellido` AS `apellido`,`empleados`.`direccion` AS `direccion`,`empleados`.`codigo_postal` AS `codigo_postal`,`empleados`.`cargo` AS `cargo`,`empleados`.`salario` AS `salario`,`empleados`.`id_dept` AS `id_dept`,`departamentos`.`nombre_dep` AS `nombre_dep` from (`empleados` join `departamentos` on((`departamentos`.`id_departamento` = `empleados`.`id_dept`)));


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
