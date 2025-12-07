[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/MigUHLuM)
# ⚔️ Type Kombat

> **Projeto Final - Programação Orientada a Objetos I** > **Instituto Federal Catarinense - Campus Videira** > **Professor:** Fábio José Rodrigues Pinheiro  
> **Data:** 09/12/2025

![Java](https://img.shields.io/badge/Language-Java-orange) ![Status](https://img.shields.io/badge/Status-Completed-green) ![License](https://img.shields.io/badge/License-MIT-blue)

## 📖 Sobre o Projeto

**Type Kombat** é um jogo de batalhas em modo texto (Console Application) desenvolvido em Java. O projeto inova ao misturar a estratégia clássica de RPGs por turno com mecânicas de **Ação e Reflexo**.

Diferente de RPGs tradicionais onde o resultado depende apenas de sorte (dados), aqui o jogador precisa provar sua habilidade: o dano dos ataques e a eficácia da defesa são calculados com base na **velocidade de digitação** do usuário.

## 🎮 Como Jogar

O jogo funciona em um sistema de rodadas híbrido:

1.  **Fase de Estratégia:** O jogador analisa sua vida e *cooldowns* (tempo de recarga) e escolhe uma ação no menu:
    * `[1] Ataque Básico`: Dano moderado, palavra curta.
    * `[2] Habilidade Especial`: Dano massivo, palavra longa (requer recarga).
    * `[3] Defender`: Recupera vida e assume postura defensiva.
2.  **Fase de Ação (Digitação):**
    * Uma palavra aparecerá na tela (ex: `CORTAR`, `METEORO`).
    * O jogador deve digitá-la e dar Enter o mais rápido possível.
    * **< 1.8s:** Dano Crítico!
    * **> 3.0s:** O ataque falha.
3.  **Fase do Inimigo:**
    * O inimigo ataca e o jogador deve digitar uma palavra de defesa (ex: `ESQUIVA`) rapidamente para evitar o dano.

## 🚀 Como Executar

Certifique-se de ter o **JDK (Java Development Kit)** instalado.

1.  Clone o repositório:
    ```bash
    git clone [https://github.com/seu-usuario/grim-typer-rpg.git](https://github.com/seu-usuario/grim-typer-rpg.git)
    ```
2.  Navegue até a pasta e compile o arquivo principal:
    ```bash
    javac JogoFinalVisual.java
    ```
3.  Execute o jogo:
    ```bash
    java JogoFinalVisual
    ```

---

## 🏗️ Estrutura e Requisitos POO

Este projeto foi desenhado especificamente para demonstrar os pilares da Programação Orientada a Objetos. Abaixo detalhamos onde cada conceito foi aplicado no código:

### 1. Abstração
Utilizada para criar modelos genéricos que não podem ser instanciados diretamente, mas servem de base para objetos reais.
* **Onde está:** Na classe `abstract class Combatente` e `abstract class Item`.
* **Explicação:** Não existe um "Combatente" genérico no jogo; existem apenas `Guerreiro`, `Mago` ou `Monstro`. A classe pai define o contrato (vida, ataque, métodos abstratos) que todos devem seguir.

### 2. Encapsulamento
Proteção dos dados internos das classes, evitando acesso direto e não autorizado.
* **Onde está:** Atributos como `protected int vida` e `private long inicio` (no Cronômetro).
* **Explicação:** O nível de vida ou o tempo do cronômetro não são alterados diretamente por outras classes. Utilizamos métodos públicos como `receberDano()`, `curar()` e `parar()` para manipular esses estados de forma segura.

### 3. Herança
Reaproveitamento de código e especialização de classes.
* **Onde está:**
    * `Guerreiro extends Combatente`
    * `Mago extends Combatente`
    * `Monstro extends Combatente`
    * `PocaoCura extends Item`
* **Explicação:** O `Guerreiro` herda toda a lógica de vida e defesa do `Combatente`, precisando apenas definir seus atributos específicos (mais força física) e suas palavras de ataque.

### 4. Polimorfismo
Ocorre quando classes filhas implementam o mesmo método da classe pai, mas com comportamentos diferentes.
* **Onde está:**
    * `getAsciiArt()`: O Guerreiro desenha uma espada, o Mago um cajado e o Monstro uma garra.
    * `getPalavraAtaque()`: O Mago retorna palavras como "FOGO", enquanto o Guerreiro retorna "LÂMINA".
    * `aplicar(Combatente alvo)`: A `PocaoCura` aumenta a vida, enquanto o `PergaminhoForca` aumenta o ataque. Todos são tratados como `Item` genéricos pelo inventário.

### 5. Composição
Criação de objetos complexos utilizando outros objetos como partes.
* **Onde está:**
    * Na classe `Equipe`, que possui uma lista de combatentes (`List<Combatente> membros`).
    * Na classe `Combatente`, que possui um inventário (Lista de Itens).
    * Na classe `BatalhaVisual`, que é composta por duas `Equipes` e um `Cronometro`.

### 6. Coesão e Baixo Acoplamento
Organização do código para que cada classe tenha uma responsabilidade única.
* **Onde está:**
    * **Classe `UI`:** Cuida exclusivamente da interface visual (limpar tela, desenhar barras, ASCII). Não faz cálculos de dano.
    * **Classe `Cronometro`:** Só conta o tempo. Não sabe quem está lutando.
    * **Classe `BatalhaVisual`:** Gerencia o fluxo (turnos), delegando a lógica de ataque para os combatentes e o desenho para a UI.

---
