const api = "/api/scolarites";
let stages = [];
let etudiants = [];
let enseignants = [];

const $ = id => document.querySelector(id);

const listPage = $("#listPage");
const editPage = $("#editPage");
const stageRows = $("#stageRows");
const stageForm = $("#stageForm");
const stageId = $("#stageId");
const code = $("#code");
const sujet = $("#sujet");
const adresse = $("#adresse");
const etudiantSelect = $("#etudiantId");
const enseignantSelect = $("#enseignantId");
const message = $("#message");
const refreshButton = $("#refreshButton");
const backButton = $("#backButton");
const saveButton = $("#saveButton");

function showList() {
    listPage.style.display = "block";
    editPage.style.display = "none";
}

function showEdit() {
    listPage.style.display = "none";
    editPage.style.display = "block";
}

async function fetchJson(url, options) {
    const response = await fetch(url, options);
    if (!response.ok) throw new Error(`Erreur HTTP ${response.status}`);
    return response.json();
}

async function loadData() {
    const [stageList, etudiantList, enseignantList] = await Promise.all([
        fetchJson(`${api}/stages`),
        fetchJson(`${api}/etudiants`),
        fetchJson(`${api}/enseignants`)
    ]);
    stages = stageList;
    etudiants = etudiantList;
    enseignants = enseignantList;
    fillSelects();
    renderStages();
}

function fillSelects() {
    etudiantSelect.innerHTML = etudiants.map(e =>
        `<option value="${e.id}">${escapeHtml(e.prenom)} ${escapeHtml(e.nom)} - ${escapeHtml(e.niveau)}</option>`
    ).join("");
    enseignantSelect.innerHTML = enseignants.map(e =>
        `<option value="${e.id}">${escapeHtml(e.prenom)} ${escapeHtml(e.nom)}</option>`
    ).join("");
}

function renderStages() {
    if (stages.length === 0) {
        stageRows.innerHTML = '<tr><td colspan="6" class="empty">Aucun stage trouve.</td></tr>';
        return;
    }
    stageRows.innerHTML = stages.map(s => {
        const etu = etudiants.find(e => e.id === s.etudiantId);
        const ens = enseignants.find(e => e.id === s.enseignantId);
        return `<tr>
            <td><strong>${escapeHtml(s.code)}</strong></td>
            <td>${escapeHtml(s.sujet)}</td>
            <td>${escapeHtml(s.adresse)}</td>
            <td>${etu ? `${escapeHtml(etu.prenom)} ${escapeHtml(etu.nom)}` : `<span class="muted">ID ${s.etudiantId}</span>`}</td>
            <td>${ens ? `${escapeHtml(ens.prenom)} ${escapeHtml(ens.nom)}` : `<span class="muted">ID ${s.enseignantId}</span>`}</td>
            <td><button class="row-button modifier-btn" type="button" data-id="${s.id}">Modifier</button></td>
        </tr>`;
    }).join("");

    document.querySelectorAll(".modifier-btn").forEach(btn =>
        btn.addEventListener("click", () => selectStage(Number(btn.dataset.id)))
    );
}

function selectStage(id) {
    const stage = stages.find(s => s.id === id);
    if (!stage) return;
    stageId.value = stage.id;
    code.value = stage.code;
    sujet.value = stage.sujet;
    adresse.value = stage.adresse;
    etudiantSelect.value = stage.etudiantId;
    enseignantSelect.value = stage.enseignantId;
    message.textContent = "";
    showEdit();
}

async function saveStage(event) {
    event.preventDefault();
    saveButton.disabled = true;
    const id = stageId.value;
    const body = {
        code: code.value.trim(),
        sujet: sujet.value.trim(),
        adresse: adresse.value.trim(),
        etudiantId: Number(etudiantSelect.value),
        enseignantId: Number(enseignantSelect.value)
    };
    const method = id ? "PUT" : "POST";
    const url = id ? `${api}/fiche-stage/${id}` : `${api}/fiche-stage`;
    await fetchJson(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body)
    });
    await loadData();
    message.textContent = "Stage modifie avec succes.";
    saveButton.disabled = false;
    showList();
}

function escapeHtml(value) {
    return String(value ?? "")
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

stageForm.addEventListener("submit", event => {
    saveStage(event).catch(err => {
        message.textContent = err.message;
        message.classList.add("error");
        saveButton.disabled = false;
    });
});

refreshButton.addEventListener("click", () => {
    loadData().catch(err => console.error(err));
});

backButton.addEventListener("click", showList);

loadData().catch(err => console.error(err));
