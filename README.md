# MeLi Test

Esto es un ejemplo de una aplicación en Android desarrollada con clean architecture usando la API de mercadolibre.

## - Funcionalidades
* Busqueda de productos
* Guardado busquedas recientes
* Busquedas basadas en la ubicacion del celular

## - Tecnologias utilizadas
* Kotlin 100%
* Clean architecture
* MVVM pattern
* Dagger Hilt
* Material Design
* Courutines
* Live Data
* Flow
* Navigation graph
* Room
* Retrofit
* DataStore
* Unit tests
* UI automation tests
* Git actions

## - Clean architecture

El proyecto está estructurado en 3 módulos, cada módulo representa una capa de clean architecture

* Presentation (App module, view models, adapters, fragments, activities...)
* Domain (use cases, repositorios, entities...)
* Data (Room, retrofit, data store...)

 <img src="assets/cleanArchitecture.png" alt="clean architecture image"/>


## - Testing:

* Cada capa contiene sus propias pruebas unitarias
* Pruebas automatizadas para los flujos básicos del app

## - Code Quality Checks
Este proyecto usa detekt para ayudar a mantener la calidad del código.

  #### Run detekt locally
  Use `./gradlew detekt`

# Screenshots
| Home | Search |  Home loaded |  Details |
|:-:|:-:|:-:|:-:|
| ![1](assets/screenshots/light_1.jpeg?raw=true) | ![2](assets/screenshots/light_2.jpeg?raw=true) | ![3](assets/screenshots/light_3.jpeg?raw=true) | ![4](assets/screenshots/light_4.jpeg?raw=true) |
| Home Dark | Search Dark|  Home Loaded Dark | Details Dark |
| ![1](assets/screenshots/night_1.jpeg?raw=true) | ![2](assets/screenshots/night_2.jpeg?raw=true) | ![3](assets/screenshots/night_3.jpeg?raw=true) | ![4](assets/screenshots/night_4.jpeg?raw=true) |


### Desarrollado por

Gabriel Fernando Neira [linkedIn](https://www.linkedin.com/in/gabriel-fernando-neira-bermudez-419b2265)

### License

GNU GENERAL PUBLIC LICENSE
Version 3, 29 June 2007

Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
Everyone is permitted to copy and distribute verbatim copies
of this license document, but changing it is not allowed.

-------
