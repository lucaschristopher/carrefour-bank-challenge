# Desafio Android: Banco Carrefour by RPerformance Group

O desafio consiste na implementação de uma aplicação Android que deverá consumir a [API pública do Github](https://docs.github.com/pt/rest/guides/getting-started-with-the-rest-api), que disponibiliza informações sobre os usuários e seus repositórios, onde o aplicativo deverá permitir a listagem de usuários, busca de usuário por nome de usuário e visualização das informações de um usuário específico, bem como a listagem de seus repositórios.

# A Solução

A aplicação se trata de uma listagem de usuários do GitHub, além de possui uma tela de detalhes que exibe as informações do usuário (nome, login, localização, instituição, repositórios) e uma tela onde é possível efetuar uma busca de usuário pelo seu nome de usuário do GitHub.

Foi adotada a **[Arquitetura MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)** de acordo com as [recomendações de arquitetura mobile da Google](https://developer.android.com/jetpack/guide) juntamente com as boas práticas da **[Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)**. Além disso, foi adotado no projeto as principais bibliotecas do mundo do [Android Jetpack](https://developer.android.com/jetpack) e utilização de demais ferramentas. Seguem abaixo cada uma com a devida justificativa de adoção:

- **[Retrofit:](https://square.github.io/retrofit/)** um cliente HTTP *type-safety* para Android de fácil utilização que fornece um simples padrão de implementação para transmissão de dados entre aplicação/servidor.
- **[Kotlin Coroutines + Flow:](https://developer.android.com/kotlin/flow)** um tipo que pode emitir vários valores sequencialmente, ao contrário das funções de suspensão, que retornam somente um valor. fluxos são criados com base nas corrotinas e podem fornecer vários valores. Conceitualmente, um fluxo é um stream de dados que pode ser computado de forma assíncrona.
- **[Coil:](https://coil-kt.github.io/coil/)** trata-se de uma biblioteca de carregamento de imagens para Android apoiada por Kotlin Coroutines.
- **[Koin:](https://insert-koin.io/)** uma biblioteca de injeção de dependências de forma inteligente, de fácil configuração e adoção. O Koin roda em tempo de execução, contudo, não apresenta uma performance ruim. Foi levantada a possível adoção do [Dagger](https://dagger.dev/dev-guide/android.html), contudo, sua configuração é um pouco mais massante e requer maior robustez e atenção. Entre isso e outros fatores, o Koin vem se destacando no mercado e isso foi considerado para a sua implantação na nossa arquitetura.
- **[Navigation:](https://developer.android.com/guide/navigation)** o componente de navegação do Android Jetpack ajuda você a implementar a navegação, desde simples cliques em botões até padrões mais complexos, como barras de aplicativos e a gaveta de navegação. Esse componente também garante uma experiência do usuário consistente e previsível por meio da adesão a um conjunto de princípios estabelecido.
- **[Jetpack Compose:](https://developer.android.com/jetpack/compose)** o Jetpack Compose é um kit de ferramentas moderno do Android para criar IUs nativas. Ele simplifica e acelera o desenvolvimento da IU no Android, tornando o código mais simples e fácil de manter (evita codificação de classes e códigos *boilerplates*). Trabalha de forma declarativa, o que acelera o desenvolvimento.
- **[Paging:](https://developer.android.com/jetpack/compose)** a biblioteca Paging facilita o carregamento gradual e tranquilo de dados na RecyclerView do seu app.  
- **[Mockk:](https://mockk.io/)** uma biblioteca para simulação escrito e feito para o Kotlin + JUnit 4 (o JUnit 4 expõe uma API baseada em regras para permitir alguma automação após o ciclo de vida do teste).
- **[Turbine](https://github.com/cashapp/turbine)** uma pequena e poderosa biblioteca para nos auxiliar nos testes com os nosso Flow's.

Por fim, falando um pouco sobre o **MVVM (Model-View-ViewModel)**, temos basicamente a divisão em:

- **Model:** a camada que encapsula a lógica de negócios e os dados. Nada mais é do que o modelo de domínio de uma aplicação.
- **View:** tem por responsabilidade definir a aparência ou estrutura que o usuário vê na tela. Se trata de toda a parte visual da aplicação.
- **ViewModel:** sua incumbência é disponibilizar para a camada de *View* uma lógica de apresentação. A *ViewModel* não tem nenhum conhecimento específico sobre a view, apenas implementa propriedades e comandos e notifica a *View* no caso de alterações. Ela permite que os dados sobrevivam às mudanças de configurações, como a rotação de tela, por exemplo.

Considerando estas camadas, podemos observar que é uma divisão que se encaixa diretamente com a sugestão proposta da **Clean Architecture**, que consiste em um diagrama de camadas, onde cada um dos seus componentes possuem suas próprias responsabilidades e cada uma delas tem conhecimento apenas de camadas mais internas. Isso traz consigo várias vantagens:

- o código é facilmente testável.
- componentes ainda mais desacoplados, a estrutura do pacote é facilmente de se navegar entre eles.
- novas funcionalidades podem ser adicionadas rapidamente pelo time de desenvolvedores.

Cada camada de MVVM usando Clean Architecture no Android e os códigos se dividem em três camadas:

- **Camada de Apresentação (Presentation Layer):** Nesta camada, são incluídas as "Activity"s, "Fragment"s como sendo as "Views", e as "ViewModel"s, devem ser mais simples e intuitivo possível e ainda, não devem ser incluídas regras de negócio nas "Activity"s e "Fragment"s. Uma "View" irá se comunicar com sua respectiva "ViewModel", e assim, a "ViewModel" se comunicará com a camada de domínio para que sejam executadas determinadas ações. Uma "ViewModel" nunca se comunicará diretamente com a camada de dados. Aqui, na estrutura de nosso projeto, temos os diretórios "presentation", que por sua vez, possui os diretórios "ui" (com nossas "View"s e "ViewModel"s) e "di" (com nosso módulo Koin para tratar as injeções de dependência).

- **Camada de Domínio (Domain Layer):** Na camada de domínio, devem conter todos os casos de uso da aplicação. Os casos de uso tem como objetivo serem mediadores entre suas "ViewModel"s e os "Repository"s. Caso for necessário adicionar uma nova funcionalidade, tudo o que deve ser feito é adicionar um novo caso de uso e todo seu respectivo código estará completamente separado e desacoplado dos outros casos de uso. A criação de um novo caso de uso é justamente para evitar que ao adicionar novas funcionalidades, quebrar as preexistentes no código. Podemos observar o diretório "domain" e, neste, o diretório "usecase" com todos os nossos casos de uso.

- **Camada de Dados (Data Layer):** Esta camada possui todos os repositórios que a camada de domínio tem disponíveis para utilização e "DataSource"s, que são responsáveis por decidir qual a fonte em que devem ser recuperados os dados, sendo de uma base de dados local ou servidor remoto. Note o repositório "data". Nele se concentra nossos modelos de dados, modelagem do banco de dados, camada de serviço (que lista todos os nosso endpoints), camada de DAO para acesso aos dados no banco e, a parte dos nossos repositórios.

## Como executar o projeto

Primeiro, faça o download do projeto executando o comando

```
git clone https://github.com/lucaschristopher/carrefour-bank-challenge.git
```

Em seguida, abra sua IDEA preferida (de preferência, o Android Studio), importe o projeto, carregue as dependências e execute o projeto.
Para executar os testes unitários, dentro da pasta do projeto, abra o terminal e execute o comando

```
./gradlew testDebugUnitTest
```
Esta foi a abordagem adotada na solução. Desde já, grato.

**Autor: Lucas Christopher.**

##### _Toda a honra e glória seja dada a Jesus!_

