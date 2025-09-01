# Agenda de Futebol Amador 

Projeto acadêmico desenvolvido por **Isabella Figueiredo** e **Vitor Mendes** com o objetivo de criar uma aplicação web para gerenciar partidas de futebol amador, permitindo que usuários se organizem para jogar "peladas" de forma prática e eficiente.

---

## Visão Geral

A Agenda de Futebol Amador é uma plataforma que permite:

- **Criar e gerenciar partidas** de futebol amador (privadas ou públicas)
- **Inscrever-se** para participar de jogos
- **Visualizar partidas disponíveis** por cidade
- **Gerenciar perfil de usuário** e histórico de partidas
- **Administradores** podem supervisionar e moderar a plataforma

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.4**
- **Spring MVC**
- **Thymeleaf** (Template Engine)
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Maven**
- **HTML5/CSS3/JavaScript**
- **Lombok** (para redução de boilerplate code)
- **Spring Validation**

---

## Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- Maven 3.6+ 
- Navegador web moderno

### Passo a passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/vitormendes09/AGENDA_FUTEBOL_AMADOR.git
   cd agenda_futebol_amador
   ```

2. **Compile o projeto**
   ```bash
   mvn clean compile
   ```

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a aplicação**
   Abra seu navegador e vá para: `http://localhost:8080`

---

## Estrutura do Projeto

```
agenda_futebol_amador/
├── src/main/java/br/edu/iff/com/agenda_futebol_amador/
│   ├── contracts/              # Interfaces e contratos
│   │   ├── controllers/view/   # Interfaces dos controllers
│   │   ├── entities/           # Interfaces das entidades
│   │   └── services/           # Interfaces dos serviços
│   ├── controllers/            # Implementações dos controllers
│   │   ├── view/              # Controllers Thymeleaf
│   │   └── restapi/           # Controllers API REST
│   ├── entities/              # Implementações das entidades
│   ├── services/              # Implementações dos serviços
│   └── AgendaFutebolAmadorApplication.java
├── src/main/resources/
│   ├── static/                # Arquivos estáticos (CSS, JS, imagens)
│   ├── templates/             # Templates Thymeleaf
│   └── application.properties # Configurações da aplicação
└── pom.xml
```

---

## Funcionalidades Implementadas

### Sistema de Usuários
- **Dois tipos de usuários**: Jogador e Administrador


### Gestão de Partidas
- **Criação de partidas** com data, hora, local e valor
- **Partidas públicas e privadas**
- **Sistema de inscrição** para jogadores
- **Controle de vagas** disponíveis

###  Painel Administrativo
- **Visualização de estatísticas**
- **Gestão de usuários**
- **Monitoramento de partidas**

###  Interface Web
- **Página inicial** com partidas em destaque
- **Navegação intuitiva** entre seções

---

## Fluxo da Aplicação

1. **Acesso inicial**: Página home com partidas disponíveis
2. **Registro/Login**: Usuário se autentica no sistema
3. **Navegação**: 
   - Jogadores: visualizam e se inscrevem em partidas
   - Administradores: acessam painel de controle
4. **Interação**: Inscrição em partidas, gestão de perfil, etc.

---

## Entidades Principais

### Usuario
- Atributos: id, nome, email, senha, tipo (JOGADOR, ORGANIZADOR, ADMINISTRADOR)
- Funcionalidades: autenticação, gestão de perfil

### Jogador
- Herda de Usuario
- Funcionalidades: inscrever-se em partidas, visualizar histórico

### Partida
- Atributos: id, nome, data, hora, cidade, valor, número de jogadores, status
- Funcionalidades: CRUD, gestão de inscrições, controle de vagas

---

## Interface e Experiência do Usuário

A aplicação utiliza **Thymeleaf** para renderização server-side com:
- Templates organizados por funcionalidade
- Layout responsivo e intuitivo
- Formulários validados no front-end e back-end
- Mensagens de feedback para o usuário

---

## Configurações Técnicas

### Banco de Dados
- **H2 Database** em modo embutido
- Dados persistem durante a execução da aplicação
- Acesso ao console H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Usuário: `sa`
  - Senha: (vazia)


---

## Equipe de Desenvolvimento

- **Isabella Figueiredo** & **Vitor Mendes** 

