const map = L.map('map').setView([-4.007890, -79.211677], 14);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 18,
    attribution: 'Map data © <a href="https://www.openstreetmap.org/">OpenStreetMap</a>'
}).addTo(map);

let url = 'http://localhost:8000/myapp/grafo';

async function fetchGrafo() {
    try {
        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`Error en la solicitud: ${response.status}`);
        }

        const r = await response.json();
        const grafo = r.data;

        console.log("Grafo recibido:", grafo);

        render(grafo);
    } catch (error) {
        console.error("Error al obtener el grafo:", error);
    }
}

function render(grafo) {
    const nodes = grafo.nodes;
    const edges = grafo.edges;

    console.log("Nodos:", nodes);
    console.log("Aristas:", edges);

    const marcadores = {};
    nodes.forEach(node => {
        const marcador = L.marker([node.lat, node.long]).addTo(map);
        marcador.bindPopup(`<b>${node.nombre}</b>`).openPopup();
        marcadores[node.id] = [node.lat, node.long];
    });

    edges.forEach(edge => {
        const from = marcadores[edge.from];
        const to = marcadores[edge.to];
        if (from && to) {
            L.polyline([from, to], { color: 'blue', weight: 3 }).addTo(map);
        } else {
            console.error("No se encontraron los nodos para la arista:", edge);
        }
    });
}

fetchGrafo();