🚀 Coworking Management API

⚠️ ADVERTENCIA
Las credenciales en texto plano dentro de application.properties son solo para fines de prueba técnica. Esto NO es recomendable en un entorno de producción.

📌 Requisitos Previos
Para ejecutar este proyecto, asegúrate de tener instalado:

✅ Docker y Docker Compose

🛠 Instalación y Configuración


1️⃣ Clonar el Repositorio
git clone https://github.com/DrakoBito/CoWorkingAPP.git  
cd CoworkingManagement  


2️⃣ Construir y Ejecutar los Contenedores con Docker
docker-compose up --build  
Esto iniciará:
📌 La aplicación en http://localhost:8080
🛢 La base de datos MySQL en localhost:3307

📌 Configuración de la Base de Datos
✅ Se insertan automáticamente 3 espacios en la base de datos al iniciar la app.
✅ La base de datos se crea al iniciar y se borra al detener la aplicación (create-drop).

🔥 Endpoints Principales

🏢 Gestión de Espacios (Spaces)

📌 Obtener todos los espacios

GET /spaces
📥 Response:

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


{
  "spaceId": 1,
  "date": "2025-03-15",
  "startTime": "08:01:01"
}
🔐 Autenticación

📌 Registro de usuario


POST /register
📤 Body:


{
  "name": "Juan",
  "email": "juan2@gmail.com",
  "password": "claveSecreta"
}
📌 Inicio de sesión

POST /login
📤 Body:


{
  "email": "juan2@gmail.com",
  "password": "claveSecreta"
}
📥 Response (Token JWT):


{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
✅ Para acceder a endpoints protegidos, debes incluir el token en la cabecera Authorization:


Authorization: "AQUI EL TOKEN"
🧪 Pruebas y Documentación

📌 📜 Swagger UI
Puedes probar la API en la interfaz gráfica de Swagger en:
🔗 http://localhost:8080/swagger-ui/index.html#/

📌 🛠 Postman
También puedes probar los endpoints usando Postman con la información de los endpoints y tokens que se detallan en este README.

🚀 Notas Finales
✅ Esta API es solo un prototipo para fines de prueba técnica.
❌ No usar las credenciales en entornos de producción.
✅ Para mejoras o colaboración, haz un pull request. 😊
