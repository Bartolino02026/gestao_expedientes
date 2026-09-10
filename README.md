Sistema de Gestão de Expedientes
Descrição

O Sistema de Gestão de Expedientes é uma aplicação web desenvolvida para apoiar a gestão, organização e acompanhamento de documentos e processos administrativos.

O sistema permite controlar o ciclo de vida dos expedientes, desde o seu registo e entrada até à tramitação, despacho e consulta de informações, garantindo maior organização, rastreabilidade e segurança dos dados.

A aplicação possui também um mecanismo de controlo de acesso baseado em papéis (RBAC), permitindo restringir determinadas funcionalidades de acordo com o perfil do utilizador.

Principais funcionalidades
Gestão de utilizadores;
Autenticação de utilizadores;
Controlo de acesso baseado em papéis (RBAC);
Registo e gestão de expedientes;
Tramitação de expedientes;
Registo de despachos;
Auditoria das acções realizadas no sistema;
Consulta de relatórios;
Impressão de relatórios;
Protecção de páginas através de permissões;
Registo das operações relevantes para garantir rastreabilidade.
Tecnologias utilizadas
Java 25
Spring Boot 4.1.1
Spring MVC
Spring Security
Spring Data JPA
Hibernate
Thymeleaf
PostgreSQL
Maven
HTML5
CSS3
Estrutura geral do projecto
gestao_expedientes
├── src
│   ├── main
│   │   ├── java
│   │   │   └── mz.co.gestao.gestao_expedientes
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       └── service
│   │   │
│   │   └── resources
│   │       ├── static
│   │       └── templates
│   │
│   └── test
│
├── pom.xml
├── README.md
└── .gitignore

Requisitos para execução

Para executar o projecto localmente, é necessário ter instalados:

Java JDK 25;
PostgreSQL 18 ou versão compatível;
Maven;
IntelliJ IDEA ou outra IDE compatível com projectos Java;
Navegador web actualizado.
Base de dados

O sistema utiliza o PostgreSQL como sistema de gestão de base de dados.

A base de dados utilizada pelo projecto é:

gestao_expedientes

A aplicação está configurada para estabelecer a ligação através de:

jdbc:postgresql://localhost:5432/gestao_expedientes
Configuração da base de dados

Antes de executar a aplicação, deve-se:

Instalar o PostgreSQL;
Criar uma base de dados chamada gestao_expedientes;
Configurar as credenciais de acesso no ficheiro application.properties;
Garantir que o servidor PostgreSQL esteja em execução.

Nota: As credenciais da base de dados não devem ser publicadas no repositório GitHub. Recomenda-se utilizar variáveis de ambiente ou um ficheiro de configuração local para informações sensíveis.

Instalação e execução
1. Clonar o projecto

Clone o repositório através do Git:

git clone URL_DO_REPOSITORIO

Entre na pasta do projecto:

cd gestao_expedientes
2. Configurar a base de dados

Crie no PostgreSQL uma base de dados com o nome:

gestao_expedientes

Em seguida, configure o acesso à base de dados no ficheiro:

src/main/resources/application.properties

Exemplo de configuração:

spring.application.name=gestao_expedientes

spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_expedientes
spring.datasource.username=postgres
spring.datasource.password=SUA_PASSWORD

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.thymeleaf.cache=false

Substitua SUA_PASSWORD pela palavra-passe configurada no PostgreSQL.

3. Compilar o projecto

Na raiz do projecto, execute:

mvn clean install
4. Executar a aplicação

Execute:

mvn spring-boot:run

Também é possível executar a aplicação directamente através da IDE, iniciando a classe principal:

GestaoExpedientesApplication
5. Aceder ao sistema

Depois de iniciar a aplicação, abra o navegador e aceda a:

http://localhost:8080

O sistema apresentará a página de autenticação para entrada no sistema.

Importante: O ficheiro application.properties contém informações de configuração da base de dados. Não publique palavras-passe ou outras credenciais no GitHub.

