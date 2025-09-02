# File Content Filter Utility

**File Content Filter Utility** — тестовое задание для _Курса Шифт_. Утилита командной строки для фильтрации данных из файлов на целые числа, вещественные числа и строки, записывая их в отдельные файлы. Утилита поддерживает настройку путей, префиксов для имен файлов, режим добавления данных в существующие файлы, а также сбор и вывод статистики по каждому типу данных.

---

## Возможности

- Фильтрация данных из входных файлов на целые числа, вещественные числа и строки
- Запись отфильтрованных данных в отдельные файлы
- Настройка пути для выходных файлов
- Указание префикса для имен выходных файлов
- Режим добавления данных в существующие файлы
- Сбор и вывод краткой и полной статистики по каждому типу данных

---

## Требования

- `Java 21` или выше
- `Gradle 8.5` для сборки проекта

---

## Используемые библиотеки

- [JUnit Jupiter API 5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0)
- [Apache Commons CLI 1.9.0](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0)

---

## Установка и запуск

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/MaksFlyOk/FileContentFilterUtility.git
   ```
2. Перейдите в директорию проекта:

   ```bash
   cd FileContentFilterUtility
   ```
   
3. Соберите проект с помощью Gradle:
   ```bash
   gradlew build
   ```

4. Запустите утилиту (вы увидите справку по использованию утилиты):
   ```bash
   java  -jar build/libs/Shift-FileContentFilterUtility-1.0.0.jar --help
   ```

> В директории _./src/test/resources_ вы найдете тестовые файлы для проверки утилиты. Так же _./src/test/resources_ можете использовать как тестовый путь для сохранения результата работы утилиты.

## Опции

| Опция                  | Описание                                                          |
|------------------------|-------------------------------------------------------------------|
| `-o`, `--output <arg>` | Путь к директории для сохранения выходных файлов                  |
| `-p`, `--prefix <arg>` | Префикс для имен выходных файлов                                  |
| `-a`, `--append`       | Режим добавления данных в существующие файлы                      |
| `-s`, `--short`        | Вывод краткой статистики                                          |
| `-f`, `--full`         | Вывод полной статистики                                           |
| `-h`, `--help`         | Вывод справки по использованию утилиты                            |
 
### Примеры использования

   ```bash
   java -jar build/libs/Shift-FileContentFilterUtility-1.0.0.jar -f -o ./src/test/resources/ .\src\test\resources\file3.txt
   ```

   ```bash
   java -jar build/libs/Shift-FileContentFilterUtility-1.0.0.jar -s -a -o ./src/test/resources/ .\src\test\resources\file1.txt .\src\test\resources\file2.txt
   ```
> В справке указаны все опции, а также примеры использования.

---

## Запуск тестов

Для запуска тестов используйте команду:
```bash
gradlew test
```

> После завершения всех тестов можно открыть файл _./build/reports/tests/test/index.html_ в котором будет показана подробная статистика по тестам.

---

## Структура проекта
```
   FileContentFilterUtility/
   ├── src/
   │   ├── main/
   │   │   ├── java/
   │   │   │   └── ru/
   │   │   │       └── shift/
   │   │   │           └── utility/
   │   │   │               ├── cli/                          # Директория с классами для обработки аргументов командной строки
   │   │   │               │   ├── ArgumentParser.java       # Класс для разбора аргументов командной строки
   │   │   │               │   └── Params.java               # Класс для хранения параметров командной строки
   │   │   │               ├── core/                         # Директория с основными классами для обработки файлов и данных
   │   │   │               │   ├── FilesProcessor.java       # Класс для обработки входных файлов
   │   │   │               │   ├── FilesWriter.java          # Класс для записи данных в выходные файлы
   │   │   │               │   ├── LineClassifier.java       # Класс для классификации строк на типы данных
   │   │   │               │   └── LineType.java             # Перечисление типов данных (INTEGER, FLOAT, STRING)
   │   │   │               ├── statistic/                    # Директория с классами для сбора и вывода статистики
   │   │   │               │   ├── FullStatistics.java       # Класс для сбора полной статистики
   │   │   │               │   ├── Statistics.java           # Базовый класс для сбора статистики
   │   │   │               │   └── StatisticsFormatter.java  # Класс для форматирования статистики
   │   │   │               └── FileContentFilterUtility.java # Главный класс приложения с методом main
   │   │   └── resources/
   │   └── test/
   │       ├── java/
   │       │   └── ru/
   │       │       └── shift/
   │       │           └── utility/
   │       │               ├── cli/                              # Директория с тестами для классов обработки аргументов командной строки
   │       │               │   └── ArgumentParserTest.java
   │       │               ├── core/                             # Директория с тестами для основных классов обработки файлов и данных
   │       │               │   ├── FilesProcessorTest.java
   │       │               │   ├── FilesWriterTest.java
   │       │               │   └── LineClassifierTest.java
   │       │               ├── statistic/                        # Директория с тестами для классов сбора статистики
   │       │               │   ├── FullStatisticsTest.java
   │       │               │   └── StatisticsTest.java
   │       │               └── FileContentFilterUtilityTest.java # Интеграционные тесты для главного класса приложения
   │       └── resources/ # Директория с ресурсами для тестов
   ├── .gitignore
   ├── README.md 
   ├── build.gradle    # Файл конфигурации Gradle для сборки проекта
   ├── gradlew         # Unix скрипт для запуска Gradle Wrapper
   ├── gradlew.bat     # Windows скрипт для запуска Gradle Wrapper
   └── settings.gradle # Файл конфигурации настроек Gradle
```