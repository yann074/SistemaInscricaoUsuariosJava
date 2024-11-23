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
