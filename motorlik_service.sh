#!/bin/bash

# Rutas a los archivos JAR de los servicios y del API Gateway
APIGATEWAY_JAR="C:\A-DEVELOP\spring\motorlinkparent\infrastructure\apigateway\target\apigateway-0.0.1-SNAPSHOT.jar"
CLIENTE_JAR="C:\A-DEVELOP\spring\motorlinkparent\domain\cliente\target\cliente-0.0.1-SNAPSHOT.jar"
PRODUCTO_JAR="C:\A-DEVELOP\spring\motorlinkparent\domain\producto\target\producto-0.0.1-SNAPSHOT.jar"
USUARIO_JAR="C:\A-DEVELOP\spring\motorlinkparent\domain\usuario\target\usuario-0.0.1-SNAPSHOT.jar"
VENTA_JAR="C:\A-DEVELOP\spring\motorlinkparent\domain\venta\target\venta-0.0.1-SNAPSHOT.jar"

# Comando para ejecutar el API Gateway y los servicios
java -jar "$APIGATEWAY_JAR" &
java -jar "$CLIENTE_JAR" &
java -jar "$PRODUCTO_JAR" &
java -jar "$USUARIO_JAR" &
java -jar "$VENTA_JAR" &

# Mensaje de confirmación
echo "API Gateway y servicios iniciados correctamente."

#bash ./utils/motorlik_service.sh