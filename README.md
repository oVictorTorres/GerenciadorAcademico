# Sistema de Gerenciamento de Alunos e Disciplinas

## Descrição do Projeto
Este projeto é um sistema simples de gerenciamento de alunos e disciplinas, desenvolvido em Java com uma interface gráfica usando `Swing`. Ele permite que o usuário adicione disciplinas, matricule alunos em disciplinas, calcule a média dos alunos, determine suas situações (aprovado, recuperação ou reprovado) e gerencie dados de forma persistente com o uso de arquivos binários.

## Funcionalidades Principais
1. **Adicionar, Editar e Excluir Disciplinas**:
   - O usuário pode adicionar uma disciplina, editar o nome de uma existente ou excluir uma disciplina.
   
2. **Matricular Alunos**:
   - O sistema permite cadastrar alunos e matriculá-los em disciplinas específicas.
   
3. **Calcular Situação dos Alunos**:
   - O sistema calcula a média dos alunos com base nas três notas inseridas. As duas maiores notas são consideradas no cálculo da média.
   - O sistema determina se o aluno está **aprovado**, **em recuperação** ou **reprovado**.
   - Caso o aluno esteja em recuperação, o sistema solicita a nota da recuperação e recalcula sua situação.
   
4. **Persistência de Dados**:
   - Todos os dados de disciplinas e alunos são salvos automaticamente em um arquivo binário (`dados.bin`) e carregados ao iniciar o sistema.

## Como Executar o Projeto
### Pré-requisitos:
- **Java**: Certifique-se de ter o JDK instalado na sua máquina.
- **IDE de Desenvolvimento**: Recomenda-se o uso de uma IDE como IntelliJ IDEA, Eclipse, ou NetBeans para facilitar a execução do projeto.

### Passos para Execução:
1. Clone ou baixe os arquivos do projeto.
2. Importe o projeto na sua IDE de preferência.
3. Certifique-se de que as seguintes classes estão no pacote correto:
   - `Controller.Controlador`
   - `Model.Aluno`
   - `Model.Disciplina`
   - `View.Interface`
   - `Main`
4. Compile e execute a classe `Main` como a classe principal.

### Estrutura do Projeto:
O projeto está organizado da seguinte forma:
src/ 
├── Controller/ │ └── Controlador.java 
├── Model/ │ ├── Aluno.java │ └── Disciplina.java 
├── View/ │ └── Interface.java 
└── Main.java

### Salvamento de Dados:
- Os dados de disciplinas e alunos são automaticamente salvos no arquivo `dados.bin` quando o programa é fechado, e carregados ao iniciar novamente. O arquivo `dados.bin` será criado na raiz do projeto.

### Interface Gráfica:
- O sistema possui uma interface gráfica simples e funcional que permite ao usuário interagir de forma intuitiva para gerenciar alunos e disciplinas.

## Funcionalidades Detalhadas:
1. **Adicionar Disciplina**: Insira o nome de uma nova disciplina e clique em "Adicionar Disciplina".
2. **Editar Disciplina**: Escolha o índice da disciplina e insira o novo nome.
3. **Excluir Disciplina**: Escolha o índice da disciplina que deseja excluir.
4. **Matricular Aluno**: Insira o nome do aluno e suas notas, depois escolha a disciplina na qual deseja matricular o aluno.
5. **Calcular Situação**: Insira as notas de um aluno e veja sua situação (aprovado, em recuperação ou reprovado).
6. **Listar Disciplinas**: Mostra todas as disciplinas cadastradas.
7. **Listar Alunos**: Exibe todos os alunos matriculados em uma disciplina específica, junto com suas notas e a situação (aprovado, em recuperação ou reprovado).

## Erros e Tratamento:
- Caso o usuário insira notas inválidas (não numéricas) ou tente realizar operações sem inserir os dados corretamente, o sistema exibirá mensagens de erro explicando o que está errado.

## Possíveis Melhorias:
- Melhorar o layout da interface gráfica para torná-la mais moderna e intuitiva.
- Adicionar novos recursos, como a geração de relatórios ou até a criação de um sistema mais fluído, que vá adicionando notas dos alunos aos poucos. E também a funcionalidade de remover alunos da disciplina, em caso de trancamento.

## Autor
Desenvolvido por [Victor Torres].
