const apiDeVeiculos = "http://localhost:8080";

document.getElementById("form-veiculo").addEventListener("submit", async (e) => {
    e.preventDefault();

    const modelo = document.getElementById("modelo").value;
    const placa = document.getElementById("placa").value;
    const anoFabricacao = document.getElementById("anoFabricacao").value;

    await fetch(`${apiDeVeiculos}/veiculos`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ modelo, placa, anoFabricacao })
    });

    carregarVeiculos();
    e.target.reset();
});

document.getElementById("form-acessorio").addEventListener("submit", async (e) => {
    e.preventDefault();

    const veiculoId = document.getElementById("veiculoId").value;
    const nome = document.getElementById("acessorioNome").value;

    await fetch(`${apiDeVeiculos}/veiculos/${veiculoId}/acessorios`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome })
    });

    carregarVeiculos();
    e.target.reset();
});

async function carregarVeiculos() {
    const lista = document.getElementById("lista-veiculos");
    lista.innerHTML = "";

    const res = await fetch(`${apiDeVeiculos}/veiculos`);
    const veiculos = await res.json();

    veiculos.forEach(v => {
        const li = document.createElement("li");
        li.innerHTML = `
            <strong>ID:</strong> ${v.id} <br>
            <strong>Modelo:</strong> ${v.modelo} <br>
            <strong>Placa:</strong> ${v.placa} <br>
            <strong>Ano:</strong> ${v.anoFabricacao} <br>
            <strong>Acessórios:</strong>
            <ul>
                ${v.acessorios.map(a => `<li>${a.nome} <button onclick="removerAcessorio(${v.id}, ${a.id})">Remover</button></li>`).join("")}
            </ul>
            <button onclick="removerVeiculo(${v.id})">Remover Veículo</button>
        `;
        lista.appendChild(li);
    });
}

async function removerVeiculo(id) {
    await fetch(`${apiDeVeiculos}/veiculos/${id}`, { method: "DELETE" });
    carregarVeiculos();
}

async function removerAcessorio(veiculoId, acessorioId) {
    await fetch(`${apiDeVeiculos}/veiculos/${veiculoId}/acessorios/${acessorioId}`, { method: "DELETE" });
    carregarVeiculos();
}

carregarVeiculos();
