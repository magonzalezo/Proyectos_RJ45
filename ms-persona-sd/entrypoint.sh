#!/bin/bash
echo "Levantando contenedor..."
echo $POSTGRES_URL
echo $POSTGRES_PORT
echo $POSTGRES_USER
echo $POSTGRES_PASSWORD
echo "Preparando servicio..."
java -jar ./target/personas-0.0.1-SNAPSHOT.jar
