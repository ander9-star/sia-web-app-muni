# Usar la imagen personalizada creada
FROM jdk-21-per
# Exponer el puerto
EXPOSE 8080

# Establecer el directorio de trabajo en el contenedor
WORKDIR /root

# Verificar la version de java que se usa en el contenedor
RUN java -version

# Copiar el archivo jar del proyecto generado manualmente a la imagen docker
COPY ./target/sia-app-web-0.0.1-SNAPSHOT.jar /root/target/sia-app-web-0.0.1-SNAPSHOT.jar

# Ejecutar la aplicacion
ENTRYPOINT [ "java", "-jar", "/root/target/sia-app-web-0.0.1-SNAPSHOT.jar" ]

# Limpiar el cache de paquetes para reducir el size de la imagen
RUN rm -rf /var/cache/apk/*

# Mensaje de la construccion exitosa
RUN echo "La imagen de la aplicacion web SIA se ha generado correctamente"


