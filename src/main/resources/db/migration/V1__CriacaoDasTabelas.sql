CREATE TABLE `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `bairro` varchar(255) NOT NULL,
  `cep` varchar(255) NOT NULL,
  `cidade` varchar(255) NOT NULL,
  `estado` varchar(255) NOT NULL,
  `rua` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foto` varchar(255) DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `preco` decimal(19,2) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `categoria_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id`)
);

CREATE TABLE `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `cliente_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`)
);


CREATE TABLE `item_do_pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` int(11) NOT NULL,
  `valor` decimal(19,2) NOT NULL,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1bdcl522h2cfp437j5v26992r` (`pedido_id`),
  KEY `FKh9wh2ar16h2ss5bj9nvkwewj2` (`produto_id`)
);