Controlo de acesso baseado em papéis (RBAC)

O sistema implementa um mecanismo de controlo de acesso baseado em papéis (Role-Based Access Control — RBAC). O acesso às funcionalidades é determinado pelo papel atribuído a cada utilizador.

Perfis de utilizador

O sistema considera diferentes níveis de acesso, permitindo separar as responsabilidades dos utilizadores.

Papel	Descrição
ADMINISTRADOR	Possui acesso às funcionalidades administrativas, incluindo a gestão de utilizadores.
FUNCIONÁRIO	Pode utilizar as funcionalidades operacionais do sistema de acordo com as permissões definidas.
Protecção das funcionalidades

A autenticação é realizada através do Spring Security.

As áreas protegidas verificam o papel do utilizador antes de permitir o acesso. Por exemplo, a gestão de utilizadores é restrita ao papel de ADMINISTRADOR.

Quando um utilizador autenticado tenta aceder a uma funcionalidade para a qual não possui permissão, o sistema apresenta uma página de Acesso Negado (HTTP 403).

Segurança das palavras-passe

As palavras-passe dos utilizadores não são armazenadas directamente em texto simples.

O sistema utiliza o BCrypt através do PasswordEncoder do Spring Security para efectuar a codificação das palavras-passe antes do seu armazenamento na base de dados.

Auditoria e rastreabilidade

O sistema possui um mecanismo de auditoria destinado a registar operações relevantes realizadas pelos utilizadores.

Cada registo de auditoria contém informações como:

Identificação do utilizador;
Acção realizada;
Descrição da operação;
Data e hora da operação.

A auditoria permite acompanhar as actividades realizadas no sistema e contribui para a rastreabilidade, integridade e segurança das informações.

Entre as operações registadas encontra-se o início de sessão dos utilizadores e outras operações relevantes, como a eliminação de expedientes.

Os registos podem ser consultados através da funcionalidade Auditoria e também através da área de Relatórios.

Objectivo da auditoria

O mecanismo de auditoria foi implementado para:

Identificar quem realizou determinada operação;
Registar quando a operação ocorreu;
Descrever a operação realizada;
Facilitar a verificação das actividades efectuadas;
Apoiar a detecção de operações indevidas;
Aumentar a rastreabilidade das informações do sistema.

Gestão de Expedientes

A funcionalidade de gestão de expedientes permite registar, consultar, actualizar e eliminar expedientes administrativos.

Cada expediente possui informações como:

Número do expediente;
Assunto;
Remetente;
Destinatário;
Data de entrada;
Estado;
Observação.

Os expedientes podem apresentar diferentes estados durante o seu ciclo de vida:

RECEBIDO — expediente registado no sistema;
EM_TRAMITACAO — expediente que se encontra em processo de encaminhamento;
DESPACHADO — expediente que já possui despacho;
ARQUIVADO — expediente concluído e arquivado.
Tramitação

A funcionalidade de tramitação permite registar o encaminhamento dos expedientes dentro do processo administrativo.

Cada tramitação pode conter:

Expediente associado;
Utilizador responsável;
Destino;
Data e hora;
Observação.

O histórico das tramitações permite acompanhar o percurso de um expediente ao longo do seu ciclo de vida.

Despachos

A funcionalidade de despachos permite registar decisões ou orientações relacionadas com os expedientes.

Cada despacho contém:

Expediente associado;
Gestor responsável;
Conteúdo do despacho;
Data e hora.

Desta forma, o sistema mantém o registo das decisões tomadas relativamente aos expedientes.

Metodologia de Desenvolvimento

Para o desenvolvimento do Sistema de Gestão de Expedientes foi adoptada uma abordagem incremental, permitindo desenvolver e testar o sistema por funcionalidades.

O desenvolvimento foi organizado em etapas, nas quais cada funcionalidade foi implementada, testada e integrada progressivamente ao sistema.

