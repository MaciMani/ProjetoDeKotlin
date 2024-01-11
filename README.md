# ProjetoDeKotlin

O nosso trabalho consiste num aplicação onde o usuário consegue criar uma conta e posteriormente utilizar a mesma para fazer login na mesma. Também contamos com uma API que permite com que a nossa aplicação vá buscar dados sobre músicas, artistas e album, onde estes dados são usados conjuntamente com um player de música que permite reproduzir as músicas que foram trazidas pela API

Começando pela API a que escolhemos foi a Deezer. Esta API é uma API que nos permite ter acesso a dados e funcionalidades da plataforma de streaming de música Deezer.
Deezer é um serviço de música que oferece um vasto catálogo de faixas musicais, playlists e álbuns. A API proporciona-nos a capacidade de integrar essas funcionalidades na nossa aplicação.

O endpoint de pesquisa (*search) na API Deezer permite aos desenvolvedores realizar pesquisas no catálogo do Deezer para encontrar faixas, álbuns, artistas ou listas de reprodução que correspondam aos critérios de pesquisa fornecidos. Aqui estão os principais parâmetros que podem ser usados neste endpoint são:

-q (query): Este parâmetro é obrigatório e representa o termo de pesquisa. Pode ser o nome de uma faixa, álbum, artista ou lista de reprodução.
-indexing (boolean): Este parâmetro controla se a API deve ou não indexar os resultados. Pode ser usado para otimizar pesquisas frequentes.
-limit (integer): Este parâmetro define o número máximo de resultados a serem retornados. Pode ser útil para paginar os resultados.
-offset (integer): Define a posição inicial dos resultados a serem retornados. Útil para paginar os resultados.

Na nossa aplicação criamos uma classe de data para representar a estrutura dos dados retornados pela API do Deezer. Esta classe chamada MusicData conta com uma variavel data que é uma lista de objetos do tipo Data que representam infoprmações sobre as músicas, também conta com uma string next que fornece o link para a próxima página de resultados e uma variavel int total que representa o número total de resultados disponíveis.

Também temos a classe data Data que contem um objeto album que representa as informações do album, artist que representa as informações do artista e duration que indica a duração da música em segundos. Existem ainda outros campos que representam informações sobre a música, como id, link, title, etc.

A classe data Artist que contem um id do artista, um link para mais informações do artista, name que é o nome do artista, picture que contem um URL da imagem do artista, entre outros campos.

Por fim temos a classe data Album que contem um cover que contem um URL com a imagem da capa do album, um title que é o titulo do álbum, entre outros campos

Criamos uma interface para a API chamada ApiInterface para interagir com uma API do Deezer usando a biblioteca Retrofit, onde usamos @Headers neste caso "X-RapidAPI-Key" e "X-RapidAPI-Host" para autentificação e acesso à API. Usamos também um @GET("search") para fazer a solicitação HTTP para o endpoint "search" da API. Criamos tambem uma função chamada getData que representa a chamada à API de pesquisa. Esta conta com um parametro @Querry que é usado para indicar que o parâmetro query deve ser incluído como um parâmetro de consulta na URL. Por fim também conta com o Call<MusicData?> que retorna uma instância de Call e o tipo <MusicData?>indica que a resposta da API será desserializada para um objeto do tipo MusicData.

Criamos também uma atividade para o registo de usuários onde usamos uma ViewBinding para vincular as views a esta classe, fazendo uso do by lazy para iniciar a o binding apenas quando for necessário, declaramos uma variável para a instância do FirebaseAuth, que é usado para autenticação no Firebase. Após isso utilizamos a override fun onCreate para Sobrescrever  método onCreate da classe base, configura o layout da atividade com o conteúdo da raiz da ViewBinding. Definimos um listener para o TextView "tvHaveAnAccount", que direciona o usuário para a LoginActivity quando clicado. Configuramos o OnClickListener para o Button "btnRegister". Definimos um listener para o botão "btnRegister" que obtém os valores dos campos de texto. Verificamos se algum campo está vazio e exibimos um aviso através de um Toast se necessário. Verifica se a senha e a confirmação de senha correspondem, mostrando um aviso se não corresponderem. Se os campos estiverem preenchidos corretamente, cria uma conta de usuário no Firebase usando o método `createUserWithEmailAndPassword`. Exibe uma mensagem de sucesso ou falha por meio de `Toast` com base no resultado da operação.

Criamos a atividade para o login dos usuários onde usamos uma ViewBinding para vincular as views a esta classe, fazendo uso do by lazy para iniciar a o binding apenas quando for necessário, declaramos uma variável para a instância do FirebaseAuth, que é usado para autenticação no Firebase. Depois disso utilizamos a override fun onCreate para Sobrescrever  método onCreate da classe base, configura o layout da atividade com o conteúdo da raiz da ViewBinding. Configuramos o OnClickListener para o Button "btnLogin". Definimos um listener para o botão "btnLogin" que obtém os valores dos campos de e-mail e senha. Verificamos se algum campo está vazio e exibimos um aviso através de um Toast se necessário. Se os campos estiverem preenchidos corretamente, efetua o login do usuário usando o método signInWithEmailAndPassword do Firebase Authentication. Exibe uma mensagem de sucesso ou falha por meio de Toast com base no resultado da operação. Por fim definimos um listener para o botão "newAccountButton", que direciona o usuário para a RegisterActivity quando clicado.

Criamos um Adapter para a RecyclerView. Esta classe Adapter estende RecyclerView.Adapter e é parametrizada com MyViewHolder, que é a classe interna que representa as visualizações individuais dentro da RecyclerView.
Recebe o contexto da atividade (Activity) e uma lista de dados (List<Data>) para serem exibidos na RecyclerView.
Criamos uma nova instância de MyViewHolder quando necessário.
Infla o layout do item da lista (line_template.xml) usando o LayoutInflater e adiciona-o ao parent.
getItemCount retorna o número total de itens na lista de dados.
onBindViewHolder atualiza o conteúdo da visualização com base nos dados na posição position na lista.
Cria uma instância do MediaPlayer com base na URL de pré-visualização fornecida nos dados.
Configura o título do item, carrega a imagem usando Picasso, e define OnClickListener para os botões de reprodução (play) e pausa (pause) associados a cada item.
MyViewHolder representa as visualizações individuais dentro da RecyclerView.
Inicializa as propriedades (image, title, play, pause) com as visualizações correspondentes do layout do item (line_template.xml).

Por fim temos a MainActivity que utiliza o Retrofit para fazer uma chamada à API, obter dados relacionados a músicas e exibir esses dados em uma RecyclerView por meio de um Adapter.
myRecyclerView: Uma instância de RecyclerView que é usada para exibir a lista de itens.
myAdapter: Uma instância do Adapter que fornece os dados para a RecyclerView.
Método onCreate que é o método chamado quando a atividade é criada. Configura o layout da atividade ao usar setContentView.
Obtémos a referência da RecyclerView do layout.
Configuramos o Retrofit para fazer chamadas à API.
Criamos uma instância da interface Retrofit e fazemos uma chamada à API para obter dados relacionados a músicas do artista.
Implementamos métodos de resposta (onResponse) e falha (onFailure) para lidar com o resultado da chamada.
Se a chamada for bem-sucedida, cria uma instância do Adapter com os dados recebidos e configura a RecyclerView para exibir esses dados.

A nivel de layout a aplicação é bem simples tendo a tela de login com os botões necessários para criar conta ou fazer login
Passando para a outra tela temos as musicas listadas onde cada linha contem a imagem do album, o nome da musica e do artista e os dois botões para pausar ou despausar a musica.
