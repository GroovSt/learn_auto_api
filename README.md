Ex1: Отправка формы авторизации
Давайте представим, что мы тестируем форму авторизации на любом веб-сайте. Пользователь должен заполнить email, пароль и отправить форму. Соответственно, в момент отправки сформируется и отправится HTTP-запрос. Какой тип запроса вы бы ожидали увидеть в этом случае: GET или POST? Почему?

Ответ: В данном примере актуален POST запрос, тк в теле запроса передаются email и пароль пользователя, так же, POST запрос является более безопасным. В GET запросе отсутсвует тело запроса.

Ex2: Структура HTTP запроса
На уроке мы рассказывали о структуре HTTP-запроса. Определите с помощью Chrome DevTools или любым другим способом:
1) в какой части запроса отправляются cookie от клиента к серверу.
2) в какой части ответа сервер указывает клиенту какие cookie нужно выставить.

Ответ: 
1) Cookie
2) Set-cookie