Entre as principais etapas encontram-se:

Análise dos requisitos do sistema;
Configuração do ambiente de desenvolvimento;
Configuração da base de dados PostgreSQL;
Desenvolvimento da autenticação e controlo de acesso;
Implementação da gestão de utilizadores;
Implementação da gestão de expedientes;
Implementação da tramitação;
Implementação dos despachos;
Implementação da auditoria;
Implementação dos relatórios;
Desenvolvimento das interfaces do sistema;
Testes e correcção de erros;
Preparação da documentação e do repositório GitHub.
Justificação da metodologia

A abordagem incremental foi escolhida porque permite dividir o sistema em funcionalidades menores e desenvolver cada componente de forma progressiva.

Esta abordagem facilita a identificação e correcção de erros durante o desenvolvimento, permite testar cada funcionalidade antes da integração das restantes e possibilita acompanhar a evolução do sistema de forma organizada.

Além disso, a metodologia é adequada ao projecto porque o sistema possui vários módulos independentes, como utilizadores, expedientes, tramitações, despachos, auditoria e relatórios, que podem ser desenvolvidos e integrados progressivamente.

Requisitos do Sistema

Requisitos funcionais

Os principais requisitos funcionais do sistema são:

RF01: Permitir o registo de utilizadores;

RF02: Permitir a autenticação dos utilizadores;

RF03: Permitir a atribuição de papéis aos utilizadores;

RF04: Controlar o acesso às funcionalidades de acordo com o papel do utilizador;

RF05: Permitir o registo de expedientes;

RF06: Permitir a consulta dos expedientes;

RF07: Permitir a alteração dos dados dos expedientes;

RF08: Permitir a eliminação de expedientes;

RF09: Permitir o registo de tramitações;

RF10: Permitir o registo de despachos;

RF11: Registar operações relevantes através da auditoria;

RF12: Permitir a consulta dos registos de auditoria;

RF13: Permitir a consulta de relatórios;

RF14: Permitir a impressão dos relatórios.

Requisitos não funcionais

RNF01 — Segurança: O sistema deve proteger as funcionalidades através de autenticação e autorização baseada em papéis.

RNF02 — Confidencialidade: As palavras-passe devem ser armazenadas de forma segura através de codificação.

RNF03 — Integridade: O sistema deve manter a consistência dos dados armazenados na base de dados.

RNF04 — Rastreabilidade: As operações relevantes devem ser registadas através do mecanismo de auditoria.

RNF05 — Usabilidade: As interfaces devem ser simples, claras e de fácil utilização.

RNF06 — Desempenho: O sistema deve responder às operações realizadas pelos utilizadores de forma adequada.

RNF07 — Manutenibilidade: O código deve estar organizado em componentes distintos, facilitando a manutenção e evolução do sistema.

RNF08 — Compatibilidade: A aplicação deve poder ser executada em ambientes compatíveis com Java e PostgreSQL.

RNF09 — Disponibilidade: O sistema deve estar disponível enquanto a aplicação e o serviço da base de dados estiverem em execução.

Diagramas UML

A modelação do sistema foi realizada utilizando diagramas UML (Unified Modeling Language), com o objectivo de representar visualmente as funcionalidades, a estrutura e o comportamento do Sistema de Gestão de Expedientes.

Diagrama de Caso de Uso

O Diagrama de Caso de Uso representa as principais interacções entre os utilizadores e o sistema.

Entre os principais casos de uso encontram-se:

Autenticar-se no sistema;
Gerir utilizadores;
Gerir expedientes;
Registar tramitação;
Registar despacho;
Consultar auditoria;
Consultar relatórios;
Imprimir relatórios;
Terminar sessão.

O diagrama permite identificar os actores do sistema e as funcionalidades que podem ser realizadas por cada um, de acordo com as respectivas permissões.

Diagrama de Classes

O Diagrama de Classes representa a estrutura estática da aplicação e as principais classes envolvidas no sistema.

