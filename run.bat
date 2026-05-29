@echo off
title Pharmacy Management CLI
cls

rem Check if app.jar exists
if exist target/pharmacy-app-1.0.jar (
    java --enable-native-access=ALL-UNNAMED -jar target/pharmacy-app-1.0.jar
) else (
    echo [INFO] target/pharmacy-app-1.0.jar not found. Please run mvn package first.
)