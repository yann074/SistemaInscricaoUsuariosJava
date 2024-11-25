-- Criar o banco de dados
CREATE DATABASE gestaodeeventoss;

-- Usar o banco de dados
USE gestaodeeventoss;

-- Criar a tabela usuario
CREATE TABLE usuario (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    is_adm BOOL  DEFAULT 0
);

-- Criar a tabela evento
CREATE TABLE evento (
    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    dt_inicio DATE NOT NULL,
    dt_fim DATE NOT NULL,
    numero_vagas INT(11) NOT NULL,
    dt_limite_inscricao DATE NOT NULL,
    nome_responsavel VARCHAR(255) NOT NULL,
    cpf_responsavel VARCHAR(11) NOT NULL,
    email_responsavel VARCHAR(100) NOT NULL
);

-- Criar a tabela palestra
CREATE TABLE palestra (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    dt_palestra DATE NOT NULL,
    horario_inicio_palestra TIME NOT NULL,
    horario_fim_palestra TIME NOT NULL,
    nome_palestrante VARCHAR(255) NOT NULL,
    minicurriculo_palestrante TEXT NOT NULL,
    data_limite DATE NOT NULL,
    id_evento INT(11),
    FOREIGN KEY (id_evento) REFERENCES evento(id)
);

-- Criar a tabela mini_curso
CREATE TABLE mini_curso (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    dt_minicurso DATE NOT NULL,
    horario_inicio_minicurso TIME NOT NULL,
    horario_fim_minicurso TIME NOT NULL,
    nome_instrutor VARCHAR(255) NOT NULL,
    minicurriculo_instrutor TEXT NOT NULL,
    numero_vagas INT(11) NOT NULL,
    dt_limite_inscricao DATE NOT NULL,
    id_evento INT(11),
    FOREIGN KEY (id_evento) REFERENCES evento(id)
);

-- Criar a tabela inscricao
CREATE TABLE inscricao(
id int primary key auto_increment,
cpf_usuario varchar(11),
id_evento int,
data_inscricao timestamp,
 FOREIGN KEY (cpf_usuario) REFERENCES usuario(cpf),
    FOREIGN KEY (id_evento) REFERENCES evento(id)
);

-- Criar a tabela inscricao_minicurso
CREATE TABLE incricao_minicurso(
id int primary key auto_increment,
cpf_usuario varchar(11),
id_mini_curso int,
data_inscricao timestamp,
 FOREIGN KEY (cpf_usuario) REFERENCES usuario(cpf),
    FOREIGN KEY (id_mini_curso) REFERENCES mini_curso(id)
)


-- Jsons para teste

usuario:
    
{
  "cpf": "12345678901",
  "nome": "João Silva",
  "email": "joao.silva@example.com",
  "senha": "senha123"
}

evento:

{
    "nome": "Nome do Evento",
    "descricao": "Descrição do eventooo",
    "dt_inicio": "2024-11-25T00:00:00",
    "dt_fim": "2024-11-30T00:00:00",
    "numero_vagas": 100,
    "dt_limite_inscricao": "2024-11-20T00:00:00",
    "nome_responsavel": "Nome do Responsável",
    "cpf_responsavel": "123.456.789-00",
    "email_responsavel": "responsavel@dominio.com"
}

palestra:
{
  "nome": "Palestra sobre Inteligência Artificial",
  "descricao": "Uma introdução ao mundo da Inteligência Artificial e suas aplicações no cotidiano.",
  "dt_palestra": "2024-12-15", 
  "horario_inicio_palestra": "09:00:00",
  "horario_fim_palestra": "12:00:00",
  "nome_palestrante": "Dr. João Silva",
  "minicurriculo_palestrante": "Dr. João Silva é PhD em Inteligência Artificial pela Universidade X e atua no desenvolvimento de soluções de IA há mais de 10 anos.",
  "data_limite": "2024-12-10",
    "id_evento": "1"
}

mini-curso:

{
  "nome": "Introdução ao Java",
  "descricao": "MiniCurso sobre os fundamentos da linguagem c.",
  "dt_minicurso": "2024-11-25",
   "horario_inicio_minicurso": "10:30:00",
  "horario_fim_minicurso": "10:30:00",
  "nome_instrutor": "Carlos Silva",
  "minicurriculo_instrutor": "Carlos é instrutor de Java com mais de 10 anos de experiência no mercado.",
  "numero_vagas": 30,
  "dt_limite_inscricao": "2024-11-20",
  "id_evento": "1"
}

Inscriacao:

{
  "cpf_usuario": "12345678901",
  "data_inscricao": "2024-11-25T15:30:00"
}

InscricaoMiniCurso

{
  "cpf_usuario": "12345678901",
  "data_inscricao": "2024-11-25T14:45:00"
}