Entre as principais entidades encontram-se:

Utilizador;
Expediente;
Tramitacao;
Despacho;
Auditoria.

O sistema também está organizado em camadas, incluindo Controllers, Services, Repositories e Entities, promovendo a separação de responsabilidades.

Diagrama de Actividade

O Diagrama de Actividade representa o fluxo das principais operações do sistema.

Um dos fluxos principais corresponde ao ciclo de vida de um expediente:

Registo do expediente
↓
Recepção
↓
Tramitação
↓
Despacho
↓
Arquivo
↓
Consulta/Relatório

Este diagrama permite visualizar a sequência das actividades e as decisões que podem ocorrer durante o processamento dos expedientes.

Diagrama de Sequência

O Diagrama de Sequência representa a comunicação entre os diferentes componentes do sistema durante a execução de uma operação.

Como exemplo, o processo de autenticação envolve:

Utilizador
↓
Página de Login
↓
Spring Security
↓
CustomUserDetailsService
↓
UtilizadorRepository
↓
Base de Dados

Após uma autenticação bem-sucedida, o sistema permite o acesso às funcionalidades autorizadas e regista a operação de login no mecanismo de auditoria.

Modelo Entidade-Relacionamento

O modelo de dados do sistema foi concebido para representar as principais informações relacionadas à gestão de expedientes e às operações realizadas pelos utilizadores.

As principais entidades da base de dados são:

Utilizador — armazena os dados dos utilizadores, incluindo nome, nome de utilizador, palavra-passe, papel e estado de activação;
Expediente — armazena os documentos/processos administrativos registados no sistema;
Tramitacao — regista os encaminhamentos e movimentações dos expedientes;
Despacho — regista os despachos associados aos expedientes;
Auditoria — regista as operações realizadas pelos utilizadores.
Principais relacionamentos
Um Expediente pode possuir várias Tramitações;
Um Expediente pode possuir vários Despachos;
Um Utilizador pode estar associado a várias Tramitações;
Um Utilizador pode estar associado a vários Despachos como gestor responsável;
Um Utilizador pode possuir vários registos de Auditoria;
Cada Tramitação está associada a um único expediente e a um único utilizador;
Cada Despacho está associado a um único expediente e a um único gestor;
Cada registo de Auditoria está associado a um único utilizador.
Estrutura simplificada
UTILIZADOR
│
├──────────< TRAMITACAO >────────── EXPEDIENTE
│                                      │
│                                      ├──────────< DESPACHO
│                                      │
└──────────< AUDITORIA                 │
│
└── ciclo de vida:
RECEBIDO
↓
EM_TRAMITACAO
↓
DESPACHADO
↓
ARQUIVADO

O modelo permite manter a organização dos dados e estabelecer relações entre os utilizadores, expedientes e operações realizadas durante o ciclo de vida dos documentos.

Estado do projecto

O sistema encontra-se em desenvolvimento, com as principais funcionalidades previstas no enunciado implementadas e integradas.

As funcionalidades desenvolvidas incluem:

Autenticação;
Controlo de acesso baseado em papéis (RBAC);
Gestão de utilizadores;
Gestão de expedientes;
Tramitação;
Despachos;
Auditoria;
Relatórios;
Impressão de relatórios.
Repositório GitHub

O código-fonte completo do projecto está disponível no seguinte repositório:

[LINK_DO_REPOSITORIO_GITHUB](https://github.com/Bartolino02026/gestao_expedientes)


Contribuição

O desenvolvimento do projecto é realizado através do Git, permitindo acompanhar a evolução do código e as contribuições de cada elemento do grupo.

Cada elemento do grupo deve realizar commits correspondentes às suas contribuições no projecto, permitindo verificar o histórico de desenvolvimento através do GitHub.

Licença

Este projecto foi desenvolvido para fins académicos, no âmbito da formação em Engenharia Informática.

