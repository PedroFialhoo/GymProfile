-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27/11/2024 às 01:53
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `server`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `ficha`
--

CREATE TABLE `ficha` (
  `id_ficha` int(11) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `nome_ficha` varchar(255) DEFAULT NULL,
  `exercicios` text DEFAULT NULL,
  `peso` text DEFAULT NULL,
  `series` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `ficha`
--

INSERT INTO `ficha` (`id_ficha`, `id`, `nome_ficha`, `exercicios`, `peso`, `series`) VALUES
(9, 25, 'Ficha 1', 'Supino\nLeg Press\nEsteira\nCadeira Extensora\nRosca Direta', '20\n80\n-\n80\n10', '3x10\n3x12\n20 min\n4x6\n5x8'),
(10, 26, 'Perna', 'Leg Press\nCadeira Extensora\nCadeira Flexora\nAgachamento\nBulgaro\nPanturrilha em pé no Hack\nPassada', '300\n100\n80\n50/50\n20\n30/30\n20', '4x10\n4x8\n4x12\n3x10\n4x8\n6x20\n2xFalha'),
(12, 29, 'Costas', 'Remada\nPuxada alta\nPuxada triangulo\nSerrote\nRosca direta\nMartelo', '20\n40\n45\n20\n10\n12\n', '3x12\n4x8\n4x8\n3x10\n4x12\n4x12'),
(13, 24, 'Peito A/B/C', 'Supino Halteres\nSup. vertical\nCross-Over polia alta\nTríceps Corda\nTríceps Francês\nElev. Lateral\nCross-Over polia baixa', '.', '.'),
(14, 24, 'Costas A/B/C', 'Remada curvada\nPuxada alta\nRemada fechada/Serrote\nCrucifixo inverso\nBíceps 45°\nRosca Martelo\nAntebraço', '.', '.'),
(15, 24, 'Perna A/B/C', 'Stiff\nAgachamento\nBulgaro\nCadeira Extensora\nPanturrilha no Leg ou Hack\nAbdômen', '.', '.'),
(21, 31, 'Treino 1', 'Supino\nPuxada\nSerrote\nRosca direta\nTriceps barr', '.', '.'),
(23, 28, 'aa', 'a', 'a', 'a');

-- --------------------------------------------------------

--
-- Estrutura para tabela `info`
--

CREATE TABLE `info` (
  `id` int(11) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `idade` varchar(11) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `academia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `info`
--

INSERT INTO `info` (`id`, `nome`, `idade`, `telefone`, `academia`) VALUES
(24, 'Pedro Henrique Bittencourt Fialho', '18', '(12) 99255-3402', 'Muskle Fit'),
(25, 'Patricia Mara Bittencourt Fialho', '49', '(12) 98187-8608', 'Muskel Fit'),
(26, 'Julia Vitoria do Nascimento Souza', '19', '(12) 99148-1389', 'Arionia'),
(27, 'Carlos Henrique Feichas', '36', '(12) 12345-1234', 'Smart Fit'),
(28, 'Pedro Fialho', '18', '(12) 99123-4567', 'IronBerg'),
(29, 'Gustavo Diniz da Silva Cruz', '19', '(12) 99154-0696', 'Das Maravilhas'),
(30, 'Nathalia Bittencourt Fialho', '11', '(12) 99345-3245', 'Muskle Fit'),
(31, 'Alexandre Antonio Fialho', '47', '(12) 98136-6470', 'Sesi'),
(34, 'Allisson Thomas da Silva', '18', '(12) 98778-3432', 'Sesi'),
(35, 'Marcos Henrique Silva', '78', '(21) 90785-6787', 'Frog Fitness'),
(36, 'Vitória Julia Nascimento', '19', '(12) 98765-4321', 'Guara Fit'),
(37, 'Roberto dos Santos', '66', '(12) 98898-3213', 'Muskle Fit');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `senha`) VALUES
(24, 'pedrohenrique@gmail.com', 'pedrofialho'),
(25, 'patriciambf@gmail.com', '123'),
(26, 'juliageller@gmail.com', 'meg'),
(27, 'carlos@gmail.com', '1234'),
(28, 'pedro@gmail.com', 'a'),
(29, 'gustavodiniz@gmail.com', '123'),
(30, 'nathalia11@gmail.com', 'nat'),
(31, 'alexandrefialho@gmail.com', '1234'),
(34, 'allisson12@gmail.com', 'allisson'),
(35, 'marcos99@hotmail.com', 'marcos12'),
(36, 'vitoriajulia@gmail.com', '123'),
(37, 'robertosan@hotmail.com', '9090');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `ficha`
--
ALTER TABLE `ficha`
  ADD PRIMARY KEY (`id_ficha`),
  ADD UNIQUE KEY `id` (`id`,`nome_ficha`);

--
-- Índices de tabela `info`
--
ALTER TABLE `info`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `ficha`
--
ALTER TABLE `ficha`
  MODIFY `id_ficha` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `ficha`
--
ALTER TABLE `ficha`
  ADD CONSTRAINT `ficha_ibfk_1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`);

--
-- Restrições para tabelas `info`
--
ALTER TABLE `info`
  ADD CONSTRAINT `info_ibfk_1` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
