# CoWorkingAPP
🚀 Coworking Management API
⚠️ ADVERTENCIA
Las credenciales en texto plano dentro de application.properties son solo para fines de prueba técnica. Esto NO es recomendable en un entorno de producción.

📌 Requisitos Previos
Para ejecutar este proyecto, asegúrate de tener instalado:

✅ Docker y Docker Compose

🛠 Instalación y Configuración
1️⃣ Clonar el Repositorio
bash
Copiar código
git clone <URL_DEL_REPOSITORIO>
cd CoworkingManagement
2️⃣ Construir y Ejecutar los Contenedores con Docker
bash
Copiar código
docker-compose up --build
Esto iniciará:

📌 La aplicación en http://localhost:8080
🛢 La base de datos MySQL en localhost:3307
🔥 Endpoints Principales
🏢 Gestión de Espacios (Spaces)
📌 Obtener todos los espacios
GET /spaces

📥 Response:
json
Copiar código
[
  {
    "id": 1,
    "name": "Sala de reuniones",
    "description": "Espacio con proyector y pizarra"
  }
]
📅 Gestión de Reservas (Reservations)
📌 Obtener todas las reservas
GET /reservations

📥 Response:
json
Copiar código
[
  {
    "id": 1,
    "user": { "id": 1, "name": "Juan", "email": "juan2@gmail.com" },
    "space": { "id": 1, "name": "Sala de reuniones", "description": "Espacio con proyector y pizarra" },
    "date": "2025-03-15",
    "startTime": "08:01:01"
  }
]
📌 Crear una reserva
POST /reservations

📤 Body:
json
Copiar código
{
  "spaceId": 1,
  "date": "2025-03-15",
  "startTime": "08:01:01"
}
🔐 Autenticación
📌 Registro de usuario
POST /register

📤 Body:
json
Copiar código
{
  "name": "Juan",
  "email": "juan2@gmail.com",
  "password": "claveSecreta"
}
📌 Inicio de sesión
POST /login

📤 Body:
json
Copiar código
{
  "email": "juan2@gmail.com",
  "password": "claveSecreta"
}
📥 Response: (Token JWT)
json
Copiar código
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
Para acceder a endpoints protegidos, debes incluir el token en la cabecera Authorization:

makefile
Copiar código
Authorization: Bearer <TOKEN_AQUÍ>
🧪 Pruebas y Documentación
📌 📜 Swagger UI
Puedes probar la API en la interfaz gráfica de Swagger en:
🔗 http://localhost:8080/swagger-ui/index.html#/

📌 🛠 Postman
También puedes probar los endpoints usando Postman con la información de los endpoints y tokens que se detallan en este README.

🚀 Notas Finales
Esta API es solo un prototipo para fines de prueba técnica.
No usar las credenciales en entornos de producción.
Para mejoras o colaboración, ¡haz un pull request! 😊
