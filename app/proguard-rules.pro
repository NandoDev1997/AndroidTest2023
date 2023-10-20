# Mantener todas las clases del paquete "com.example.miaplicacion" sin ofuscar
-keep class com.nandodev1997.androidtestfernandomorales.** { *; }

# Mantener las clases y métodos que se utilizan como puntos de entrada, por ejemplo, el punto de entrada de la aplicación
-keep class com.nandodev1997.androidtestfernandomorales.MainActivity { *; }
