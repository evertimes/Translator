На вход подается json вида:

```json
{
"text": "hello world",
"sourceLanguageCode": "en",
"targetLanguageCode": "ru"
}
```
Код языка указывается в формате [ISO_639-1](https://en.wikipedia.org/wiki/ISO_639-1)

На выход поступает json вида:
```json
{
"translatedString": "здравствуйте мир"
}
```
Конфигурация лежит в файле application.properties

translator.uri - ссылка на ресурс

translator.apiKey - api-key для доступа

