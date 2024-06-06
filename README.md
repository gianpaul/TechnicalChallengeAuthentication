# TechnicalChallengeAuthentication

Este proyecto es parte de un desafío técnico para una big tech, que demuestra una implementación de autenticación utilizando Android. La aplicación está desarrollada en Kotlin y sigue principios de arquitectura limpia (Clean Architecture) y patrones de diseño recomendados.

[Ver video de demostración](https://www.youtube.com/watch?v=tu-video-id](https://youtube.com/shorts/uk3wAAR-klQ)
## Características

- Autenticación de usuarios mediante correo electrónico y contraseña.
- Recuperación de contraseña.
- Validación de formularios.
- Manejo de estados de carga y error.

## Requisitos

- Android Studio 4.2+
- Gradle 7.0+
- SDK de Android 21+

## Tecnologías y Bibliotecas Utilizadas

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://coil-kt.github.io/coil/)
- [Firebase Authentication](https://firebase.google.com/docs/auth)

## Arquitectura

Este proyecto sigue la arquitectura limpia (Clean Architecture) con las siguientes capas:

- **Capa de Datos**: Gestiona las fuentes de datos (remotas y locales).
- **Capa de Dominio**: Contiene la lógica de negocio.
- **Capa de Presentación**: Maneja la interfaz de usuario y las interacciones.

## Estructura del Proyecto

TechnicalChallengeAuthentication/
pp/ # Módulo principal de la aplicación
src/
main/
java/com/example/technicalchallengeauthentication/
data/ # Capa de datos
domain/ # Capa de dominio
ui/ # Capa de presentación
res/
build.gradle.kts
gradle/
gradle.properties
gradlew
gradlew.bat
settings.gradle.kts


### Descripción del Código

- **data**: Contiene los repositorios y fuentes de datos. Aquí se encuentra la implementación de Retrofit para las llamadas a la API y el uso de Firebase Authentication.
- **domain**: Contiene los casos de uso (use cases) que representan la lógica de negocio de la aplicación.
- **ui**: Contiene las pantallas y componentes de la interfaz de usuario utilizando Jetpack Compose. También incluye los ViewModels que siguen el patrón MVVM.
- **di**: Configuración de Hilt para la inyección de dependencias.

## Instalación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/gianpaul/TechnicalChallengeAuthentication.git
2. Abre el proyecto en Android Studio.
3. Sincroniza el proyecto con Gradle.
4. Configura Firebase Authentication en tu proyecto de Firebase y descarga el archivo google-services.json en el directorio app.

Uso
1. Ejecuta la aplicación en un dispositivo o emulador.
2. Regístrate con un nuevo usuario o inicia sesión con uno existente.
3. Explora las funcionalidades de autenticación y recuperación de contraseña.

Contribuciones
Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

Haz un fork del proyecto.
1. Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
2. Realiza tus cambios y haz commits (git commit -m 'Agrega nueva funcionalidad').
3. Sube tus cambios a tu fork (git push origin feature/nueva-funcionalidad).
4. Abre un Pull Request.

Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Contacto
Para cualquier consulta o sugerencia, por favor contacta a gianprp@hotmail.com.
