# Zadanie 1

## 1
- Kod logujący informacje:
```
src/main/java/pl/bartekbiegajlo/pfswcho/LogWriter.java
```
- Kod pobierający dane o IP i dacie:
```
src/main/java/pl/bartekbiegajlo/pfswcho/HomeController.java
```

## 2
Plik dockerfile z komentarzami znajduje się w głównym katalogu

## 3
- budowanie obrazu:
```
docker buildx build -t pfswcho-zadanie1:0.0.1 .
```
![image](https://github.com/BeekeepingRunner/chmura-lab-docker/assets/54111855/5a34fb67-0e65-47a9-b9b7-d4ac4abb4fc1)

- uruchomienie kontenera:
```
docker run --name pfswcho-bartek -d -p 8080:8080 pfswcho-zadanie1:0.0.1  
```
![image](https://github.com/BeekeepingRunner/chmura-lab-docker/assets/54111855/d8c74d29-8d02-4ec7-bd95-112899320461)

- uzyskanie output'u z serwera - w przeglądarce wchodzimy na localhost:8080
```
Niestety mój adres IP jest nieprawidłowy dla API geolokalizacyjnego
```
![image](https://github.com/BeekeepingRunner/chmura-lab-docker/assets/54111855/1efd5ea8-5f04-4810-b3d0-623d5cbeed69)

- sprawdzenie ile warstw posiada zbudowany obraz:
```
docker inspect pfswcho-zadanie1:0.0.1
```
![image](https://github.com/BeekeepingRunner/chmura-lab-docker/assets/54111855/bff90a2e-446c-457c-b5ec-3f18a2d5b049)


# Zadanie 2