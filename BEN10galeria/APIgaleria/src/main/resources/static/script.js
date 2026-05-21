const BASE_HOST = window.location.origin.includes('localhost') ? window.location.origin : 'http://localhost:8080';
const API_ALIEN = `${BASE_HOST}/api/alienigena`;
const API_USER = `${BASE_HOST}/api/usuarios`;

// Variable global para guardar los aliens y poder filtrarlos luego sin llamar a la base de datos de nuevo
let alienigenasCargados = [];

// 2. Al arrancar la página, ejecutar carga
document.addEventListener('DOMContentLoaded', cargarBaseDeDatos);

// ===================== MÉTODOS GET =====================

// GET - Obtener todos los alienígenas
function obtenerAlienigenas() {
	return fetch(API_ALIEN)
		.then(res => {
			if (!res.ok) throw new Error("Error al obtener alienígenas");
			return res.json();
		});
}

// GET - Obtener todos los usuarios
function obtenerUsuarios() {
	return fetch(API_USER)
		.then(res => {
			if (!res.ok) throw new Error("Error al obtener usuarios");
			return res.json();
		});
}

// GET - Obtener un alienígena por ID
function obtenerAlienigenaPorId(id) {
	return fetch(`${API_ALIEN}/${id}`)
		.then(res => {
			if (!res.ok) throw new Error("Error al obtener el alienígena");
			return res.json();
		});
}

// GET - Obtener un usuario por ID
function obtenerUsuarioPorId(id) {
	return fetch(`${API_USER}/${id}`)
		.then(res => {
			if (!res.ok) throw new Error("Error al obtener el usuario");
			return res.json();
		});
}

// ===================== CARGAR BASE DE DATOS =====================

function cargarBaseDeDatos() {
	const estadoDiv = document.getElementById('estado-api');

	obtenerAlienigenas()
		.then(aliens => {
			alienigenasCargados = aliens; // Guardamos en la variable global
			pintarGaleria(aliens);
			if (estadoDiv) {
				estadoDiv.innerHTML = '<span class="badge bg-success bg-opacity-10 text-success border border-success p-2"><i class="fa-solid fa-check-circle me-1"></i> API SPRING BOOT CONECTADA</span>';
			}
		})
		.catch(() => {
			if (estadoDiv) {
				estadoDiv.innerHTML = '<span class="badge bg-danger bg-opacity-10 text-danger border border-danger p-2"><i class="fa-solid fa-xmark-circle me-1"></i> SERVIDOR APAGADO</span>';
			}
			document.getElementById('galeria-aliens').innerHTML = `
			<div class="col-12 text-center py-4">
				<p class="text-danger">Enciende tu aplicación Java en IntelliJ para cargar PostgreSQL.</p>
			</div>`;
		});
}

function pintarGaleria(aliens) {
        const grid = document.getElementById('galeria-aliens');
        grid.innerHTML = '';

        if (aliens.length === 0) {
                grid.innerHTML = '<div class="col-12 text-center"><p class="text-muted fs-5">No hay coincidencias en la base de datos.</p></div>';
                return;
        }

        aliens.forEach(alien => {
                const imagenSegura = alien.imagenUrl ? alien.imagenUrl : 'https://via.placeholder.com/400x220?text=Sin+Imagen';

                const card = document.createElement('div');
                card.className = 'col-lg-4 col-md-6';
                card.innerHTML = `
                    <div class="alien-card d-flex flex-column">
                        <div class="alien-img-container">
                            <img src="${imagenSegura}" class="alien-img" alt="${alien.nombre}">
                        </div>
                        <div class="card-body p-4 d-flex flex-column flex-grow-1">
                            <h4 class="font-sci-fi text-white mb-1">${alien.nombre}</h4>
                            <div class="text-success small mb-3">
                                <i class="fa-solid fa-globe me-1"></i> ${alien.planeta} • <strong class="text-white">${alien.raza}</strong>
                            </div>
                            <p class="text-muted small flex-grow-1">${alien.descripcion}</p>
                            <div class="d-flex justify-content-between align-items-center mt-3 pt-3 border-top border-secondary border-opacity-50">
                                <span class="power-tag"><i class="fa-solid fa-bolt me-1"></i> ${alien.habilidad}</span>
                                <button onclick="eliminarAlien(${alien.id})" class="btn btn-sm btn-outline-danger" title="Purgar Espécimen">
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>`;
                grid.appendChild(card);
        });
}

