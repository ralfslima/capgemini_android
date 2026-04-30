// Importar Express
const express = require("express");

// Criar o objeto app (responsável por gerenciar a API)
const app = express()

// Aceitar informações vindas do body
app.use(express.json());

// Banco de dados fake e auto incremento
let students = [];
let idGenerator = 1;

// Rotas
app.get("/students", (req, res) => {
    res.json(students);
});

app.post("/students", (req, res) => {
    // Extrair as propriedades da requisição
    //const nome = req.body.nome;
    //const nota1 = req.body.nota1;
    //const nota2 = req.body.nota2;
    const {name, grade1, grade2} = req.body;

    // Criar o objeto aluno
    const student = {
        id: idGenerator++,
        name,
        grade1,
        grade2,
        mean: (grade1 + grade2) / 2
    }

    // Adicionar student no vetor
    students.push(student);

    // Retorno
    res.status(201).json(student);
});

app.delete("/students/:id", (req, res) => {
    // Obter o id
    const id = parseInt(req.params.id)

    // Filtrar todos os alunos com id diferente do obtido
    students = students.filter(s => s.id !== id);

    // Retorno
    res.json({msg: "Aluno removido com sucesso!"});
});

// Servidor
app.listen(3000)