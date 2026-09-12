CREATE DATABASE IF NOT EXISTS todo_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE todo_db;

-- =========================================
-- TABELA DE TAREFAS
-- =========================================

CREATE TABLE tarefas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    concluida BOOLEAN NOT NULL DEFAULT FALSE,
    data_criacao DATE NOT NULL,

    PRIMARY KEY (id)
);

-- =========================================
-- DADOS PARA TESTE
-- =========================================

INSERT INTO tarefas (titulo, descricao, concluida, data_criacao)
VALUES
    ('Estudar para a prova', 'Revisar capitulos 1 a 4', FALSE, CURDATE()),
    ('Comprar mantimentos', 'Leite, ovos, pao', FALSE, CURDATE()),
    ('Entregar trabalho MVC', 'Finalizar sistema To-Do', FALSE, CURDATE());
