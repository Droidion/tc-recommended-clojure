![Clojure CI](https://github.com/Droidion/tc-recommended-clojure/workflows/Clojure%20CI/badge.svg)
![Github Issues](https://img.shields.io/github/issues/Droidion/tc-recommended-clojure)
![License](https://img.shields.io/github/license/Droidion/tc-recommended-clojure)

# Lists of classical music works

![Screenshot of the site](doc/screenshot.png)

As recommended by TalkClassical forum members.

An exercise in making traditional website using Clojure for everything, including
- HTML ([hiccup](https://github.com/weavejester/hiccup))
- CSS ([garden](https://github.com/noprompt/garden))
- SQL ([honeysql](https://github.com/seancorfield/honeysql))

SQLite is used for data storage.

See live: https://tclist.best

## How to run

### Docker

- `$ docker-compose up -d`
- Open `http://localhost:7888/`

### Native

- Have [leiningen](https://leiningen.org) and [Clojure](https://clojure.org) installed;
- `$ lein run`
- Open `http://localhost:7888/`

## Tests

`$ lein test`

## Deploy

Use it in Docker container. Simple `docker-compose.yml` is provide to run with docker compose.

So, in the basic case just have docker and docker-compose installed and run `$ docker-compose up -d`.

In more complex cases, build your own docker-compose files based on the provided one.