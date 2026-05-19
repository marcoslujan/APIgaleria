document.addEventListener('DOMContentLoaded', comprobarConexionYListar);

function comprobarConexionYListar() {
        const badge = document.getElementById('api-status-badge');
        fetch(API_ALIEN)
                .then(res => {
                        if (res.ok) {
                                badge.className = "status-badge status-online";
                                badge.innerHTML = '<i class="fa-solid fa-wifi me-1"></i> OMNITRIX API ONLINE';
                                return res.json();
                        }
                        throw new Error();
                })
                .then(aliens => pintarGaleria(aliens))
                .catch(() => {
                        badge.className = "status-badge text-danger border-danger";
                        badge.innerHTML = '<i class="fa-solid fa-circle-xmark me-1"></i> API DESCONECTADA';
                        document.getElementById('alien-grid').innerHTML = `
                        <div class="col-12 text-center py-5">
                            <h3 class="text-danger">Error de Conexión</h3>
                            <p class="text-muted">Enciende tu aplicación Spring Boot en IntelliJ para cargar los datos de PostgreSQL.</p>
                        </div>`;
                });
}

function pintarGaleria(aliens) {
        const grid = document.getElementById('alien-grid');
        grid.innerHTML = '';

        if (aliens.length === 0) {
                grid.innerHTML = '<p class="text-center text-muted fs-5">La base de datos está vacía.</p>';
                return;
        }

        aliens.forEach(alien => {
                // Si el alien no tiene imagen en la BD, usamos una por defecto por seguridad visual
                const imagenSegura = alien.imagenUrl ? alien.imagenUrl : 'https://via.placeholder.com/400x220?text=Sin+Imagen';

                const card = document.createElement('div');
                card.className = 'col-lg-4 col-md-6';
                card.innerHTML = `
            <div class="alien-card">
                <div class="alien-img-container">
                    <img src="${imagenSegura}" class="alien-img" alt="${alien.nombre}">
                </div>
                <div class="card-body">
                    <h4 class="alien-title mb-1">${alien.nombre}</h4>
                    <div class="text-success small mb-2">
                        <i class="fa-solid fa-globe me-1"></i> ${alien.planeta} • <strong>${alien.raza}</strong>
                    </div>
                    <p class="text-muted small">${alien.descripcion}</p>
                    <div class="d-flex justify-content-between align-items-center mt-3">
                        <span class="power-tag"><i class="fa-solid fa-bolt me-1"></i> ${alien.habilidad}</span>
                    </div>
                </div>
            </div>`;
                grid.appendChild(card);
        });
}

// POST ALIEN
document.getElementById('form-alien').addEventListener('submit', function (e) {
        e.preventDefault();
        const payload = {
                nombre: document.getElementById('alien-nombre').value,
                raza: document.getElementById('alien-raza').value,
                planeta: document.getElementById('alien-planeta').value,
                habilidad: document.getElementById('alien-habilidad').value,
                descripcion: document.getElementById('alien-descripcion').value
        };
        fetch(API_ALIEN, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
        })
                .then(res => {
                        if (!res.ok) throw new Error();
                        triggerToast("ADN inyectado con éxito en la Base de Datos.");
                        this.reset();
                        showSection('galeria');
                })
                .catch(() => triggerToast("Error al guardar el alienígena.", false));
});

// POST USUARIO
document.getElementById('form-usuario').addEventListener('submit', function (e) {
        e.preventDefault();
        const payload = {
                nombre: document.getElementById('user-nombre').value,
                email: document.getElementById('user-email').value,
                password: document.getElementById('user-password').value
        };
        fetch(API_USER, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
        })
                .then(res => {
                        if (!res.ok) throw new Error();
                        triggerToast("Agente registrado con éxito.");
                        this.reset();
                        showSection('galeria');
                })
                .catch(() => triggerToast("Error al registrar el usuario.", false));
});

// DELETE ALIEN
function eliminarAlien(id) {
        if (confirm("¿Purgar esta secuencia de ADN?")) {
                fetch(`${API_ALIEN}/${id}`, { method: 'DELETE' })
                        .then(() => {
                                triggerToast("Secuencia eliminada correctamente.");
                                comprobarConexionYListar();
                        });
        }
}

function showSection(id) {
        document.getElementById('sec-galeria').classList.add('d-none');
        document.getElementById('sec-agregar').classList.add('d-none');
        document.getElementById('sec-registro').classList.add('d-none');
        document.getElementById('nav-galeria').classList.remove('active');
        document.getElementById('nav-agregar').classList.remove('active');
        document.getElementById('nav-registro').classList.remove('active');

        document.getElementById(`sec-${id}`).classList.remove('d-none');
        document.getElementById(`nav-${id}`).classList.add('active');
        if (id === 'galeria') comprobarConexionYListar();
}

function triggerToast(msg, exito = true) {
        const toastEl = document.getElementById('live-toast');
        document.getElementById('toast-text').innerText = msg;
        toastEl.className = `toast align-items-center text-white bg-dark border-${exito ? 'success' : 'danger'}`;
        new bootstrap.Toast(toastEl).show();
}
