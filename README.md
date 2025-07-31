# Jogo da Forca

Este projeto consiste numa implementação simples e funcional do clássico **Jogo da Forca** em Java. O jogo é jogado via consola e permite ao utilizador adivinhar palavras letra a letra, com um limite de tentativas. Inclui estatísticas por jogo e da sessão, bem como uma representação visual da forca em ASCII.

---

## Estrutura do Projeto
JogoDaForca/ └── src/ ├── GestorPalavras.java ├── JogoForca.java └── Main.java

- `GestorPalavras.java`: Responsável por armazenar e selecionar palavras aleatórias.
- `JogoForca.java`: Contém a lógica do jogo, incluindo verificação de letras, estado da forca e estatísticas.
- `Main.java`: Ponto de entrada do programa. Gera o menu, controla o fluxo e apresenta os resultados.

---

## Como Executar

1. Certifica-te de que tens o **Java JDK** instalado.
2. Abre a linha de comandos ou terminal.
3. Navega até à pasta `src`:
   ```bash
   cd caminho/para/JogoDaForca/src

---

## Funcionalidades

-   Escolha aleatória de palavras.
-   Validação de letras já tentadas.
-   Representação visual da forca em ASCII.
-   Contador de tentativas e erros.
-   Estatísticas por jogo (tempo, palavra, tentativas).
-   Estatísticas da sessão (número de jogos, vitórias, derrotas).
-   Menu interativo com opção de jogar novamente ou sair.

---

## Requisitos Técnicos

-   Java 8 ou superior.
-   Ambiente de execução via consola (CLI).

---

## Autor

**João Pinto**  
Projeto desenvolvido no âmbito da UFCD 5425.

---

## Notas Finais

Este projeto foi desenvolvido com fins educativos e pode ser expandido com funcionalidades adicionais como:

-   Leitura de palavras a partir de ficheiros externos.
-   Interface gráfica (GUI).
-   Suporte a múltiplos jogadores.

---
