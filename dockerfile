FROM eclipse-temurin:17

WORKDIR /app

# Crear directorio para datos persistentes
RUN mkdir -p /app/data

# Copiar archivos Java y clases
COPY *.java /app/
COPY *.class /app/
COPY README.md /app/

RUN javac *.java

# Comando para ejecutar la aplicación
CMD ["java", "Main"]