// 3. Función de filtrado que faltaba (Se ejecuta al escribir o cambiar el select)
function filtrarAliens() {
        const textoBuscado = document.getElementById('buscar-alien').value.toLowerCase();
        const planetaSeleccionado = document.getElementById('filtro-planeta').value;

        const aliensFiltrados = alienigenasCargados.filter(alien => {
                // Filtro por nombre o raza
                const coincideTexto = alien.nombre.toLowerCase().includes(textoBuscado) ||
                        alien.raza.toLowerCase().includes(textoBuscado);

                // Filtro por planeta
                const coincidePlaneta = planetaSeleccionado === 'todos' || alien.planeta === planetaSeleccionado;

                return coincideTexto && coincidePlaneta;
        });

        pintarGaleria(aliensFiltrados);
}

// 4. POST ALIEN (Arreglado para inyectar la URL de la imagen y usar alertas inline)
function guardarAlienigena(e) {
        e.preventDefault();
        // Validación cliente: campos obligatorios
        const nombre = document.getElementById('alien-nombre').value.trim();
        const raza = document.getElementById('alien-raza').value.trim();
        const planeta = document.getElementById('alien-planeta').value.trim();
        const habilidad = document.getElementById('alien-habilidad').value.trim();
        const descripcion = document.getElementById('alien-desc').value.trim();
        const imagenUrl = document.getElementById('alien-imagen').value.trim(); // opcional

        if (!nombre || !raza || !planeta || !habilidad || !descripcion) {
                mostrarAlerta('alerta-alien', 'Rellena todos los campos obligatorios antes de guardar.', 'danger');
                return;
        }

        const payload = { nombre, raza, planeta, habilidad, descripcion, imagenUrl };

        fetch(API_ALIEN, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
        })
                .then(res => {
                        if (!res.ok) throw new Error();
                        mostrarAlerta('alerta-alien', '¡ADN inyectado con éxito en PostgreSQL!', 'success');
                        document.getElementById('formulario-alien').reset(); // Limpia el formulario
                        cargarBaseDeDatos(); // Recarga la galería para mostrar el nuevo alien

                        // Hace scroll automático hacia arriba para ver el nuevo alien
                        window.location.href = '#galeria-seccion';
                })
                .catch(() => mostrarAlerta('alerta-alien', 'Error al conectar con Spring Boot', 'danger'));
}

// 5. POST USUARIO
function guardarUsuario(e) {
        e.preventDefault();
        // Validación cliente: campos obligatorios
        const nombre = document.getElementById('reg-nombre').value.trim();
        const rango = document.getElementById('reg-rango').value.trim();
        const email = document.getElementById('reg-email').value.trim();
        const planeta = document.getElementById('reg-planeta').value.trim();
        const password = document.getElementById('reg-password').value.trim();

        if (!nombre || !rango || !email || !password) {
                mostrarAlerta('alerta-registro', 'Rellena los campos Nombre, Rango, Email y Password.', 'danger');
                return;
        }

        const payload = { nombre, rango, email, planeta, password };

        fetch(API_USER, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
        })
                .then(res => {
                        if (!res.ok) throw new Error();
                        mostrarAlerta('alerta-registro', '¡Agente registrado en la base de datos!', 'success');
                        document.getElementById('formulario-registro').reset();
                })
                .catch(() => mostrarAlerta('alerta-registro', 'Error al guardar el agente', 'danger'));
}

// DELETE ALIEN
function eliminarAlien(id) {
        if (confirm("¿Autorización nivel 5 requerida: purgar esta secuencia de ADN?")) {
                fetch(`${API_ALIEN}/${id}`, { method: 'DELETE' })
                        .then(res => {
                                if (res.ok || res.status === 204) {
                                        cargarBaseDeDatos(); // Recargar la lista
                                }
                        });
        }
}

// Función auxiliar para mostrar alertas de colores sobre los formularios
function mostrarAlerta(elementId, mensaje, clase) {
        const alertBox = document.getElementById(elementId);
        alertBox.className = `alert alert-${clase} mb-4 font-sci-fi small`;
        alertBox.innerHTML = clase === 'success'
                ? `<i class="fa-solid fa-circle-check me-2"></i> ${mensaje}`
                : `<i class="fa-solid fa-circle-exclamation me-2"></i> ${mensaje}`;
        alertBox.classList.remove('d-none');

        setTimeout(() => {
                alertBox.classList.add('d-none');
        }, 4500);
}

