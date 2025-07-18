# CoinApp - Listagem de Exchanges de Criptomoedas
CoinApp é um aplicativo clean e intuitivo que permite visualizar, comparar e explorar as principais exchanges de criptomoedas do mercado. 
O app fornece dados atualizados sobre volume de transações, histórico de atividade, rankings e outras informações essenciais sobre cada exchange.

# Funcionalidades:
* Lista completa de exchanges com dados como nome, volume de negociação (1h, 24h e 30 dias), número de símbolos e status de integração.
* Favoritos: marque suas exchanges favoritas para acesso rápido.
* Tela de detalhes com informações aprofundadas sobre cada exchange.
* Acesso direto ao site oficial da exchange.
* Desempenho otimizado com cache inteligente de dados.
* Navegação intuitiva com suporte à interface moderna em Jetpack Compose.

# Tecnologias utilizadas:
* Kotlin + Jetpack Compose
* MVVM + CLEAN (Model-View-ViewModel) + (Clean Archtecture)
* Koin para injeção de dependência
* Retrofit para comunicação com a API
* Room para persistência de dados locais
* Testes unitários e automatizados com JUnit e MockK

![GitHub top language](https://img.shields.io/github/languages/top/Carlosjr01/Filmes-App) 
[![MVVM](https://img.shields.io/badge/Architecture-MVVM_+_CLEAN_ARCHITECTURE-black)](https://www.youtube.com/watch?v=tIPxSWx5qpk) 
[![Flow](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 
[![Coroutines](https://img.shields.io/badge/Coroutines-1.7.3-black.svg)]()
[![Koin](https://img.shields.io/badge/Koin-3.5.3-black.svg)]()
[![Retrofit](https://img.shields.io/badge/Retrofit-2.9.0-black.svg)]()
[![OkHttp](https://img.shields.io/badge/Okhttp-5.0.0-black.svg)]()
[![Mockk](https://img.shields.io/badge/Mockito-1.13.10-black.svg)]()
[![Espresso](https://img.shields.io/badge/Espresso-3.6.1-black.svg)]()
[![API](https://img.shields.io/badge/Coinapi.io-black.svg)]()
![Testes](https://img.shields.io/badge/Testes_Unitários_+_Ui_Testes-lightgrey)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)]()

*******
<img width="1247" height="701" alt="image" src="https://github.com/user-attachments/assets/fb81e73e-b8a9-44dd-b758-b162a32b5aa3" />
<img width="1245" height="699" alt="image" src="https://github.com/user-attachments/assets/7059ab52-0146-4659-8f2b-ddbc63e8608c" />

*******
Aplicativo Android Nativo escrito em Kotlin e Jetpack Compose, consumindo api Rest coinapi.io para listagem de exchanges, processando resposta com retrofit e tratamento de concorrência com Kotlin Flow, utilizando padrão de arquitetura MVVM + Clean Architecture, com divisão de responsabilidades e separação de conceitos e desacoplamento de camadas. Injeção de dependência com Koin. Cache de dados da api para evitar chamadas nesnecessárias. Funcionalidade de listagem de exchanges e listagem de favoritados, tratamento de erro. Testes unitários e Testes de interface utilizando JUnit, Mockito e Espresso.

**MVVM:** Tem como principal objetivo separar responsabilidades entre Views e Modelos
Aqui temos a View que responde somente para a ViewModel, e a ViewModel não comunica diretamente com a View. A ViewModel é então uma classe intermediaria entre a View e a Model que conecta uma com a outra fazendo assim intermediação entre ambas.

**Modelo (Model):**
A Model caracteriza um conjunto de classes para descrever a lógica de negócios. Ela também descreve as regras de negócios para dados sobre como os dados podem ser manipulados ou alterados.

**Visão (View):**
A View representa componentes da interface do usuário. Ela também exibe os modelos na interface do usuário a partir do retorno da Presenter e da ViewModel. Assim como no MVP, a View aqui também tende a possuir o mínimo de regra de negócio possível, ela também é "burra", quem vai definir o que ela vai exibir é a ViewModel.

**ViewModel:**
A ViewModel é responsável por apresentar funções, métodos e comandos para manter o estado da View, operar a Model e ativar os eventos na View.
O ViewModel expõe fluxos de dados relevantes para o View. Mesmo neste caso, é a ponte entre o repositório e a View e contém toda a lógica de negócios.

### Principais benefícios da arquitetura Android
**Manutenção**
É possível entrar em partes menores e focadas do código e fazer alterações facilmente por causa da separação de diferentes tipos de códigos de maneira mais limpa. Isso ajudará a lançar novas versões rapidamente e a manter a agilidade.

**Testabilidade:**
No caso da arquitetura MVVM, cada parte do código permanece granular. Caso seja implementada da maneira adequada, todas as dependências internas e externas permanecerão do código que contém a lógica principal do aplicativo que você estava planejando testar.
Portanto, se você planeja escrever testes unitários com a lógica principal, fica muito mais fácil. Lembre-se de verificar se funciona bem quando escrito e continue executando, principalmente quando houver qualquer tipo de alteração no código, por menor que seja.

**Extensibilidade:**
Devido ao aumento de partes granulares de código e limites de separação, às vezes ele se mistura com a capacidade de manutenção. Se você planeja reutilizar qualquer uma dessas partes terá mais chances de fazê-lo.
Ele também vem com o recurso no qual você pode adicionar novos trechos de código ou substituir os existentes que executam trabalhos semelhantes em alguns locais da estrutura da arquitetura.
Sem dúvida, o principal objetivo de escolher a arquitetura MVVM é abstrair as Views para que o código por trás da lógica de negócios possa ser reduzido a uma extensão.

*******
* **Explicação de frameworks utilizados no projeto:**
*******

[![Retrofit](https://img.shields.io/badge/retrofit-2.9.0-black.svg)]()

Retrofit: É uma biblioteca desenvolvida pela Square que é utilizada como um REST Client no Android.
Utiliza a biblioteca OkHttp para fazer os Http Requests.
O Retrofit torna mais fácil recuperar e fazer upload de JSON através de uma Web service REST.
Com o Retrofit podemos escolher que conversor usar para a serialização de dados, como por exemplo o Moshi e GSON.
   
[![Kotlin_Flow](https://img.shields.io/badge/Kotlin_Flow-Asynchronous-black)](https://developer.android.com/kotlin/coroutines) 

Kotlin Flow: Utilizada para fluxos assíncronos, com isso podemos retornar vários valores calculados de forma assíncrona ao contrário de ma função de suspensão que retorna de forma assíncrona um único valor.

[![Koin](https://img.shields.io/badge/Koin-3.5.3-black.svg)]()

Koin: É uma biblioteca de injeção de dependência que reduz a injeção manual de código no projeto, criando e gerenciando as instâncias provendo os módulos dentro da aplicação.

[![Mockk](https://img.shields.io/badge/Mockito-1.13.10-black.svg)]()

Mockk: É uma ferramenta para mocking quando escrevemos testes para aplicações Kotlin. Nos ajuda a escrever código limpo e conciso ao testar aplicações Java e Kotlin.

[![Jetpack Compose](https://img.shields.io/badge/Compose-1.5.0-black.svg)]()

Jetpack Compose: É o toolkit moderno do Android para construção de interfaces de usuário de forma declarativa.
Com Compose, você descreve a UI com funções Kotlin reutilizáveis, reativas e fáceis de testar.
Elimina a necessidade de usar XML para construir telas, proporcionando maior produtividade e legibilidade.
É totalmente integrado com o Android Studio e com outras bibliotecas do Jetpack, como Navigation, LiveData e ViewModel.

*******
* **Tests: (Unitários + UI)**
*******

<img width="1246" height="698" alt="image" src="https://github.com/user-attachments/assets/25cd1393-e2f2-4222-95bd-00c15d0756e1" />

*******
* **Configuração do Projeto:** 
*******
Para que o aplicativo funcione corretamente, é necessário configurar uma chave de API da CoinAPI, que é responsável por fornecer os dados de exchanges utilizados pelo app.

1. Obtenha sua chave de API: <br>
* Acesse: https://www.coinapi.io/
* Crie uma conta (gratuita ou paga, conforme sua necessidade).
* Copie sua chave de API pessoal.
  
2. Atualize o arquivo secrets.properties na raiz do projeto: <br>
Onde: Na raiz do projeto (nível do build.gradle) / secrets.properties

3. Adicione sua chave no arquivo: <br>
Dentro do secrets.properties, insira a chave substituindo “YOUR-API-KEY-HERE”:
COIN_API_KEY=YOUR-API-KEY-HERE

Importante: Nunca suba a chave arquivo para o controle de versão (Git) para proteger suas credenciais.

*******
* **Conclusão:** 
*******
Para este projeto foi escolhido o padrão de Arquitetura MVVM com Clean Architecture justamente por fazer uso um padrão de divisão de responsabilidades, com separação de conceitos, e camadas diferentes, nele temos o desacoplamento da camada de "Network" da camada de "Apresentação", Repository: Utilizado para centralizar funções e não repetir códigos centraliza o acesso aos dados. UseCases com responsabilidade única, possuindo as regras de negócio da aplicação e fazendo o meio de campo entre as duas camadas "ViewModel" e "Repository" separando ainda mais as responsabilidades da aplicação, separando a camada de apresentação da camada de dados.
Com essas duas arquiteturas implementadas melhoramos a clareza e entendimento de cada parte do projeto, facilitando e possibilitando o trabalho em diferentes frentes de camadas desacopladas em um projeto mais organizado, expandível, testável e flexível de forma padronizada de desenvolvimento para que a aplicação venha a ser escalável e testável com maior separação de conceitos e responsabilidades. 

*******
* **Baixe agora mesmo:** 
*******
<img width="1248" height="703" alt="image" src="https://github.com/user-attachments/assets/4b9134d3-ac14-4529-89f9-49bf9162ec11" />

*******
