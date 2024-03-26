# MICROSERICE SPRING BOOT
 
## Estructura del microservicio 

![MICROSERICE](https://raw.githubusercontent.com/jhamisruiz/motorlinkparent/main/assets/image/app.PNG)


## Driagrama de base de datos con Mysql



![Diagrama DB](https://raw.githubusercontent.com/jhamisruiz/motorlinkparent/main/assets/image/ddb.png)

# Iniciar microservicio

## 1. Ejecutar el Build de cada microservicio y apigateway
### Esto creara una imagen en docker de cada servicio

## 2. Ejecutar docker-compose para iniciar los contenedores de los servicios
- Run `docker compese up `

# Nota

- El Apigateway esta comfigurado para usar el puerto 8090 `http://localhost:8090 ` donde servira todos los microservicios
- Configurar el nombre de base de datos, usaurio y password de Mysql