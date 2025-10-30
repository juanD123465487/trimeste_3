
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";


CREATE TABLE `bicicleta` (
  `id` bigint(20) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `precio_hora` decimal(10,2) NOT NULL,
  `disponible` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `bicicleta` (`id`, `marca`, `modelo`, `tipo`, `precio_hora`, `disponible`) VALUES
(1, 'GW', '129', 'carrera', 1.00, 1),
(2, 'GW', '123', 'carrera', 2000.00, 1);


ALTER TABLE `bicicleta`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `bicicleta`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;



