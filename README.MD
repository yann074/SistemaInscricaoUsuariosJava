# Sistema de Inscrição de Usuários em Eventos - API REST

Este é um sistema backend para gerenciamento de usuários e inscrições em eventos, desenvolvido como uma API REST utilizando Java e MySQL. Ele permite que usuários se cadastrem e façam inscrições em eventos, enquanto administradores podem gerenciar usuários e eventos.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Banco de Dados:** MySQL
- **Arquitetura:** RESTful API
- **Outras dependências:** JWT

---

## 📚 Funcionalidades

### Usuários
- Cadastro de novos usuários.
- Autenticação JWT
- Atualização e exclusão de perfis de usuários.
- Consulta de detalhes do usuário.
- Tipos de usuários:
  - **Usuário Comum:** Pode visualizar e se inscrever em eventos.
  - **Administrador:** Pode gerenciar eventos e usuários.

### Eventos
- Criação, edição e exclusão de eventos.
- Listagem de eventos disponíveis.
- Detalhes de cada evento.

### Inscrições
- Inscrição de usuários em eventos.
- Validação de datas de inscrição.
- Cancelamento de inscrições.

## 📚 Caminhos

### Usuários
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/usuario
- Get: Listar todos os usuários
- Post: Cadastrar usuários
- Put: Atualizar usuários (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/usuario/{"cpf"})
- Delete: Deletar usuários (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/usuario/{"cpf"})
- Post / Logar: Logar usuários, se estiver certo o login, gera um token JWT (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/usuario/logar)
- Post / Promover: Promove um usuário normal a admin (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/usuario/promover/{"cpf"})
  Algumas das opções são apenas para administradores

### Eventos
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento
- Post: Salvar Eventos
- Get: Listar todos os eventos entre uma data e outra (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento/{dataInicio}/{dataFim})
- Get: Obter Programação de um evento específico (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento/programacao/{id})
- Get: Listar todas as palestras e cursos do evento (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento/programacao/evento/{id})
- Delete: Deletar Eventos, apenas se não existir palestras/minicursos e não ter nenhum usuário inscrito (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento/{"id"})
- Put: Atualizar Evento (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/evento/{"id"})

### Palestras
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/palestra
- Post: Salvar Palestras
- Get: Listar todas as Palestras Existentes
- Delete: Deletar Palestras(http://localhost:8080/GestaoDeProjeto-1.0/api/v1/palestra/{"id"})
- Put: Atualizar Palestra (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/palestra/{"id"})

### Mini Cursos
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/minicurso
- Post: Salvar Mini Cursos
- Get: Listar todos os Mini Cursos Existentes
- Delete: Deletar Mini Cursos (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/minicurso/{"id"})
- Put: Atualizar Mini Cursos (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/minicurso/{"id"})

### Inscrição no Evento
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/evento
- Post: Inscrever usuário em um evento (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/evento/{"id_evento"})
- Get: Listar todos os Usuários em um evento (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/evento/{"id_evento"})
- Delete: Deletar Usuário do evento (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/evento/{"cpf_usuario"})


### Inscrição no Mini Curso
http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/minicurso
- Post: Inscrever usuário em um mini curso (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/minicurso/{"id_miniCurso"})
- Get: Listar todos os Usuários em um mini curso (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/minicurso/{"id_miniCurso"})
- Delete: Deletar Usuário do mini curso (http://localhost:8080/GestaoDeProjeto-1.0/api/v1/inscricao/minicurso/{"cpf_usuario"})
