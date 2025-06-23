#  Agenda de Futebol Amador

Projeto acadêmico desenvolvido por **Isabella Figueiredo** e **Vitor Mendes** com o objetivo de criar uma aplicação back-end para gerenciar partidas de futebol amador, permitindo que usuários se organizem para jogar "peladas" de forma prática e eficiente.

---

##  Visão Geral

A Agenda de Futebol Amador permite que jogadores e organizadores possam:

- Criar partidas de futebol amador (privadas ou públicas).
- Se inscrever para participar de jogos.
- Gerenciar jogadores inscritos.
- Administradores podem supervisionar e moderar o uso da plataforma.

---

##  Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Maven**
- **MongoDB Atlas** *(preferencial, sujeito a diretrizes da disciplina)*
- **JWT para autenticação** *(se permitido)*
- **React.js** *(caso haja front-end permitido)*

---



##  Entidades Principais

###  Usuario (Abstrato)

Atributos:
- `id`
- `nome`
- `email`
- `senha`
- `tipo`: `JOGADOR`, `ORGANIZADOR`, `ADMINISTRADOR`

---

### Jogador

- Pode se inscrever em partidas públicas.
- Pode visualizar o histórico de jogos.
- Pode ver outros jogadores de uma partida pública.
- Pode se retirar de uma partida.

---

### Organizador

- Pode criar, atualizar ou excluir uma partida que criou.
- Pode tornar uma partida pública ou privada.
- Pode adicionar e remover jogadores da sua partida.
- Pode ver jogadores inscritos em qualquer partida (mesmo as privadas).

---

### Administrador

- Pode listar todos os usuários.
- Pode deletar qualquer usuário.

---

### Partida

Atributos:
- `id`
- `nome`
- `data`
- `hora`
- `valor`
- `status`: `PRIVADA`, `PUBLICA`
- `numeroJogadores`
- `organizadorId`

Funcionalidades:
- CRUD completo apenas pelo Organizador criador da partida.
- Jogadores podem se inscrever se a partida for pública.
- Jogadores podem visualizar lista de participantes, se for pública.

---

## Autenticação

- O sistema utilizará **JWT (JSON Web Token)** para autenticação segura.
- Apenas usuários autenticados poderão acessar as rotas protegidas.
- O token será gerado no login e utilizado em todas as requisições subsequentes.

---

## Regras de Negócio (Resumo)

1. Um organizador pode gerenciar apenas suas próprias partidas.
2. Apenas partidas públicas podem ser vistas e acessadas por outros jogadores.
3. Um jogador não pode se inscrever duas vezes na mesma partida.
4. Um jogador pode sair de uma partida que ele mesmo se inscreveu.
5. O número máximo de jogadores por partida deve ser respeitado.
6. Apenas administradores podem excluir usuários.
7. Apenas usuários autenticados têm acesso ao sistema.

---

## Funcionalidades

| Funcionalidade                     | Jogador | Organizador | Administrador |
|-----------------------------------|---------|-------------|----------------|
| Criar Partida                     | x      | v          | x             |
| Atualizar / Deletar Partida      | x      | v (própria) | x             |
| Inscrever-se em Partida Pública  | v      | v          | x             |
| Visualizar Participantes Públicos| v      | v          | x             |
| Adicionar / Remover Jogadores    | x      | v          | x             |
| Listar Usuários                  | x      | x          | v             |
| Deletar Usuário                  | x      | x          | v             |

---

## 📊 Diagrama UML

Abaixo está o diagrama de entidades e relacionamentos do projeto:

![UML Agenda Futebol Amador](./UML_AgendaFutebolAmador.png)


