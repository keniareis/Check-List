# 📝**Check-List**
Este repositório contém uma aplicação de Check-List desenvolvida para estudar e entender a integração entre as tecnologias de backend e frontend e conceitos sobre o springboot. A aplicação permite que os usuários criem, visualizem e gerenciem tarefas.

## **Visão geral do projeto**
O projeto ajudou a aprender e aplicar muitos conceitos, incluindo:

- **Configuração de uma aplicação Spring Boot.**
- **Integração do Spring Boot com PostgreSQL.**
- **Desenvolvimento de uma API REST para gerenciamento de tarefas.**
- **Construção de um frontend dinâmico usando JavaScript.**
- **Estilização do frontend usando HTML/CSS.**

#

![screenshot](https://github.com/keniareis/Check-List/blob/main/img_4_readme/preview.png)

#

## **Tecnologias utilizadas**
### Backend
- **Java**
- **Springboot**
- **PostgreSQL**

### Frontend
- **Javascript**
- **HTML/CSS**

## Funcionalidades
- **➕ Adicionar novas tarefas com um nome e descrição.**
- **📋 Exibir uma lista de tarefas.**
- **🗑️ Deletar tarefas.**


### Pré-requisitos

- Java 8 ou superior
- PostgreSQL
- Node.js (para gerenciar dependências do frontend)

### Instalação

1. **Clone o repositório:**
    ```bash
    git clone https://github.com/seuusuario/check-list.git
    cd check-list
    ```

2. **Configuração do Backend:**

    - Configure seu banco de dados PostgreSQL e atualize o arquivo `application.properties` com suas credenciais de banco de dados.
    - Construa e execute a aplicação Spring Boot:
      ```bash
      ./mvnw spring-boot:run
      ```

3. **Configuração do Frontend:**

    - Navegue até o diretório do frontend e instale as dependências:
      ```bash
      cd frontend
      npm install
      ```
    - Execute a aplicação frontend:
      ```bash
      npm start
      ```

### Uso

- Abra seu navegador e navegue até `http://localhost:5500`.
- Use a interface para adicionar, visualizar e deletar tarefas.